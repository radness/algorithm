package pro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRO_230120_�����Ա�2 {
	static int T; // �׽�Ʈ ���̽�
	static int H, W;
	static long[][][] dp;
	static long[][] score; // �� �� ����
	static long[] leftMax;
	static long[] rightMax;
	static int ans;
	static boolean[][] visit; // �湮 �迭
	static long INF = -1_000_000_000_000_000l;

	public static void main(String[] args) throws Exception {
		System.out.println(INF);
		Long start = System.currentTimeMillis();
//		System.setIn(new FileInputStream("src/pro/PRO_230120_�����Ա�"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken()); // ����
			W = Integer.parseInt(st.nextToken()); // ����

			score = new long[H + 1][W + 1];
			visit = new boolean[H + 1][W + 1];
			dp = new long[H + 1][W + 1][2]; // ����Ʈ�� ����

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
			

			for (int j = 1; j <= W; j++) { // j: ����
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
		visit[i][j] = true; // �湮 ǥ��

		
	}
}
