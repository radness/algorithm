package pro.p230512;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution230512 {

	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_REMOVE = 3;
	private final static int CMD_ANNOUNCE = 4;

	private final static UserSolution230512 userSolution = new UserSolution230512();
	public static StringTokenizer st;

	private static boolean run(BufferedReader br) throws Exception {

		boolean isCorrect = false;
		int N;
		int cmd;
		int mId, mStart, mEnd, mDuration, M;
		int userAns, ans;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int q = 0; q < N; q++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				userSolution.init();
				isCorrect = true;
				break;

			case CMD_ADD:
				mId = Integer.parseInt(st.nextToken());
				mStart = Integer.parseInt(st.nextToken());
				mEnd = Integer.parseInt(st.nextToken());
				userAns = userSolution.add(mId, mStart, mEnd);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_REMOVE:
				mId = Integer.parseInt(st.nextToken());
				userAns = userSolution.remove(mId);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_ANNOUNCE:
				mDuration = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				userAns = userSolution.announce(mDuration, M);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			default:
				isCorrect = false;
				break;
			}

		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("공지사항_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC, MARK;

		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; testcase++) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}
		br.close();
	}
}