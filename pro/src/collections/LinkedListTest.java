package collections;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListTest {
	public static void main(String[] args) {
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(new Node(1, 2));
		
		Node node = queue.poll();
		
		System.out.println("목표 : " + node.target + " 비용 : " + node.cost);
	}
	
	private static class Node {
		int target;	// 목표
		int cost;	// 비용
		
		public Node(int target, int cost) { 
			this.target= target;
			this.cost = cost;
		}
	}
}
