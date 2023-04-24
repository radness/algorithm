package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 네트워크 연결
// 최소비용 출력
public class BJ_1922 {
	static int N, M;
	static ArrayList<Node> list;
	static boolean[] visited;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		
		visited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.add(new Node(start, end, cost));
		}
		
		
		
		System.out.println(ans);
	}

	private static void dfs(int now) {
	}
}

class Node implements Comparable<Node>{
	int from;
	int to;
	int cost;
	
	public Node(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node other) {
		return this.cost - other.cost;
	}
}