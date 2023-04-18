package basic.priority_queue;

import java.util.PriorityQueue;

public class PriorityQueueExam3 {
	public static void main(String[] args) {

		/*
		 * PQ ��� �� ���� �� �߿� �켱���� ������ 3���� �̴´�.
		 * 
		 * �켱������ �������� ���� �̾Ƴ��� ���ؼ� ��� �ð����⵵ �� �߰� add() : O(logN) �� �б� peek() : O(1) �� ����
		 * poll() : O(logN)
		 */
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		// // ���� ����

		pq.add(1);
		pq.add(6);
		pq.add(3);
		pq.add(5);

		while (!pq.isEmpty()) {
			int num = pq.poll();
			System.out.println(num);
		}

		System.out.println();
		
		// 1. ���� Ű �켱
		// 2. ����� �켱
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
		// 1. ���� Ű �켱
		if (this.height > other.height) return -1;
		if (this.height < other.height) return 1;
		// 2. ����� �켱
		if (this.age > other.age) return -1;
		if (this.age < other.age) return 1;
		
		return 0;
	}

}