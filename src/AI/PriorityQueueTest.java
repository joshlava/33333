package AI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args){
		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comparator);

		Node a = new Node(10);
		Node b = new Node(23);
		Node c = new Node(14);
		Node d = new Node(55);
		queue.add(a);
		queue.add(b);
		queue.add(c);
		queue.add(d);
		
		while (queue.size() != 0)
        {
            System.out.println(queue.remove().score);
        }
	}
	
}
