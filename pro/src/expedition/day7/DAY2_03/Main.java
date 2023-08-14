package expedition.day7.DAY2_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[] arr;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N]; // 노드 갯수

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		map = new int[N][N]; // 인접 행렬

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0번 노드에서 탐색
		bfs(0);

	}

	private static void bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		int[] visited = new int[N];
		visited[start] = 1;

		while (!q.isEmpty()) {
			int now = q.remove();

			System.out.print(arr[now] + " ");

			for (int next = 0; next < N; next++) {
				if (map[now][next] == 0)
					continue;

				if (visited[next] == 1)
					continue;

				visited[next] = 1;
				q.add(next);
			}
		}
	}
}
