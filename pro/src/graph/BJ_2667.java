package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2667_������ȣ���̱� {
	private static int N;
	private static char[][] map = new char[25][25];

	private static int[][] visit = new int[25][25]; // �湮 �迭
	private static int num; // ������ȣ

	private static int list[] = new int[625];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // �Է°�

		String input = null;

		// ���� ���� �Է�
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		// ��� ���� ���ؼ� Ž��
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1' && visit[i][j] == 0) { // �� ������ 1�̰� �湮���� �ʾ�����
					num++; // ���� ��ȣ�� ����
					bfs(i, j); // ���� Ž��
				}
			}
		}

		Arrays.sort(list, 1, num + 1);

		System.out.println(num);

		for (int i = 1; i <= num; i++) {
			System.out.println(list[i]);
		}

	}

	private static void bfs(int i, int j) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.offer(i * 100 + j); // ť�� �ֱ�

		int pos = 0; // ��ġ
		int count = 0; // ���� ��

		while (!queue.isEmpty()) {
			pos = queue.poll(); // ť���� ������
			i = pos / 100;
			j = pos % 100; // ������

			if (visit[i][j] != 0) // �湮 ���� üũ
				continue;

			visit[i][j] = num; // �湮 �迭�� ���� ��ȣ ����
			count++;

			// Ž��
			if (i > 0 && map[i - 1][j] == '1' && visit[i - 1][j] == 0) // ��
				// ������ �ְ� �湮���� ���� ���
				queue.offer((i - 1) * 100 + j);
			if (i < N - 1 && map[i + 1][j] == '1' && visit[i + 1][j] == 0) // ��
				queue.offer((i + 1) * 100 + j);
			if (j > 0 && map[i][j - 1] == '1' && visit[i][j - 1] == 0) // ��
				queue.offer(i * 100 + (j - 1));
			if (j < N - 1 && map[i][j + 1] == '1' && visit[i][j + 1] == 0) // ��
				queue.offer(i * 100 + (j + 1));
		}

		list[num] = count; // ��������Ʈ�� �������� ���� �� �Է�
	}
}
