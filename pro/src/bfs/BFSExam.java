package bfs;

import java.util.LinkedList;
import java.util.Queue;

/* BFS
 * Queue �ڷᱸ��
 * ���� ���� ���������� ���� ���� ��� ������ �ȵȴ�.
 * ������ ���� ó���� �����ߴ� �����͸� ���� �� �ִ�.
 * ������ ���� ó���� �����ߴ� �����͸� ������ �� �ִ�.
 * */
public class BFSExam {
	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		q.add(2);
		q.add(3);
		
		System.out.println(q.peek());	// ť �б�
		
		System.out.println(q.poll());	// ť �б� & ����
		
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(3, "A"));
		queue.add(new Node(8, "D"));
		queue.add(new Node(1, "C"));
		queue.add(new Node(4, "Z"));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			System.out.println("�ε���: " + node.index + ", ����: " + node.value);
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
