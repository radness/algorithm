import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 피보나치 수
 * 점화식 : D[i] = D[i-1] + D[i-2]
 * (i >= 2)
 * */
public class BJ_2748_피보나치수2 {
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];

		arr[0] = 0;
		arr[1] = 1;

		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}

		System.out.println(arr[N]);
	}
}
