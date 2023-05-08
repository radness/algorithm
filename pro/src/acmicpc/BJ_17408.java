package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Index Tree(Segment Tree)
 * 
 * RMQ - 구간의 최소값(Range Minimun Query)
 * RSQ - 구간합(Range Sum Query)
 * 
 * 초기 설정
 * - 배열 크기 설정
 * - Leaf 입력
 * - Tree 전체 갱신
 * 
 * 필요함수
 * Update(Top-Down / Bottom-Up)
 * Query(Top-Down / Bottom-Up)
 * (필요시 merge 함수 추가)
 * 
 * 고급기술
 * Lazy Propagation(Top-Down)
 * 범위 업데이트 시 사용
 * 
 * */

// 수열과 쿼리 24
public class BJ_17408 {
	static Node[] tree;

	static class Node {
		int max1;
		int max2;

		public Node(int max1, int max2) {
			super();
			this.max1 = max1;
			this.max2 = max2;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int nn = 1;
		while (nn < n) {
			nn *= 2;
		}
		tree = new Node[nn * 2];
		init(nn, arr);
		int queryCnt = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= queryCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd1 = Integer.parseInt(st.nextToken());
			int cmd2 = Integer.parseInt(st.nextToken());
			int cmd3 = Integer.parseInt(st.nextToken());
			if (cmd1 == 1) {
				update(1, 1, nn, cmd2, cmd2, cmd3);
			} else {
				Node answer = query(1, 1, nn, cmd2, cmd3);
				// System.out.println(answer.max1+answer.max2);
				sb.append((answer.max1 + answer.max2) + "\n");
			}
		}
		System.out.println(sb);

	}

	private static Node query(int node, int l, int r, int ql, int qr) {
		if (qr < l || r < ql)
			return new Node(0, 0);
		if (ql <= l && r <= qr) {
			return tree[node];
		}
		int mid = (l + r) / 2;
		Node left = query(node * 2, l, mid, ql, qr);
		Node right = query(node * 2 + 1, mid + 1, r, ql, qr);
		return merge(left, right);
	}

	private static void update(int node, int l, int r, int ql, int qr, int value) {
		if (qr < l || r < ql)
			return;
		if (ql <= l && r <= qr) { // 리프블럭
			tree[node].max1 = value;
			return;
		}
		int mid = (l + r) / 2;
		update(node * 2, l, mid, ql, qr, value);
		update(node * 2 + 1, mid + 1, r, ql, qr, value);
		tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
	}

	private static void init(int nn, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			tree[nn + i] = new Node(arr[i], 0);
		}
		for (int i = nn + arr.length; i < tree.length; i++) {
			tree[i] = new Node(0, 0);
		}
		for (int i = nn - 1; i >= 1; i--) {
			tree[i] = merge(tree[i * 2], tree[i * 2 + 1]);
		}
	}

	private static Node merge(Node Node1, Node Node2) {
		int max1 = 0;
		int max2 = 0;
		if (Node1.max1 == Node2.max1) {
			max1 = Node1.max1;
			max2 = Node2.max1;
		} else if (Node1.max1 > Node2.max1) {
			max1 = Node1.max1;
			if (Node1.max2 > Node2.max1)
				max2 = Node1.max2;
			else
				max2 = Node2.max1;
		} else {
			max1 = Node2.max1;
			if (Node1.max1 > Node2.max2)
				max2 = Node1.max1;
			else
				max2 = Node2.max2;
		}
		return new Node(max1, max2);
	}
}

/*
 * 예제 입력 5 5 4 3 2 1 6 2 2 4 2 1 4 1 5 5 2 3 5 1 4 9 2 3 5
 * 
 * 출력 7 9 8 14
 * 
 */
