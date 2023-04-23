package basic;

import java.util.ArrayList;
import java.util.Collections;

/* 최소 비용 신장 트리를 찾느 알고리즘
 * 가작 적은 비용으로 모든 ㄴ노드를 연결하기 위해 사용하는 알고리즘
 * 최소 스패닝 트리(MST, Minimum Spanning Tree)를 찾는다.
 * 간선의 가중치의 합이 최소값이 되도록 하는 알고리즘
 * 스패닝 트리 : 그래프에서 일부 간선을 선택해서 만든 트리
 * 최소 스패닝 트리 : 스패닝 트리 중에 선택한 간선의 가중치의 합이 최소인 트리
 * 
 * 변의 갯수 : E(간선), 꼭지점의 갯수 : V(노드)
 * -> 시간복잡도 : O(ElogV)
 * -> Union-Find 개념에 대한 정립이 선행되어야 한다.
*/
public class KruskalAlgorithmExam {
	static int[] parent;
	static ArrayList<Node> edgeList;
	static int ans = 0;

	public static void main(String[] args) {
		edgeList = new ArrayList<>();
		edgeList.add(new Node(1, 2, 5));
		edgeList.add(new Node(1, 3, 4));
		edgeList.add(new Node(2, 3, 2));
		edgeList.add(new Node(2, 4, 7));
		edgeList.add(new Node(3, 4, 6));
		edgeList.add(new Node(3, 5, 11));
		edgeList.add(new Node(4, 5, 8));
		edgeList.add(new Node(4, 6, 8));
		edgeList.add(new Node(5, 6, 8));

		parent = new int[6 + 1];

		for (int i = 1; i <= 6; i++) {
			parent[i] = i;
		}

		Collections.sort(edgeList);

		for (int i = 0; i < edgeList.size(); i++) {
			Node node = edgeList.get(i);

			if (!isSameParent(node.from, node.to)) {
				ans += node.cost;
				union(node.from, node.to);
			}
		}

		System.out.println(ans);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y)
			parent[y] = x;

	}

	private static int find(int val) {
		if (parent[val] == val)
			return val;

		return parent[val] = find(parent[val]);
	}

	private static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;

		return false;
	}

	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost)
				return -1;

			if (this.cost > other.cost)
				return 1;

			return 0;
		}
	}
}
