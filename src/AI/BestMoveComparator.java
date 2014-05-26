package AI;

import java.util.Comparator;

public class BestMoveComparator implements Comparator<Node>{

	public int compare(Node o1, Node o2) {
		if(o1.whiteSpace>5){
			if(o1.score>o2.score)
				return -1;
			else 
				return 1;
		}else
		if(o1.whiteSpace==o2.whiteSpace){
			if(o1.score>o2.score)
				return -1;
			else 
				return 1;
		}
		if(o1.whiteSpace < o2.whiteSpace)
			return 1;
		else if(o1.whiteSpace > o2.whiteSpace)
			return -1;
		else return 0;
	}



}
