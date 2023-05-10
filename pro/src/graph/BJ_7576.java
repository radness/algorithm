package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576 {
	private static int N;
	private static int M;
	private static int[][] visit; // �湮
	private static int[][] box; // ����

	static int answer;

	static Queue<Node> queue = new LinkedList<Node>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visit = new int[N][M];
		box = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.valueOf(st.nextToken());

				if (box[i][j] == 1) {
					Node node = new Node(i, j);
					queue.offer(node);
				}
			}
		}

		bfs();
	}

	private static void bfs() {
		int i = 0;
		int j = 0;

		Node node = null;

		while (!queue.isEmpty()) {
			node = queue.poll();
			i = node.x;
			j = node.y;

			box[i][j] = 1;

			// �����¿� Ž��
			// visit �迭���� 0�� �湮���� ���� �迭, 1�� �湮�� �迭
			if (i > 0 && box[i - 1][j] == 0 && visit[i - 1][j] == 0) { // ��
				visit[i - 1][j] = visit[i][j] + 1;
				Node next = new Node(i - 1, j);
				queue.offer(next);	// ���� �湮�� ������ NodeŬ������ ����� queue�� �ִ´�.
			}
			if (i < N - 1 && box[i + 1][j] == 0 && visit[i + 1][j] == 0) { // ��
				visit[i + 1][j] = visit[i][j] + 1;
				Node next = new Node(i + 1, j);
				queue.offer(next);
			}
			if (i > 0 && box[i][j - 1] == 0 && visit[i][j - 1] == 0) { // ��
				visit[i][j - 1] = visit[i][j] + 1;
				Node next = new Node(i, j - 1);
				queue.offer(next);
			}
			if (i < M - 1 && box[i][j + 1] == 0 && visit[i][j + 1] == 0) { // ��
				visit[i][j + 1] = visit[i][j] + 1;
				Node next = new Node(i, j + 1);
				queue.offer(next);
			}
		}
	}
	
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}