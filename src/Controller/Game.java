package Controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Board.*;
import Piece.*;
import AI.*;

public class Game { 
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Logger log = Logger.getLogger("Chessboard");
		
		//inicializa o logger
		FileHandler fh;
		
		try
		{
			fh = new FileHandler("Chessboard.log");
			log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        log.setUseParentHandlers(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Chessboard cb = new Chessboard();
		cb.setupBoard();
		
		int player = 1;
		int cplayer = 1;
		if (args.length > 0 && args[0].compareTo("2") == 0)
		{
			log.info("player 2 chosen");
			player = -1;
		}
		
		MiniMax mm = new MiniMax(4);
		
		try
		{
			Scanner sc = new Scanner(System.in);
			String response = sc.nextLine(); //lê xboard
			response = sc.nextLine();
			if (response.compareTo("protover 2") == 0)
			{
				System.out.println("accepted");
				log.info("protover 2: accepted");
				System.out.println("feature usermove=1 time=0");
				System.out.println("feature done=1");
			}
			if (player == cplayer)
			{
				Chessboard newMove = mm.bestMove(cb, player);
				String move = cb.moveString(newMove, player);
				cb.printBoard();
				newMove.printBoard();
				cb = newMove;
				System.out.println("move " + move);
				log.info("move " + move);
				cplayer*=-1;
				response = sc.nextLine();
			}
			
			boolean dumping = true;
			boolean playing = false;
			
			while(dumping)
			{
				response = br.readLine();
				
				//le a entrada
				//detecta movimento
				if (response.compareTo("random") == 0)
				{
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("post") == 0)
				{
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("hard") == 0)
				{
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("easy") == 0)
				{
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("computer") == 0)
				{
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("new") == 0)
				{
					cb = new Chessboard();
					cb.setupBoard();
					log.info("'new' command detected, setting up a new board");
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.compareTo("go") == 0 )
				{
					if (playing == false)
					{
						player = cplayer;
						//response = br.readLine();//sc.nextLine();
						log.info("Accepted go");
						Chessboard newMove = mm.bestMove(cb, player);
						log.info("Printing board");
						cb.printBoard();
						newMove.printBoard();
						String move = newMove.getLastMove();
						cb = newMove;
						System.out.println("move " + move);
						log.info("move " + move);
						cplayer*=-1;
						playing = true;
						//response = br.readLine();//sc.nextLine();
					}
				}
				else if (response.compareTo("force") == 0)
				{
					log.info("Accepted force");
					//response = br.readLine();//sc.nextLine();
				}
				else if (response.length() > 8 && response.substring(0, 8).compareTo("usermove")==0)
				{
					if (cplayer != player)
					{
						log.info("received usermove: " + response);
						try
						{
							response = response.substring(9);
							char s1, s2;
							s1 = response.charAt(0);
							s2 = response.charAt(2);
							s1 = (char) (s1 - 'a' + 1);
							s2 = (char) (s2 - 'a' + 1);
							int n1, n2;
							n1 = Integer.parseInt(response.charAt(1) + "");
							n2 = Integer.parseInt(response.charAt(3) + "");
							
							if (s1 > 0 && s2 > 0 && s1 < 9 && s2 < 9
									&& n1 > 0 && n1 < 9 && n2 > 0 && n2 < 9 
									&& (response.length() == 4 || response.length() == 5))
							{
								log.info("Move Detected");
								cb.makeMove(response,cplayer);
								log.info("Opponent Move Maked: " + response);
								cplayer *= -1;
								if(cplayer == player)
								{
									Chessboard newMove = mm.bestMove(cb, player);
									log.info("Printing board");
									newMove.printBoard();
									String move = newMove.getLastMove();
									cb = newMove;
									System.out.println("move " + move);
									log.info("move " + move);
									cplayer*=-1;
									playing = true;
									//response = br.readLine();//sc.nextLine();
								}
							}
						}
						catch(Exception E)
						{
							log.info("Exception: probably not a move command \n Exception" + E.getMessage() + "\n Exception: response = " + response);
							response = br.readLine();//sc.nextLine();
						}
					}
				}
				else
				{
					log.info("XBoard unkown command: " + response);
					response = br.readLine();//sc.nextLine();
				}
			}
		}
		catch(Exception E)
		{
			
		}
		
		//MachineLearning ml = new MachineLearning();
		//ml.train();
		
		
		/*
		player = -1;
		int playerc = 1;
		while(cb.hasKing(1) && cb.hasKing(-1))
		{
			//System.out.println("Player " + ((playerc%2)+1) + " move");
			Chessboard newMove = mm.bestMove(cb,player);
			//System.out.println();
			cb.moveString(newMove,player);
			cb = newMove;
			player = player*(-1);
			playerc++;
		}*/
		
	}
}
