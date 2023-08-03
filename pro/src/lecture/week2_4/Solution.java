package lecture.week2_4;

// 그룹의 갯수 관리
public class Solution {
	public static void main(String[] args) throws Exception {
		UnionFind uf = new UnionFind();

		uf.union('A', 'B');
		System.out.println("그룹 갯수" + uf.groupCnt);
		uf.union('C', 'D');
		System.out.println("그룹 갯수" + uf.groupCnt);
		uf.union('E', 'F');
		System.out.println("그룹 갯수" + uf.groupCnt);
		uf.union('B', 'F');
		System.out.println("그룹 갯수" + uf.groupCnt);
		uf.union('A', 'C');
		System.out.println("그룹 갯수" + uf.groupCnt);

	}

	static class UnionFind {

		char[] arr = new char[200];
		int[] isNew = new int[200];
		int groupCnt = 0;

		public UnionFind() {
			// 자기 자신을 가르키도록 초기 셋팅
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
//				System.out.print(arr[i] + " ");
			}
		}

		char find(char a) {
			if (arr[a] == a)
				return a;
			
			return arr[a] = find(arr[a]);
		}

		void union(char a, char b) {
			char team1 = find(a);
			char team2 = find(b);
			
			if (isNew[team1] == 0)
				groupCnt += ++isNew[team1];
			
			if (isNew[team2] == 0)
				groupCnt += ++isNew[team2];
			
			// 같은 보스라면 같은 그룹이므로 그룹을 지울 필요가 없다.
			if (team1 == team2)
				return;
			
			// team2는 team1 밑으로 들어간다.
			arr[team2] = team1;
			
			groupCnt -= 1; // 그룹이 하나 줄었다.
		}
		
	}
}
