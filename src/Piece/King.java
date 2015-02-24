package Piece;

import java.util.ArrayList;

import Board.Chessboard;

public class King extends Piece {

	boolean moved = false;
	
	public void setMoved()
	{
		moved = true;
	}
	
	public King(int x, int y, int player) {
		super(x, y, player);
		this.moved = moved;
	}

	@Override
	public int Symbol() {
		// TODO Auto-generated method stub
		//return Integer.MAX_VALUE;
		return 25;
	}

	@Override
	public ArrayList<Chessboard> possibleMoves(Chessboard X) {
		ArrayList<Chessboard> moves = new ArrayList<Chessboard>();
		
		for(int x = -1; x < 2; x++)
		{
			for(int y = -1; y < 2; y++)
			{
				if (X.checkLimits(posx+x, posy+y))
				{
					if (X.hasPiece(posx+x, posy+y) == false)
					{
						Chessboard aux = new Chessboard(X);
						aux.movePiece(posx,posy,posx+x,posy+y);
						moves.add(aux);
					}
					else if (X.getPiece(posx+x, posy+y).player != player)
					{
						Chessboard aux = new Chessboard(X);
						aux.movePiece(posx,posy,posx+x,posy+y);
						moves.add(aux);
					}
				}
			}
		}
		
		if (moved == false)
		{
			if ((posy == 0 && player > 0) || (posy == 7 && player < 0))
			{
				//castling O-O-O
				for(int x = 0; X.checkLimits(posx-x, posy); x++)
				{
					if (X.hasPiece(posx-x, posy) && X.getPiece(posx-x, posy) instanceof Rook)
					{
						if (posx-x == 0)
						{
							int auxposx = posx;
							int auxposy = posy;
							Chessboard aux = new Chessboard(X);
							aux.movePiece(posx, posy, posx-2, posy);
							aux.movePiece(posx-x, posy, auxposx-1, auxposy);
							aux.setLastMove("O-O-O");
							moves.add(aux);
						}
					}
					else
						break;
				}
				//castling O-O
				for(int x = 0; X.checkLimits(posx+x, posy); x++)
				{
					if (X.hasPiece(posx+x, posy) && X.getPiece(posx+x, posy) instanceof Rook)
					{
						if (posx+x == 7)
						{
							int auxposx = posx;
							int auxposy = posy;
							Chessboard aux = new Chessboard(X);
							aux.movePiece(posx, posy, posx+2, posy);
							aux.movePiece(posx+x, posy, auxposx+1, auxposy);
							aux.setLastMove("O-O");
							moves.add(aux);
						}
					}
					else
						break;
				}
			}
		}
		
		return moves;
	}

}
