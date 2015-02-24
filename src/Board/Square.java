package Board;
import Piece.*;

public class Square {
	
	public Piece piece;
	public boolean hasPiece = false;
	
	public Square()
	{
		hasPiece = false;
		piece = null;
	}
	
	public Square(Square copy)
	{
		this.hasPiece = copy.hasPiece;
		if (hasPiece)
		{
			if (copy.piece instanceof King)
				piece = new King(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			else if (copy.piece instanceof Queen)
				piece = new Queen(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			else if (copy.piece instanceof Rook)
				piece = new Rook(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			else if (copy.piece instanceof Knight)
				piece = new Knight(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			else if (copy.piece instanceof Bishop)
				piece = new Bishop(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			else
				piece = new Pawn(copy.piece.getPosx(),copy.piece.getPosy(),copy.piece.getPlayer());
			
		}
			
	}
	
	public Square(Piece p)
	{
		hasPiece = true;
		piece = p;
	}
	
	public void putPiece(Piece p)
	{
		hasPiece = true;
		piece = p;
	}
	
	public Piece removePiece()
	{
		Piece r = piece;
		hasPiece = false;
		piece = null;
		return r;
	}

}
