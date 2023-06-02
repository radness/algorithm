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
			System.out.println("같은 그룹");
		} else {
			System.out.println("다른 그룹");
		}
	}

}

class UnionFind {
	char[] arr = new char[200];

	// 생성자
	public UnionFind() {
		// 자기 자신을 가르키도록 초기 셋팅
		for (int i = 0; i < 200; i++) {
			arr[i] = (char) i;
		}
	}

	char find(char a) {
		// 최종보스면 return
		if (arr[a] == a)
			return a;

		// 재귀
//		char boss = find(arr[a]);
//		arr[a] = boss;
//		return boss;

		// 경로 압축 코드
		return arr[a] = find(arr[a]);
	}

	void union(char a, char b) {
		char t1 = find(a);
		char t2 = find(b);

		// 같은 보스라면, 같은 그룹이므로 그룹을 지울 필요가 없다.
		if (t1 == t2)
			return;

		// t2는 t1 밑으로 들어간다.
		arr[t2] = t1;
	}
}