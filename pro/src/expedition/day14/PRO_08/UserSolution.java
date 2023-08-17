package expedition.day14.PRO_08;

import java.util.ArrayDeque;

// 세포 실험
// BFS + Union-Find
class UserSolution {

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	// Flood-Fill(N = 120)
	static int[][] MAP;

	static int[] dY = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dX = { 0, 0, -1, 1, -1, 1, -1, 1 };

	static int SIZE;

	// Union-Find
	// index : (y, x)
	// value : (y, x) 좌표 소속 = Node(y, x)
	static Node[][] PARENT;

	static Node Find(Node node) {
		// Node == (주소 hashcode) node
		if (node.y == PARENT[node.y][node.x].y && node.x == PARENT[node.y][node.x].x)
			return node;

		return PARENT[node.y][node.x] = Find(PARENT[node.y][node.x]);
	}

	static void Union(Node a, Node b) {
		Node pa = Find(a);
		Node pb = Find(b);

		if (pa.y == pb.y && pa.x == pb.x)
			return;

		PARENT[pb.y][pb.x] = pa;
	}

	// 초기화
	void init(int N) {
		MAP = new int[N][N];
		
		PARENT = new Node[N][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				PARENT[y][x] = new Node(y, x);
			}
		}

		SIZE = N;
	}

	int put(int mRow, int mCol) {
		// 1 ~ N(포함)
		mRow--;
		mCol--;

		// (mRow, mCol) 좌표 위치에 세포를 둔다.
		MAP[mRow][mCol] = 1;

		int[][] DAT = new int[SIZE][SIZE];
		boolean flag = false; // 울타리가 만들어졌는지?

		// 주변을 확인하면서 울타리가 생성되었는지 확인한다.
		// 울타리가 생성되면 -> 주변 8개 공간에서 같은 소속인 애들이 2개이상 나타나면 -> 울타리 완성
		for (int i = 0; i < 8; i++) {
			int nY = mRow + dY[i];
			int nX = mCol + dX[i];

			// 방향배열은 범위 체크
			if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE)
				continue;

			// 세포들이 연결되었는지? (빈칸이면 pass)
			if (MAP[nY][nX] == 0)
				continue;

			// 다른 세포가 있는지 체크
			// 연결해서 같은 소속으로 변경(Union)
			Node node = new Node(nY, nX); // 소속 확인
			Node group = Find(node);

			// 지금 연결하는 이 세포의 소속을 카운팅( = 이 소속의 세포 하나를 찾았다.)
			DAT[group.y][group.x]++;

			// 같은 소속인 세포를 2개 이상 찾았다면
			if (DAT[group.y][group.x] >= 2)
				flag = true; // 울타리가 완성

			Union(node, new Node(mRow, mCol));

		}

		// 울타리가 생성된다면 -> 주변의 빈칸에서부터 Flood-Fill 하면서 크기 찾기
		if (flag) {
			// FF 하면서 크기를 찾는데 테두리를 만나게되면 무효
			return getSize(mRow, mCol); // (mRow, mCol) 위치에서 상하좌우를 체크하고 FF
		}

		return 0;
	}

	private int getSize(int row, int col) {
		// 울타리가 생성된 상태
		for (int i = 0; i < 4; i++) {
			int nY = row + dY[i];
			int nX = col + dX[i];

			if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE)
				continue;

			// 빈칸의 갯수 -> 세포가 있는 공간에서는 찾아볼 필요가 없다.
			if (MAP[nY][nX] == 1)
				continue;

			// (nY, nX)부터 FF
			// 테두리를 만나는 경우에는 -1 리턴
			// 아니라면 -> 양수값
			int cnt = bfs(nY, nX);

			// 테두리 안을 봤다면
			if (cnt > -1)
				return cnt;

		}

		return 0;
	}

	private int bfs(int y, int x) {
		ArrayDeque<Node> q = new ArrayDeque<>();

		q.add(new Node(y, x));

		int[][] visited = new int[SIZE][SIZE];
		visited[y][x] = 1; // 시작하는 위치 방문 체크

		int cnt = 0;

		while (!q.isEmpty()) {
			Node now = q.poll();

			cnt++;

			for (int i = 0; i < 4; i++) {
				int nY = now.y + dY[i];
				int nX = now.x + dX[i];

				// 테두리를 만난 경우
				if (nY < 0 || nX < 0 || nY >= SIZE || nX >= SIZE)
					return -1; // 이미 유효하지 않은 공간(테두리 안을 확인하는게 아니다.)

				if (MAP[nY][nX] == 1)
					continue;

				if (visited[nY][nX] == 1)
					continue;

				visited[nY][nX] = 1;

				q.add(new Node(nY, nX));

			}
		}

		// 테두리를 만난적이 없다 -> 울타리 안을 보았다.
		// 내가 FF 을 한 빈 공간의 갯수를 리턴
		return cnt;
	}
}