package pro.past.no24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_REMOVE = 3;
	private final static int CMD_INSTALL = 4;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception {
		StringTokenizer st;

		int Q;
		int n, mid, mloc, mstart, mend, m;
		int[] midArr = new int[100];
		int[] mlocArr = new int[100];
		int cmd, ans, userAns = 0;
		boolean isCorrect = false;

		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());

		for (int q = 0; q < Q; ++q) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				isCorrect = true;
				n = Integer.parseInt(st.nextToken());
				for (int i = 0; i < n; i++) {
					midArr[i] = Integer.parseInt(st.nextToken());
					mlocArr[i] = Integer.parseInt(st.nextToken());
				}
				usersolution.init(n, midArr, mlocArr);
				isCorrect = true;
				break;
			case CMD_ADD:
				mid = Integer.parseInt(st.nextToken());
				mloc = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.add(mid, mloc);
				if (ans != userAns)
					isCorrect = false;
				break;
			case CMD_REMOVE:
				mstart = Integer.parseInt(st.nextToken());
				mend = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.remove(mstart, mend);
				if (userAns != ans)
					isCorrect = false;
				break;
			case CMD_INSTALL:
				m = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.install(m);
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
		System.setIn(new java.io.FileInputStream("src/pro/past/no24/sample_input.txt"));
		
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