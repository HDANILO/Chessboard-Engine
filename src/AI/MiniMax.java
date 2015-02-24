package AI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import Board.Chessboard;


public class MiniMax {

	Logger log = Logger.getLogger("Chessboard");
	
	public class Node{
		
		ArrayList<Node> children;
		int player;
		Chessboard chess;
		public long value;
		
		public Node(int player, Chessboard chess)
		{
			this.player = player;
			this.chess = chess;
			children = new ArrayList<Node>();
		}
		
		public void addChild(Node n)
		{
			children.add(n);
		}
		
		public ArrayList<Node> getChildren()
		{
			return children;
		}
	}
	
	int maxDepth;
	public int cTotal = 0;
	public int eTotal = 0;
	Node head;
	
	public MiniMax(int maxDepth)
	{
		this.maxDepth = maxDepth;
	}
	
	public Chessboard bestMove(Chessboard chess, int player)
	{
		head = new Node(player,chess);
		head.value = alphabeta(head,0,player,Integer.MIN_VALUE,Integer.MAX_VALUE);
		if (!head.getChildren().isEmpty())
		{
			Node max = head.getChildren().get(0);
			for(Node move : head.getChildren())
			{
				if (move.value > max.value)
					max = move;
			}
			log.info("Best heuristic: " + head.value);
			log.info("Total considerated: " + eTotal + "; Total prunned: " + cTotal);
			return max.chess;
		}
		else
		{
			log.info("player " + player + " Checkmate");
			return chess;
		}
	}
	
	public long alphabeta(Node n, int currDepth, int player, long alpha, long beta)
	{
		Chessboard X = n.chess;
		
		
		if (currDepth == maxDepth)
		{
			long eval = Evaluation.evaluate(X, n.player);
			return eval;
		}
		
		if (player == n.player)
		{
			int c = 0;
			ArrayList<Chessboard> movesList = X.possibleMoves(n.player);
			
			for (Chessboard move : movesList)
			{
				eTotal++;
				c++;
				Node newNode = new Node(n.player*(-1),move);
				alpha = Math.max(alpha, alphabeta(newNode,currDepth+1, player, alpha, beta));
				newNode.value = alpha;
				
				if (beta <= alpha)
				{
					this.cTotal += (movesList.size()-c);
					//log.info("Beta cut-off: cutted " + (movesList.size()-c) + " movelists");
					break;
				}
				n.addChild(newNode);
			}
			return alpha;
		}
		else
		{
			int c = 0;
			ArrayList<Chessboard> movesList = X.possibleMoves(n.player);
			
			for (Chessboard move : movesList)
			{
				eTotal++;
				c++;
				Node newNode = new Node(n.player*(-1),move);
				beta = Math.min(beta, alphabeta(newNode,currDepth+1, player, alpha, beta));
				newNode.value = beta;
				if (beta <= alpha)
				{
					this.cTotal += (movesList.size()-c);
					//log.info("Alpha cut-off: cutted " + (movesList.size()-c) + " moves");
					break;
				}
				n.addChild(newNode);
			}
			
			return beta;
		}
	}
	
}
