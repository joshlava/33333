package AI;

import java.util.ArrayList;

public class Node {

	
	
	Board board;
	ArrayList<String> moves = new ArrayList<String>(); //an ArrayDeque to hold the sequence of moves taken to reach this current board
	
	public Node(Board board,  ArrayList<String> moves){
		
		this.moves = moves;
		this.board=board;
	}
	public Node(Board board){
		this.board=board;
	
		moves=new ArrayList<String>();
		
	}
	
	
	public ArrayList<String> getMoves(){
		return moves;
	}
	public Board getBoard(){
		return board;
	}
public ArrayList<Board> getNextMoves(){
		int n=moves.size();
		ArrayList<Board> nextMoves=new ArrayList<Board> ();
		Board temp;
		temp=betterMoves.Left(board.getBoard(), n);
		nextMoves.add(temp);
		temp=betterMoves.Right(board.getBoard(), n);
		nextMoves.add(temp);
		temp=betterMoves.Up(board.getBoard(), n);
		nextMoves.add(temp);
		temp=betterMoves.Down(board.getBoard(), n);
		nextMoves.add(temp);
		
		return nextMoves;
		
		
	}
	
	/*public ArrayList<ArrayList<String>> getNextMoves(){
		
		ArrayList<ArrayList<String>> nextMoves=new ArrayList<ArrayList<String>> ();
		ArrayList<String> temp=(ArrayList<String>) moves.clone();
		temp.add(0,"L");
		nextMoves.add(temp);
		temp.remove(0);
		temp.add(0,"R");
		nextMoves.add(temp);
		temp.remove(0);
		temp.add(0,"U");
		nextMoves.add(temp);
		temp.remove(0);
		temp.add(0,"D");
		nextMoves.add(temp);
		temp.remove(0);
		return nextMoves;
		
		
	}*/
	
	public void setMoves(ArrayList<String> moves){
		this.moves=moves;
	}
	public void setBoard(Board board){
		this.board=board;
	}
}
