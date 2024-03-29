package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 로봇 청소기
public class BJ_14503_bk {
	static int N, M;
	static int[][] arr;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()); // 좌표 y
		int c = Integer.parseInt(st.nextToken()); // 좌표 x
		int d = Integer.parseInt(st.nextToken()); // 방향, 북동남서 0, 1, 2, 3

		arr = new int[N + 2][M + 2];

		for (int i = 0; i <= N + 1; i++) {
			Arrays.fill(arr[i], 2);
		}

		ans = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(r + 1, c + 1, d, ans);

		System.out.println(ans);

	}

	private static void bfs(int y, int x, int dir, int cnt) {
		// 작동 조건 1
		if (arr[y][x] == 0) { // 청소하지 않았으면
			arr[y][x] = 1; // 청소 표시
			cnt++; // 청소
		}

		// 작동 조건 2
		// 북: y-1, x
		// 동: y, x+1
		// 남: y+1, x
		// 서: y, x-1
		if (arr[y - 1][x] != 0 && arr[y][x + 1] != 0 && arr[y + 1][x] != 0 && arr[y][x - 1] != 0) {
			if (dir == 0) { // 북
				if (arr[y + 1][x] != 2) {
					bfs(y + 1, x, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 1) { // 동
				if (arr[y][x - 1] != 2) {
					bfs(y, x - 1, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 2) { // 남
				if (arr[y - 1][x] != 2) {
					bfs(y - 1, x, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 3) { // 서
				if (arr[y][x + 1] != 2) {
					bfs(y, x + 1, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			}
		}

		// 작동 조건3
		if (arr[y - 1][x] == 0 || arr[y][x + 1] == 0 || arr[y + 1][x] == 0 || arr[y][x - 1] == 0) {
			// 반시계 방향 회전
			if (dir == 0) { // 서
				dir = 3;
			} else if (dir == 1) { // 북
				dir = 0;
			} else if (dir == 2) { // 동
				dir = 1;
			} else if (dir == 3) { // 남
				dir = 2;
			}

			// 북동남서 -> 서북동남
			// 한 칸 전진
			if (dir == 3) { // 서
				if (arr[y][x - 1] == 0) {
					bfs(y, x - 1, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 0) { // 북
				if (arr[y - 1][x] == 0) {
					bfs(y - 1, x, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 1) { // 동
				if (arr[y][x + 1] == 0) {
					bfs(y, x + 1, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 2) { // 남
				if (arr[y + 1][x] == 0) {
					bfs(y + 1, x, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			}
		}

	}
}
