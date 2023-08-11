package pro.past.no12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 버스 노선 관리
class Main {
	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_MIN_TRAVEL_TIME = 3;
	private final static int CMD_MIN_TRANSFER = 4;

	private final static UserSolution usersolution = new UserSolution();

	private final static int numberOfLine = 5;
	private static int[] mLastStation1 = new int[numberOfLine];
	private static int[] mLastStation2 = new int[numberOfLine];

	private static boolean run(BufferedReader br) throws Exception {
		StringTokenizer st;

		int numQuery;

		int N;
		int mLine, mPrevStation, mStation;
		int mStartStation, mEndStation;

		int userAns, ans;

		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());

		for (int q = 0; q < numQuery; ++q) {
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				N = Integer.parseInt(st.nextToken());
				for (int i = 0; i < numberOfLine; i++)
					mLastStation1[i] = Integer.parseInt(st.nextToken());
				for (int i = 0; i < numberOfLine; i++)
					mLastStation2[i] = Integer.parseInt(st.nextToken());
				usersolution.init(N, mLastStation1, mLastStation2);
				isCorrect = true;
				break;
			case CMD_ADD:
				mLine = Integer.parseInt(st.nextToken());
				mPrevStation = Integer.parseInt(st.nextToken());
				mStation = Integer.parseInt(st.nextToken());
				usersolution.add(mLine, mPrevStation, mStation);
				break;
			case CMD_MIN_TRAVEL_TIME:
				mStartStation = Integer.parseInt(st.nextToken());
				mEndStation = Integer.parseInt(st.nextToken());
				userAns = usersolution.minTravelTime(mStartStation, mEndStation);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			case CMD_MIN_TRANSFER:
				mStartStation = Integer.parseInt(st.nextToken());
				mEndStation = Integer.parseInt(st.nextToken());
				userAns = usersolution.minTransfer(mStartStation, mEndStation);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new FileInputStream("src/pro/past/no12/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}