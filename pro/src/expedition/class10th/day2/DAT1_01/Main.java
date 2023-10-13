package expedition.class10th.secondary.DAT1_01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/expedition/class10th/secondary/DAT1_01/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int input = Integer.parseInt(st.nextToken());
			
			boolean flag = find(input);

			if (flag) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}
		System.out.println(sb.toString());
	}

	private static boolean find(int input) {
		int left = 1;
		int right = N;
		int mid = 0;
		
		while (left <= right) {
			mid = (left + right) / 2;
			if (arr[mid] <= input) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
			
			if (arr[mid] == input) {
				return true;
			}
		}
		
		return false;
	}
}
