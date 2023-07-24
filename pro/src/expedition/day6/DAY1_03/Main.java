package expedition.day6.DAY1_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean run(BufferedReader br) throws IOException {

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());

		usersolution.init(s, e, limit);

		boolean isCorrect = true;
		int num;
		int ans;
		int userAns;

		st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		int[] updowncnt = usersolution.guess(num);
		int upcnt = Integer.parseInt(st.nextToken());
		int downcnt = Integer.parseInt(st.nextToken());
		if (updowncnt[0] != upcnt || updowncnt[1] != downcnt)
			isCorrect = false;
		userAns = usersolution.sendResult();
		ans = Integer.parseInt(st.nextToken());
		if (userAns != ans)
			isCorrect = false;
		return isCorrect;
	}

	private final static UserSolution usersolution = new UserSolution();

	public static void main(String[] args) throws Exception {

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

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