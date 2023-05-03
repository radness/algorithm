package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15681_트리와쿼리 {
	static int N, R, Q;
	static int[] dp;
	static List<Integer>[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1];
	
		tree = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			tree[U].add(V);
			tree[V].add(U);
		}
		
		search(R);
		
		int ans = 0;
		
		for (int i = 0; i < Q; i++) {
			ans = Integer.parseInt(br.readLine());
			bw.write(dp[ans] + "\n");
		}
		
		bw.flush();
		bw.close();
	}

	private static int search(int node) {
		if (dp[node] != 0)
			return 0;
		
		dp[node] = 1;	// 초기화
		
		for (int next : tree[node]) {
			dp[node] += search(next);
		}
		
		return dp[node];
	}
}
