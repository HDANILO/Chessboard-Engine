package Board;

import java.util.ArrayList;
import java.util.logging.Logger;

import Piece.*;

public class Chessboard{
	
	Square[][] chessboard;
	Logger log = Logger.getLogger("Chessboard");
	String lastMove;
	boolean possibleEnPassant;
	int enPassantX, enPassantY;
	
	public boolean hasEnPassant()
	{
		return possibleEnPassant;
	}
	
	public Piece getEnPassant() {
		if(possibleEnPassant)
		{
			return chessboard[enPassantX][enPassantY].piece;
		}
		else
			return null;
	}

	public void setPossibleEnPassant(boolean possibleEnPassant, int enPassantX, int enPassantY) {
		this.enPassantX = enPassantX;
		this.enPassantY = enPassantY;
		this.possibleEnPassant = possibleEnPassant;
	}

	public String getLastMove() {
		return lastMove;
	}

	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}

	public Square[][] getChessboard() {
		return chessboard;
	}

	public Chessboard(Chessboard copy)
	{
		lastMove = "";
		possibleEnPassant = false;
		chessboard = new Square[8][8];
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				chessboard[i][j] = new Square(copy.getChessboard()[i][j]);
			}
		}
	}
	
	public Chessboard()
	{
		lastMove = "";
		possibleEnPassant = false;
		chessboard = new Square[8][8];
		for (int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				chessboard[i][j] = new Square();
			}
		}
	}
	
	public boolean checkLimits(int x, int y)
	{
		if (x < 0 || x > 7)
			return false;
		
		if (y < 0 || y > 7)
			return false;
		
		return true;
	}
	
	public boolean hasPiece(int x, int y)
	{
		if (checkLimits(x,y) == false)
			return false;
		
		return chessboard[x][y].hasPiece;
	}
	
	public Piece getPiece(int x, int y)
	{
		if (checkLimits(x,y) == false)
			return null;
		
		if (hasPiece(x,y) == false)
			return null;
		
		return chessboard[x][y].piece;
	}
	
	public void setupBoard()
	{
		//Setting Rook for both players
		chessboard[0][0].putPiece(new Rook(0,0,1));
		chessboard[7][0].putPiece(new Rook(7,0,1));
		
		chessboard[0][7].putPiece(new Rook(0,7,-1));
		chessboard[7][7].putPiece(new Rook(7,7,-1));
		
		//Setting Knights for both players
		chessboard[1][0].putPiece(new Knight(1,0,1));
		chessboard[6][0].putPiece(new Knight(6,0,1));
		
		chessboard[1][7].putPiece(new Knight(1,7,-1));
		chessboard[6][7].putPiece(new Knight(6,7,-1));
		
		//Setting Bishop for both players
		chessboard[2][0].putPiece(new Bishop(2,0,1));
		chessboard[5][0].putPiece(new Bishop(5,0,1));
		
		chessboard[2][7].putPiece(new Bishop(2,7,-1));
		chessboard[5][7].putPiece(new Bishop(5,7,-1));
		
		//Setting King and Queen for both players
		chessboard[4][0].putPiece(new King(4,0,1));
		chessboard[3][0].putPiece(new Queen(3,0,1));
		
		chessboard[4][7].putPiece(new King(4,7,-1));
		chessboard[3][7].putPiece(new Queen(3,7,-1));
		
		for(int i = 0; i < 8; i++)
		{
			chessboard[i][1].putPiece(new Pawn(i,1,1));
			chessboard[i][6].putPiece(new Pawn(i,6,-1));
		}
	}
	
	public void printBoard()
	{
		String output = "\n";
		for(int i = 7; i >= 0; i--)
		{
			for(int j = 0; j < 8; j++)
			{
				if (chessboard[j][i].hasPiece)
					output += String.format("%03d ", chessboard[j][i].piece.Representation());
				else
					output += "000 ";
			}
			output += "\n";
		}
		log.info(output);
	}
	
	public boolean movePiece(int fromX, int fromY, int toX, int toY)
	{
		if (this.checkLimits(toX, toY) == false)
			return false;
		
		if (this.hasPiece(fromX, fromY) == false)
			return false;
			
		Piece p = chessboard[fromX][fromY].removePiece();
		
		p.moveTo(toX, toY);
		if (p instanceof King)
			((King)p).setMoved();
		
		chessboard[toX][toY].putPiece(p);
		
		//En Passant, must test
		if (p instanceof Pawn && this.hasEnPassant())
		{
			Piece enPassant = chessboard[enPassantX][enPassantY].piece;
			if (enPassant.getPosx() == toX && enPassant.getPlayer() != p.getPlayer())
			{
				chessboard[toX][fromY].removePiece();
			}
		}
		else if(p instanceof Pawn && Math.abs(toY-fromY) == 2)
		{
			this.setPossibleEnPassant(true, toX, toY);
		}
		
		String f1, t1;
		f1 = (char)(fromX + 97) + "";
		t1 = (char)(toX + 97) + "";
		lastMove =  f1 + (fromY+1) + t1 + (toY+1);
		return true;
	}
	
	public boolean movePiece(int fromX, int fromY, int toX, int toY, char change)
	{
		if (this.checkLimits(toX, toY) == false)
			return false;
		
		if (this.hasPiece(fromX, fromY) == false)
			return false;
			
		Piece p = chessboard[fromX][fromY].removePiece();
		
		switch (change)
		{
			case 'q':
				p = new Queen(fromX,fromY,p.getPlayer());
				break;
			case 'n':
				p = new Knight(fromX,fromY,p.getPlayer());
				break;
			case 'b':
				p = new Bishop(fromX,fromY,p.getPlayer());
				break;
			case 'r':
				p = new Rook(fromX,fromY,p.getPlayer());
				break;
		}
		
		p.moveTo(toX, toY);
		if (p instanceof King)
			((King)p).setMoved();
		
		chessboard[toX][toY].putPiece(p);
		
		String f1, t1;
		f1 = (char)(fromX + 97) + "";
		t1 = (char)(toX + 97) + "";
		lastMove =  f1 + (fromY+1) + t1 + (toY+1) + change;
		
		return true;
	}
	
	public ArrayList<Chessboard> possibleMoves(int player)
	{
		ArrayList<Chessboard> moves = new ArrayList<Chessboard>();
		if (isInCheck(player))
		{
			//log.info("Board in check:");
			/*printBoard();*/
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if (chessboard[i][j].hasPiece && chessboard[i][j].piece.getPlayer() == player)
					{
						for(Chessboard move : chessboard[i][j].piece.possibleMoves(this))
						{
							if (!move.isInCheck(player))
								moves.add(move);
						}
					}
				}
			}
		}
		else
		{
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if (chessboard[i][j].hasPiece && chessboard[i][j].piece.getPlayer() == player)
						moves.addAll(chessboard[i][j].piece.possibleMoves(this));
				}
			}
		}
		return moves;
	}
	
	public boolean isInCheck(int player)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if (chessboard[i][j].hasPiece && chessboard[i][j].piece.getPlayer() != player)
				{
					for(Chessboard move : chessboard[i][j].piece.possibleMoves(this))
					{
						if (!move.hasKing(player))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean hasKing(int player)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(chessboard[i][j].hasPiece)
				{
					if (chessboard[i][j].piece instanceof King && chessboard[i][j].piece.getPlayer() == player)
						return true;
				}
			}
		}
		return false;
	}
	
	public void makeMove(String move, int player)
	{
		if (move.compareTo("O-O") == 0)
		{
			if (player > 0)
			{
				movePiece(4,0,6,0);
				movePiece(7,0,5,0);
			}
			else
			{
				movePiece(4,7,6,7);
				movePiece(7,7,5,7);
			}
		}
		else if(move.compareTo("O-O-O") == 0)
		{
			if (player > 0)
			{
				movePiece(4,0,2,0);
				movePiece(0,0,3,0);
			}
			else
			{
				movePiece(4,0,2,0);
				movePiece(0,0,3,0);
			}
		}
		else
		{
			int s1, s2;
			s1 = move.charAt(0);
			s2 = move.charAt(2);
			s1 = (char) (s1 - 'a');
			s2 = (char) (s2 - 'a');
			int n1, n2;
			n1 = Integer.parseInt(move.charAt(1) + "") - 1;
			n2 = Integer.parseInt(move.charAt(3) + "") - 1;
			if (move.length() == 5)
			{				
				
				Piece p = chessboard[s1][n1].removePiece();
				if (p == null)
				{
					log.info("Error, invalid position " + s1 + "" + n1 + "" + s2 + "" + n2 + " null piece on this coordinate");
				}
				
				switch (move.charAt(4))
				{
					case 'q':
						p = new Queen(s2,n2,p.getPlayer());
						break;
					case 'n':
						p = new Knight(s2,n2,p.getPlayer());
						break;
					case 'b':
						p = new Bishop(s2,n2,p.getPlayer());
						break;
					case 'r':
						p = new Rook(s2,n2,p.getPlayer());
						break;
				}
				
				chessboard[s1][n1].putPiece(p);
			}
				
			
			this.movePiece(s1, n1, s2, n2);
			
			log.info("Printing board after the move");
			printBoard();
		}
	}
	
	public String moveString(Chessboard X, int player)
	{
		int[][] values = new int[8][8];
		boolean queening = false;
		char change = '\0';
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				int value1 = 0;
				int value2 = 0;
				if (hasPiece(i,j))
				{
					value1 = getPiece(i, j).Representation()*player;
					if (getPiece(i,j) instanceof Pawn)
					{
						if (player > 0 && j == 6 && !hasPiece(i,7) && X.hasPiece(i, 7))
						{
							queening = true;
							if (X.getPiece(i, 7) instanceof Queen)
								change = 'q';
							else if (X.getPiece(i, 7) instanceof Rook)
								change = 'r';
							else if (X.getPiece(i, 7) instanceof Bishop)
								change = 'b';
							else
								change = 'n';
						}
						else if (player < 0 && j == 1 && !hasPiece(i,0) && X.hasPiece(i, 0))
						{
							queening = true;
							if (X.getPiece(i, 0) instanceof Queen)
								change = 'q';
							else if (X.getPiece(i, 0) instanceof Rook)
								change = 'r';
							else if (X.getPiece(i, 0) instanceof Bishop)
								change = 'b';
							else
								change = 'n';
						}
					}
				}
				
				if (X.hasPiece(i, j))
					value2 = X.getPiece(i, j).Representation()*player;
				
				values[i][j] = value1 - value2; 
			}
		}
		String moveStart = "";
		String moveEnd = "";
		for(int i = 7; i >= 0; i--)
		{
			for(int j = 0; j < 8; j++)
			{
				if (values[i][j] > 0)
					moveStart = ((char)(i+97)) + "" + (j+1);
				
				if (values[i][j] < 0)
					moveEnd = ((char)(i+97)) + "" + (j+1);
				
				//System.out.format("%03d ", values[j][i]);
			}
			//System.out.println();
		}
		
		//System.out.println("jogada: " + (queening == true? moveStart.concat(moveEnd).concat(change+ "") : moveStart.concat(moveEnd)));
		return (queening == true? moveStart.concat(moveEnd).concat(change+ "") : moveStart.concat(moveEnd));
	}

	
	
}
