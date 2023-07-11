package expedition.day2.DAY2_02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연쇄 폭탄
public class Main {

	static int[][] map;
	static int[] dY = { -1, 0, 1, 0 };
	static int[] dX = { 0, 1, 0, -1 };
	static int N;
	static int totalSize;
	static int start;
	static int ans;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/expedition/day2/DAY2_02/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		start = 1;
		ans = 0;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		totalSize = N * N;
		int cnt = 0;

		findTime(start, cnt);

		System.out.println(ans + "초");
	}

	private static void findTime(int start, int cnt) {
		// 폭탄 터트리기
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == start) {
					if (map[y][x] != -1) {
						map[y][x] = -1;
						cnt++;
					}

					for (int i = 0; i < 4; i++) {
						int nY = y + dY[i];
						int nX = x + dX[i];

						if (nY < 0 || nX < 0 || nY >= N || nX >= N)
							continue;

						if (map[nY][nX] != -1) {
							map[nY][nX] = -1;
							cnt++;
						}
					}

				}
			}
		}

		// 정답 찾기
		if (cnt == totalSize) {
			ans = start;
			return;
		}

		findTime(start + 1, cnt);

	}
}
