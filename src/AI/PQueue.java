package AI;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQueue<Node> extends PriorityQueue<Node>{
	
	int initialCapacity;
	Comparator comparator;
	public PQueue(int initialCapacity,Comparator comparator){
		this.comparator=comparator;
		this.initialCapacity=initialCapacity;
		
	}
	
	public boolean has(Node a){
		
		
		
		return false;
	}

}
