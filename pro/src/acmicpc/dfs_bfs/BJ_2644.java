package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 촌수계산
public class BJ_2644 {
	static int N; // 사람의 수
	static int start, end; // 촌수를 계산해야하는 사람 번호
	static int M; // 부모 자식들 간의 관계의 수
	static int ans;
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited; // 방문 여부

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		visited = new boolean[N + 1];
		ans = -1;

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 양방향이므로 양쪽에 넣어준다.
			list.get(x).add(y);
			list.get(y).add(x);
		}

		dfs(start, 0);

		System.out.println(ans);
	}

	private static void dfs(int point, int count) {
		visited[point] = true; // 방문 체크

		for (int x : list.get(point)) {
			if (!visited[x]) { // 방문하지 않았으면 탐색
				if (x == end) {
					ans = count + 1;
					return;
				}
				
				dfs(x, count + 1);
			}
		}
	}
}
