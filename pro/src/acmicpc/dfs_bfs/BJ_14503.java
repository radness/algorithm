package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 로봇 청소기
public class BJ_14503 {
	static int N, M;
	static int[][] arr;
	static int ans;
	static int[] dY = { -1, 0, 1, 0 }; // 북동남서
	static int[] dX = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()); // 좌표 y
		int c = Integer.parseInt(st.nextToken()); // 좌표 x
		int d = Integer.parseInt(st.nextToken()); // 방향, 북동남서 0, 1, 2, 3

		arr = new int[N][M];
		ans = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(r, c, d);

		System.out.println(ans);

	}

	private static void dfs(int y, int x, int dir) {
		// 작동 조건 1
		arr[y][x] = -1; // 현재 위치를 청소한다.

		// 현재 위치에서 차례대로 탐색
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4; // 북서남동(반시계 방향)
			int nowY = y + dY[dir];
			int nowX = x + dX[dir];

			// 청소가 안된 곳이 있으면 cnt++, dfs
			if (nowY >= 0 && nowY < N && nowX >= 0 && nowX < M && arr[nowY][nowX] == 0) {
				ans++;
				dfs(nowY, nowX, dir);

				// 리턴을 하지 않으면 복귀 하는 도중 뒤로가서 다른 곳을 청소할 수 있다.
				return;
			}
		}

		// 작동 조건3
		// 모두 청소가 되어 있거나 벽인 경우에는
		// 벽이 아니면 바라보는 방향대로 한칸 후진
		int back = (dir + 2) % 4;
		int backY = y + dY[back];
		int backX = x + dX[back];

		if (backY >= 0 && backY < N && backX >= 0 && backX < M && arr[backY][backX] != 1) {
			dfs(backY, backX, dir);
		}

	}
}
