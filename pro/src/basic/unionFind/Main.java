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
		
		if (uf.find('C') == uf.find('A'))
			System.out.println("같은 그룹");
		else
			System.out.println("다른 그룹");
	}

}

class UnionFind {
	char[] arr = new char[200];
	
	// 자기 자신을 가르키도록 초기 셋팅
	public UnionFind() {
		for (int i = 0; i < 200; i++) {
			arr[i] = (char) i;
		}
	}
	
	public void union(char a, char b) {
		char pa = find(a);
		char pb = find(b);
		
		if (pa == pb)
			return;
		
		arr[pb] = pa;
		
	}

	public char find(char a) {
		if (arr[a] == a)
			return a;
		
		return arr[a] = find(arr[a]);
	}
	
}