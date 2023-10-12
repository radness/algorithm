package pro.past.no38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int CMD_INIT = 100;
	private static final int CMD_STOCK = 200;
	private static final int CMD_SHIP = 300;
	private static final int CMD_GET_HEIGHT = 400;
	private static UserSolution userSolution = new UserSolution();
	static BufferedReader br;
	static StringTokenizer st;

	private static void print(int q, String cmd, int ans, int uans, Object... obj) {
		System.out.println("[" + q + "] " + cmd + " | " + ans + "=" + uans + " | " + Arrays.deepToString(obj));
	}

	public static boolean run(BufferedReader br) throws IOException {
		int Q = Integer.parseInt(br.readLine());
		int N, mLoc, mBox;
		int ret = -1, ans;

		boolean okay = false;

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case CMD_INIT:
				N = Integer.parseInt(st.nextToken());
				userSolution.init(N);

				print(q, "init", 0, 0, N);

				okay = true;
				break;
			case CMD_STOCK:
				mLoc = Integer.parseInt(st.nextToken());
				mBox = Integer.parseInt(st.nextToken());
				ret = userSolution.stock(mLoc, mBox);
				ans = Integer.parseInt(st.nextToken());

				print(q, "stock", ans, ret, "mLoc : " + mLoc, "mBox : " + mBox);

				if (ret != ans)
					okay = false;
				break;
			case CMD_SHIP:
				mLoc = Integer.parseInt(st.nextToken());
				mBox = Integer.parseInt(st.nextToken());
				ret = userSolution.ship(mLoc, mBox);
				ans = Integer.parseInt(st.nextToken());

				print(q, "ship", ans, ret, "mLoc : " + mLoc, "mBox : " + mBox);

				if (ret != ans)
					okay = false;
				break;
			case CMD_GET_HEIGHT:
				mLoc = Integer.parseInt(st.nextToken());
				ret = userSolution.getHeight(mLoc);
				ans = Integer.parseInt(st.nextToken());

				print(q, "get height", ans, ret, "mLoc : " + mLoc);

				if (ret != ans)
					okay = false;
				break;
			default:
				okay = false;
				break;
			}
		}

		return okay;
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		System.setIn(new java.io.FileInputStream("src/pro/past/no38/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		int MARK = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; ++tc) {
			boolean result = run(br);
			int score = result ? MARK : 0;
			System.out.println("#" + tc + " " + score);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("걸린 시간 : " + (endTime - startTime) + "ms");
	}
}