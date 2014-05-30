package AI;

import java.util.Comparator;

/*
 * Nicolas La Verghetta 20762905
 * Andrew Edwards 20937641
 * A class to compare different moves against each other using the heuristic function score as the item to be compared for use in a sorting algorithm.
 */

public class BestMoveComparator implements Comparator<Node> {

	/*
	 * A function to compare two different nodes
	 * 
	 * @param o1 the first node
	 * 
	 * @param o2 the second node
	 * 
	 * @return an int representing a numerical value to be used for sorting.
	 */
	public int compare(Node o1, Node o2) {

		if (o1.board.getHScore() > o2.board.getHScore()) {
			return -1;
		} else if (o1.board.getHScore() == o2.board.getHScore()) {

			return 0;

		} else
			return 1;
	}

}
