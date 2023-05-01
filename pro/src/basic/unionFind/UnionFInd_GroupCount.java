package basic.unionFind;

/* 그룹의 갯수 관리
 * 
 * 그룹의 갯수 관리 방법
 * dat 배열(isNew)
 * groupCnt 변수
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
		int groupCnt = 0; // 그룹 갯수

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

			if (isNew[t1] == 0)
				groupCnt += ++isNew[t1];
			if (isNew[t2] == 0)
				groupCnt += ++isNew[t2];

			// 같은 보스라면 같은 그룹이므로 그룹을 지을 필요가 없다.
			if (t1 == t2)
				return;

			arr[t2] = t1; // t2는 t1 밑트로 들어간다.

			groupCnt -= 1; // 그룹이 하나 줄었다.
		}

	}
}
