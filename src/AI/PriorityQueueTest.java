package AI;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args){
		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comparator);

		ArrayDeque moves = new ArrayDeque();
		
		Node a = new Node(120,8, moves);
		Node b = new Node(10,8, moves);
		Node c = new Node(55,6, moves);
		Node d = new Node(55,8, moves);
		queue.add(a);
		queue.add(b);
		queue.add(c);
		queue.add(d);
		
		while (queue.size() != 0)
        {
            System.out.println(queue.remove().whiteSpace);
        }
	}
	
}
