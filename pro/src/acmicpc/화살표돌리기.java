package acmicpc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* ���ͽ�Ʈ��
 *  1. �ʱ�ȭ
 *  2. ���۰� �Է�
 *  3. while(ť�� ������� ���� ��)
 * 	- pool
 * 	- �������� �ƴϸ� skip(üũ)
 * 		-- ���������̸� skip
 * 		-- �� �� �ִ� ������ for loop
 *  	-- �� �� �ִ��� �Ǵ�, ���������� ���� �������� �Ǵ�
 *  		--- ������ ���� ���� -> q�� add
*/
public class ȭ��ǥ������ {
	private static final int INF = 1_000_000_000;
	// ��: 1, ��: 2, ��: 3, ��: 4
	static int[] dx = new int[] {0, -1, 1, 0, 0};
	static int[] dy = new int[] {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ���� = Integer.parseInt(st.nextToken()); 
			int ���� = Integer.parseInt(st.nextToken());
			int[][] map = new int[����][����];
			int[][] dist = new int[����][����];

			for (int y = 0; y < map.length; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < map.length; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					dist[y][x] = INF; // dist�迭�� ���ϴ� �� : 0,0 ���� ���ϴ����������� �ּҺ��
				}
			}

			// �켱���� ť ���
			PriorityQueue<Item> pq = new PriorityQueue<Item>(new Comparator<Item>() {
				@Override
				public int compare(Item o1, Item o2) { // ���� ����
					return Integer.compare(o1.���, o2.���);
				}
			});

			dist[0][0] = 0; // ť�� �ֱ� ���� dist ���۰��� ����
			pq.add(new Item(0, 0, 0)); // ó�������̹Ƿ� ����� 0���� ����

			while (!pq.isEmpty()) {
				Item cur = pq.poll();
				if (dist[cur.y][cur.x] < cur.���) continue;
				// ����, ����: ĭ�� ũ�⿡�� index �ϳ� �������
				// ������ ��ǥ������ ������ �ʴ´�.
				if (cur.y == ���� - 1 && cur.x == ���� - 1) continue;
				
				// �����¿� 4���� �˻�
				for (int ������� = 1; ������� < dist.length; �������++) {
					int nextY = cur.y + dy[�������];
					int nextX = cur.x + dx[�������];
					if (nextY < 0 || nextY > ���� - 1) continue;
					if (nextX < 0 || nextX > ���� - 1) continue;
					int next��� = �����(map[cur.y][cur.x], �������);
					
					if (dist[nextY][nextX] > cur.��� + next���) {
						dist[nextY][nextX] = cur.��� + next���;	// ��� ����
						pq.add(new Item(nextY, nextX, dist[nextY][nextX]));
					}
				}
			}

			System.out.println("�׽�Ʈ ���̽� : " + T + " ������ : " + dist[����-1][����-1]);
		}
	}

	private static int �����(int ��������, int �������) {
		// 1. �ȵ����� ���
		if (��������==�������) return 0;
		// ��: 1, ��: 2, ��: 3, ��: 4
		/* ���� ���� ���϶�, 90�� ���ư��� �� & �� , 180���� ��
		 * ���� ���� ���϶�, 90�� ���ư��� �� & �� , 180���� ��
		 * ���� ���� ���϶�, 90�� ���ư��� �� & �� , 180���� ��
		 * ���� ���� ���϶�, 90�� ���ư��� �� & �� , 180���� ��
		 */
		// 2. 90�� ������ ���
		if (�������� == 1 && (������� == 3 || ������� == 4)) return 1;	// ��
		if (�������� == 2 && (������� == 3 || ������� == 4)) return 1;	// ��
		if (�������� == 3 && (������� == 1 || ������� == 2)) return 1;	// ��
		if (�������� == 4 && (������� == 1 || ������� == 2)) return 1;	// ��
		
		// 3. 180�� ������ ���
		if (�������� == 1 && (������� == 2)) return 2;	// �� -> ��
		if (�������� == 2 && (������� == 1)) return 2;	// �� -> ��
		if (�������� == 3 && (������� == 4)) return 2;	// �� -> ��
		if (�������� == 4 && (������� == 3)) return 2;	// �� -> ��
		
		return 0;
	}

	static class Item {
		int y; // ������
		int x; // ������
		int ���; // ���

		public Item(int y, int x, int ���) {
			this.y = y;
			this.x = x;
			this.��� = ���;
		}
	}
}
