package basic.unionFind;

/* 바둑집
 *
 * 가로로 바둑이 존재한다.
 * 특정 index에 돌을 하나씩 둔다.
 * 돌이 좌우로 붙어 있는 한 그룹을 "바둑 집" 이라고 한다.
 * 
 * -> 돌을 순차적으로 둘 때 마다 바둑집의 갯수를 구하여라.
 * 정답 : 1 2 3 2 3
*/
public class UnionFInd_BadukHouse {
	public static void main(String[] args) {
		new UnionFInd_BadukHouse().solution();
	}

	private void solution() {
		UnionFind uf = new UnionFind();
		
		
	}
	
	static class UnionFind {
		char[] arr = new char[200];
		
		public UnionFind() {
			// 자기 자신을 가르키도록 초기 셋팅
			for (int i = 0; i < 200; i++) {
				arr[i] = (char) i;
			}
		}
		
		void union() {
			
		}
		
		char find(char a) {
			return a;
		}
		
	}
}
