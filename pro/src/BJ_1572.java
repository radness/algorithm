import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_1572_�߾Ӱ� {
	static int N;
	static int K;
	static int[] input;
	static int[] tree;
	static int NN;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("BJ_1572_�߾Ӱ�"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		input = new int[N + 1];

		NN = 1;

		while (65537 > NN) {
			NN *= 2;	// Ʈ���� ũ��
		}

		tree = new int[NN * 2];

		// System.out.println(tree.length); // Ʈ���� ������

		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		// �Է� �Ϸ�

		// NN = 0
		// NN + 1 = 1
		// NN + 2 = 2
		// NN + 3 = 3
		// NN + 65536 = 65536
		for (int i = 1; i < K; i++) {
			updateTree(NN + input[i], 1);
		}

		long sum = 0; // ����

		for (int i = K; i <= N; i++) {
			updateTree(NN + input[i], 1);	// �Է�
			sum += query(1, 1, NN, (K + 1) / 2);
			updateTree(NN + input[i - (K - 1)], -1); // K��ŭ ���鼭 ù��° �Է°� ���� -> ������ Ʈ�� ����
		}

		bw.append(String.valueOf(sum));

		bw.close();
	}

	// �߾Ӱ� ���ϱ�
	private static int query(int node, int l, int r, int target) {
		if (l == r) {	//���� ����
			return l - 1;
		}
		
		// Ž��
		int mid = (l + r) / 2;
		if (tree[2 * node] >= target) { // target = 2, ���� �ڽ��� 3���� ������ ������
			// ���� �ڽ� Ž��
			return query(2 * node, l, mid, target);
		} else { // ������ �ڽ� Ž��
			return query(2 * node + 1, mid + 1, r, target - tree[2 * node]);
		}
	}

	// Ʈ�� ������Ʈ
	private static void updateTree(int node, int value) {
		tree[node] += value;

		while (node > 1) {
			node /= 2;
			tree[node] = tree[2 * node] + tree[2 * node + 1];	// ������
		}
	}
}
