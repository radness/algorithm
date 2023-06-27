import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS_BFS {
	
	static ArrayList<Integer>[] graph;
	static int[] used;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DFS_BFS_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int node = Integer.parseInt(st.nextToken());	// 정점의 갯수
		int edge = Integer.parseInt(st.nextToken());	// 간선의 갯수
		int vertex = Integer.parseInt(st.nextToken());	// 정점의 번호
		
		graph = new ArrayList[node + 1];
		
		for (int i = 0; i < node + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start].add(end);
			graph[end].add(start);
		}
		
//		for (int i = 0; i < graph.length; i++) {
//			Collections.sort(graph[i]);
//		}
		
		used = new int[node + 1];
		used[1] = 1;
		
		dfs(vertex);
		
		Arrays.fill(used, 0);
		System.out.println();
		
		bfs(vertex);
	}

	private static void bfs(int vertex) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(vertex);
		
		used[vertex] = 1;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			System.out.print(now + " ");
			
			for (int cur : graph[now]) {
				if (used[cur] == 0) {
					used[cur] = 1;
					q.add(cur);
				}
			}
			
		}
	}

	private static void dfs(int vertex) {
		used[vertex] = 1;
		System.out.print(vertex + " ");
		
		for (int now : graph[vertex]) {
			if (used[now] == 0)
				dfs(now);
		}
//		System.out.println();
	}
}
