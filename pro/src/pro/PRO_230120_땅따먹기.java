package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRO_230120_땅따먹기 {
	static long INF = -2_000_000_000_000_000_000l;
	private static long[][][] dp;
	private static long[] rightmax;
	private static long[] leftmax;
	private static int 세로;
	private static int 가로;

	public static void main(String[] args) throws Exception {
		Long start = System.currentTimeMillis();
		System.setIn(new FileInputStream(new File("src/pro/PRO_230120_땅따먹기")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			세로 = Integer.parseInt(st.nextToken());
			가로 = Integer.parseInt(st.nextToken());
			dp = new long[세로 + 2][가로 + 2][2];
			rightmax = new long[세로 + 가로 + 4];
			leftmax = new long[세로 + 가로 + 4];
			long[][] map = new long[세로 + 2][가로 + 2];
			for (int y = 1; y <= 세로; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 1; x <= 가로; x++) {
					map[y][x] = Long.parseLong(st.nextToken());
				}
			}
			Arrays.fill(leftmax, INF);
			Arrays.fill(rightmax, INF);
			for (int y = 0; y <= 세로 + 1; y++) {
				for (int x = 0; x <= 가로 + 1; x++) {
					dp[y][x][0] = INF;
					dp[y][x][1] = INF;
				}
			}
			for (int x = 1; x <= 가로; x++) {
				set(1, x, map[1][x]);
			}
			for (int y = 2; y <= 세로; y++) {
				for (int x = 1; x <= 가로; x++) {
					set2(y, x, leftmax[x + y] + map[y][x]);
					set2(y, x, rightmax[(가로 - x) + y] + map[y][x]);
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
			for (int x = 1; x <= 가로; x++) {
				answer = Math.max(answer, dp[세로][x][0]);
				answer = Math.max(answer, dp[세로][x][1]);
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
			if (rightmax[(가로 - x) + y] < value)
				rightmax[(가로 - x) + y] = value;
		}
	}

	private static void set2(int y, int x, long value) {
		if (dp[y][x][1] < value)
			dp[y][x][1] = value;
	}
}
