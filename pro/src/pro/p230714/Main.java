package pro.p230714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private final static int CMD_INIT = 0;
	private final static int CMD_STOCK = 1;
	private final static int CMD_ORDER = 2;

	public static final class Result {
		int mPrice;
		int mPerformance;

		Result() {
			mPrice = 0;
			mPerformance = 0;
		}
	}

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception {
		StringTokenizer st;

		int Q;
		int in, in2, in3, in4;
		int ret;

		Result res;

		int cmd, ans = 0, ans2 = 0;
		boolean okay = false;

		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());

		for (int q = 0; q < Q; ++q) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_INIT:
				in = Integer.parseInt(st.nextToken());
				usersolution.init(in);
				okay = true;
				break;
			case CMD_STOCK:
				in = Integer.parseInt(st.nextToken());
				in2 = Integer.parseInt(st.nextToken());
				in3 = Integer.parseInt(st.nextToken());
				in4 = Integer.parseInt(st.nextToken());

				ret = usersolution.stock(in, in2, in3, in4);
				ans = Integer.parseInt(st.nextToken());

				if (ret != ans)
					okay = false;

				break;

			case CMD_ORDER:
				in = Integer.parseInt(st.nextToken());
				res = usersolution.order(in);
				ans = Integer.parseInt(st.nextToken());
				ans2 = Integer.parseInt(st.nextToken());

				if (res.mPrice != ans || res.mPerformance != ans2)
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
		int TC, MARK;

		System.setIn(new java.io.FileInputStream("src/0714_input.txt"));

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