package basic.priority;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ComparatorExam {
	public static void main(String[] args) {

		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.charAt(1) < o2.charAt(1))
					return -1;
				if (o1.charAt(1) > o2.charAt(1))
					return 1;
				return 0;
				
//				return o1.compareTo(o2); // 오름차순
//				return o2.compareTo(o1); // 내림차순
			}
		});
		
		pq.add("bwfd");
		pq.add("brsd");
		pq.add("sdfg");
		pq.add("asdd");

		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}
