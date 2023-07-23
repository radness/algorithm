package pro.pre_test.p230721;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 농사로봇
// 시간 제한 : 3000ms
// 메모리 제한 : 256MB
public class Main {

	static int[][] map; // 행성
	static int[][] farm; // 심은 농지
	static int[][] harvestingCnt; // 농지별 싹 난 횟수
	// 오른쪽부터 시계방향으로(우 하 좌 상)
	static int[] dY;
	static int[] dX;

	static int sDay; // 시작 일자
	static int ans; // 테스트 케이스별 정답
	static int N;
	static int M; // 로봇이 동작 가능한 일 수
	static int K; // 싹이 난 횟수
	static int tmpAnsCase;	// M * M * 4번

	public static void main(String[] args) throws Exception {

//		long startTime = System.currentTimeMillis();
//		System.setIn(new FileInputStream("src/pro/pre_test/p230721/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 지형의 한 변의 길이
			M = Integer.parseInt(st.nextToken());

			// 지형정보(농지 : 0, 산 : 1)
			map = new int[N][N];
			farm = new int[N][N];
			harvestingCnt = new int[N][N];
			ans = 0; // 로봇 개척자의 최대 수확 횟수
			dY = new int[4];
			dX = new int[4];

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					harvestingCnt[y][x] = 1;
				}
			}

			// map값 입력
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if (map[y][x] == 1) {
						farm[y][x] = -3;
					}
				}
			}

			for (int y = 0; y < N - 1; y++) {
				for (int x = 0; x < N - 1; x++) {
					if (map[y][x] == 1)
						continue;

					for (int dir = 0; dir < 4; dir++) {
						// 씨를 심는 농장 초기화
						for (int i = 1; i < N - 1; i++) {
							for (int j = 1; j < N - 1; j++) {
								if (farm[i][j] > -3) {
									farm[i][j] = -1;
								}
							}
						}
						// 카운트 초기화
						for (int i = 0; i < N; i++) {
							for (int j = 0; j < N; j++) {
								harvestingCnt[i][j] = 1;
							}
						}

						tmpAnsCase = 0;	// 농장 탐색
						sDay = 1;

//						System.out.println("입력전 : y x dir tmpAns : " + y + " " + x + " " + dir);
						findFarm(y, x, dir, sDay);
//						findFarm(3, 3, 2, 1);

//						System.out.println("y x dir tmpAns : " + y + " " + x + " " + dir + " " + tmpAnsCase);
						ans = Math.max(ans, tmpAnsCase);

					}
				}
			}

			System.out.println("#" + tc + " " + ans); // 결과 출력
		}

