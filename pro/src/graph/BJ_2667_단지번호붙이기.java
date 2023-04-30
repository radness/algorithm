package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2667_단지번호붙이기 {
	private static int N;
	private static char[][] map = new char[25][25];

	private static int[][] visit = new int[25][25]; // 방문 배열
	private static int num; // 단지번호

	private static int list[] = new int[625];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 입력값

		String input = null;

		// 단지 정보 입력
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		// 모든 집에 대해서 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1' && visit[i][j] == 0) { // 집 정보가 1이고 방문하지 않았으면
					num++; // 단지 번호를 증가
					bfs(i, j); // 단지 탐색
				}
			}
		}

		Arrays.sort(list, 1, num + 1);

		System.out.println(num);

		for (int i = 1; i <= num; i++) {
			System.out.println(list[i]);
		}

	}

	private static void bfs(int i, int j) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.offer(i * 100 + j); // 큐에 넣기

		int pos = 0; // 위치
		int count = 0; // 집의 수

		while (!queue.isEmpty()) {
			pos = queue.poll(); // 큐에서 꺼내서
			i = pos / 100;
			j = pos % 100; // 나머지

			if (visit[i][j] != 0) // 방문 여부 체크
				continue;

			visit[i][j] = num; // 방문 배열에 단지 번호 저장
			count++;

			// 탐색
			if (i > 0 && map[i - 1][j] == '1' && visit[i - 1][j] == 0) // 상
				// 단지가 있고 방문하지 않은 경우
				queue.offer((i - 1) * 100 + j);
			if (i < N - 1 && map[i + 1][j] == '1' && visit[i + 1][j] == 0) // 하
				queue.offer((i + 1) * 100 + j);
			if (j > 0 && map[i][j - 1] == '1' && visit[i][j - 1] == 0) // 좌
				queue.offer(i * 100 + (j - 1));
			if (j < N - 1 && map[i][j + 1] == '1' && visit[i][j + 1] == 0) // 우
				queue.offer(i * 100 + (j + 1));
		}

		list[num] = count; // 단지리스트에 단지별로 집의 수 입력
	}
}
