package AI;

import java.io.InputStream;
import java.util.logging.Logger;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMOreg;
import weka.core.Attribute;
import weka.core.Instance;
import Board.*;
import Piece.*;

public class Evaluation {
	
	static Logger log = Logger.getLogger("Chessboard");
	
	//static SMOreg Model;
	static MultilayerPerceptron Model;
	
	public static long evaluate(Chessboard chess, int player)
	{
		
		if (Model == null)
		{	
			try {
				InputStream resource = Evaluation.class.getResourceAsStream("/RESOURCE/mlp_chess.model");
				Model = (MultilayerPerceptron)weka.core.SerializationHelper.read(resource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Instance chessdata = new Instance(64);
		int c = 0;
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				int value = 0;
				if (chess.getChessboard()[x][y].hasPiece)
					value = chess.getChessboard()[x][y].piece.Representation();
				
				if (value > 0)
					 value = Math.min(10, value);
				 else
					 value = Math.max(-10, value);
				
				chessdata.setValue(c,value);
				c++;
			}	
		}
		double evaluation = 0;
		
		try {
			evaluation = Model.classifyInstance(chessdata)/(3*player);
			log.info("Regression Evaluated: " + evaluation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		evaluation = Math.max(Math.min(3, evaluation),-3);
		for(Square[] sqArr: chess.getChessboard())
		{
			for(Square sq: sqArr)
			{
				if (sq.hasPiece)
				{
					if (sq.piece.getPlayer() == player)
					{
						evaluation += sq.piece.Symbol();
					}
					else
					{
						evaluation -= sq.piece.Symbol();
					}
				}
			}
		}
		//log.info("Evaluation total: " + Math.round(evaluation));
		return Math.min(Math.max(Math.round(evaluation),Integer.MIN_VALUE),Integer.MAX_VALUE);
	}
	
	
}
