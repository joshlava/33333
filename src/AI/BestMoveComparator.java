package AI;

import java.util.Comparator;

public class BestMoveComparator implements Comparator<Node>{

	public int compare(Node o1, Node o2) {

		if(o1.big > o2.big){
			return -1;
		} else if(o1.big == o2.big){
			if(o1.score > o2.score){
				return -1;
			} else if(o1.score == o2.score){
				if(o1.whiteSpace > o2.whiteSpace){
					return -1;
				} else if(o2.whiteSpace == o1.whiteSpace){
					return 0;
				} else return 1;
			} else
				return 1;
		} else
			return 1;
	}

}
