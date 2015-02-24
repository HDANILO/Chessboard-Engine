package AI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Board.Chessboard;
import weka.classifiers.evaluation.*;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;

public class MachineLearning {
	
	public void train()
	{
		Attribute[][] attr = new Attribute[8][8];
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				attr[i][j] = new Attribute("sq" + i + "" + j);
			}
		}
		Attribute AttRegression = new Attribute("HeuristicValue");
		
		FastVector fvWekaAttributes = new FastVector(65);
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
				fvWekaAttributes.addElement(attr[i][j]);
		}
		
		fvWekaAttributes.addElement(AttRegression);
		
		Instances isTrainingSet = new Instances("Heuristics", fvWekaAttributes, 35000);
		isTrainingSet.setClassIndex(64);
		int totalRegister = 0;
		
		/*carrega as instancias de treinamento*/
		
		String File = MachineLearning.class.getResource("/RESOURCE/kasparov.dat").getPath().replace("%20", " ");
		BufferedReader br = null;
		String SplitBy = " ";
		
		try {
			 
			 br = new BufferedReader(new FileReader(File));
			 StringBuffer fileb = new StringBuffer();
			 while (br.ready()) {
				 fileb.append(br.readLine() + "\n");
			 }
			 String file = fileb.toString();
			        // use comma as separator
			 String[] line = file.split("\n");
			 ArrayList<String[]> lineItems = new ArrayList<String[]>();
			 Chessboard chess[] = new Chessboard[1877];
			 
			 boolean done[] = new boolean[1877];
			 for(int i = 0; i < line.length; i++)
			 {
				 lineItems.add(i,line[i].split(SplitBy));
				 chess[i] = new Chessboard();
				 chess[i].setupBoard();
				 done[i] = false;
			 }
			 boolean end = false;
			 int playcount = 0;
			 while(!end)
			 {
				 int predictCount[] = new int[1877];
				 int i = 0;
				 while(i < lineItems.size())
				 {	 
					 if (lineItems.get(i).length > playcount)
					 {
						 //int prediction = Integer.parseInt(item[item.length-1]);
						 
						 String[] item = lineItems.get(i);
						 //System.out.println("playcount = " + playcount + "; length = " + lineItems.get(i).length);
						 String play = item[playcount];
						 int ctotal = 0;
						 int fromX = play.charAt(0) - 97;
						 int fromY = play.charAt(1);
						 int toX = play.charAt(2) - 97;
						 int toY = play.charAt(3);
						 chess[i].movePiece(fromX, fromY, toX, toY);
						 int j;
						 for(j = i+1; j < lineItems.size(); j++)
						 {
							 
							 String[] item2 = lineItems.get(j);
							 if (item2.length > playcount)
							 {
								 String play2 = item2[playcount];
								 if (play.compareTo(play2) == 0)
								 {
									 chess[j].movePiece(fromX, fromY, toX, toY);
									 predictCount[i]++;
								 }
								 else
									 break;
							 }
							 else
								 done[j] = true;
						 }
						 ctotal = j;
						 for( j = i+1; j < ctotal; j++)
							 predictCount[j] = predictCount[i];
						 
					 	 Instance data = new Instance(65);
						 int c = 0;
						 for(int x = 0; x < 8; x++)
						 {
							 for(int y = 0; y < 8; y++)
							 {
								 float value = 0;
								 if (chess[i].getChessboard()[x][y].hasPiece)
									 value = chess[i].getChessboard()[x][y].piece.Representation();
								 /*
								 if (value > 0)
									 value = Math.min(10, value);
								 else
									 value = Math.max(-10, value);
								 */
								 data.setValue((Attribute)fvWekaAttributes.elementAt(c), value);
								 c++;
							 }
						 }
						 data.setValue((Attribute)fvWekaAttributes.elementAt(64), predictCount[i]);
						 isTrainingSet.add(data);
						 totalRegister++;
						 //System.out.println("Added new data; i = " + i);
						 i = ctotal-1;
					 }
					 else
						 done[i] = true;
					 i++;
				 }
				 playcount++;
				 boolean doneall = true;
				 int d = 0;
				 for(int k = 0; k < lineItems.size(); k++)
				 {
					 if (done[k] == false)
					 {
						 d++;
						 doneall = false;
						 break;
					 }
				 }
				 end = doneall;
				 System.out.println("Done already " + d + " plays; playcount = " + playcount);
			 }
	 
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			e.printStackTrace();
		 } finally {
			 if (br != null) {
				 try {
					 br.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		
		/*treina*/
		System.out.println("Começou o treinamento; Número de registros a treinar: " + totalRegister);
		//SMOreg model = new SMOreg();
		MultilayerPerceptron model = new MultilayerPerceptron();
		model.setValidationSetSize(20);
		model.setAutoBuild(true);
		model.setNormalizeAttributes(true);
		model.setNormalizeNumericClass(true);
		model.setTrainingTime(1000);
		model.setDecay(true);
		model.setGUI(true);
		try {
			model.buildClassifier(isTrainingSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		try {
			weka.classifiers.Evaluation eTest = new weka.classifiers.Evaluation(isTrainingSet);
			eTest.evaluateModel(model, isTrainingSet);
			
			String strSummary = eTest.toSummaryString();
			System.out.println(strSummary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = MachineLearning.class.getResource("/RESOURCE/mlp_chess.model").getPath().replace("%20", " ");
		
		try {
			weka.core.SerializationHelper.write(path, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
