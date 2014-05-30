package old;

import java.util.ArrayDeque;

import AI.Node;

/* 
 * Josh La Verghetta 20762905
 * Andrew Edwards 20937641
 * A class to hold the required information of each board move.
 */
public class Nodeold {
	
	int hScore; //the score our heuristic function gives for the current move
	int score; //the current score of the board at this current move
	
	ArrayDeque<String> moves = new ArrayDeque<String>(); //an ArrayDeque to hold the sequence of moves taken to reach this current board
	
	public Nodeold(int hScore , int score,  ArrayDeque<String> moves){
		this.hScore = hScore;
		this.moves = moves;
		this.score=score;
	}
	
	/*
	 * A function to print the contents of a node
	 */
	public void printNode(){
		ArrayDeque<String> tempnode=moves.clone();
		System.out.println("NODE.MOVES CONTENT BEING PRINTED HERE ");
		while(!tempnode.isEmpty()){
			System.out.print(tempnode.remove());
		}
		System.out.println("");
		System.out.println("NODE MOVES CONTENT FINISHED PRINTING");
	}
	
	/*
	 * A function to compare two nodes
	 * @return true, if the nodes have the same contents, false otherwise
	 */
	public boolean isEquals(Node a, Node b){
		if(a.moves.equals(b.moves))
			return true;
		else
			return false;
		
	}
	
}
