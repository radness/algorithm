package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
입력
5
9
1 2 10
1 3 5
2 3 2
3 1 1
3 2 13
4 1 8
4 5 3
5 4 9
5 2 31
4

출력
0 8 18 13 0 3
*/
public class 다익스트라1 {
	static List<Node>[] graph;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/그래프/다익스트라1_입력"));
		// 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 정점의 개수, 간선의 개수
		int n = Integer.parseInt(bf.readLine());
		int m = Integer.parseInt(bf.readLine());

		graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[v].add(new Node(w, cost));
		}

		int start = Integer.parseInt(bf.readLine());

		// 다익스트라 알고리즘 수행
		Dijkstra(n, start);
	}

	// n: 노드의 크기, start: 출발지
	// 노드의 크기, 출발지
	public static void Dijkstra(int n, int start) {
		boolean[] check = new boolean[n + 1];
		int[] dist = new int[n + 1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			int nowVertex = pq.poll().index;

			if (check[nowVertex])
				continue;
			check[nowVertex] = true;

			// index의 연결된 정점 비교
			for (Node next : graph[nowVertex]) {
				if (dist[next.index] > dist[nowVertex] + next.cost) {
					dist[next.index] = dist[nowVertex] + next.cost;

					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}

		// 최소거리 출력
		for (int i : dist) {
			if (i == INF)
				System.out.print(0 + " ");
			else
				System.out.print(i + " ");
		}
	}

	// 우선순위 큐에 정점번호 + 가중치 저장에 필요
	static class Node implements Comparable<Node> {
		int index; // 정점 번호
		int cost; // 가중치(=비용)

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		/* cost 중심으로 우선순위가 정해지기 때문에 compareTo 오버라이딩 */
		@Override
		public int compareTo(Node o) {
			return Integer.compare(cost, o.cost);
		}
	}
}
