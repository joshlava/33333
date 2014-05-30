package AI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		int z = GUI.Threes.sizeNext();
		while (!OpenList.isEmpty()) {
			Node current = OpenList.poll();

			if (current.getBoard().getGame() || numNodes > z * 4) {
				if (numNodes > 10000 || numNodes > z * 4) {
					closedList.add(current);
					current = closedList.poll();
					String score = "Score was " + current.board.score
							+ " heuristic score was " + current.board.hScore;
					String gg = "Game over after expanding " + numNodes
							+ " nodes";

					GUI.Threes.printToFile(current.stringMoves(), score, gg,
							current.board);
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

	public static void main(String[] args) {
		String input = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Please enter an input filename location: ");
			input = br.readLine();
		} catch (IOException io) {
			io.printStackTrace();
		}

		GUI.Threes.readFile(input);
		GUI.Threes.set();

		ArrayList<String> test = almostAstar(GUI.Threes.board);
		String printMoves = "";
		while (!test.isEmpty()) {
			printMoves += test.remove(test.size() - 1);
		}
	}

}
