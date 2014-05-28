package AI;

import java.util.ArrayList;

public class ListCompTest {

	
	
	public static void main(String[] args){
		ArrayList<Integer> zero= new ArrayList<Integer>();
		ArrayList<Integer> one= new ArrayList<Integer>();
		ArrayList<Integer> two= new ArrayList<Integer>();
		ArrayList<Integer> three= new ArrayList<Integer>();
		
		zero.add(-1);
		zero.add(10);
		zero.add(0);
		zero.add(0);
		zero.add(0);

		
		one.add(1);
		one.add(1);
		one.add(1);
		one.add(1);
		one.add(1);

		two.add(1);
		two.add(2);
		two.add(2);
		two.add(2);
		two.add(2);
		
		three.add(1);
		three.add(3);
		three.add(3);
		three.add(3);
		three.add(3);


		
		

		
		int res= ListComp(zero,one,two,three);
			
		switch (res){
		case 0: 
			System.out.println("zero is the smallest");
			break;
		case 1: 
			System.out.println("one is the smallest");
			break;
		case 2: 
			System.out.println("two is the smallest");
			break;
		case 3:
			System.out.println("three is the smallest");
			break;
		}
		
		

	}

	public static int ListComp(ArrayList<Integer> a, ArrayList<Integer> b,ArrayList<Integer> c,ArrayList<Integer> d){
		int start=0;
		int low=0;

		ArrayList<Integer> lowest=new ArrayList<Integer>();
		if(a.get(0)==1)
			 lowest= (ArrayList<Integer>) a.clone();
		else if(b.get(0)==1){
			lowest= (ArrayList<Integer>) b.clone();
			low=1;
			start =1;
		}else if(c.get(0)==1){
			lowest=(ArrayList<Integer>) c.clone();
			low=2;
			start=2;
		}else
			low=3;
		
		if(start==0){
			for (int i =1;i<a.size();i++){
				if(a.get(i) >b.get(i)){
					lowest=(ArrayList<Integer>) b.clone();
					low=1;
					break;
				}else if(a.get(i)<b.get(i)){
					break;				
	
				}
			}
			start++;
		}
		if(start>0){
			for (int i=1;i<5;i++){
				if(lowest.get(i)>c.get(i)){
					lowest=(ArrayList<Integer>) c.clone();
					low=2;
					break;
				}else if(lowest.get(i)<c.get(i)){
					break;
				}
			}
			start++;
		}
		if(start>1){
			for (int i=1;i<5;i++){
				if(lowest.get(i)>d.get(i)){
					lowest=(ArrayList<Integer>) d.clone();
					low=3;
					break;
				}else if(lowest.get(i)<d.get(i)){
					break;
				}
			}
		}
		
		return low;

			
	}
}
