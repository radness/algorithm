package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ��� �Ա� ����
public class PRO_230428_TEST {
	static int T; // �׽�Ʈ ���̽��� ��
	static int N; // ���� ũ��
	static int[][] map; // ��
	static int M; // ����� ����
	static int ans;
	static int sY, sX; // ��� ��ǥ
	static int nY, nX; // ����� ��ǥ
	static Node[] alist;

	// �׽�Ʈ ���̽��� �ּ� ��ȸ���� Ƚ��
	public static void main(String[] args) throws Exception {
//		long startTime = System.currentTimeMillis();

//		System.setIn(new FileInputStream(new File("src/pro/PRO_230428_TEST_input")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// �ʱ�ȭ
			map = new int[N][N];
			alist = new Node[11];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// input end

			// ����� ����
			M = findAppleCnt(0);
//			System.out.println("M : " + M);

			// 1���� ���� �ּ� ȸ�� Ƚ���� ������ 1��
			// -> 1���� ���� �� 1�� ����� ��ǥ���� Ž��
			ans = 1;
			
			// ��ǥ : ����� �׻� �������� ���� ���¿��� ����
			// ��ǥ y��, ��ǥ x��, ����, Ž����� ���� ���
			// BFS
//			findApple(sY, sX, 0, 2);
			findApple(0, 0, 3, 0);

			System.out.println("#" + tc + " " + ans);
		}
		
//		long endTime = System.currentTimeMillis();
//		System.out.println("�ɸ� �ð� : " + (endTime - startTime) + "(ms)");
	}

	// ���� : 0(��), 1(��), 2(��), 3(��)
	private static void findApple(int y, int x, int dir, int cnt) {
		if (cnt == M && map[y][x] == M) { // ������ ����� �����ϸ� ����
			ans = ans - 1;
			return;
		}
		
		// ����� ã���� ���� ����� �˻�
		if (map[y][x] == cnt) {
			cnt++;
		}
		
		Node now = alist[cnt];
		nY = now.y;
		nX = now.x;

		// ���� ��ȯ
		if (dir == 0) { // �� : 0
			// map�� ���̰ų� �ش�Ǵ� ����� ��ǥ�� �ִ� ��� ��ȸ���Ѵ�.
			if (y == (N - 1) || (y == nY && (x > nX))) {
				dir = 1;
				ans++;
			}
		} else if (dir == 1) { // �� : 1
			if (x == 0 || (x == nX && y > nY)) {
				dir = 2;
				ans++;
			}
		} else if (dir == 2) { // �� : 2
			if (y == 0 || (y == nY)) {
				dir = 3;
				ans++;
			}
		} else if (dir == 3) { // �� : 3
			if (x == (N - 1) || (x == nX && y < nY)) {
				dir = 0;
				ans++;
			}
		}

		// �ι�° ��� ã�� -> M��° ��� ã��
		if (dir == 0) {
			findApple(y + 1, x, dir, cnt);
		} else if (dir == 1) {
			findApple(y, x - 1, dir, cnt);
		} else if (dir == 2) {
			findApple(y - 1, x, dir, cnt);
		} else if (dir == 3) {
			findApple(y, x + 1, dir, cnt);
		}

	}

	// �ʿ��� �� : M�� ����
	// �� ����� ��ġ
	private static int findAppleCnt(int cnt) {
		for (int y = 1; y < N; y++) {
			for (int x = 1; x < N; x++) {
				if (map[y][x] == 0) {
					continue;
				} else {
					alist[map[y][x]] = new Node(y, x);
				}

				cnt = Math.max(cnt, map[y][x]);
			}
		}
		
		sY = alist[1].y;
		sX = alist[1].x;
		
		return cnt;
	}

	// ������ ����� ��ġ�� �����Ѵ�.
	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}