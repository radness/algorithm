package basic.priority_queue;

import java.util.PriorityQueue;

public class PriorityQueueExam3 {
	public static void main(String[] args) {

		/*
		 * PQ 사용 예 많은 값 중에 우선순위 높은것 3개만 뽑는다.
		 * 
		 * 우선순위가 높은것을 빨리 뽑아내기 위해서 사용 시간복잡도 값 추가 add() : O(logN) 값 읽기 peek() : O(1) 값 제거
		 * poll() : O(logN)
		 */
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		// // 역순 정렬

		pq.add(1);
		pq.add(6);
		pq.add(3);
		pq.add(5);

		while (!pq.isEmpty()) {
			int num = pq.poll();
			System.out.println(num);
		}

		System.out.println();
		
		// 1. 작은 키 우선
		// 2. 고령자 우선
		PriorityQueue<Node> pq2 = new PriorityQueue<>();
		pq2.add(new Node(30, 180));
		pq2.add(new Node(32, 180));
		pq2.add(new Node(18, 175));
		pq2.add(new Node(36, 182));
		
		while (!pq2.isEmpty()) {
			Node node = pq2.poll();
			
			System.out.println(node.age + " " + node.height);
		}
	}
}

class Node implements Comparable<Node> {
	int age;
	int height;

	public Node(int age, int height) {
		this.age = age;
		this.height = height;
	}

	@Override
	public int compareTo(Node other) {
		// 1. 작은 키 우선
		if (this.height > other.height) return -1;
		if (this.height < other.height) return 1;
		// 2. 고령자 우선
		if (this.age > other.age) return -1;
		if (this.age < other.age) return 1;
		
		return 0;
	}

}