package 그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 큰수의법칙 {
	static int N, M, K;
	static Integer[] arr;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		arr = new Integer[N];
		ans = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, Collections.reverseOrder()); // 내림차순 정렬

//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}

		int first = (M / (K + 1) * K) + (M % (K + 1));
		int second = M - first;
		
		ans = arr[0] * first + arr[1] * second;
		
		System.out.println("first : " + first);
		System.out.println("second : " + second);
		System.out.println("result : " + ans);
	}

}
