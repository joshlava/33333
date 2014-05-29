package AI;

import java.util.ArrayDeque;

public class Node {

	
	int hScore; //the score our heuristic function gives for the current move
	int score; //the current score of the board at this current move
	int [][]board;
	ArrayDeque<String> moves = new ArrayDeque<String>(); //an ArrayDeque to hold the sequence of moves taken to reach this current board
	
	public Node(int hScore , int score,int [][] board,  ArrayDeque<String> moves){
		this.hScore = hScore;
		this.moves = moves;
		this.score=score;
		this.board=board;
	}
	
	public int getScore()
	{
		return score;
	}
	public ArrayDeque<String> getMoves(){
		return moves;
	}
	public int[][] getBoard(){
		return board;
	}
	public int getHScore(){
		return hScore;
	}
	public void setScore(int score){
		this.score=score;
	}
	public void setHScore(int hScore){
		this.hScore=hScore;
	}
	public void setMoves(ArrayDeque<String> moves){
		this.moves=moves;
	}
	public void setBoard(int [][]board){
		this.board=board;
	}
}
