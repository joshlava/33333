package AI;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ListIterator;
public class Search {
	
	public static PriorityQueue<Node> OpenList;
	public static int numNodes;
public static double almostAstar(int [][] board){
	Comparator<Node> comparator = new BestMoveComparator();
	OpenList =new PriorityQueue<Node>(15,comparator);
	numNodes=0;
	int [][] newGame = board;
	Node first= new node(board);
}

}
