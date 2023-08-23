package pro.past.no9;

import java.util.LinkedList;
import java.util.Queue;

class UserSolution2 {

	int[][] map;
	int[] parents;
	int CELL = 10000;
	int SIZE;
	static int[] dY = { -1, 0, 1, 0, -1, -1, 1, 1 };
	static int[] dX = { 0, 1, 0, -1, -1, 1, 1, -1 };

	void init(int N) {
		map = new int[N + 1][N + 1];
		parents = new int[N * N + 1]; // R, C -> (R-1) * N + C - 1
		SIZE = N;
	}

	int put(int mRow, int mCol) {
		// mRow, mCol에 세포를 생성
		map[mRow][mCol] = CELL;
		int idx = getIdx(mRow, mCol);
		parents[idx] = idx;
		boolean flag = false; // 세포 생성 여부

		// 인접한 8개 위치에서 세포인지 체크
		for (int i = 0; i < 8; i++) {
			int nY = mRow + dY[i];
			int nX = mCol + dX[i];

			if (nY < 1 || nX < 1 || nY > SIZE || nX > SIZE)
				continue;

			// 세포인지 아닌지 판단
			if (map[nY][nX] == CELL) {
				int tmpIdx = getIdx(nY, nX);
				int root = find(tmpIdx);

				if (root == idx) {
					// 세포가 완성됨
					flag = true;
				}

				parents[root] = idx;
			}
		}

		if (!flag) {
			return 0;
		}

		// mRow, mCol의 상하좌우 4개 위치를 시작점으로 완전탐색
		int ans = 0;
		int[][] check = new int[SIZE + 1][SIZE + 1];

		for (int i = 0; i < 4; i++) {
			int nY = mRow + dY[i];
			int nX = mCol + dX[i];

			if (nY < 1 || nX < 1 || nY > SIZE || nX > SIZE)
				continue;

			if (map[nY][nX] == CELL || check[nY][nX] == 1)
				continue;

			ans = getCnt(nY, nX, check);

			// 세포 크기를 카운팅 한 이후
			if (ans > 0) {

			}

		}

		return ans;
	}

	private int getCnt(int row, int col, int[][] check) {
		// BFS
		int cnt = 1;
		check[row][col] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(row);
		q.add(col);
		boolean flag = true;

		while (!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			// row, col에 방문 표시 -> 1칸 참색
			// 현재 위치에서 상하좌우 위치 이동할 수 있는지 확인
			// 다음 위치가 map의 범위를 넘어간 경우 -> 세포 밖으로 나감 -> 세포가 아님
			
			for (int i = 0; i < 4; i++) {
				int nY = r + dY[i];
				int nX = c + dX[i];
				
				// 테두리
				if (nY < 1 || nX < 1 || nY > SIZE || nX > SIZE) {
					flag = false;
					continue;
				}
				
				if (map[nY][nX] == CELL && check[nY][nX] == 1)
					continue;
				
				q.add(nY);
				q.add(nX);
				cnt++;
				check[nY][nX] = 1;
			}
		}
		// 연결된 모든 위치를 탐색하는 동안 map 범위를 벗어나지 않는다면
		// -> Y 이면 방문 개수를 리턴
		// -> N 이면 0 리턴

		return flag ? cnt : 0;
	}

	private int find(int idx) {
		if (parents[idx] == idx)
			return idx;

		return parents[idx] = find(parents[idx]);
	}

	// R, C -> (R-1) * N + C - 1
	private int getIdx(int y, int x) {
		return (y - 1) * SIZE + x - 1;
	}
}