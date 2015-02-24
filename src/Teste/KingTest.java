package Teste;

import static org.junit.Assert.*;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.Test;

import Board.Chessboard;
import Piece.King;
import Piece.Pawn;

public class KingTest {

	@Test
	public void test() {
		Logger log = Logger.getLogger("Chessboard");
		
		//inicializa o logger
		FileHandler fh;
		
		try
		{
			fh = new FileHandler("Chessboard.log");
			log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        //log.setUseParentHandlers(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Chessboard cb = new Chessboard();
		King k = new King(3,3,1);
		cb.getChessboard()[3][3].putPiece(k);
		cb.getChessboard()[3][4].putPiece(new Pawn(3,4,-1));
		cb.getChessboard()[0][0].putPiece(new King(0,0,-1));
		
		for(Chessboard move : k.possibleMoves(cb))
		{
			move.printBoard();
			System.out.println("Evaluation: " + AI.Evaluation.evaluate(move, 1));
		}
		
	}

}
