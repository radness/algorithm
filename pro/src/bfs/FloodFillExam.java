package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFillExam {

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		int SIZE = 3;
		int[][] arr = { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		int[] dY = { 1, -1, 0, 0 };
		int[] dX = { 0, 0, 1, -1 };

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));

		while (!q.isEmpty()) {
			Node now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nY = now.y + dY[i];
				int nX = now.x + dX[i];

				if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE)
					continue;

				if (arr[nY][nX] != 0)
					continue; // 이미 탐색한 좌표면 후보지 등록 제외

				arr[nY][nX] = arr[now.y][now.x] + 1;
				q.add(new Node(nY, nX));
			}
		}

		// 출력
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(arr[y][x] + " ");
			}
			System.out.println();
		}

	}
}
