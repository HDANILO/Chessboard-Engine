package Teste;

import static org.junit.Assert.*;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.Test;

import Board.Chessboard;
import Piece.King;
import Piece.Pawn;

public class enPassant {

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
		Pawn enPassant = new Pawn(5,6,-1);
		cb.getChessboard()[5][6].putPiece(enPassant);
		Pawn opEnPassant= new Pawn(4,4,1);
		cb.getChessboard()[4][4].putPiece(opEnPassant);
		cb.printBoard();
		cb.makeMove("f7f5", 1);
		System.out.println(cb.hasEnPassant());
		for(Chessboard move : opEnPassant.possibleMoves(cb))
		{
			move.printBoard();
			System.out.println("Evaluation: " + AI.Evaluation.evaluate(move, 1));
		}
	}

}
