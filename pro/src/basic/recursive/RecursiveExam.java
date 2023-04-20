package basic.recursive;

public class RecursiveExam {
	static int[] path = new int[3];

	public static void main(String[] args) {
		run(0, 0);
	}

	// �⺻ ���ȣ��
	static void run(int num, int sum) {
		// ���ȣ�� -> ��� ��츦 �� �õ� -> ��� ���� ���ϱ� ������ ������.
		// ����ġ��
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