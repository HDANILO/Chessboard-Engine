package Piece;

import java.util.ArrayList;

import Board.Chessboard;

public class Rook extends Piece {

	
	public Rook(int x, int y, int player) {
		super(x, y, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Symbol() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public ArrayList<Chessboard> possibleMoves(Chessboard X) {
		ArrayList<Chessboard> moves = new ArrayList<Chessboard>();
		
		for(int pos = 1; X.checkLimits(posx+pos,posy); pos++)
		{
			if (X.hasPiece(posx+pos, posy) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+pos, posy);
				moves.add(aux);
			}
			else if (X.getPiece(posx+pos, posy).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+pos, posy);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int pos = 1; X.checkLimits(posx-pos,posy); pos++)
		{
			if (X.hasPiece(posx-pos, posy) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-pos, posy);
				moves.add(aux);
			}
			else if (X.getPiece(posx-pos, posy).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-pos, posy);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int pos = 1; X.checkLimits(posx,posy+pos); pos++)
		{
			if (X.hasPiece(posx, posy+pos) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy+pos);
				moves.add(aux);
			}
			else if (X.getPiece(posx, posy+pos).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy+pos);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		for(int pos = 1; X.checkLimits(posx,posy-pos); pos++)
		{
			if (X.hasPiece(posx, posy-pos) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-pos);
				moves.add(aux);
			}
			else if (X.getPiece(posx, posy-pos).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy+pos);
				moves.add(aux);
				break;
			}
			else
				break;
		}
		
		
		return moves;
	}

}
