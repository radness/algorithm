package basic.unionFind;

/* 자료구조
 * => 데이터를 저장하고 관리하는 방법
 * 배열 : 값을 저장, 순차적으로 저장
 * 링크드리스트 : 값을 저장, 순차적으로 저장
 * 그래프 : 값을 저장, 노드 관계 정보드 함께 저장
 * Union-Find
 * 값을 저장, 노드 끼리 그룹 정보도 함꼐 저장
 * 
 * 각 그룹에는 대표 노드가 존재한다.
 * 대표노드는 하나의 그룹을 상징한다.
 * 
 * 그룹 합병
 * 대표가 아니더라도 그룹원 한 사람이, 다른 그룹원 사람과 union이 되면
 * 그룹 자체가 union이 된다.
 * 
 * [중요] Boss 선정 규칙
 * Union(A, B)를 수행하면 아래 순서대로 진행된다.
 * 1. A의 보스와 B의 보스를 각각 찾아낸다.
 * 2. B의 보스가 A의 보스 밑으로 들어가고, A는 통합된 조직의 Boss 이다.
 * 
 * Union-Find는 tree로 관리된다.
 *   
 * 
 * */
public class UnionFindExam {
	public static void main(String[] args) throws Exception {
		new UnionFindExam().solution();
	}

	void solution() {
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
