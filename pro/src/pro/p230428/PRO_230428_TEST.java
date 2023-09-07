package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사과 먹기 게임
public class PRO_230428_TEST {
	static int T; // 테스트 케이스의 수
	static int N; // 맵의 크기
	static int[][] map; // 맵
	static int M; // 사과의 갯수
	static int ans;
	static int sY, sX; // 출발 좌표
	static int nY, nX; // 사과별 좌표
	static Node[] alist;

	// 테스트 케이스별 최소 우회전의 횟수
	public static void main(String[] args) throws Exception {
//		long startTime = System.currentTimeMillis();

//		System.setIn(new FileInputStream(new File("src/pro/PRO_230428_TEST_input")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			map = new int[N][N];
			alist = new Node[11];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// input end

			// 사과의 갯수
			M = findAppleCnt(0);
//			System.out.println("M : " + M);

			// 1까지 가는 최소 회전 횟수는 무조건 1번
			// -> 1부터 시작 후 1번 사과의 좌표부터 탐색
			ans = 1;
			
			// 좌표 : 출발은 항상 오른쪽을 향한 상태에서 시작
			// 좌표 y값, 좌표 x값, 방향, 탐색대상 시작 사과
			// BFS
//			findApple(sY, sX, 0, 2);
			findApple(0, 0, 3, 0);

			System.out.println("#" + tc + " " + ans);
		}
		
//		long endTime = System.currentTimeMillis();
//		System.out.println("걸린 시간 : " + (endTime - startTime) + "(ms)");
	}

	// 방향 : 0(하), 1(좌), 2(상), 3(우)
	private static void findApple(int y, int x, int dir, int cnt) {
		if (cnt == M && map[y][x] == M) { // 마지막 사과에 도달하면 종료
			ans = ans - 1;
			return;
		}
		
		// 사과를 찾으면 다음 사과를 검색
		if (map[y][x] == cnt) {
			cnt++;
		}
		
		Node now = alist[cnt];
		nY = now.y;
		nX = now.x;

		// 방향 전환
		if (dir == 0) { // 하 : 0
			// map의 끝이거나 해당되는 사과의 좌표가 있는 경우 우회전한다.
			if (y == (N - 1) || (y == nY && (x > nX))) {
				dir = 1;
				ans++;
			}
		} else if (dir == 1) { // 좌 : 1
			if (x == 0 || (x == nX && y > nY)) {
				dir = 2;
				ans++;
			}
		} else if (dir == 2) { // 상 : 2
			if (y == 0 || (y == nY)) {
				dir = 3;
				ans++;
			}
		} else if (dir == 3) { // 우 : 3
			if (x == (N - 1) || (x == nX && y < nY)) {
				dir = 0;
				ans++;
			}
		}

		// 두번째 사과 찾기 -> M번째 사과 찾기
		if (dir == 0) {
			findApple(y + 1, x, dir, cnt);
		} else if (dir == 1) {
			findApple(y, x - 1, dir, cnt);
		} else if (dir == 2) {
			findApple(y - 1, x, dir, cnt);
		} else if (dir == 3) {
			findApple(y, x + 1, dir, cnt);
		}

	}

	// 필요한 값 : M의 갯수
	// 각 사과별 위치
	private static int findAppleCnt(int cnt) {
		for (int y = 1; y < N; y++) {
			for (int x = 1; x < N; x++) {
				if (map[y][x] == 0) {
					continue;
				} else {
					alist[map[y][x]] = new Node(y, x);
				}

				cnt = Math.max(cnt, map[y][x]);
			}
		}
		
		sY = alist[1].y;
		sX = alist[1].x;
		
		return cnt;
	}

	// 각각의 사과별 위치를 저장한다.
	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}