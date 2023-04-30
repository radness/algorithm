package pro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class PRO_230324_���ü {
	static int T;
	static int N;

	static long[] WORKTIMES;
	static long[][] MAX_TIMES;

	static List<Integer>[] LIST; // i�� �۾��� ���� �۾� ���

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // �׽�Ʈ ���̽� �Է�

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// �ʱ�ȭ
			WORKTIMES = new long[N + 1];
			MAX_TIMES = new long[N + 1][2]; // i�� �۾����� ��� ��ü ���� 2���� ���
			LIST = new ArrayList[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				WORKTIMES[i] = Long.parseLong(st.nextToken());
				LIST[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i < N; i++) {	// N-1�� ���� loop
				int parent = Integer.parseInt(st.nextToken());
				LIST[parent].add(i);
			}
			
			// ���� -> ��Ʈ ������ Ž���ϱ� ���� ���� ����
			// N�� ��Ʈ���ؼ� BFS�� �湮������� stack�� ���� -> ���߿� �湮�� �������� ��ȸ�ȴ�.
			Queue<Integer> queue = new LinkedList<>();
			Stack<Integer> stack = new Stack<>();
			
			queue.add(N);
			stack.add(N);
			
			while (!queue.isEmpty()) {
				for (int next : LIST[queue.poll()]) {
					queue.add(next);
					stack.add(next);
				}
			}
			
			
		}
	}
}
