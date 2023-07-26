package expedition.day8.DAY3_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private final static int CMD_INIT = 1;
	private final static int CMD_DIG = 2;
	private final static int CMD_GET_SIZE = 3;
	private final static int CMD_GET_CNT = 4;

	private final static UserSolution usersolution = new UserSolution();
	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean run() throws Exception {
		StringTokenizer st;
		int Q;
		int y;
		int x;
		int userAns, ans;
		boolean isCorrect = false;

		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());

		for (int q = 0; q < Q; ++q) {
			st = new StringTokenizer(br.readLine());
			int cmd;
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case CMD_INIT:
				isCorrect = true;
				int N = Integer.parseInt(st.nextToken());
				int[][] MAP = new int[N][N];
				for (int i = 0; i < N; i++) {
					st = new StringTokenizer(br.readLine());
					for (int j = 0; j < N; j++)
						MAP[i][j] = Integer.parseInt(st.nextToken());
				}
				usersolution.init(N, MAP);
				break;

			case CMD_DIG:
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				usersolution.dig(y, x);
				break;

			case CMD_GET_SIZE:
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				userAns = usersolution.getSize(y, x);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_GET_CNT:
				userAns = usersolution.getHoleCnt();
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
		int TC, MARK;

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run() ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}