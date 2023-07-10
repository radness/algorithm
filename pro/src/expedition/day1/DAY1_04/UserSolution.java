package expedition.day1.DAY1_04;

class UserSolution {

	static int[][] map;
	static int[][] visited;
	static int size;
	static int[] rows;
	static int[] cols;
	static int diag1;
	static int diag2;

	public void init(int N) {
		map = new int[N][N];
		visited = new int[N][N];
		int cnt = 1;
		size = N;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				map[y][x] = cnt;
				cnt++;
			}
		}

		rows = new int[size];
		cols = new int[size];
		diag1 = 0;
		diag2 = 0;

	}

	public void mark(int num) {

		int dY = (num - 1) / size;
		int dX = ((num - 1) % size);

		visited[dY][dX] = 1;
		rows[dX]++;
		cols[dY]++;

		if (dY == dX) {
			diag1++;
		}
		if ((size - dY - 1) == dX) {
			diag2++;
		}

	}

	public int bingo() {
		int ans = 0;

		// 1. 가로 체크
		for (int i = 0; i < rows.length; i++) {
			if (rows[i] == size) {
				ans++;
			} 
		}

		// 2. 세로 체크
		for (int i = 0; i < cols.length; i++) {
			if (cols[i] == size) {
				ans++;
			}
		}

		// 3. 대각선 체크
		if (diag1 == size)
			ans++;

		if (diag2 == size)
			ans++;

		return ans;
	}
}