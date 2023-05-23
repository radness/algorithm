package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSExam {
	public static void main(String[] args) {
		// BFS
		
		// Queue
		// ���� ���� ������ �� ������, ���� ���� ��� ������ �ȵȴ�.
		// ���� ó���� �����ߴ� �����͸� ���� �� �ִ�.
		// ���� ó���� �����ߴ� �����͸� ������ �� �ִ�.
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		q.add(3);
		
		System.out.println(q.peek());

		System.out.println(q.poll());
		
		Queue<Node> qNode = new LinkedList<>();
		
		qNode.add(new Node(1, 'A'));
		qNode.add(new Node(3, 'C'));
		qNode.add(new Node(2, 'D'));
		
		System.out.println("Queue ������� ���.");
		
		while (!qNode.isEmpty()) {
			Node node = qNode.poll();
			
			System.out.println("num : " + node.num + " ch : " + node.ch);
		}
	}
	
	private static class Node {
		int num;
		int ch;
		
		public Node(int num, int ch) {
			this.num = num;
			this.ch = ch;
		}
	}
}
