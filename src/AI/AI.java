package AI;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
//Why doesnt it move to DDDDLR?
//and how is DDDDL still at the front of the list if its already removed. 
//because it will remove it, then it will check the best and re make the same node over and over

public class AI {

	private ArrayDeque<String> AStar(int[][] board) {
<<<<<<< HEAD
		int z = 15;
=======
		int z = 6;
>>>>>>> fbacf704b75b3a5db744f76d04e9df5c72b33677
		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> openList = new PriorityQueue<Node>(z, comparator);
		ArrayDeque<Node> closedList = new ArrayDeque<Node>();
		GUI.Threes.readFile();
		GUI.Threes.set();
		ArrayDeque<String> initialMoves = new ArrayDeque<String>();
		// initialMoves.add(" ");
		Node initial = new Node(Moves.CountWhite(GUI.Threes.board),
				Moves.countScore(GUI.Threes.board), initialMoves);
		openList.add(initial); // add initial board to openList

		ArrayDeque<String> moves = new ArrayDeque<String>();
		int whiteSpace = 0;
		int Score = 0;
		int goal = 10;
		int i = 0;
		while (!openList.isEmpty() && i < z) {
			Node node = openList.element();
			if (!closedList.contains(node)) {
				closedList.add(node);
				/*
				 * System.out.println("/=/=/=/=/==/=/=/=/=/=/==");
				 * System.out.print("Size of openList ");
				 * System.out.println(openList.size());
				 * System.out.print("The whitespace of best node is: ");
				 * System.out.println(node.whiteSpace);
				 */

				moves = node.moves;
				// node.printNode();
				whiteSpace = node.whiteSpace;
				Score = node.score;
				LinkedList<Object> temp = new LinkedList<Object>();

				// while(whiteSpace < goal && openList.size() < 100){

				node.moves.offer("L");
				temp = Moves.doMove(board, node.moves);
				int tempint = (Integer) temp.get(1);
				Node left = new Node(tempint, (Integer) temp.get(0),
						node.moves.clone());
				openList.add(left);
				node.moves.removeLast();
				System.out.println("Whitespace is: " + (Integer)temp.get(1));

				node.moves.add("R");
				temp = Moves.doMove(board, node.moves);
				Node right = new Node((Integer) temp.get(1),
						(Integer) temp.get(0), node.moves.clone());
				openList.add(right);
				node.moves.removeLast();
				System.out.println("Whitespace is: " + (Integer)temp.get(1));

				node.moves.add("D");
				temp = Moves.doMove(board, node.moves);
				Node down = new Node((Integer) temp.get(1),
						(Integer) temp.get(0), node.moves.clone());
				openList.add(down);
				node.moves.removeLast();
				System.out.println("Whitespace is: " + (Integer)temp.get(1));

				node.moves.offer("U");
				temp = Moves.doMove(board, node.moves);
				Node up = new Node((Integer) temp.get(1),
						(Integer) temp.get(0), node.moves.clone());
				openList.add(up);
				node.moves.removeLast();
				System.out.println("Whitespace is: " + (Integer)temp.get(1));

				// }
			}

			i++;
		}
		String whitescore = "White Space Score is: " + whiteSpace;
		String scorescore = "Game Score is: " + Score;
		moves.addFirst(whitescore);
		moves.addFirst(scorescore);
		return moves;

	}

	public static void main(String[] args) {
		AI a = new AI();
		GUI.Threes.readFile();
		GUI.Threes.set();
		ArrayDeque<String> Res = a.AStar(GUI.Threes.board);
		while (!Res.isEmpty()) {
			System.out.println(Res.removeLast());
		}
	}

}
