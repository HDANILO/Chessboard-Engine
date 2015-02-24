package Piece;

import java.util.ArrayList;
import Board.Chessboard;

public class Pawn extends Piece {
	
	public Pawn(int x, int y, int player) {
		super(x, y, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Symbol() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ArrayList<Chessboard> possibleMoves(Chessboard X) {
		ArrayList<Chessboard> moves = new ArrayList<Chessboard>();

		//double pass
		if (player > 0)
		{
			if (posy == 1 && !X.hasPiece(posx, posy+2) && !X.hasPiece(posx, posy+1))
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy+2);
				moves.add(aux);
			}
		}
		else
		{
			if(posy == 6 && !X.hasPiece(posx, posy-2) && !X.hasPiece(posx, posy-1))
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-2);
				moves.add(aux);
			}
		}
		
		//normal move
		if (player > 0)
		{
			//check queening
			if (posy+1 == 7 && !X.hasPiece(posx, posy+1))
			{
				Chessboard aux = new Chessboard(X);
				aux.getChessboard()[posx][posy].removePiece();
				aux.getChessboard()[posx][posy+1].putPiece(new Queen(posx,posy+1,player));
				moves.add(aux);
				aux = new Chessboard(X);
				aux.getChessboard()[posx][posy].removePiece();
				aux.getChessboard()[posx][posy+1].putPiece(new Knight(posx,posy+1,player));
				moves.add(aux);
				aux = new Chessboard(X);
				aux.getChessboard()[posx][posy].removePiece();
				aux.getChessboard()[posx][posy+1].putPiece(new Rook(posx,posy+1,player));
				moves.add(aux);
				aux = new Chessboard(X);
				aux.getChessboard()[posx][posy].removePiece();
				aux.getChessboard()[posx][posy+1].putPiece(new Bishop(posx,posy+1,player));
				moves.add(aux);
			}//else normal move
			else if (!X.hasPiece(posx, posy+1))
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy+1);
				moves.add(aux);
			}
		}
		else
		{
			//check queening
			if (posy-1 == 0 && !X.hasPiece(posx, posy-1))
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-1,'q');
				moves.add(aux);
				aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-1,'n');
				moves.add(aux);
				aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-1,'r');
				moves.add(aux);
				aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-1,'b');
				moves.add(aux);
			}//else normal move
			else if (!X.hasPiece(posx, posy-1))
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx, posy-1);
				moves.add(aux);
			}
		}
		
		if (player > 0)
		{
			if (X.hasPiece(posx+1, posy+1) && X.getPiece(posx+1, posy+1).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+1, posy+1);
				moves.add(aux);
			}
		
			if (X.hasPiece(posx-1, posy+1) && X.getPiece(posx-1, posy+1).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-1, posy+1);
				moves.add(aux);
			}
		}
		else
		{
			if (X.hasPiece(posx+1, posy-1) && X.getPiece(posx+1, posy-1).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx+1, posy-1);
				moves.add(aux);
			}
		
			if (X.hasPiece(posx-1, posy-1) && X.getPiece(posx-1, posy-1).player != player)
			{
				Chessboard aux = new Chessboard(X);
				aux.movePiece(posx, posy, posx-1, posy-1);
				moves.add(aux);
			}
		}
		
		return moves;
	}

}
