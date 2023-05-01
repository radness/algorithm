package basic.unionFind;

public class UnionFindExam {
	public static void main(String[] args) throws Exception {
		new UnionFindExam().solution();
	}

	private void solution() {
		UnionFind uf = new UnionFind();

		uf.union('D', 'A');
		uf.union('F', 'A');
		uf.union('A', 'C');

		if (uf.find('C') == uf.find('A')) {
			System.out.println("same group");
		} else {
			System.out.println("another group");
		}
	}

	static class UnionFind {
		// ���� �ʿ�
		char[] arr = new char[200];

		// �ڱ� �ڽ��� ����Ű���� �ʱ� ����(������ ����)
		public UnionFind() {
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
			}
		}

		// ���ȣ��
		char find(char a) {
			if (arr[a] == a)
				return a;

			/*
			char boss = find(arr[a]);
			// ��� ���� �ڵ�
			arr[a] = boss;

			return boss;
			*/
	
			// ��� ���� �ڵ� ����
			return arr[a] = find(arr[a]);
		}

		void union(char a, char b) {
			char t1 = find(a); // find�� �ϸ� ���� ������ �˷��ش�.
			char t2 = find(b);

			// ���� ������� ���� �׷��̹Ƿ� �׷��� ���� �ʿ䰡 ����.
			if (t1 == t2)
				return;

			arr[t2] = t1; // t2�� t1 ������ ����.
		}
	}
}
