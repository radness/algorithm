package expedition.day5.PRO_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private final static int CMD_INIT = 100;
	private final static int CMD_DESTROY = 200;
	private final static int CMD_UPDATE = 300;
	private final static int CMD_UPDATE_JOB = 400;
	private final static int CMD_MOVE = 500;
	private final static int MAX_N = 100000;

	private final static UserSolution userSolution = new UserSolution();
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;

	private static boolean run() throws Exception {

		boolean isCorrect = false;
		int N;
		int cmd;
		int mN, mM, mJ;
		int mID, mJobID;
		int mPoint, mNum;
		int userAns, ans;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int q = 0; q < N; q++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				mN = Integer.parseInt(st.nextToken());
				mM = Integer.parseInt(st.nextToken());
				mJ = Integer.parseInt(st.nextToken());
				int[] Point = new int[MAX_N];
				int[] JobID = new int[MAX_N];
				for (int i = 0; i < mN; i++)
					Point[i] = Integer.parseInt(st.nextToken());
				for (int i = 0; i < mN; i++)
					JobID[i] = Integer.parseInt(st.nextToken());
				userSolution.init(mN, mM, mJ, Point, JobID);
				isCorrect = true;
				break;

			case CMD_UPDATE:
				mID = Integer.parseInt(st.nextToken());
				mPoint = Integer.parseInt(st.nextToken());
				userAns = userSolution.update(mID, mPoint);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_UPDATE_JOB:
				mJobID = Integer.parseInt(st.nextToken());
				mPoint = Integer.parseInt(st.nextToken());
				userAns = userSolution.updateByJob(mJobID, mPoint);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_MOVE:
				mNum = Integer.parseInt(st.nextToken());
				userAns = userSolution.move(mNum);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			default:
				isCorrect = false;
				break;
			}

		}
		userSolution.destroy();
		return isCorrect;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; testcase++) {
			int score = run() ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}
		br.close();
	}
}