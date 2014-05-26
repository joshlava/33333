package AI;
import java.util.Comparator;

public class BestMoveComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.score < o2.score)
			return 1;
		else if(o1.score > o2.score)
			return -1;
		else return 0;
	}



}
