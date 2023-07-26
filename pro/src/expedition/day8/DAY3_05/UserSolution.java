package expedition.day8.DAY3_05;

import java.util.ArrayDeque;

class UserSolution {
	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	// 초기 맵 정보
	static int[][] map;

	// Union Find
	// index : (y, x)
	// value : 대장(상사) Node(y,x)
	static Node[][] PARENT;

	// 각 개미굴에 대한 정보 => 이 개미굴의 크기?
	static int[][] SIZE;

	static int[][] visited;

	static int[] ydir = { -1, 1, 0, 0 };
	static int[] xdir = { 0, 0, -1, 1 };

	static Node Find(Node node) {
		// 내가 최종 대장이라면 PARENT[node] == node
		if (node.y == PARENT[node.y][node.x].y && node.x == PARENT[node.y][node.x].x)
			return node;
		return PARENT[node.y][node.x] = Find(PARENT[node.y][node.x]);
	}

	static void Union(Node A, Node B) {
		Node pa = Find(A);
		Node pb = Find(B);
		if (pa.y == pb.y && pa.x == pb.x)
			return;
		PARENT[pb.y][pb.x] = pa;

		// 추가 데이터 관리
		SIZE[pa.y][pa.x] += SIZE[pb.y][pb.x];
		SIZE[pb.y][pb.x] = 0;

		// 두개의 개미굴이 한개가 되었다!
		cnt--;
	}

	static void bfs(int y, int x) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(y, x));

		while (!q.isEmpty()) {
			Node now = q.remove();
			for (int i = 0; i < 4; i++) {
				int ny = now.y + ydir[i];
				int nx = now.x + xdir[i];
				if (ny < 0 || nx < 0 || ny >= MAPSIZE || nx >= MAPSIZE)
					continue;
				// 굴이 아닌곳으로는 퍼지지 않음
				if (map[ny][nx] == 0)
					continue;
				if (visited[ny][nx] == 1)
					continue;

				visited[ny][nx] = 1;
				Union(now, new Node(ny, nx)); // 두개의 개미굴을 결합
				q.add(new Node(ny, nx));
			}
		}
	}

	static int cnt;
	static int MAPSIZE;

	public void init(int N, int[][] MAP) {
		MAPSIZE = N;
		PARENT = new Node[N][N];
		SIZE = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				PARENT[i][j] = new Node(i, j);
				SIZE[i][j] = 1;
			}
		}
		map = MAP;

		int temp = 0;
		// 초기 상태 확인 (섬찾기)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 굴이 있는 곳이고, 이미 체크한 굴이 아니라면
				if (map[i][j] == 1 && visited[i][j] != 1) {
					visited[i][j] = 1;
					bfs(i, j); // 여기에서 연결된 개미굴의 크기
					temp++; // 하나의 개미굴이 있다.
				}
			}
		}
		cnt = temp; // 초기의 개미굴 세팅에서 cnt 떨어진것을 제외
	}

	public void dig(int y, int x) {
		// (y,x) 위치 파고,
		// 상하좌우 확인하면서
		// 연결 가능하다면 연결시키면서 데이터 관리
		map[y][x] = 1;
		cnt++; // 굴의 개수는 일단 늘었죠.

		for (int i = 0; i < 4; i++) {
			int ny = y + ydir[i];
			int nx = x + xdir[i];
			if (ny < 0 || nx < 0 || ny >= MAPSIZE || nx >= MAPSIZE)
				continue;
			if (map[ny][nx] == 0)
				continue;
			Node now = new Node(y, x);
			Node next = new Node(ny, nx);
			// 두개의 개미굴은 연결된다!
			Union(now, next);
		}
	}

	public int getSize(int y, int x) {
		Node now = new Node(y, x);
		Node pnow = Find(now);
		return SIZE[pnow.y][pnow.x];
	}

	public int getHoleCnt() {
		return cnt;
	}
}