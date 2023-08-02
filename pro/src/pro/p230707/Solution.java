package pro.p230707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_REMOVE = 3;
	private final static int CMD_REORGANIZE = 4;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private final static pro.p230707.UserSolution usersolution = new UserSolution();

	static void print(int q, String cmd, int userans, int ans, Object... obj) {
		if (userans != ans) {
//			System.out.println("===============================틀렸음");
//			System.out.println("[" + q + "/" + cmd + "]" + userans + " " + ans + "/" + "=" + Arrays.toString(obj));
		}

	}

	private static boolean run() throws Exception {

		int q = Integer.parseInt(br.readLine());
		int mid, mnum, mparent, m, k;
		int cmd, ans, ret = 0;
		boolean okay = true;

		for (int i = 0; i < q; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {

			case CMD_INIT:
				mid = Integer.parseInt(st.nextToken());
				mnum = Integer.parseInt(st.nextToken());
				usersolution.init(mid, mnum);
				print(i, "init", 0, 0, mid, mnum);
				break;

			case CMD_ADD:
				mid = Integer.parseInt(st.nextToken());
				mnum = Integer.parseInt(st.nextToken());
				mparent = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				ret = usersolution.add(mid, mnum, mparent);
				print(i, "add", ret, ans, mid, mnum, mparent);
				if (ret != ans)
					okay = false;
				break;

			case CMD_REMOVE:
				mid = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				ret = usersolution.remove(mid);
				print(i, "remove", ret, ans, mid);
				if (ret != ans)
					okay = false;
				break;

			case CMD_REORGANIZE:
				m = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				ret = usersolution.reorganize(m, k);
				print(i, "reorganize", ret, ans, m, k);
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

	public static void main(String[] args) throws Exception {
		long stime = System.currentTimeMillis();
		int T, MARK;
		System.setIn(new java.io.FileInputStream("src/pro/p230707/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			int score = run() ? MARK : 0;
			System.out.println("#" + tc + " " + score);
		}
		br.close();
		System.out.println(System.currentTimeMillis() - stime + "ms");
		System.out.println(Solution.class.getPackage());
	}

}
