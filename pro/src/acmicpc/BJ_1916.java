package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* 다익스트라
 * 하나의 정점에서 다른 모든 정점까지의 최소 거리
 * 
 * 플로이드와샬
 * 모든 정점에서 다른 모든 정점까지의 최소 거리
 * 
 * 최소비용 구하기
 * -> 하나의 도시에서 최소비용을 구하는 문제로 다익스트라 사용
 * */
public class BJ_1916 {
	static int N, M;
	static ArrayList<Node>[] graph;// 인접리스트 그래프
	static int[] dist; // 최단거리 저장 배열
	static boolean[] visited; // 방문 저장 배열
	static int INF = Integer.MAX_VALUE;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 도시의 갯수
		M = Integer.parseInt(br.readLine()); // 버스의 갯수
		
		graph = new ArrayList[N + 1];	// 간선 연결 정보
		dist = new int[N + 1];
		visited = new boolean[N + 1];

		// 그래프 초기화
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = INF;
		}

		// 그래프 입력 받기
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			
			graph[from].add(new Node(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		findMin(start);
		
		System.out.println(dist[end]);
		
	}

	private static void findMin(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));	// 시작지점, 비용

		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			visited[now.index] = true;
			
			for (Node next : graph[now.index]) {
				if (visited[next.index])
					continue;
				
				if (dist[next.index] > dist[now.index] + next.cost) {
					dist[next.index] = dist[now.index] + next.cost;
					pq.add(new Node(next.index, dist[next.index]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
//			return Integer.compare(cost, other.cost);
			return this.cost - other.cost;
		}
	}
}
