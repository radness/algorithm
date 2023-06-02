package basic.unionFind;

public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}

	void solution() {
		UnionFind uf = new UnionFind();

		uf.union('D', 'A');
		uf.union('F', 'A');
		uf.union('A', 'C');

		if (uf.find('C') == uf.find('A')) {
			System.out.println("���� �׷�");
		} else {
			System.out.println("�ٸ� �׷�");
		}
	}

}

class UnionFind {
	char[] arr = new char[200];

	// ������
	public UnionFind() {
		// �ڱ� �ڽ��� ����Ű���� �ʱ� ����
		for (int i = 0; i < 200; i++) {
			arr[i] = (char) i;
		}
	}

	char find(char a) {
		// ���������� return
		if (arr[a] == a)
			return a;

		// ���
//		char boss = find(arr[a]);
//		arr[a] = boss;
//		return boss;

		// ��� ���� �ڵ�
		return arr[a] = find(arr[a]);
	}

	void union(char a, char b) {
		char t1 = find(a);
		char t2 = find(b);

		// ���� �������, ���� �׷��̹Ƿ� �׷��� ���� �ʿ䰡 ����.
		if (t1 == t2)
			return;

		// t2�� t1 ������ ����.
		arr[t2] = t1;
	}
}