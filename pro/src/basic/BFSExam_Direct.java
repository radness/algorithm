package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ¹æÇâ Å½»ö
public class BFSExam_Direct {
	static int[][] arr;
	static int N, M;
	static int sum;

	static int[] dY = { -1, 0, 1, 0 };
	static int[] dX = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = 5;
		M = 5;
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int inputY = 1;
		int inputX = 2;

		sum = 0;

		for (int i = 0; i < 4; i++) {
			int nextY = inputY + dY[i];
			int nextX = inputX + dX[i];
			
			if (nextY < 0 || nextX < 0 || nextY >= 4 || nextX >= 5)
				continue;
			
			sum += arr[nextY][nextX];
		}
		
		System.out.println(sum);
	}
}

/*
 * ÀÔ·Â °ª »ùÇÃ
1 2 3 4 5
1 1 1 1 1
2 2 2 2 2
3 3 3 3 3
4 4 4 4 4
 */