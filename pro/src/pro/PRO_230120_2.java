package pro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRO_230120_땅따먹기2 {
	static int T; // 테스트 케이스
	static int H, W;
	static long[][][] dp;
	static long[][] score; // 땅 별 점수
	static long[] leftMax;
	static long[] rightMax;
	static int ans;
	static boolean[][] visit; // 방문 배열
	static long INF = -1_000_000_000_000_000l;

	public static void main(String[] args) throws Exception {
		System.out.println(INF);
		Long start = System.currentTimeMillis();
//		System.setIn(new FileInputStream("src/pro/PRO_230120_땅따먹기"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken()); // 세로
			W = Integer.parseInt(st.nextToken()); // 가로

			score = new long[H + 1][W + 1];
			visit = new boolean[H + 1][W + 1];
			dp = new long[H + 1][W + 1][2]; // 포인트별 방향

			leftMax = new long[H + W + 1];
			rightMax = new long[H + W + 1];
			Arrays.fill(leftMax, INF);
			Arrays.fill(rightMax, INF);

			ans = 0;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 1; j <= W ; j++) {
				set(1, j, score[1][j]);
			}
			

			for (int j = 1; j <= W; j++) { // j: 가로
				findMaxValue(1, j, score[1][j]);
			}

			System.out.println("#" + tc + " " + ans);
		}

		System.out.println("time(ms)=" + (System.currentTimeMillis() - start));
	}

	private static void set(int i, int j, long val) {
		if (dp[i][j][0] < val) {
			dp[i][j][(int) val] = val;

			if (leftMax[i + j] < val)
				leftMax[i + j] = val;
			
			if (rightMax[(W-i) + j] < val)
				rightMax[(W-i)+j] = val;
		}

	}

	private static void findMaxValue(int i, int j, long val) {
		visit[i][j] = true; // 방문 표시

		
	}
}
