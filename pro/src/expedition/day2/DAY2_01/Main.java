package expedition.day2.DAY2_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 폭탄 투하
public class Main {

	static char[][] map;
	static int[] dY = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dX = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new char[4][5];

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 5; x++) {
				map[y][x] = '_';
			}
		}

		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());

			int pointY = Integer.parseInt(st.nextToken());
			int pointX = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 8; j++) {
				int nY = pointY + dY[j];
				int nX = pointX + dX[j];

				if (nY < 0 || nX < 0 || nY >= 4 || nX >= 5)
					continue;

				map[nY][nX] = '#';
			}
		}

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 5; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}

}
