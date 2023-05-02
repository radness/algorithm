package pro.recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13907 {
	static class Edge {
		int to;
		int cost;
		int count;

		public Edge(int to, int cost, int count) {
			this.to = to;
			this.cost = cost;
			this.count = count;
		}
	}

	static List<Edge>[] LIST; // [i] => i 번 정점과 연결된 간선들
	static int[][] MIN_COST; // [i][j] => i 번 정점까지 j 개의 간선을 사용해서 도착하는 최소비용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 수
		int K = Integer.parseInt(st.nextToken()); // 세금 인상 횟수

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // 출발점
		int D = Integer.parseInt(st.nextToken()); // 도착점

		LIST = new ArrayList[N + 1];
		MIN_COST = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			LIST[i] = new ArrayList<>();
			Arrays.fill(MIN_COST[i], Integer.MAX_VALUE / 2);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			LIST[from].add(new Edge(to, cost, 0));
			LIST[to].add(new Edge(from, cost, 0));
		}

		Queue<Edge> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		que.add(new Edge(S, 0, 0));
		MIN_COST[S][0] = 0;
		while (!que.isEmpty()) {
			Edge now = que.poll();
			if (now.count == N) {
				continue;
			}
			if (MIN_COST[now.to][now.count] < now.cost) {
				continue;
			}
			A: for (Edge next : LIST[now.to]) {
				int nextCost = now.cost + next.cost;
				int nextCount = now.count + 1;
				for (int i = 0; i <= nextCount; i++) {
					if (MIN_COST[next.to][i] <= nextCost) {
						continue A;
					}
				}
				MIN_COST[next.to][nextCount] = nextCost;
				que.add(new Edge(next.to, nextCost, nextCount));
			}
		}

		StringBuilder sb = new StringBuilder();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, MIN_COST[D][i]);
		}
		sb.append(min);
		sb.append(System.lineSeparator());

		int tax = 0;
		for (int i = 0; i < K; i++) {
			tax += Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				min = Math.min(min, MIN_COST[D][j] + (j * tax));
			}
			sb.append(min);
			sb.append(System.lineSeparator());
		}
		System.out.println(sb);
	}
}
/*
 * 3 3 2 1 3 1 3 5 1 2 1 2 3 2 1 2
 * 
 * 3 5 8
 */