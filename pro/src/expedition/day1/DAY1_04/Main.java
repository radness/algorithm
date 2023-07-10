package expedition.day1.DAY1_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Bingo
class Main {
	private final static UserSolution usersolution = new UserSolution();
	private static BufferedReader br;
	
	private static boolean run() throws Exception {
		StringTokenizer st;

		int N;
		int userAns, ans;
		boolean isCorrect = true;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		usersolution.init(N);

		for (int q = 0; q < N; ++q) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				int num = Integer.parseInt(st.nextToken());
				usersolution.mark(num);
			}
			userAns = usersolution.bingo();
			ans = Integer.parseInt(st.nextToken());
			if (userAns != ans)
				isCorrect = false;
		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		int TC, MARK;

		System.setIn(new java.io.FileInputStream("src/expedition/day1/DAY1_04/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run() ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();

		long endTime = System.currentTimeMillis();

		System.out.println("걸린 시간 : " + (endTime - startTime) + "ms");
	}
}