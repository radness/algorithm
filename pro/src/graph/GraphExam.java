package graph;

/* �ڷᱸ��
 * �迭 : ���ӵ� ������ ����
 * ��ũ�帮��Ʈ : ������ + ���� ��� ���� ����
 * �׷��� : ������ + ������ ������ ���� ����
 * 
*/
public class GraphExam {
	static int[][] connect = {
			{0,0,0,1,1},
			{1,0,1,0,0},
			{1,0,0,0,1},
			{0,0,0,0,1},
			{0,0,0,0,0},
			
	};

	static int[] value = {4,1,5,3,7};
	
	public static void main(String[] args) throws Exception {
		run();
	}
	
	static void run() {
		int tar = 2;
		
		for (int i = 0; i < 5; i++) {
			if (connect[tar][i] == 0) continue;
			System.out.println(i + "��, " + value[i]);
		}
	}
}
