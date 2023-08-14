package expedition.day7.DAY2_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N; // 도시의 개수
	static int M; // 길의 수
	static int R; // 직장이 있는 곳
	static int K; // 민철이가 편하다고 느끼는 이동 횟수

	static ArrayList<Integer>[] alist;

	static void bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		int[] visited = new int[N + 1];
		visited[start] = 1;

		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.remove();

			// now에 왔다.
			// 언젠가는 과거에 visited에 예약을 하고 왔다 = visited에는 이미 now까지의 거리 = 기록이 되어있다.
			if (visited[now] - 1 > K)
				// 여기서 더 가보면 어차피 K보다 멀어진다. => 더 볼 필요가 없다.
				break;

			cnt++;
			for (int i = 0; i < alist[now].size(); i++) {
				int next = alist[now].get(i);
				if (visited[next] != 0)
					continue;
				visited[next] = visited[now] + 1;
				q.add(next);
			}
		}
		// int de = 1;
		// 이제 여기서 경로 기록 끝났으니까,
		// K개 이하로 이동 가능한 도시들 보면서 counting
//		int de = 1; 
//		int cnt = 0; 
//		for(int i = 1; i <= N; i++) {
//			// 실제 거리 visited[i] - 1 
//			// 0으로 기록되어있는곳 = 못가는 곳 
//			if(visited[i] - 1 <= K && visited[i] != 0)
//				cnt++; 
//		}
//		System.out.println(cnt);
		System.out.println(cnt);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 그래프의 정보
		alist = new ArrayList[N + 1];
		// 활성화
		for (int i = 0; i <= N; i++)
			alist[i] = new ArrayList<>();

		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 양방향 연결
			alist[from].add(to);
			alist[to].add(from);
		}

		// R, K 입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 모든 다른 node => R로 가는 BFS를 여러번 돌릴것이 아니라,
		// R => 다른 모든 노드로 가는 BFS를 돌려서 경로를 한번에 찾을겁니다.
		bfs(R); // R을 시작 노드로
	}
}
