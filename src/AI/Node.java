package AI;

import java.util.ArrayDeque;


public class Node {
	
	int whiteSpace;
	ArrayDeque moves = new ArrayDeque();
	
	public Node(int whiteSpace, ArrayDeque moves){
		this.whiteSpace = whiteSpace;
		this.moves = moves;
	}
	
	public void printNode(){
		while(!moves.isEmpty()){
			System.out.print(moves.remove());
		}
	}
	
}
