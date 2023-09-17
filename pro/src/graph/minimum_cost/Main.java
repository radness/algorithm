package graph.minimum_cost;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소비용 구하기
public class Main {

	static class Node implements Comparable<Node> {
		int id;
		int cost;

		public Node(int id, int cost) {
			super();
			this.id = id;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	static ArrayList<Node>[] graph;
	static int nodeCount;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/graph/minimum_cost/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		nodeCount = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken());

		graph = new ArrayList[nodeCount];

		for (int i = 0; i < nodeCount; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < nodeCount; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[node1].add(new Node(node2, cost));
			graph[node2].add(new Node(node1, cost));
		}

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int ans = Integer.parseInt(st.nextToken());

			if (ans == dfs(start, end)) {
				System.out.println("#" + i + " 정답.");
			} else {
				System.out.println("#" + i + " 오답.");
			}
		}

	}

	private static int dfs(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] cost = new int[nodeCount];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		pq.add(new Node(start, 0));
		cost[start] = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (Node next : graph[now.id]) {
				if (cost[next.id] <= cost[now.id] + next.cost)
					continue;
				
				cost[next.id] = cost[now.id] + next.cost;
				
				pq.add(new Node(next.id, cost[next.id]));
			}
		}
		
		return cost[end];
	}

	
}
