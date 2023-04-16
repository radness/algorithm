package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// �̼����
public class BJ_2644 {
	static int N; // ����� ��
	static int start, end; // �̼��� ����ؾ��ϴ� ��� ��ȣ
	static int M; // �θ� �ڽĵ� ���� ������ ��
	static int ans;
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited; // �湮 ����

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

			// ������̹Ƿ� ���ʿ� �־��ش�.
			list.get(x).add(y);
			list.get(y).add(x);
		}

		dfs(start, 0);

		System.out.println(ans);
	}

	private static void dfs(int point, int count) {
		visited[point] = true; // �湮 üũ

		for (int x : list.get(point)) {
			if (!visited[x]) { // �湮���� �ʾ����� Ž��
				if (x == end) {
					ans = count + 1;
					return;
				}
				
				dfs(x, count + 1);
			}
		}
	}
}
