package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �κ� û�ұ�
public class BJ_14503 {
	static int N, M;
	static int[][] arr;
	static int ans;
	static int[] dY = { -1, 0, 1, 0 }; // �ϵ�����
	static int[] dX = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()); // ��ǥ y
		int c = Integer.parseInt(st.nextToken()); // ��ǥ x
		int d = Integer.parseInt(st.nextToken()); // ����, �ϵ����� 0, 1, 2, 3

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
		// �۵� ���� 1
		arr[y][x] = -1; // ���� ��ġ�� û���Ѵ�.

		// ���� ��ġ���� ���ʴ�� Ž��
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4; // �ϼ�����(�ݽð� ����)
			int nowY = y + dY[dir];
			int nowX = x + dX[dir];

			// û�Ұ� �ȵ� ���� ������ cnt++, dfs
			if (nowY >= 0 && nowY < N && nowX >= 0 && nowX < M && arr[nowY][nowX] == 0) {
				ans++;
				dfs(nowY, nowX, dir);

				// ������ ���� ������ ���� �ϴ� ���� �ڷΰ��� �ٸ� ���� û���� �� �ִ�.
				return;
			}
		}

		// �۵� ����3
		// ��� û�Ұ� �Ǿ� �ְų� ���� ��쿡��
		// ���� �ƴϸ� �ٶ󺸴� ������ ��ĭ ����
		int back = (dir + 2) % 4;
		int backY = y + dY[back];
		int backX = x + dX[back];

		if (backY >= 0 && backY < N && backX >= 0 && backX < M && arr[backY][backX] != 1) {
			dfs(backY, backX, dir);
		}

	}
}
