package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ������
// �׷���
public class BJ_14502 {
	static int N, M;
	static int[][] arr;
	static int[][] visited;
	static int[] dx = { 0, 1, 0, -1 };	// �����¿� ����
	static int[] dy = { -1, 0, 1, 0 };
	static int ans;
	
	// 0: ��ĭ, 1: ��, 2: ���̷���
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		visited = new int[N + 1][M + 1];
		ans = 0;	// ���������� ũ��

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input
		
		dfs(0);
		
		System.out.println(ans);
	}

	private static void dfs(int wallCnt) {
		if (wallCnt == 3) {
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] == 0) {
					
				}
			}
		}

		
	}
}
