package basic.unionFind;

/* �ٵ���
 * 
 * ���� �ٵ��� �����Ѵ�.
 * Ư�� index�� ���� �ϳ��� �д�.
 * ���� �¿�� �پ� �ִ� �� �׷��� "�ٵ���" �̶�� �Ѵ�.
 * 
 * ���� ���������� �� �� ���� �ٵ����� ������ ������ ���Ͽ���.
 * -> Union-Find
 * �ϳ� �� ������ �翷�� ���� ������ �׷��� ���´�.
 * 
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
			// �ڱ� �ڽ��� ����Ű���� �ʱ� ����
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
