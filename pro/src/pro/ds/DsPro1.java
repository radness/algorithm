package pro.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DsPro1 {
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_REMOVE = 300;
	private final static int CMD_CROSS = 400;
	private final static int CMD_LINE = 500;

	private final static DsPro1UserSolution usersolution = new DsPro1UserSolution();

	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		// #1 100
		System.setIn(new java.io.FileInputStream("src/pro/ds/DsPro1_sample_input.txt"));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run() ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}

	private static boolean run() throws Exception {
		StringTokenizer st;

		int numQuery;
		int x, y, ID;
		int userAns, ans;
		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());

		for (int q = 0; q < numQuery; ++q) {
			st = new StringTokenizer(br.readLine());

			int cmd;
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_ADD:
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				usersolution.add(x, y);
				break;
			case CMD_REMOVE:
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				usersolution.remove(x, y);
				break;
			case CMD_CROSS:
				ID = Integer.parseInt(st.nextToken());
				userAns = usersolution.getCrossCnt(ID);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			case CMD_LINE:
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				userAns = usersolution.getID(x, y);
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

}
