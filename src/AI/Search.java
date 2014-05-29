package AI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ListIterator;

public class Search {

	public static PriorityQueue<Node> OpenList;
	public static int numNodes;

	public static ArrayList almostAstar(int[][] board) {
		Comparator<Node> comparator = new BestMoveComparator();
		OpenList = new PriorityQueue<Node>(15, comparator);
		numNodes = 0;
		Board newGame = new Board(board);

		Node first = new Node(newGame);
		OpenList.add(first);
		numNodes++;

		while (!OpenList.isEmpty()) {
			Node current = OpenList.poll();

			if (current.getBoard().getGame()) {
				System.out.println("game over after "+numNodes+" nodes");
				System.out.println("Score was "+current.board.score);
				System.out.println("Hscore was "+ current.board.hScore);
				current.board.printBoard();
				return current.getMoves();
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
		GUI.Threes.readFile("exampleinput.txt");
		GUI.Threes.set();
		
		ArrayList<String> test=almostAstar(GUI.Threes.board);
		
	}

}
