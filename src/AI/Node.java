package AI;

import java.util.ArrayDeque;


public class Node {
	
	int whiteSpace;
	int score;
	ArrayDeque<String> moves = new ArrayDeque<String>();
	
	public Node(int whiteSpace, int score, ArrayDeque<String> moves){
		this.whiteSpace = whiteSpace;
		this.moves = moves;
		this.score=score;
	}
	
	public void printNode(){
		ArrayDeque<String> tempnode=moves.clone();
		System.out.println("NODE.MOVES CONTENT BEING PRINTED HERE ");
		while(!tempnode.isEmpty()){
			System.out.print(tempnode.remove());
		}
		System.out.println("");
		System.out.println("NODE MOVES CONTENT FINISHED PRINTING");
	}
	public boolean isEqauls(Node a, Node b){
		if(a.whiteSpace==b.whiteSpace&&a.score==b.score&& a.moves.equals(b.moves))
			return true;
		else
			return false;
		
	}
	
}
