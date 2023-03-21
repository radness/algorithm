package pro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PRO_230310_기차역 {
	private static int N; // 도시 수
	private static int Q; // 쿼리

	private static int sudo = 1;

	private static int T; // 테스트 케이스

	private static List<Node_230310>[] list;
	private static int[] dist; // 거리
	private static int[] p;
	private static int[] u;

	private static int sum;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/pro/PRO_230310_기차역"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 수도 : 1번
		T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력

		for (int tc = 1; tc <= T; tc++) { // 테스트 케이스 만큼
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			list = new ArrayList[N + 1];

			p = new int[N + 1];
			u = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
				u[i] = i;
			}

			for (int j = 0; j < N - 1; j++) { // N-1개의 도시정보
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				list[start].add(new Node_230310(end, value));
				list[end].add(new Node_230310(start, value));
			}

			dist = new int[N + 1];

			Arrays.fill(dist, Integer.MAX_VALUE);

			dijk(1);

			sum = 0;

			for (int j = 0; j < Q; j++) { // Q개의 쿼리
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				int city = Integer.parseInt(st.nextToken());

				if (type == 1) {
					sum += dist[find(city)];
				} else {
					union(city, p[city]);
				}
			}

			sb.append("#" + tc + " " + sum + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		u[a] = b;
	}

	private static int find(int x) {
		if (x == u[x])
			return x;

		return u[x] = find(u[x]);
	}

	private static void dijk(int start) {
		PriorityQueue<Node_230310> pq = new PriorityQueue<>();
		dist[start] = 0;

		pq.add(new Node_230310(start, 0));

		boolean[] visited = new boolean[N + 1];

		while (!pq.isEmpty()) {
			Node_230310 cur = pq.poll();

			if (visited[cur.v]) // 방문여부 체크
				continue;

			visited[cur.v] = true; // 방문표시

			for (Node_230310 node : list[cur.v]) {
				if (!visited[node.v] && dist[node.v] > node.dis + dist[cur.v]) {
					dist[node.v] = node.dis + dist[cur.v];
					pq.add(new Node_230310(node.v, dist[node.v]));
					p[node.v] = cur.v;
				}
			}
		}
	}
}

class Node_230310 implements Comparable<Node_230310> {
	int v;
	int dis;

	public Node_230310(int v, int dis) {
		this.v = v;
		this.dis = dis;
	}

	@Override
	public int compareTo(Node_230310 o) {
		return this.dis - o.dis;
	}
}
