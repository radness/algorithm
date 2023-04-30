package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 입력
//6
//10 20 10 30 20 50
public class BJ_11053 {

	static int[] d;
	static int[] data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		data = new int[N];
		d = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		// 구현
		for (int i = 0; i < N; i++) {
			int sum = 0;

			for (int j = 0; j < i; j++) {
				if (data[i] > data[j]) {
					sum = Math.max(sum, d[j]);
				}
			}

			d[i] = sum + 1;
		}

		int ans = 0;

		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, d[i]);
		}

		System.out.println(ans);
	}
}
