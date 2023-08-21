package mini_contast.PRACTICE_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private final static int CMD_ADD = 1;
	private final static int CMD_SPAN = 2;
	private final static int CMD_GET_LIFE = 3;

	private static boolean run(BufferedReader br) throws IOException {

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());

		usersolution.init();

		boolean isCorrect = true;
		int cmd;
		int id;
		int life;
		int year;
		int userAns;
		int ans;

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case CMD_ADD:
				id = Integer.parseInt(st.nextToken());
				life = Integer.parseInt(st.nextToken());
				usersolution.add(id, life);
				break;

			case CMD_SPAN:
				year = Integer.parseInt(st.nextToken());
				userAns = usersolution.span(year);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
					isCorrect = false;
				break;

			case CMD_GET_LIFE:
				id = Integer.parseInt(st.nextToken());
				userAns = usersolution.getLife(id);
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

	private final static UserSolution usersolution = new UserSolution();

	public static void main(String[] args) throws Exception {

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new java.io.FileInputStream("src/mini_contast/PRACTICE_02/sample_input.txt"));
		
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