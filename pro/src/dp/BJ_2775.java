package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2775 {
	static int T;
	static int K;
	static int N;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			ans = 0;

			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());

			ans = findPerson(K, N);

			System.out.println(ans);
		}

	}

	private static int findPerson(int K, int N) {
		if (N == 0)
			return 0;
		else if (K == 0)
			return N;
		else
			return findPerson(K, N - 1) + findPerson(K - 1, N);
	}
}
