package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class PRO_230324_���ü_���� {
	static long[] WORKTIMES; // i �� �۾��� �ҿ�ð�
	static long[][] MAX_TIMES; // [i][0] = i �� �۾����� ��� ��ü ���� �����ϴ� �ð�
								// [i][1] = i �� �۾����� ��� ��ü�� �ؼ� �����ϴ� �ּ� �ð�
	static List<Integer>[] LIST; // i �� �۾��� �����۾� ���

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("java\\src\\miracom\\pro2023\\input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			WORKTIMES = new long[N + 1];
			MAX_TIMES = new long[N + 1][2];
			LIST = new ArrayList[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				WORKTIMES[i] = Long.parseLong(st.nextToken());
				LIST[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N; i++) {
				int parent = Integer.parseInt(st.nextToken());
				LIST[parent].add(i);
			}

			// LEAF -> ROOT ������ Ž���ϱ� ���� ���� ����
			// N �� ��Ʈ�� �ؼ� BFS�� �湮������� stack �� ������ -> ���߿� �湮�� �������� ��ȸ ��
			Queue<Integer> que = new LinkedList<>();
			Stack<Integer> stack = new Stack<>();
			que.add(N);
			stack.add(N);
			while (!que.isEmpty()) {
				for (int next : LIST[que.poll()]) {
					que.add(next);
					stack.add(next);
				}
			}

			while (!stack.isEmpty()) {
				int now = stack.pop();
				if (LIST[now].size() == 0) {
					// SUB NODE�� ���� ��� = LEAF
					MAX_TIMES[now][0] = WORKTIMES[now];
					MAX_TIMES[now][1] = 1;
					continue;
				}
				// LEAF �� �ƴ� ��� = 1�� �̻��� SUB NODE�� ������ �ִ� ���
				long max = 0; // ���� �۾��� �� ���ü ���� ���� �����ɸ��� �ð�
				long max_sub = 0; // ���ü ���� ���� �۾��� ���� ���� �ɸ��� �۾��� ���ü�� �ؼ� �����Ų �ð�
				Queue<Long> mque = new PriorityQueue<>();
				for (int sub : LIST[now]) {
					if (MAX_TIMES[sub][0] > max) {
						max = MAX_TIMES[sub][0];
						max_sub = MAX_TIMES[sub][1];
					}
					mque.add(MAX_TIMES[sub][0]);
					if (mque.size() > 2) {
						mque.poll();
					}
				}

				MAX_TIMES[now][0] = max + WORKTIMES[now]; // �̹� �۾� ���� ���ü�� ���� ��� �ҿ�ð�
				MAX_TIMES[now][1] = max + 1; // �̹� ��� ��ü�� ����� �ҿ�ð�
				if (mque.size() == 1) {
					// ���� ���� �۾��� 1���� ���
					// [�����۾����� ���ü�� �ؼ� �����Ų �ð� + �̹� ����� �ҿ�ð�] �� ���� �� ��
					MAX_TIMES[now][1] = Math.min(MAX_TIMES[now][1], max_sub + WORKTIMES[now]);
				} else {
					// ���� ���� �۾��� 2�� �̻��� ���
					// ���ü�� ����Ͽ� �����۾��� �Ϸ��ϴ� �ð�
					// = ���� ���� �����ɸ� �����۾��� ���ü�Ͽ� �����Ų �ð� vs ���ü ���� �� ��°�� �����ɸ� �����۾��� �Ϸ�ð� �� ū ��
					MAX_TIMES[now][1] = Math.min(MAX_TIMES[now][1], Math.max(max_sub, mque.peek()) + WORKTIMES[now]);
				}
			}
			System.out.println("#" + test_case + " " + MAX_TIMES[N][1]);
		}
	}
}

/*
 * 3 8 3 2 3 2 5 3 4 1 2 3 8 2 3 7 8 10 8 9 7 2 6 6 6 2 6 6 10 10 2 10 9 2 10 9
 * 3 3 5 2 4 3 1
 * 
 * [���] #1 8 #2 26 #3 7
 * 
 */
