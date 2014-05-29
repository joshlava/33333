package AI;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * Nicolas La Verghetta 20762905
 * Andrew Edwards 20937641
 * A class to hold the logic for our "Artificial Intelligence" agent 
 */

public class AI {

	/*
	 * A function that generates a list of the series of moves to play that are considered the best by our heuristic function.
	 * @param board the board to be used to manipulate
	 * @param z the depth for the algorithm to go to.
	 * @return an ArrayDeque containing the series of recommended moves
	 */
	public static ArrayDeque<String> AStar(int[][] board, int z) {

		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> openList = new PriorityQueue<Node>(z, comparator);
		PriorityQueue<Node> closedList = new PriorityQueue<Node>(z, comparator);
		ArrayDeque<String> initialMoves = new ArrayDeque<String>();
		Node initial = new Node(Moves.countScore(GUI.Threes.board),
				Moves.Hscore(GUI.Threes.board)[1], initialMoves);
		openList.add(initial); // add initial board to openList

		ArrayDeque<String> moves = new ArrayDeque<String>();
		moves.clear();
		int HScore = 0;
		int Score = 0;

		int i = 0;
		while (!openList.isEmpty() && i < z) {
			Node node = openList.remove();

			closedList.add(node);

			LinkedList<Object> temp = new LinkedList<Object>();

			node.moves.add("D");
			temp = Moves.doMove(board, node.moves, false);
				if((int)temp.peek()!=-1){
				Node down = new Node((Integer) temp.get(0), (Integer) temp.get(1),
						node.moves.clone());
				openList.offer(down);
				}
			node.moves.removeLast();

			node.moves.add("U");
			temp = Moves.doMove(board, node.moves, false);
			if((int)temp.peek()!=-1){
			Node up = new Node((Integer) temp.get(0), (Integer) temp.get(1),
					node.moves.clone());
			openList.offer(up);
			}
			node.moves.removeLast();
			node.moves.add("L");
			temp = Moves.doMove(board, node.moves, false);
			if((int)temp.peek()!=-1){
			int tempint = (Integer) temp.get(0);
			Node left = new Node(tempint, (Integer) temp.get(1),
					node.moves.clone());
			openList.offer(left);
			}
			node.moves.removeLast();

			node.moves.add("R");
			temp = Moves.doMove(board, node.moves, false);
			if((int)temp.peek()!=-1){
			Node right = new Node((Integer) temp.get(0), (Integer) temp.get(1),
					node.moves.clone());
			openList.offer(right);
			}
			node.moves.removeLast();

			i++;
			
		}
		Node bestOpen = openList.remove();
		closedList.add(bestOpen);
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
		LinkedList<Object> temp3 = new LinkedList<Object>();

		String nextMove = "";
		for (int i = 0; i < z; i++) {

			temp = new ArrayDeque<String>(AStar(tempBoard, 100));

			temp.poll();
			temp.poll();
			tempBoard = GUI.Threes.board.clone();

			temp3 = Moves.doMove(tempBoard, temp, false);
			temp3.poll();
			temp3.poll();
			int k = 0;
			boolean gameon=true;
			while (!temp.isEmpty() && k<5&&gameon) {
				nextMove = temp.pollLast();

				if (nextMove.equals("L")) {
					gameon=GUI.Threes.Left();
				} else if (nextMove.equals("R")) {
					gameon=GUI.Threes.Right();
				} else if (nextMove.equals("U")) {
					gameon=GUI.Threes.Up();
				} else if (nextMove.equals("D")) {
					gameon=GUI.Threes.Down();
				} else
					nextMove = "X";
				k++;
				if (!nextMove.equals("X")&&gameon)
					finalmoves.addFirst(nextMove);
				Moves.popNext();
			}
			//tempBoard = GUI.Threes.board.clone();

			// if(!nextMove.equals("X"))
			// finalmoves.addFirst(nextMove);
			// Moves.popNext();
			if(!gameon){
				i=z;
			}

		}
		System.out.println("contents of finalmoves");
		int movesize = finalmoves.size();
		ArrayDeque<String> printemp = new ArrayDeque<String>(finalmoves);
		for (int i = 0; i < movesize; i++) {
			System.out.print(printemp.remove());
		}
		System.out.println();
		temp2 = Moves.doMove(board, finalmoves, true);
		System.out.println("heuristic score was: " + temp2.remove(0));

		System.out.println("Final score was: " + temp2.remove(0));
		GUI.Threes.finalBoard();

		System.out.println("Move string was");
		while (!temp2.isEmpty()) {
			System.out.print(temp2.remove());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// AI a = new AI();
		
		GUI.Threes.readFile();
		GUI.Threes.set();
		Moves.storeNext();
		Moves.resetNext();
		ArrayDeque<String> Res = AStar(GUI.Threes.board, 400);
		System.out.println(Res.removeFirst());
		System.out.println(Res.removeFirst());

		Moves.doMove(GUI.Threes.board, Res, true);
		System.out.println("Final board is");
		//GUI.Threes.finalBoard();

		while (!Res.isEmpty()) {
			System.out.print(Res.removeLast());
		}
		System.out.println();
		GUI.Threes.readFile();
		GUI.Threes.set();
		Moves.resetNext();
		recur(GUI.Threes.board, 10);
	}

}
