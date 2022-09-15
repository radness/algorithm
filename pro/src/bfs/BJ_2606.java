package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2606번 문제
// BFS : 너비 우선 탐색
public class BJ_2606 {
	private static int N = 0;
	private static int M = 0;
	private static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		StringTokenizer st;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			arr[X][Y] = arr[Y][X] = 1;
		}

		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(1);
		visited[1] = true;
		
		int ANSWER = 0;

		while (!queue.isEmpty()) {
			int tmp = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				if (arr[tmp][i] == 1 && !visited[i]) {
					visited[i] = true;
					queue.add(i);
					ANSWER++;
				}
			}
		}
		
		// answer
		System.out.println(ANSWER);

	}
}
