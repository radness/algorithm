package acmicpc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스티커, dp
public class BJ_9465 {
	static int T;
	static int N;
	static int[][] sticker;
	static int[][] D;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			sticker = new int[2][N + 1];
			D = new int[2][N + 1];

			for (int i = 0; i <= 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기값
			D[0][0] = 0;
			D[1][0] = 0;
			D[0][1] = sticker[0][1];
			D[1][1] = sticker[1][1];

			for (int i = 2; i <= N; i++) {
				D[0][i] = Integer.max(D[1][i - 1], D[1][i - 2]) + sticker[0][i];
				D[1][i] = Integer.max(D[0][i - 1], D[0][i - 2]) + sticker[1][i];
			}
			
			ans = Integer.max(D[0][N], D[1][N]);

			System.out.println(ans);
		}
	}
}

/*
2
5
50 10 100 20 40
30 50 70 10 60
7
10 30 10 50 100 20 40
20 40 30 50 60 20 80
*/
