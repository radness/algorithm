package bfs;

import java.util.LinkedList;
import java.util.Queue;

/* BFS
 * Queue 자료구조
 * 값을 쉽게 저장하지만 넣은 값을 모두 읽으면 안된다.
 * 오로지 가장 처음에 저장했던 데이터만 읽을 수 있다.
 * 오로지 가장 처음에 저장했던 데이터만 삭제할 수 있다.
 * */
public class BFSExam {
	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		q.add(2);
		q.add(3);
		
		System.out.println(q.peek());	// 큐 읽기
		
		System.out.println(q.poll());	// 큐 읽기 & 삭제
		
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(3, "A"));
		queue.add(new Node(8, "D"));
		queue.add(new Node(1, "C"));
		queue.add(new Node(4, "Z"));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			System.out.println("인덱스: " + node.index + ", 문자: " + node.value);
		}
	}
	
	static class Node {
		int index;
		String value;
		
		public Node(int index, String value) {
			this.index = index;
			this.value = value;
		}
	}
}
