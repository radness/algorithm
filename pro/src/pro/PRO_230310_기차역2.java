package pro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class PRO_230310_������2 {
	static int T; // �׽�Ʈ ���̽�
	static int N; // ������ ��
	static int Q; // ������ ��

	static List<Train>[] LIST; // i�� ���ÿ���(������ ���ÿ���) ����ϴ� ���� ���
	static long[] MIN_COST; // �������� i�� ���ñ��� �̵��ϴ� �ּ� ���
	static int[] PRE_STATIONS; // �������� i�� ���ð��� �ּҺ������ �̵��� �� ���� �� ��ȣ
	static boolean[] IS_CLOSED; // i�� ���� ��� ����

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/pro/PRO_230310_������"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine()); // �׽�Ʈ ���̽� �Է�

		for (int tc = 1; tc <= T; tc++) { // �׽�Ʈ ���̽���ŭ �Է�
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // ������ ��
			Q = Integer.parseInt(st.nextToken()); // ������ ��

			LIST = new ArrayList[N + 1];
			MIN_COST = new long[N + 1];
			PRE_STATIONS = new int[N + 1];
			IS_CLOSED = new boolean[N + 1];

			for (int i = 1; i <= N; i++) {
				LIST[i] = new ArrayList<>();
				MIN_COST[i] = Long.MAX_VALUE;
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()); // ����
				int end = Integer.parseInt(st.nextToken()); // ����
				int cost = Integer.parseInt(st.nextToken()); // ���

				LIST[start].add(new Train(end, cost));
				LIST[end].add(new Train(start, cost));
			}

			// �������� �� ���ñ����� �ִܰŸ� Ž��, �������� �̵� �� ��ȯ�ϴ� �κ��� ���� ������
			// �켱���� ť�� ����� �ʿ䰡 ����.
			MIN_COST[1] = 0; // �ʱ�ȭ
			Queue<Train> queue = new LinkedList<>();
			queue.add(new Train(1, 0)); // �������� : 1, ��� : 0

			while (!queue.isEmpty()) {
				Train current = queue.poll(); // ���� ������

				for (Train next : LIST[current.end]) {
					if (MIN_COST[next.end] <= current.cost + next.cost)
						continue;

					MIN_COST[next.end] = current.cost + next.cost; // �ּҰ��� ���� ��� + ���� �湮 ���
					PRE_STATIONS[next.end] = current.end; // �ִ� ��η� �̵��� ��� ���� �� ���� ����
					queue.add(new Train(next.end, current.cost + next.cost));
				}
			}

			long ans = 0; // ����

			for (int i = 0; i < Q; i++) { // ���� ����ŭ loop
				st = new StringTokenizer(br.readLine());

				int target = Integer.parseInt(st.nextToken()); // ������ ����
				int station = Integer.parseInt(st.nextToken()); // ��� �� ����

				if (target == 0) { // ���� ���̶��
					IS_CLOSED[station] = true; // ��� ó��
					continue;
				}

				// �Է¹��� ��ġ���� ������ ���� ���� ����� �� Ȯ��
				int nearStation = findNearestStation(station);

				ans += MIN_COST[nearStation];
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	// ��⿪�� �� ���� ����� �� ã��
	private static int findNearestStation(int station) {
		if (!IS_CLOSED[station])
			return station;

		if (station == 1)
			return station;
		
		// ���� ���̶�� �ִ� ��η� �̵��� ����� ���� ������
		// ���� ����� ���� Ȯ���ؼ� ���� ���� �� ����
		return PRE_STATIONS[station] = findNearestStation(PRE_STATIONS[station]);
	}

	static class Train {
		int end; // ��������
		long cost; // ���

		public Train(int end, long cost) {
			this.end = end;
			this.cost = cost;
		}
	}
}
