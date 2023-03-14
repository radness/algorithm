import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP
 * 점화식 : D[i][j] = D[i-1][j-1] + D[i-1][j]
 * */
public class BJ_16395_파스칼삼각형 {
	static int N;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long[][] D = new long[N + 1][N + 1];

		for (int 행 = 1; 행 <= N; 행++) {
			for (int 열 = 1; 열 <= 행; 열++) {
				// 초기화
				if (열 == 1 || 열 == 행) {
					D[행][열] = 1;
					continue;
				}
				// 점화식
				D[행][열] = D[행 - 1][열 - 1] + D[행 - 1][열];
			}
		}

		// 결과
		System.out.println(D[N][K]);
	}
}
