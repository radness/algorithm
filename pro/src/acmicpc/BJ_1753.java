package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// �ִܰ��
public class BJ_1753 {
	static int V; // ����
	static int E; // ����
	static int point;
	static ArrayList<Node>[] graph;
	static int[] visited; // �湮�迭
	static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		point = Integer.parseInt(br.readLine()); // ������

		graph = new ArrayList[V + 1];
		answer = new int[V + 1];
		
		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, score));
		}

		for (int i = 1; i <= V; i++) {
			answer[i] = 0;
			int sum = 0;
			visited = new int[V + 1];
			visited[point] = 1;
			
//			dfs(point, i, sum); // point : ���� ���, i : ���� ���
			dfs(point);

		}
		
		for (int i = 1; i <= answer.length; i++) {
			System.out.println(i + "�� ����� �ִܰŸ� " + answer[i]);
		}

	}

	private static void dfs(int startPoint) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		answer[startPoint] = 0;
		
		pq.add(new Node(startPoint, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (int i = 0; i < graph[startPoint].size(); i++) {
				Node next = graph[now.val].get(i);
				
				if (answer[next.val] > now.score + next.score) {
					answer[next.val] = now.score + next.score;
					pq.add(new Node(next.val, answer[next.val]));
				}
			}
		}
	}

	// �׷��� Ž��
//	private static void dfs(int now, int endPoint, int sum) { // startPoint : ���۳��, endPoint : ���� ���
//		if (now == endPoint) { // �����ϸ� ����
////			ans = sum;
//			answer[endPoint] = Math.min(answer[endPoint], sum); // �־��� ���������κ��� �� ����� �ּҰ�
//			return;
//		}
//
//		for (int i = 0; i < graph[now].size(); i++) {
//			Node next = graph[now].get(i);
//
//			if (visited[next.val] == 1) // �湮 ������ Ž������ �ʴ´�.
//				continue;
//
//			visited[next.val] = 1; // �湮 ǥ��
//
//			dfs(next.val, endPoint, sum + next.score);
//
//			visited[next.val] = 0;
//		}
//	}

	static class Node implements Comparable<Node> {
		int val;
		int score;

		public Node(int val, int score) {
			this.val = val;
			this.score = score;
		}

		@Override
		public int compareTo(Node other) {
			return this.score - other.score;
		}
	}
}

/*
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */