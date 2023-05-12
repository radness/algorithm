package basic.recursive;

public class RecursiveExam_used {
	static int[] path = new int[10];
	static int[] used = new int[10];

	public static void main(String[] args) {
		run(0);
	}

	// used ����ġ��
	// �ѹ� ���� ������ �ٽ� ������ �ʵ��� �ϴ°�
	static void run(int num) {
		if (num == 3) {
			for (int i = 0; i < num; i++) {
				System.out.print(path[i]);
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (used[i] == 1)
				continue;
			used[i] = 1;
			path[num] = i + 1;
			run(num + 1);
			path[num] = 0;
			used[i] = 0;
		}
	}
}
