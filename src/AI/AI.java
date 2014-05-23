package AI;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AI {

	
	private LinkedList<String> AStar(int [][] board){
		LinkedList<String> moves = new LinkedList<String>();
		
		PriorityQueue<ArrayList> OpenList = new PriorityQueue<ArrayList>();
		ArrayList<Object> nodes =new ArrayList<Object>();
		int goal =10;
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
		
		
		
		
		
		return moves;
	
		
	}
	public static void main(String [] args){
		
	}
	
}
