package expedition.day12.PRO_03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private final static int MAX_N = 50000;
	private final static int MAX_M = 50000;
	private final static int WORD_MAXLEN = 11;

	private final static Solution solution = new Solution();

	private static char[][] words = new char[MAX_M][WORD_MAXLEN];
	private static BufferedReader br;
	private static StringTokenizer st;

	private static boolean run() throws Exception {
		boolean ok = true;
		int N, M;
		int gameCnt;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int m = 0; m < M; m++) {
			words[m] = br.readLine().toCharArray();
		}

		solution.init(N, M, words);

		gameCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < gameCnt; i++) {
			int playerId, ret, ans;
			char startChar;
			st = new StringTokenizer(br.readLine());
			playerId = Integer.parseInt(st.nextToken());
			startChar = st.nextToken().charAt(0);

			ret = solution.playGame(playerId, startChar);

			ans = Integer.parseInt(st.nextToken());
			if (ret != ans)
				ok = false;
		}
		return ok;
	}

	public static void main(String[] args) throws Exception {
		int T, SUCCESS;
		System.setIn(new FileInputStream("src/expedition/day12/PRO_03/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		SUCCESS = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			int score = run() ? SUCCESS : 0;
			System.out.println("#" + tc + " " + score);
		}
		br.close();
	}
}