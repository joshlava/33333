package AI;

import java.util.Comparator;

public class BestMoveComparator implements Comparator<Node>{

	public int compare(Node o1, Node o2) {
		if(o1.whiteSpace < o2.whiteSpace)
			return 1;
		else if(o1.whiteSpace > o2.whiteSpace)
			return -1;
		else return 0;
	}



}
