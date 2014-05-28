package AI;

import java.util.Comparator;

public class BestMoveComparator implements Comparator<Node> {

	public int compare(Node o1, Node o2) {

		if (o1.hScore > o2.hScore) {
			return -1;
		} else if (o1.hScore == o2.hScore) {

			return 0;

		} else
			return 1;
	}

}
