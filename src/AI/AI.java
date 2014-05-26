package AI;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AI {

	
	private LinkedList<String> AStar(int [][] board){
		
		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> openList = new PriorityQueue<Node>(10, comparator);
		
		GUI.Threes.readFile();
		GUI.Threes.set();
		ArrayDeque initialMoves = new ArrayDeque();
		initialMoves.add(" ");
		Node initial = new Node(Moves.CountWhite(GUI.Threes.board), initialMoves);
		openList.add(initial); //add initial board to openList
		
		LinkedList<String> moves = new LinkedList<String>();
		
		int goal = 10;
		int i = 0;
		while(!openList.isEmpty() && i < 10){
			Node node = openList.element();
			node.printNode();
			int whiteSpace = node.whiteSpace;
			LinkedList temp = new LinkedList();
			
			//while(whiteSpace < goal && openList.size() < 100){
				
				node.moves.add("L");
				temp = Moves.doMove(board, node.moves);
				Node left = new Node((Integer)temp.get(1), node.moves.clone());
				openList.add(left);
				node.moves.removeLast();
				
				node.moves.add("R");
				temp = Moves.doMove(board, node.moves);
				Node right = new Node((Integer)temp.get(1), node.moves);
				openList.add(right);
				node.moves.removeLast();
				
				node.moves.add("U");
				temp = Moves.doMove(board, node.moves);
				Node up = new Node((Integer)temp.get(1), node.moves);
				openList.add(up);
				node.moves.removeLast();
				
				node.moves.add("D");
				temp = Moves.doMove(board, node.moves);
				Node down = new Node((Integer)temp.get(1), node.moves);
				openList.add(down);
				node.moves.removeLast();

			//}
			i++;
		}
		/*
		PriorityQueue<ArrayList> OpenList = new PriorityQueue<ArrayList>();
		ArrayList<Object> nodes =new ArrayList<Object>();
		int goal = 10;
		while(!OpenList.isEmpty()){
			ArrayList<Object> node=OpenList.element();
			int score = (Integer) node.get(1);
			ArrayDeque<String> listMoves = (ArrayDeque<String>) node.get(0);
			LinkedList temp=new LinkedList();
			while(score!=goal&&OpenList.size()<100){
				for(int i=0;i<4;i++){
					if(i==0){
						listMoves.add("L");
						node.clear();
						node.add(listMoves);
						temp=Moves.doMove(board, listMoves); // may need to reset board
						node.add(temp.get(1));  
						listMoves.removeLast(); //can put this at top of for loop
						
						
					}else if(i==1){
						listMoves.add("R");
						node.clear();
						node.add(listMoves);
						temp=Moves.doMove(board, listMoves);
						node.add(temp.get(1)); 
						listMoves.removeLast();

					}
				}
				
			}
			
			
			
			
			
			
			
		}
		
		
		
		
	*/	
		return moves;
	
		
	}
	public static void main(String [] args){
		AI a = new AI();
		GUI.Threes.readFile();
		GUI.Threes.set();
		a.AStar(GUI.Threes.board);
	}
	
}
