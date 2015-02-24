package Piece;

import java.util.ArrayList;

import Board.Chessboard;

public class Bishop extends Piece {

	public Bishop(int x, int y, int player) {
		super(x, y, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Symbol() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public ArrayList<Chessboard> possibleMoves(Chessboard X) {
		ArrayList<Chessboard> moves = new ArrayList<Chessboard>();
		
		for(int diagonal = 1; X.checkLimits(posx+diagonal, posy+diagonal); diagonal++)
		{
			if (X.hasPiece(posx+diagonal, posy+diagonal) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+diagonal, posy+diagonal);
				moves.add(aux);
			}
			else if (X.getPiece(posx+diagonal, posy+diagonal).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+diagonal, posy+diagonal);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int diagonal = 1; X.checkLimits(posx+diagonal, posy-diagonal); diagonal++)
		{
			if (X.hasPiece(posx+diagonal, posy-diagonal) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+diagonal, posy-diagonal);
				moves.add(aux);
			}
			else if (X.getPiece(posx+diagonal, posy-diagonal).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+diagonal, posy-diagonal);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int diagonal = 1; X.checkLimits(posx-diagonal, posy+diagonal); diagonal++)
		{
			if (X.hasPiece(posx-diagonal, posy+diagonal) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-diagonal, posy+diagonal);
				moves.add(aux);
			}
			else if (X.getPiece(posx-diagonal, posy+diagonal).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-diagonal, posy+diagonal);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int diagonal = 1; X.checkLimits(posx-diagonal, posy-diagonal); diagonal++)
		{
			if (X.hasPiece(posx-diagonal, posy-diagonal) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-diagonal, posy-diagonal);
				moves.add(aux);
			}
			else if (X.getPiece(posx-diagonal, posy-diagonal).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-diagonal, posy-diagonal);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		return moves;
	}

}
