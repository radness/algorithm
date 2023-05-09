package bfs;

import java.util.LinkedList;
import java.util.Queue;

// BFS 기본 형태
public class BFSExam_basicSet {
	public static void main(String[] args) {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(3, 'A'));
		q.add(new Node(1, 'D'));
		q.add(new Node(4, 'F'));

		while (!q.isEmpty()) {
			Node now = q.poll();
			
			// 넣은 순서대로 출력.
			System.out.println("순번 : " + now.num + " 값 : " + now.ch);
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
