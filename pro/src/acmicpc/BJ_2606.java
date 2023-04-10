package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 바이러스
public class BJ_2606 {
	static int N, M; 
	static List<Integer>[] LIST;	// [i] = i번 정점과 연결되어 있는 정점들의 번호
	static int[] visited; // 방문 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		LIST = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			LIST[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			LIST[from].add(to);
			LIST[to].add(from);
		}
		// input end
		
		// 1번 기준으로 완전 탐색(BFS)
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		
		int answer = 0;
		
		// 양방향 간선
		visited = new int[N + 1];
		
		visited[1] = 1; // 기본 방문 체크
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : LIST[now]) {
				if (visited[next] == 1)
					continue;
				
				// 새로 방문한 정점
				visited[next] = 1;
				answer++;
				queue.add(next);
			}
		}
		
		System.out.println(answer);
		
	}
}

/*
7
6
1 2
2 3
1 5
5 2
5 6
4 7
 */
