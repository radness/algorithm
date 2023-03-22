package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRO_230120_�����Ա� {
	static long INF = -2_000_000_000_000_000_000l;
	private static long[][][] dp;
	private static long[] rightmax;
	private static long[] leftmax;
	private static int ����;
	private static int ����;

	public static void main(String[] args) throws Exception {
		Long start = System.currentTimeMillis();
		System.setIn(new FileInputStream(new File("src/pro/PRO_230120_�����Ա�")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			���� = Integer.parseInt(st.nextToken());
			���� = Integer.parseInt(st.nextToken());
			dp = new long[���� + 2][���� + 2][2];
			rightmax = new long[���� + ���� + 4];
			leftmax = new long[���� + ���� + 4];
			long[][] map = new long[���� + 2][���� + 2];
			for (int y = 1; y <= ����; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 1; x <= ����; x++) {
					map[y][x] = Long.parseLong(st.nextToken());
				}
			}
			Arrays.fill(leftmax, INF);
			Arrays.fill(rightmax, INF);
			for (int y = 0; y <= ���� + 1; y++) {
				for (int x = 0; x <= ���� + 1; x++) {
					dp[y][x][0] = INF;
					dp[y][x][1] = INF;
				}
			}
			for (int x = 1; x <= ����; x++) {
				set(1, x, map[1][x]);
			}
			for (int y = 2; y <= ����; y++) {
				for (int x = 1; x <= ����; x++) {
					set2(y, x, leftmax[x + y] + map[y][x]);
					set2(y, x, rightmax[(���� - x) + y] + map[y][x]);
					if (dp[y - 1][x - 1][0] != INF)
						set(y, x, dp[y - 1][x - 1][0] + map[y][x]);
					if (dp[y - 1][x + 0][0] != INF)
						set(y, x, dp[y - 1][x + 0][0] + map[y][x]);
					if (dp[y - 1][x + 1][0] != INF)
						set(y, x, dp[y - 1][x + 1][0] + map[y][x]);
					if (dp[y - 1][x - 1][1] != INF)
						set2(y, x, dp[y - 1][x - 1][1] + map[y][x]);
					if (dp[y - 1][x + 0][1] != INF)
						set2(y, x, dp[y - 1][x + 0][1] + map[y][x]);
					if (dp[y - 1][x + 1][1] != INF)
						set2(y, x, dp[y - 1][x + 1][1] + map[y][x]);
				}
			}
			long answer = INF;
			for (int x = 1; x <= ����; x++) {
				answer = Math.max(answer, dp[����][x][0]);
				answer = Math.max(answer, dp[����][x][1]);
			}
			System.out.println("#" + testcase + " " + answer);
		}
		System.out.println("time(ms)=" + (System.currentTimeMillis() - start));
	}

	private static void set(int y, int x, long value) {
		if (dp[y][x][0] < value) {
			dp[y][x][0] = value;
			if (leftmax[x + y] < value)
				leftmax[x + y] = value;
			if (rightmax[(���� - x) + y] < value)
				rightmax[(���� - x) + y] = value;
		}
	}

	private static void set2(int y, int x, long value) {
		if (dp[y][x][1] < value)
			dp[y][x][1] = value;
	}
}
