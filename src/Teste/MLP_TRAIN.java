package Teste;

import static org.junit.Assert.*;

import org.junit.Test;

import AI.MachineLearning;
import Board.Chessboard;

public class MLP_TRAIN {

	@Test
	public void test() {
		MachineLearning ml = new MachineLearning();
		ml.train();
		
		Chessboard cb = new Chessboard();
		cb.setupBoard();
		cb.movePiece(1, 1, 1, 2);
		cb.printBoard();
		System.out.println(AI.Evaluation.evaluate(cb, -1));
	}

}
