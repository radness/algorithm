package mini_contast.PRACTICE_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean run(BufferedReader br) throws IOException {

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());

		usersolution.init();

		boolean isCorrect = true; 
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			usersolution.enroll(A, B); 
			int userAns = usersolution.getMidValue(); 
			int ans = Integer.parseInt(st.nextToken());
			if(userAns != ans)
				isCorrect = false; 
		}
		return isCorrect;
	}

	private final static UserSolution usersolution = new UserSolution();

	public static void main(String[] args) throws Exception {

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new java.io.FileInputStream("src/mini_contast/PRACTICE_01/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		int MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; testcase++) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}
		br.close();
	}
}