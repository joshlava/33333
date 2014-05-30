package AI;

import java.util.ArrayList;

public class Node {

	Board board;
	ArrayList<String> moves = new ArrayList<String>(); // an ArrayDeque to hold
														// the sequence of moves
														// taken to reach this
														// current board

	public Node(Board board) {
		this.board = board;

		moves = new ArrayList<String>();

	}

	public Node(Board board, ArrayList<String> moves) {
		this.board = board;

		this.moves = moves;
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public Board getBoard() {
		return board;
	}

	public ArrayList<Board> getNextMoves() {
		int n = moves.size();
		
		
		ArrayList<Board> nextMoves = new ArrayList<Board>();
		Board temp;
		temp = betterMoves.Left(resetBoard(), n);
		nextMoves.add(temp);
		temp = betterMoves.Right(resetBoard(), n);
		nextMoves.add(temp);
		temp = betterMoves.Up(resetBoard(), n);
		nextMoves.add(temp);
		temp = betterMoves.Down(resetBoard(), n);
		nextMoves.add(temp);

		return nextMoves;

	}
	public int[][] resetBoard(){
		int[][] tempBoard=new int [4][4];
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				tempBoard[i][j]=board.getBoard()[i][j];
			}
		}
		return tempBoard;
	}
	/*
	 * public ArrayList<ArrayList<String>> getNextMoves(){
	 * 
	 * ArrayList<ArrayList<String>> nextMoves=new ArrayList<ArrayList<String>>
	 * (); ArrayList<String> temp=(ArrayList<String>) moves.clone();
	 * temp.add(0,"L"); nextMoves.add(temp); temp.remove(0); temp.add(0,"R");
	 * nextMoves.add(temp); temp.remove(0); temp.add(0,"U");
	 * nextMoves.add(temp); temp.remove(0); temp.add(0,"D");
	 * nextMoves.add(temp); temp.remove(0); return nextMoves;
	 * 
	 * 
	 * }
	 */

	public void setMoves(ArrayList<String> moves) {
		this.moves = moves;
	}
	public void printMoves(){
		for(int i=0;i<moves.size();i++){
			System.out.println(moves.get(i));
		}
	}
	public String stringMoves(){
		String pMoves="";
		for(int i=0;i<moves.size();i++){
			pMoves+=moves.get(i);
		}
		return pMoves;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
