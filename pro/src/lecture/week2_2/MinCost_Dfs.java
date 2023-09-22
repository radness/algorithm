package lecture.week2_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinCost_Dfs {
	
	static ArrayList<Node>[] arr;
	static int[] used;
	static int minResult;
	
	static class Node {
		int id;
		int cost;
		
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/lecture/week2_2/sample_input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		arr = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			
			while (st.hasMoreTokens()) {
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				arr[idx].add(new Node(end, cost));
			}
		}
		
		used = new int[N];
		used[0] = 1;
		minResult = Integer.MAX_VALUE;
		
		dfs(0, 0);
		
		System.out.println(minResult);
	}

	private static void dfs(int now, int sum) {
		if (now == 3) {
			minResult = Math.min(minResult, sum);
			return;
		}
		
		for (int i = 0; i < arr[now].size(); i++) {
			Node node = arr[now].get(i);
			
			if (used[node.id] == 1)
				continue;
			
			used[node.id] = 1;
			
			dfs(node.id, sum + node.cost);
			
			used[node.id] = 0;
		}
			
	}
}
