package expedition.day10.PRO_03;

public class UnionFindExam {
	public static void main(String[] args) throws Exception {

		new UnionFindExam().solution();
	}
	
	void solution() {
		UnionFind uf = new UnionFind();
		uf.union('D', 'A');
		uf.union('F', 'A');
		uf.union('A', 'C');
		uf.union('B', 'A');
		
		if (uf.find('C') == uf.find('A')) {
			System.out.println("같은 그룹");
		} else {
			System.out.println("다른 그룹");
		}
		
	}
	
	static class UnionFind {
		char[] arr = new char[200];
		
		public UnionFind() {
			// 자기 자신을 가르키도록 초기 세팅
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
			}
		}
		
		char find(char a) {
			if (arr[a] == a)
				return a;
			
			char boss = find(arr[a]);
			return boss;
		}
		
		// 경로 압축
		char find2(char a) {
			if (arr[a] == a)
				return a;
			
			char boss = find(arr[a]);
			arr[a] = boss;
			return boss;
		}
		
		// 경로 압축 코드 정리
		char find3(char a) {
			if (arr[a] == a)
				return a;
			
			return arr[a] = find(arr[a]);
		}
		
		void union(char a, char b) {
			char pa = find(a);
			char pb = find(b);
			
			// 같은 보스라면, 같은 그룹이므로 그룹을 지을 필요가 없다.
			if (pa == pb)
				return;
			
			arr[pb] = pa; // pb는 pa 밑으로 들어간다.
		}
	}
}
