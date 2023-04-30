package acmicpc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_9663_NQueen {
	private static int N = 0;
	private static int[] arr;
	private static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		nQueen(0);

		long endTime = System.currentTimeMillis();
		
		long elapsedTime = endTime - startTime;
//		System.out.println(result);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(result));
		bw.newLine();
//		bw.write(String.valueOf(elapsedTime));
		System.out.println(elapsedTime);
		bw.flush();
		bw.close();
	}
	
	private static void nQueen(int depth) {
		if (depth == N) {
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			
			if (check(depth)) {
				nQueen(depth + 1);
			}
		}
	}
	
	private static boolean check(int col) {
		for (int i = 0; i < col; i++) {
			if (arr[i] == arr[col]) {
				return false;
			} else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {	// ??각선
				return false;
			}
		}
		
		return true;
	}
}
