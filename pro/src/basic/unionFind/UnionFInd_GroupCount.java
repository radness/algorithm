package basic.unionFind;

/* �׷��� ���� ����
 * 
 * �׷��� ���� ���� ���
 * dat �迭(isNew)
 * groupCnt ����
*/
public class UnionFInd_GroupCount {
	public static void main(String[] args) {
		new UnionFInd_GroupCount().solution();

	}

	private void solution() {
		UnionFind uf = new UnionFind();

		uf.union('A', 'B');
		System.out.println(uf.groupCnt);
		uf.union('C', 'D');
		System.out.println(uf.groupCnt);
		uf.union('E', 'F');
		System.out.println(uf.groupCnt);
		uf.union('B', 'F');
		System.out.println(uf.groupCnt);
		
		/* 1 2 3 2 */
	}

	static class UnionFind {
		char[] arr = new char[200];
		int[] isNew = new int[200];
		int groupCnt = 0; // �׷� ����

		public UnionFind() {
			// �ڱ� �ڽ��� ����Ű���� �ʱ� ����
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
			}
		}

		char find(char a) {
			if (arr[a] == a)
				return a;
			return arr[a] = find(arr[a]);
		}

		void union(char a, char b) {
			char t1 = find(a);
			char t2 = find(b);

			if (isNew[t1] == 0)
				groupCnt += ++isNew[t1];
			if (isNew[t2] == 0)
				groupCnt += ++isNew[t2];

			// ���� ������� ���� �׷��̹Ƿ� �׷��� ���� �ʿ䰡 ����.
			if (t1 == t2)
				return;

			arr[t2] = t1; // t2�� t1 ��Ʈ�� ����.

			groupCnt -= 1; // �׷��� �ϳ� �پ���.
		}

	}
}
