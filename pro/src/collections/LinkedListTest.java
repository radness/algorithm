package collections;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListTest {
	public static void main(String[] args) {
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(new Node(1, 2));
		
		Node node = queue.poll();
		
		System.out.println("��ǥ : " + node.target + " ��� : " + node.cost);
	}
	
	private static class Node {
		int target;	// ��ǥ
		int cost;	// ���
		
		public Node(int target, int cost) { 
			this.target= target;
			this.cost = cost;
		}
	}
}
