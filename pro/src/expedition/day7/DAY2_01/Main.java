package expedition.day7.DAY2_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 노드
		int T = Integer.parseInt(st.nextToken()); // 간선

		map = new int[N + 1][N + 1];

		ArrayList<Integer>[] alist = new ArrayList[N+1];

		for (int i = 0; i <= N; i++) {
			alist[i] = new ArrayList<>();
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			map[start][end] = 1;
//			map[end][start] = 1;

			alist[start].add(end);
//			alist[end].add(start);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
