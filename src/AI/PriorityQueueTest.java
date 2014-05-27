package AI;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args){
		Comparator<Node> comparator = new BestMoveComparator();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comparator);

		ArrayDeque moves = new ArrayDeque();
		
		Node a = new Node(8,120, moves);
		Node b = new Node(8,10, moves);
		Node c = new Node(6,55, moves);
		Node d = new Node(8,55, moves);
		Node e=new Node(8,55, moves);
		queue.add(a);
		queue.add(b);
		queue.add(c);
		queue.add(d);
	/*	b = new Node(66,58,moves);
		queue.add(b);*/
		if(e.equals(d)){
			System.out.println("Contains");
		}else
			System.out.println("Does not ");
		while (queue.size() != 0)
        {
            System.out.println(queue.remove().score);
        }
	}
	
}