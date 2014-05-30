package AI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ListIterator;

public class Search {

	public static PriorityQueue<Node> OpenList;
	public static PriorityQueue<Node> closedList;
	public static int numNodes;

	public static ArrayList almostAstar(int[][] board) {
		Comparator<Node> comparator = new BestMoveComparator();
		OpenList = new PriorityQueue<Node>(15, comparator);
		closedList = new PriorityQueue<Node>(15, comparator);
		numNodes = 0;
		Board newGame = new Board(board);

		Node first = new Node(newGame);
		OpenList.add(first);
		numNodes++;
		int z= GUI.Threes.sizeNext();
		while (!OpenList.isEmpty()) {
			Node current = OpenList.poll();
		
			if (current.getBoard().getGame()||numNodes>z*4) {
				if(numNodes>5000||numNodes>z*4){
					closedList.add(current);
					current =closedList.poll();
					System.out.println("game over after "+numNodes+" nodes");
					System.out.println("Score was "+current.board.score);
					System.out.println("Hscore was "+ current.board.getHScore());
					current.board.printBoard();
					return current.getMoves();
				}
				closedList.add(current);
				 current = OpenList.poll();
				
			}

			ListIterator nekMoves = current.getNextMoves().listIterator();

			while (nekMoves.hasNext()) {
	
				Board nek = (Board) nekMoves.next();
				ArrayList<String> upMoves = (ArrayList<String>) current.moves
						.clone();
				upMoves.add(0, nek.newMove);
				Node nekNode = (Node) new Node(nek, upMoves);
				OpenList.add(nekNode);
				numNodes++;
			}
		}
		return null;
		
	}
	
	
	public static void main(String [] args){
		String input = "exampleinput.txt";
		GUI.Threes.readFile(input);
		GUI.Threes.set();
		
		ArrayList<String> test=almostAstar(GUI.Threes.board);
		while(!test.isEmpty()){
			System.out.print(test.remove(test.size()-1));
		}System.out.println();
	}

}
