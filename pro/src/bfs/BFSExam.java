package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSExam {
	public static void main(String[] args) {
		// BFS
		
		// Queue
		// 값을 쉽게 저장할 수 있지만, 넣은 값을 모두 읽으면 안된다.
		// 가장 처음에 저장했던 데이터만 읽을 수 있다.
		// 가장 처음에 저장했던 데이터만 삭제할 수 있다.
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		q.add(3);
		
		System.out.println(q.peek());

		System.out.println(q.poll());
		
		Queue<Node> qNode = new LinkedList<>();
		
		qNode.add(new Node(1, 'A'));
		qNode.add(new Node(3, 'C'));
		qNode.add(new Node(2, 'D'));
		
		System.out.println("Queue 순서대로 출력.");
		
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
