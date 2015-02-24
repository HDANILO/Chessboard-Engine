package Piece;
import java.util.ArrayList;

import Board.Chessboard;

public abstract class Piece {
	
	int player;
	
	int posx;
	int posy;
	
	public Piece(int x, int y, int player)
	{
		posx = x;
		posy = y;
		this.player = player;
	}
	
	public int Representation()
	{
		return Symbol() * player;
	}
	
	public void moveTo(int x, int y)
	{	
		posx = x;
		posy = y;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}
	
	abstract public int Symbol();
	abstract public ArrayList<Chessboard> possibleMoves(Chessboard X);

}
