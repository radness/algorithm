package basic.recursive;

public class RecursiveExam {
	static int[] path = new int[3];

	public static void main(String[] args) {
		run(0, 0);
	}

	// 기본 재귀호출
	static void run(int num, int sum) {
		// 재귀호출 -> 모든 경우를 다 시도 -> 모든 답을 구하기 때문에 느리다.
		// 가지치기
		if (sum > 10)
			return;
		
		if (num == 3) {
			for (int i = 0; i < num; i++) {
				System.out.print(path[i]);
			}
			System.out.println(" sum : " + sum);
			return;
		}

		for (int i = 0; i < 6; i++) {
			path[num] = i + 1;

			run(num + 1, sum + i);
			
			path[num] = 0;
		}
	}
}
