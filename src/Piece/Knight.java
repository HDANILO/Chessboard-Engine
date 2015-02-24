package Piece;

import java.util.ArrayList;

import Board.Chessboard;

public class Knight extends Piece {

public Knight(int x, int y, int player) {
		super(x, y, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Symbol() {
		return 5;
	}

	@Override
	public ArrayList<Chessboard> possibleMoves(Chessboard X) {
		ArrayList<Chessboard> moves = new ArrayList<>();
		
		int relocX = 1;
		int relocY = 2;
				
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = -1;
		relocY = 2;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = 1;
		relocY = -2;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = -1;
		relocY = -2;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = 2;
		relocY = 1;
				
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = -2;
		relocY = 1;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = 2;
		relocY = -1;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		relocX = -2;
		relocY = -1;
		
		if(X.checkLimits(posx+relocX, posy+relocY))
		{
			if (X.hasPiece(posx+relocX, posy+relocY) == false)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
			else if (X.getPiece(posx+relocX, posy+relocY).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+relocX, posy+relocY);
				moves.add(aux);
			}
		}
		
		return moves;
	}

}
