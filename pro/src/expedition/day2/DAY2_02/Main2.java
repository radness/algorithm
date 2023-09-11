package expedition.day2.DAY2_02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연쇄 폭탄
public class Main2 {

	static int[][] MAP;
	static int[][] EXPLODE;

	// O(N^2)의 탐색을 줄이기 위해 DAT
	// index : 폭탄 번호(터지는 시간)
	// value : 이 폭탄의 위치를 기록(객체 / 좌표)
	static Bomb[] bombs;

	// 현재 위치 + 상하좌우
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int[] dx = { 0, 0, 1, 0, -1 };

	static class Bomb {
		int y;
		int x;

		public Bomb(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/expedition/day2/DAY2_02/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		MAP = new int[N][N];
		EXPLODE = new int[N][N];

		bombs = new Bomb[N * N + 1];

		// 맵 정보 입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				int num = Integer.parseInt(st.nextToken());
				MAP[y][x] = num;
				bombs[num] = new Bomb(y, x); // num번 폭탄은 (y, x) 좌표에 있다.
			}
		}

		int cnt = 0;

		// 1번부터 순서대로 폭탄을 터트린다.
		for (int i = 1; i < N * N; i++) {
			Bomb now = bombs[i];

			// 이미 터진 폭탄이라면 터지면 안된다.
			if (EXPLODE[now.y][now.x] == 1)
				continue;

			// now번 폭탄을 기준으로 현재위치 + 상하좌우를 터트린다.
			for (int j = 0; j < 5; j++) {
				int ny = now.y + dy[j];
				int nx = now.x + dx[j];

				// 범위 체크
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;

				// 터진 곳에서 cnt가 증가하면 안된다.
				if (EXPLODE[ny][nx] == 1)
					continue;

				// 터지는 부분
				// 1. 터진 위치를 기록하는 방법
				// 2. MAP에 기록하는 방법
				EXPLODE[ny][nx] = 1;

				cnt++;

				if (cnt == N * N) {
					System.out.println(i + "초");
					return;
				}

			}
		}
	}
}
