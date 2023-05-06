package basic.recursive;

public class RecursiveExam3 {
	static int[] path = new int[3];

	public static void main(String[] args) {
		run(0);
	}

	private static void run(int level) {
		if (level == 3) {
			for (int i = 0; i < level; i++) {
				System.out.print(path[i]);
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			path[level] = i + 1;
			run(level + 1);
			path[level] = 0;
		}
	} 
}
