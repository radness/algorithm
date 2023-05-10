package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2839_¼³ÅÁ¹è´Ş {
	static int N;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ans = 0;

		int ºÀÁö1 = 0;
		int ºÀÁö2 = 0;

		while (N % 5 != 0) {
			N = N - 3;
			ºÀÁö1++;

			if (N < 3 && N != 0) {
				ans = -1;
				System.out.println(ans);
				return;
			}
		}

		ºÀÁö2 = N / 5;

		ans = ºÀÁö1 + ºÀÁö2;

		System.out.println(ans);
	}
}
