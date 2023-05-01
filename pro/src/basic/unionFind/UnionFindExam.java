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
		// 구현 필요
		char[] arr = new char[200];

		// 자기 자신을 가르키도록 초기 설정(생성자 설정)
		public UnionFind() {
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
			}
		}

		// 재귀호출
		char find(char a) {
			if (arr[a] == a)
				return a;

			/*
			char boss = find(arr[a]);
			// 경로 압축 코드
			arr[a] = boss;

			return boss;
			*/
	
			// 경로 압축 코드 정리
			return arr[a] = find(arr[a]);
		}

		void union(char a, char b) {
			char t1 = find(a); // find를 하면 최종 보스를 알려준다.
			char t2 = find(b);

			// 같은 보스라면 같은 그룹이므로 그룹을 지을 필요가 없다.
			if (t1 == t2)
				return;

			arr[t2] = t1; // t2는 t1 밑으로 들어간다.
		}
	}
}
