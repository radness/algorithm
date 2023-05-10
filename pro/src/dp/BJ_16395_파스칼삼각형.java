<<<<<<< master
package dynamicProgramming;
=======
package dp;
>>>>>>> b351bfa ê²½ë¡œ ì •ë¦¬
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP
 * Á¡È­½Ä : D[i][j] = D[i-1][j-1] + D[i-1][j]
 * */
public class BJ_16395_ÆÄ½ºÄ®»ï°¢Çü {
	static int N;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long[][] D = new long[N + 1][N + 1];

		for (int Çà = 1; Çà <= N; Çà++) {
			for (int ¿­ = 1; ¿­ <= Çà; ¿­++) {
				// ÃÊ±âÈ­
				if (¿­ == 1 || ¿­ == Çà) {
					D[Çà][¿­] = 1;
					continue;
				}
				// Á¡È­½Ä
				D[Çà][¿­] = D[Çà - 1][¿­ - 1] + D[Çà - 1][¿­];
			}
		}

		// °á°ú
		System.out.println(D[N][K]);
	}
}
