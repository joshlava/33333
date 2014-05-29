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

	public static ArrayDeque<String> AStar(int[][] board, int z) {

		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> openList = new PriorityQueue<Node>(z, comparator);
		PriorityQueue<Node> closedList = new PriorityQueue<Node>(z, comparator);
		GUI.Threes.readFile();
		GUI.Threes.set();
		ArrayDeque<String> initialMoves = new ArrayDeque<String>();
		// initialMoves.add(" ");
		Node initial = new Node(Moves.countScore(GUI.Threes.board),
				Moves.Hscore(GUI.Threes.board)[1], initialMoves);
		openList.add(initial); // add initial board to openList

		ArrayDeque<String> moves = new ArrayDeque<String>();
		int HScore = 0;
		int Score = 0;

		int goal = 10;
		int i = 0;
		while (!openList.isEmpty() && i < z) {
			Node node = openList.remove();

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
			Score = node.score;
			LinkedList<Object> temp = new LinkedList<Object>();

			// while(whiteSpace < goal && openList.size() < 100){

			node.moves.add("L");
			temp = Moves.doMove(board, node.moves, false);
			int tempint = (Integer) temp.get(0);
			Node left = new Node(tempint, (Integer) temp.get(1),
					node.moves.clone());
			openList.add(left);
			node.moves.removeLast();

			node.moves.add("R");
			temp = Moves.doMove(board, node.moves, false);
			Node right = new Node((Integer) temp.get(0), (Integer) temp.get(1),
					node.moves.clone());
			openList.add(right);
			node.moves.removeLast();

			node.moves.add("D");
			temp = Moves.doMove(board, node.moves, false);
			Node down = new Node((Integer) temp.get(0), (Integer) temp.get(1),
					node.moves.clone());
			openList.add(down);
			node.moves.removeLast();

			node.moves.add("U");
			temp = Moves.doMove(board, node.moves, false);
			Node up = new Node((Integer) temp.get(0), (Integer) temp.get(1),
					node.moves.clone());
			openList.add(up);
			node.moves.removeLast();

			// }

			i++;
		}
		Node node = closedList.remove();
		Score = node.score;
		HScore = node.hScore;
		moves = node.moves;
		String heurScore = "Heuristic Score is: " + HScore;
		String scorescore = "Game Score is: " + Score;

		moves.addFirst(heurScore);
		moves.addFirst(scorescore);
		return moves;

	}

	public static void recur(int[][] board, int z) {
		int x = z;
		int[][] tempBoard = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
		Moves.storeNext();
		// GUI.ThreesGUI gui = new GUI.ThreesGUI();
		ArrayDeque<String> finalmoves = new ArrayDeque<String>();
		ArrayDeque<String> temp = new ArrayDeque<String>();
		LinkedList<Object> temp2 = new LinkedList<Object>();
		String nextMove = "";
		for (int i = 0; i < x; i++) {
	

			temp = new ArrayDeque<String>(AStar(tempBoard, z));

			temp.poll();
			temp.poll();
			nextMove = temp.poll();
			if (nextMove.equals("L")) {
				GUI.Threes.Left();
			} else if (nextMove.equals("R")) {
				GUI.Threes.Right();
			} else if (nextMove.equals("U")) {
				GUI.Threes.Up();
			} else if (nextMove.equals("D")) {
				GUI.Threes.Down();
			}else
				nextMove="X";
			
			tempBoard=GUI.Threes.board.clone();
		
		
			if(!nextMove.equals("X"))
				finalmoves.addFirst(nextMove);
			Moves.popNext();

		}
		temp2 = Moves.doMove(board, finalmoves, true);
		//GUI.Threes.finalBoard();
		System.out.println("heuristic score was: " + temp2.remove(0));

		System.out.println("Final score was: " + temp2.remove(0));
		GUI.Threes.finalBoard();

		System.out.println("Move string was");
		while (!temp2.isEmpty()) {
			System.out.print(temp2.removeLast());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// AI a = new AI();
		GUI.Threes.readFile();
		GUI.Threes.set();
		
		recur(GUI.Threes.board, 100);
		GUI.Threes.readFile();
		GUI.Threes.set();
		Moves.storeNext();

		ArrayDeque<String> Res = AStar(GUI.Threes.board, 500);
		System.out.println(Res.removeFirst());
		System.out.println(Res.removeFirst());

		Moves.doMove(GUI.Threes.board, Res, true);
		System.out.println("Final board is");
		GUI.Threes.finalBoard();

		while (!Res.isEmpty()) {
			System.out.print(Res.removeLast());
		}
		System.out.println();
	}

}
