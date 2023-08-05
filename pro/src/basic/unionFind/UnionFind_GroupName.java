package basic.unionFind;

// 그룹 이름
public class UnionFind_GroupName {
	public static void main(String[] args) {
		new UnionFind_GroupName().solution();

	}

	private void solution() {
		UnionFind uf = new UnionFind();

		uf.setGroupName('A', "����");
		uf.setGroupName('B', "����");
		uf.setGroupName('C', "����");
		uf.setGroupName('D', "����");

		uf.union('A', 'B');
		uf.union('C', 'D');
		uf.union('E', 'F');
		uf.union('B', 'F');

		// F�� ���� �̸��� �������� union ���� "����" �Ҽ��̴�.
		System.out.println(uf.names[uf.find('F')]);
	}

	static class UnionFind {
		char[] arr = new char[200];
		String[] names = new String[200];

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

			if (t1 == t2)
				return;

			arr[t2] = t1;
		}

		// a�� ���� �׷��� �̸��� name�̴�.
		void setGroupName(char a, String name) {
			int target = find(a);
			names[target] = name;
		}
	}
}