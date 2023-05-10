import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// �ּҰ�
public class BJ_10868 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] arr;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tree = new int[N * 4];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		initTree(0, N - 1, 1);
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			bw.write(findTree(0, N - 1, 1, left, right) + "\n");
		}
		bw.close();
	}

	// start, end: Ʈ���� ���۹���, ������
	// now: Ʈ������ ���� Ž���ϴ� ��� �ε���
	// left, right: �Էµ� ���� Ž���Ϸ��� ���� (��������� ����)
	private static int findTree(int start, int end, int now, int left, int right) {
		// TODO Auto-generated method stub
		if (left > end || right < start) // Ž�������� Ʈ�������� ��� ���
			return Integer.MAX_VALUE;
		if (left <= start && right >= end) // Ž�������� Ʈ�� ���� ��� ������ �ش��ϴ� �θ��� ��ȯ
			return tree[now];

		// ���������� Ž��
		int mid = (start + end) / 2;
		int resL = findTree(start, mid, now * 2, left, right);
		int resR = findTree(mid + 1, end, now * 2 + 1, left, right);
		return resL < resR ? resL : resR;
	}

	// start, end: Ʈ���� ���۹���, ������
	// now: Ʈ������ ���� Ž���ϴ� ��� �ε���
	private static int initTree(int start, int end, int now) {
		// TODO Auto-generated method stub
		if (start == end) // �������
			return tree[now] = arr[start];

		// ������尡 �ƴ� ��� ���������� Ž��
		int mid = (start + end) / 2;
		int resL = initTree(start, mid, now * 2);
		int resR = initTree(mid + 1, end, now * 2 + 1);
		return tree[now] = resL < resR ? resL : resR; // �ּڰ� ��ȯ
	}

}