//		long endTime = System.currentTimeMillis();
//		System.out.println("걸린 시간: " + (endTime - startTime) + "ms");
	}

	// sY : 시작 y좌표, sX : 시작 x좌표, dir : 방향, sDay : 시작한 날로부터 경과 일, tmpAns : 찾을 때 수확량
	private static void findFarm(int sY, int sX, int dir, int sDay) {
		if (sDay == (M + 1)) { // 경과일과 동작 가능한 일이 동일한 경우 탈출
			return;
		}

		// 씨를 뿌린 농장이 있으면 시간 경과 표시
		for (int y = 1; y < N - 1; y++) {
			for (int x = 1; x < N - 1; x++) {
				if (farm[y][x] > -1) {
					farm[y][x] += 1;
				}
			}
		}
		for (int y = 1; y < N - 1; y++) {
			for (int x = 1; x < N - 1; x++) {
				K = harvestingCnt[y][x]; // 해당 자리에 싹이 난 횟수

				if (farm[y][x] == (3 + K + 1)) {
					farm[y][x] = -2; // 곡식이 열린 농지로 변경
					harvestingCnt[y][x] += 1; // 해당 농지에 추수한 횟수 + 1
				}
			}
		}

		// 시계방향
		if (dir == 0) { // 로봇이 바라보는 방향 기준 오른쪽
			dY = new int[] { 1, 0, -1, 0 };
			dX = new int[] { 0, 1, 0, -1 };
		} else if (dir == 1) { // 앞쪽
			dY = new int[] { 0, -1, 0, 1 };
			dX = new int[] { 1, 0, -1, 0 };
		} else if (dir == 2) { // 왼쪽
			dY = new int[] { -1, 0, 1, 0 };
			dX = new int[] { 0, -1, 0, 1 };
		} else if (dir == 3) { // 뒷쪽
			dY = new int[] { 0, 1, 0, -1 };
			dX = new int[] { -1, 0, 1, 0 };
		} 
		
		boolean isMove = false;
		// 이동 가능 여부 판단
		for (int i = 0; i < 4; i++) {
			if (sY + dY[i] < 0 || sX + dX[i] < 0 || sY + dY[i] >= N || sX + dX[i] >= N)
				continue;

			if (map[sY + dY[i]][sX + dX[i]] == 1)
				continue;

			if (farm[sY + dY[i]][sX + dX[i]] == -1 || farm[sY + dY[i]][sX + dX[i]] == -2) {
				isMove = true;
				break;
			}
		}

		// 1. 오전에 하는 일
		// 현재 농지가 빈 농지이고 로봇이 다음 농지로 이동할 수 있는 경우 씨를 심는다.
		// 현재 농지가 빈 농지이고, 로봇이 다음 농지로 이동할 수 없을 경우 아무것도 하지 않고 현재 위치에서 머무른다.
		// 현재 농지에 곡식이 열린 경우 수확을 한다. 수확을 하면 농지는 빈 농지가 된다.
		if (farm[sY][sX] == -2) {// 수확
//			System.out.println("farm[sY][sX] : " + farm[sY][sX]);
			tmpAnsCase++; // 수확
			farm[sY][sX] = -1; // 빈 농지로 변경
		} else if (farm[sY][sX] == -1 && isMove) { // 빈 농지, 다음 농지로 이동 할 수 있는 경우
			farm[sY][sX] = 0; // 오전에 씨를 심는다
		}

		// 2. 오후에 하는 일
		// 이동 가능한 곳은 빈 농지, 또는 곡식이 열린 농지이다. 산이거나 싹이 나는 농지인 경우 이동이 불가능하다.
		// 만약, 이동 가능한 곳이 여러 개인 경우, 로봇의 오른쪽, 앞쪽, 왼쪽, 뒤쪽의 순서로 가장 먼저인 이동 가능한 곳으로 이동한다.
		// 만약 이동 가능한 곳이 없는 경우 로봇은 이동하지 않고 현재 위치에 머무른다.
		for (int i = 0; i < 4; i++) {
			// 이동가능한 곳이 없는 경우 현재 위치에 머무른다.
			if (isMove == false) {
				break;
			}
			// 오른쪽부터 반 시계방향
			if (dir == 0) { // 로봇이 바라보는 방향 기준 오른쪽
				dY = new int[] { 1, 0, -1, 0 };
				dX = new int[] { 0, 1, 0, -1 };
				if (sY + dY[i] < 0 || sX + dX[i] < 0 || sY + dY[i] >= N || sX + dX[i] >= N)
					continue;
				if (map[sY + dY[i]][sX + dX[i]] == 1) // 산
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] >= 0)
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] == -1 || farm[sY + dY[i]][sX + dX[i]] == -2) {
					if (i == 0) {
						dir = 3;
					} else if (i == 1) {
						dir = 0;
					} else if (i == 2) {
						dir = 1;
					} else if (i == 3) {
						dir = 2;
					}
					break;
				}
			} else if (dir == 1) { // 앞쪽
				dY = new int[] { 0, -1, 0, 1 };
				dX = new int[] { 1, 0, -1, 0 };
				if (sY + dY[i] < 0 || sX + dX[i] < 0 || sY + dY[i] >= N || sX + dX[i] >= N)
					continue;
				if (map[sY + dY[i]][sX + dX[i]] == 1) // 산
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] >= 0)
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] == -1 || farm[sY + dY[i]][sX + dX[i]] == -2) {
					if (i == 0) {
						dir = 0;
					} else if (i == 1) {
						dir = 1;
					} else if (i == 2) {
						dir = 2;
					} else if (i == 3) {
						dir = 3;
					}
					break;
				}
			} else if (dir == 2) { // 왼쪽
				dY = new int[] { -1, 0, 1, 0 };
				dX = new int[] { 0, -1, 0, 1 };
				if (sY + dY[i] < 0 || sX + dX[i] < 0 || sY + dY[i] >= N || sX + dX[i] >= N)
					continue;
				if (map[sY + dY[i]][sX + dX[i]] == 1) // 산
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] >= 0)
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] == -1 || farm[sY + dY[i]][sX + dX[i]] == -2) {
					if (i == 0) {
						dir = 1;
					} // 앞
					else if (i == 1) {
						dir = 2;
					} // 왼
					else if (i == 2) {
						dir = 3;
					} // 뒤
					else if (i == 3) {
						dir = 0;
					} // 오른
					break;
				}
			} else if (dir == 3) { // 뒷쪽
				dY = new int[] { 0, 1, 0, -1 };
				dX = new int[] { -1, 0, 1, 0 };
				if (sY + dY[i] < 0 || sX + dX[i] < 0 || sY + dY[i] >= N || sX + dX[i] >= N)
					continue;
				if (map[sY + dY[i]][sX + dX[i]] == 1) // 산
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] >= 0) // 곡식 심은 곳
					continue;
				if (farm[sY + dY[i]][sX + dX[i]] == -1 || farm[sY + dY[i]][sX + dX[i]] == -2) {
					if (i == 0) {
						dir = 2;
					} else if (i == 1) {
						dir = 3;
					} else if (i == 2) {
						dir = 0;
					} else if (i == 3) {
						dir = 1;
					}
					break;
				}
			}
		}

		// 이동이 가능한 경우
		if (isMove) {
			// 방향대로 움직인다.
			if (dir == 0) { // 오른쪽
				sX += 1;
			} else if (dir == 1) { // 앞
				sY -= 1;
			} else if (dir == 2) { // 왼쪽
				sX -= 1;
			} else if (dir == 3) { // 뒤
				sY += 1;
			}

			findFarm(sY, sX, dir, sDay + 1);
		} else { // 이동이 불가능한 경우
			findFarm(sY, sX, dir, sDay + 1);
		}

//		return;
	}
}
