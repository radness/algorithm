package pro.past.no30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	private static final int CMD_INIT = 0;
	private static final int CMD_STOCK = 1;
	private static final int CMD_ORDER = 2;

	public static final class Result {
		int mPrice;
		int mPerformance;

		Result() {
			mPrice = 0;
			mPerformance = 0;
		}
	}

	private static UserSolution usersolution = new UserSolution();

	static void print(int q, String cmd, int ans1, int uans1, int ans2, int uans2, Object... obj) {
		if (ans1 != uans1 || ans2 != uans2)
			System.out.println("----------fail----------");
		System.out.println("[" + q + "] " + cmd + " / " + ans1 + "=" + uans1 + ", " + ans2 + "=" + uans2 + " "
				+ Arrays.deepToString(obj));
	}

	private static boolean run(BufferedReader br) throws Exception {
		int Q;

		StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

		Q = Integer.parseInt(stdin.nextToken());

		boolean okay = false;

		for (int q = 0; q < Q; ++q) {
			stdin = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(stdin.nextToken());
			int ret, ans, ans2, in, in2, in3, in4;
			Result res;

			switch (cmd) {
			case CMD_INIT:
				in = Integer.parseInt(stdin.nextToken());
				usersolution.init(in);
				okay = true;
				print(q, "init", 0, 0, 0, 0, in);
				break;
			case CMD_STOCK:
				in = Integer.parseInt(stdin.nextToken());
				in2 = Integer.parseInt(stdin.nextToken());
				in3 = Integer.parseInt(stdin.nextToken());
				in4 = Integer.parseInt(stdin.nextToken());
				ret = usersolution.stock(in, in2, in3, in4);
				ans = Integer.parseInt(stdin.nextToken());
				print(q, "stock", ans, ret, 0, 0, in, in2, in3, in4);
				if (ret != ans)
					okay = false;
				break;
			case CMD_ORDER:
				in = Integer.parseInt(stdin.nextToken());
				res = usersolution.order(in);
				ans = Integer.parseInt(stdin.nextToken());
				ans2 = Integer.parseInt(stdin.nextToken());
				print(q, "order", ans, res.mPrice, ans2, res.mPerformance, in);
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
		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		System.setIn(new java.io.FileInputStream("src/pro/past/no30/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stinit = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(stinit.nextToken());
		int MARK = Integer.parseInt(stinit.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}