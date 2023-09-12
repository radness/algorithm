package pro.past.no34;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private static final int CMD_INIT = 0;
	private static final int CMD_ADD = 1;
	private static final int CMD_DISTANCE = 2;

	private static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception {

		final int Q;
		final int MAX_N = 350;

		StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

		Q = Integer.parseInt(stdin.nextToken());

		int cmd, ans, ret = 0;
		int N, range, id, id2, r, c;
		int[][] region = new int[MAX_N][MAX_N];
		boolean okay = false;

		for (int q = 0; q < Q; ++q) {
			stdin = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(stdin.nextToken());

			switch (cmd) {
			case CMD_INIT:
				N = Integer.parseInt(stdin.nextToken());
				range = Integer.parseInt(stdin.nextToken());
				for (int i = 0; i < N; i++) {
					stdin = new StringTokenizer(br.readLine(), " ");
					for (int j = 0; j < N; j++) {
						region[i][j] = Integer.parseInt(stdin.nextToken());
					}
				}
				usersolution.init(N, range, region);
				okay = true;
				break;
			case CMD_ADD:
				id = Integer.parseInt(stdin.nextToken());
				r = Integer.parseInt(stdin.nextToken());
				c = Integer.parseInt(stdin.nextToken());
				usersolution.add(id, r, c);
				break;
			case CMD_DISTANCE:
				id = Integer.parseInt(stdin.nextToken());
				id2 = Integer.parseInt(stdin.nextToken());
				ans = Integer.parseInt(stdin.nextToken());
				ret = usersolution.distance(id, id2);
				if (ans != ret)
					okay = false;
				break;
			default:
				okay = false;
				break;
			}
		}
		return okay;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

		int TC = Integer.parseInt(stinit.nextToken());
		int MARK = Integer.parseInt(stinit.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}