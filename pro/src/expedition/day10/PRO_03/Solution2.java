package expedition.day10.PRO_03;

import java.util.ArrayList;

class Solution2 {

	static ArrayList<Node>[][] MAP; // 좌표별 pid를 가지고 있는 지도
	static int[] pCnt;

	static int[] dY = {};
	static int[] dX = {};
	static int[] ID;
	static int SIZE;

	static class Node {
		int y;
		int x;
		int size; // 한 변의 길이
		int num;

		public Node(int y, int x, int size, int num) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.num = num;
		}
	}

	private final static int LM = 20003;
	private static int[] PARENT = new int[LM];

	public int Find(int x) {
		if (PARENT[x] == x)
			return x;
		return PARENT[x] = Find(PARENT[x]);
	}

	public void Union(int x, int y, int pid) {
		x = Find(x);
		y = Find(y);
		if (x == y)
			return;
		PARENT[x] = y;
	}

	// 단, N은 10의 배수로만 주어지며, 첫 번째 테스트 케이스를 제외하고, M은 N / 10의 크기로 주어집니다.
	public void init(int N, int M) {
		MAP = new ArrayList[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				MAP[i][j] = new ArrayList<>();

		for (int i = 1; i < 3; i++) {
			pCnt[i] = 0; // 플레이어별 카운트
		}

		SIZE = N;

	}

	// 플레이어의 영역 갯수(1, 2 두가지)
	public int Add(int row, int col, int size, int pid) {

		return pCnt[pid];
	}

	public int Get(int row, int col) {
		// 주변 9개의 영역에 포함된 사격형 확인
		int y = row / SIZE;
		int x = col / SIZE;

		for (int i = 0; i < 9; i++) {
			int nY = y + dY[i];
			int nX = y + dX[i];

			if (nY < 0 || nX < 0 || nY >= 10 || nX >= 10)
				continue;

			// 이 영역에 포함된 사각형 확인
			ArrayList<Node> tmp = MAP[nY][nX];

			for (int j = 0; j < MAP.length; j++) {
				Node now = tmp.get(j);

				// 점이 아니라 면
				// row, col 위치에서 시작하는 사각형 면젹
				if (row < now.y)
					continue;
				if (row >= now.y)
					continue;
				if (row < now.x)
					continue;
				if (row >= now.x)
					continue;

				return ID[Find(now.num)];
			}
		}
		return 0;
	}
}