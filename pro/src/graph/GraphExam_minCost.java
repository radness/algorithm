package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphExam_minCost {
	static ArrayList<Node>[] arr;
	static int[] used;
	static int minResult;
	
	public static void main(String[] args) {
		arr = new ArrayList[4];
		used = new int[4];
		
		minResult = (int) 21e8; // 21��

		run();
	}

	private static void run() {
		arr[0] = new ArrayList<>(Arrays.asList(new Node(1, 3), new Node(2, 5), new Node(3, 8)));
		arr[1] = new ArrayList<>(Arrays.asList(new Node(2, 1), new Node(3, 8)));
		arr[2] = new ArrayList<>(Arrays.asList(new Node(0, 1), new Node(3, 2)));
		arr[3] = new ArrayList<>();
		
		used[0] = 1;
		
		dfs(0, 0);	// 0�� ��忡�� �����ϰ� 0�������� ����
		
		System.out.println(minResult + "����");
		
	}

	private static void dfs(int now, int sum) {
		if (now == 3) {
			minResult = Math.min(minResult, sum);	// �ּҰ� ����
			return;
		}
		
		for (int i = 0; i < arr[now].size(); i++) {
			Node next = arr[now].get(i);
			
			if (used[next.n] == 1)	// �湮����
				continue;
			
			used[next.n] = 1;	// �湮 üũ
			
			dfs(next.n, sum + next.cost);
			
			used[next.n] = 0;
			
		}
	}

	static class Node {
		int n;
		int cost;

		public Node(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}
	}

}
