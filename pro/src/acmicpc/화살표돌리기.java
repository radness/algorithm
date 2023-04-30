package acmicpc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 다익스트라
 *  1. 초기화
 *  2. 시작값 입력
 *  3. while(큐가 비어있지 않을 때)
 * 	- pool
 * 	- 최적값이 아니면 skip(체크)
 * 		-- 도착지점이면 skip
 * 		-- 갈 수 있는 지점에 for loop
 *  	-- 갈 수 있는지 판단, 최적값으로 갱신 가능한지 판단
 *  		--- 최적의 값을 갱신 -> q에 add
*/
public class 화살표돌리기 {
	private static final int INF = 1_000_000_000;
	// 상: 1, 하: 2, 좌: 3, 우: 4
	static int[] dx = new int[] {0, -1, 1, 0, 0};
	static int[] dy = new int[] {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int 세로 = Integer.parseInt(st.nextToken()); 
			int 가로 = Integer.parseInt(st.nextToken());
			int[][] map = new int[세로][가로];
			int[][] dist = new int[세로][가로];

			for (int y = 0; y < map.length; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < map.length; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					dist[y][x] = INF; // dist배열이 뜻하는 것 : 0,0 부터 원하는지점까지의 최소비용
				}
			}

			// 우선순위 큐 사용
			PriorityQueue<Item> pq = new PriorityQueue<Item>(new Comparator<Item>() {
				@Override
				public int compare(Item o1, Item o2) { // 정렬 기준
					return Integer.compare(o1.비용, o2.비용);
				}
			});

			dist[0][0] = 0; // 큐에 넣기 전에 dist 시작값을 갱신
			pq.add(new Item(0, 0, 0)); // 처음시작이므로 비용을 0부터 시작

			while (!pq.isEmpty()) {
				Item cur = pq.poll();
				if (dist[cur.y][cur.x] < cur.비용) continue;
				// 세로, 가로: 칸의 크기에서 index 하나 빼줘야함
				// 마지막 좌표에서는 돌리지 않는다.
				if (cur.y == 세로 - 1 && cur.x == 가로 - 1) continue;
				
				// 상하좌우 4방향 검색
				for (int 진행방향 = 1; 진행방향 < dist.length; 진행방향++) {
					int nextY = cur.y + dy[진행방향];
					int nextX = cur.x + dx[진행방향];
					if (nextY < 0 || nextY > 세로 - 1) continue;
					if (nextX < 0 || nextX > 가로 - 1) continue;
					int next비용 = 비용계산(map[cur.y][cur.x], 진행방향);
					
					if (dist[nextY][nextX] > cur.비용 + next비용) {
						dist[nextY][nextX] = cur.비용 + next비용;	// 비용 갱신
						pq.add(new Item(nextY, nextX, dist[nextY][nextX]));
					}
				}
			}

			System.out.println("테스트 케이스 : " + T + " 최종값 : " + dist[세로-1][가로-1]);
		}
	}

	private static int 비용계산(int 원래방향, int 진행방향) {
		// 1. 안돌리는 경우
		if (원래방향==진행방향) return 0;
		// 상: 1, 하: 2, 좌: 3, 우: 4
		/* 원래 방향 상일때, 90도 돌아가면 좌 & 우 , 180도는 하
		 * 원래 방향 하일때, 90도 돌아가면 좌 & 우 , 180도는 상
		 * 원래 방향 좌일때, 90도 돌아가면 상 & 하 , 180도는 우
		 * 원래 방향 우일때, 90도 돌아가면 상 & 하 , 180도는 좌
		 */
		// 2. 90도 돌리는 경우
		if (원래방향 == 1 && (진행방향 == 3 || 진행방향 == 4)) return 1;	// 상
		if (원래방향 == 2 && (진행방향 == 3 || 진행방향 == 4)) return 1;	// 하
		if (원래방향 == 3 && (진행방향 == 1 || 진행방향 == 2)) return 1;	// 좌
		if (원래방향 == 4 && (진행방향 == 1 || 진행방향 == 2)) return 1;	// 우
		
		// 3. 180도 돌리는 경우
		if (원래방향 == 1 && (진행방향 == 2)) return 2;	// 상 -> 하
		if (원래방향 == 2 && (진행방향 == 1)) return 2;	// 하 -> 상
		if (원래방향 == 3 && (진행방향 == 4)) return 2;	// 좌 -> 우
		if (원래방향 == 4 && (진행방향 == 3)) return 2;	// 우 -> 좌
		
		return 0;
	}

	static class Item {
		int y; // 세로축
		int x; // 가로축
		int 비용; // 비용

		public Item(int y, int x, int 비용) {
			this.y = y;
			this.x = x;
			this.비용 = 비용;
		}
	}
}
