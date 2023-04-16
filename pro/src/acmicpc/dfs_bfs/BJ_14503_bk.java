package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ·Îº¿ Ã»¼Ò±â
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

		int r = Integer.parseInt(st.nextToken()); // ÁÂÇ¥ y
		int c = Integer.parseInt(st.nextToken()); // ÁÂÇ¥ x
		int d = Integer.parseInt(st.nextToken()); // ¹æÇâ, ºÏµ¿³²¼­ 0, 1, 2, 3

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
		// ÀÛµ¿ Á¶°Ç 1
		if (arr[y][x] == 0) { // Ã»¼ÒÇÏÁö ¾Ê¾ÒÀ¸¸é
			arr[y][x] = 1; // Ã»¼Ò Ç¥½Ã
			cnt++; // Ã»¼Ò
		}

		// ÀÛµ¿ Á¶°Ç 2
		// ºÏ: y-1, x
		// µ¿: y, x+1
		// ³²: y+1, x
		// ¼­: y, x-1
		if (arr[y - 1][x] != 0 && arr[y][x + 1] != 0 && arr[y + 1][x] != 0 && arr[y][x - 1] != 0) {
			if (dir == 0) { // ºÏ
				if (arr[y + 1][x] != 2) {
					bfs(y + 1, x, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 1) { // µ¿
				if (arr[y][x - 1] != 2) {
					bfs(y, x - 1, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 2) { // ³²
				if (arr[y - 1][x] != 2) {
					bfs(y - 1, x, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			} else if (dir == 3) { // ¼­
				if (arr[y][x + 1] != 2) {
					bfs(y, x + 1, dir, cnt);
				} else {
					ans = cnt;
					return;
				}
			}
		}

		// ÀÛµ¿ Á¶°Ç3
		if (arr[y - 1][x] == 0 || arr[y][x + 1] == 0 || arr[y + 1][x] == 0 || arr[y][x - 1] == 0) {
			// ¹Ý½Ã°è ¹æÇâ È¸Àü
			if (dir == 0) { // ¼­
				dir = 3;
			} else if (dir == 1) { // ºÏ
				dir = 0;
			} else if (dir == 2) { // µ¿
				dir = 1;
			} else if (dir == 3) { // ³²
				dir = 2;
			}

			// ºÏµ¿³²¼­ -> ¼­ºÏµ¿³²
			// ÇÑ Ä­ ÀüÁø
			if (dir == 3) { // ¼­
				if (arr[y][x - 1] == 0) {
					bfs(y, x - 1, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 0) { // ºÏ
				if (arr[y - 1][x] == 0) {
					bfs(y - 1, x, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 1) { // µ¿
				if (arr[y][x + 1] == 0) {
					bfs(y, x + 1, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			} else if (dir == 2) { // ³²
				if (arr[y + 1][x] == 0) {
					bfs(y + 1, x, dir, cnt);
				} else {
					bfs(y, x, dir, cnt);
				}
			}
		}

	}
}
