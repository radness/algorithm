package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2747_피보나치수 {
	static long count = 0;
	static long[] fibo;

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(br.readLine());

		count = fibonacci(input);

		long end = System.currentTimeMillis();

		System.out.println("count: " + count + " time: " + (end - start));
	}

	private static long fibonacci(int input) {
		fibo = new long[input + 1];
		fibo[0] = 1;
		fibo[1] = 1;

		for (int i = 2; i <= input; i++) {
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		}

		return fibo[input];
	}
}
