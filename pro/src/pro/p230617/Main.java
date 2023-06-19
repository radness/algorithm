package pro.p230617;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// start, end 까지 최단거리 비용
	// 최단거리 알고리즘 -> 다익스트라 -> 인접리스트 그래프 + 우선순위 큐 사용
	// 모든 경로 탐색 (dfs)

	static class Node implements Comparable<Node> {
		int id;
		int cost;

		// 정점에 대한 번호화 비용(가중치) 저장
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}

		// cost 중심으로 우선순위가 정해지기 때문에 compareTo 메서드 오버라이딩
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	static ArrayList<Node>[] graph = new ArrayList[7];
	static int[] used = new int[7];

	public static void main(String[] args) throws Exception {
		/*
		 * pq는 우선순위 큐로 정점과 출발지에서 정점까지 가는 최소 거리를 저장. 우선순위는 거리가 짧을수록 높다. check는 boolean
		 * 배열로 해당 정점을 방문하는지 체크한다. dist는 int 배열로 출발지로부터 최소 거리를 저장한다.

		 */

		long startTime = System.currentTimeMillis();
		
		System.setIn(new FileInputStream("src/pro/p230617/sample_input2.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 7; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < 7; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[node1].add(new Node(node2, cost));
			graph[node2].add(new Node(node1, cost));
		}

		System.out.println("Node 정보 입력 완료");

		int firstCase = Integer.MAX_VALUE;
		int secondCase = Integer.MAX_VALUE;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (cost == findMinCost(start, end)) {
				System.out.println(start + "에서 " + end + "까지 도달하는 최소 비용은 : " + cost);
			} else {
				System.out.println("오답. 비용은 : " + cost);
			}
			
			if (i == 1 || i == 2) {
				firstCase = Math.min(firstCase, cost);
			} else {
				secondCase = Math.min(secondCase, cost);
			}
		}
		
		System.out.println("정답은 : " + (firstCase + secondCase));
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("소요시간 : " + (endTime - startTime) + "ms");
	}

	// start부터 end까지 도달하는 최소 비용
	private static int findMinCost(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int[] cost = new int[7];
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
