package basic.unionFind;

// 그룹 이름 관리
public class UnionFind_GroupName {
	public static void main(String[] args) {
		new UnionFind_GroupName().solution();

	}

	private void solution() {
		UnionFind uf = new UnionFind();

		uf.setGroupName('A', "양파");
		uf.setGroupName('B', "대파");
		uf.setGroupName('C', "쪽파");
		uf.setGroupName('D', "실파");

		uf.union('A', 'B');
		uf.union('C', 'D');
		uf.union('E', 'F');
		uf.union('B', 'F');

		// F는 조직 이름이 없었으나 union 이후 "양파" 소속이다.
		System.out.println(uf.names[uf.find('F')]);
	}

	static class UnionFind {
		char[] arr = new char[200];
		String[] names = new String[200];

		public UnionFind() {
			// 자기 자신을 가르키도록 초기 세팅
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

		// a에 속한 그룹의 이름은 name이다.
		void setGroupName(char a, String name) {
			int target = find(a);
			names[target] = name;
		}
	}
}