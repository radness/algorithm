package pro.past.no9;

class UserSolution {

	static int[][] MAP;
	static Node[][] PARENTS;
	static int SIZE;

	// 8개 방향
	static int[] dY = { -1, 0, 1, 0, -1, -1, 1, 1 };
	static int[] dX = { 0, 1, 0, -1, -1, 1, 1, -1 };

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static void union(Node a, Node b) {
		Node pa = find(a);
		Node pb = find(b);

		if (pa.y == pb.y && pa.x == pb.x)
			return;

		PARENTS[pb.y][pb.x] = pa;
	}

	static Node find(Node a) {
		if (PARENTS[a.y][a.x] == a)
			return a;

		Node boss = find(a);
		PARENTS[a.y][a.x] = boss;
		return boss;
	}

	void init(int N) {
		SIZE = N;
		MAP = new int[N][N];
		PARENTS = new Node[N][N];

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				PARENTS[y][x] = new Node(y, x);
			}
		}
	}

	int put(int mRow, int mCol) {

		// 현재 위치에 세포를 생성
		MAP[mRow][mCol] = 1;

		int[][] DAT = new int[SIZE][SIZE];
		boolean createFlag = false;

		for (int i = 0; i < 8; i++) {
			int nY = mRow + dY[i];
			int nX = mCol + dX[i];

			if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE)
				continue;

			if (MAP[nY][nX] == 0)
				continue;

			// 다른 세포가 있는지 체크
			Node node = new Node(nY, nX);
			Node now = find(node);

			DAT[now.y][now.x]++;
			
			if (DAT[now.y][now.x] >= 2)
				createFlag = true;
			
			union(node, new Node(mRow, mCol));
		}
		
		if (createFlag)
			return getCnt(mRow, mCol);

		return 0;
	}

	// 울타리가 생성된 경우
	private int getCnt(int y, int x) {
		for (int i = 0; i  < 4; i++) {
			int nY = y + dY[i];
			int nX = x + dX[i];
			
			if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE) {
				
			}
				
		}
		
		return 0;
	}

}