package 그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_7576_토마토2 {
	static int N, M;
	static int[][] map;
	static int[][] visit;
	static int ans;
	static int day;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[M + 1][N + 1];
		visit = new int[M + 1][N + 1];

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		} // end input

		BFS();
	}

	public static void BFS() {
		Queue<Point7576> q = new LinkedList<Point7576>();
		ans = 0;

		// 익은 토마토(1)을 큐에 넣기.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					q.add(new Point7576(i, j, 0));
				}
			}
		}

		// bfs 탐색 시작
		while (!q.isEmpty()) {
			Point7576 p = q.poll();
			day = p.day;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
					if (map[nx][ny] == 0 && visit[nx][ny] == 0) {
						map[nx][ny] = 1;
						q.add(new Point7576(nx, ny, day + 1));
					}
				}
			}
		}
		if (check())
			System.out.println(day);
		else
			System.out.println(-1);
	}

	// map을 순회하면서 0이 있으면 false, 없으면 true
	private static boolean check() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}
}

class Point7576 {
	int x, y, day;

	Point7576(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
}