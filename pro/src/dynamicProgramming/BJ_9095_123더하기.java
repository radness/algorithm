package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9095_123¥ı«œ±‚ {
	static int N;
	static int T;
	static int[] dp;
	static int[] ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		ans = new int[T + 1];
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			dp = new int[12];
			
			ans[tc] = searchCase(N);
		}
		
		for (int i = 1; i <= T; i++) {
			System.out.println(ans[i]);
		}
	}

	private static int searchCase(int num) {
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for (int i = 4; i <= num; i++) {
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		
		return dp[num];
	}
}
