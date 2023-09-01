package pro.past.no20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_REMOVE = 3;
	private final static int CMD_PRODUCE = 4;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception {
		StringTokenizer st;

		int Q;
		int n, mid, mtime, k, m;
		int[] midArr = new int[100];
		int[] mtimeArr = new int[100];
		int cmd, ans, userAns;
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
					mtimeArr[i] = Integer.parseInt(st.nextToken());
				}
				usersolution.init(n, midArr, mtimeArr);
				break;
			case CMD_ADD:
				mid = Integer.parseInt(st.nextToken());
				mtime = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.add(mid, mtime);
				if (ans != userAns)
					isCorrect = false;
				break;
			case CMD_REMOVE:
				k = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.remove(k);
				if (userAns != ans)
					isCorrect = false;
				break;
			case CMD_PRODUCE:
				m = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.produce(m);
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
		long startTime = System.currentTimeMillis();
		int TC, MARK;

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new java.io.FileInputStream("src/pro/past20/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
		long endTime = System.currentTimeMillis();
		System.out.println("걸린시간 : " + (endTime - startTime) + "ms");
	}
}