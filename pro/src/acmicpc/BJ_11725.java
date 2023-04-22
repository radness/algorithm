package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Ʈ���� �θ� ã��
// �׷���  -> LinkedList
public class BJ_11725 {
	static int N; // ����� ����
	static ArrayList<Integer>[] list;
	static int[] visited;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		visited = new int[N + 1];
		parent = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) { // ���� ����
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			list[node1].add(node2);
			list[node2].add(node1);
		}

		dfs(1);

		for (int i = 2; i < parent.length; i++) {
			System.out.println(parent[i]);
		}
	}

	private static void dfs(int idx) {
		visited[idx] = 1; // �湮 ǥ��

		for (int i : list[idx]) {
			if (visited[i] == 0) {
				parent[i] = idx;

				dfs(i);
			}
		}

	}

}
