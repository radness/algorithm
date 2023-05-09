package bfs;

import java.util.LinkedList;
import java.util.Queue;

// BFS �⺻ ����
public class BFSExam_basicSet {
	public static void main(String[] args) {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(3, 'A'));
		q.add(new Node(1, 'D'));
		q.add(new Node(4, 'F'));

		while (!q.isEmpty()) {
			Node now = q.poll();
			
			// ���� ������� ���.
			System.out.println("���� : " + now.num + " �� : " + now.ch);
		}
	}

	static class Node {
		int num;
		char ch;

		public Node(int num, char ch) {
			this.num = num;
			this.ch = ch;
		}
	}
}
