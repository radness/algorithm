package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도시 분할 계획
// 크루스칼 알고리즘 -> Union-Find
// 최소 건설 비용
public class BJ_1647 {
	static int N, M;
	static int[] parent;
	static long ans = 0L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 노드
		M = Integer.parseInt(st.nextToken());	// 간선
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
			
		});
		
		
		for (int i = 0; i < M; i++) {	// 간선 수
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {node1, node2, cost});
		}
		
		parent = new int[N+1];
		
	
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int connectionLine = 0;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int group1 = find(cur[0]);
			int group2 = find(cur[1]);
			
			if (group1 != group2) {
				ans += cur[2];
				parent[group2] = group1;
				connectionLine++;
				
				if (connectionLine == (N - 2))
					break;
			}
			
			System.out.println(ans);
		}
		
	}

	private static int find(int i) {
		if (parent[i] == i)
			return i;
		
		return parent[i] = find(parent[i]);
	}
}
