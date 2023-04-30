package graph;

/* 자료구조
 * 배열 : 연속된 데이터 저장
 * 링크드리스트 : 데이터 + 다음 노드 정보 저장
 * 그래프 : 데이터 + 연관된 노드들의 정보 저장
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
			System.out.println(i + "번, " + value[i]);
		}
	}
}
