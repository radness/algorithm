package pro.p230624;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static final int CMD_INIT = 0;
	private static final int CMD_ADD_ROOM = 1;
	private static final int CMD_SET_CURRENT = 2;
	private static final int CMD_MOVE_DIR = 3;
	private static final int CMD_CHANGE_WORD = 4;

	private static UserSolution userSolution = new UserSolution();

	static BufferedReader br;
	static int T, P;
	static int N;

	public static boolean run() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean ok = true;
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == CMD_INIT) {
				userSolution.init();
			} else if (cmd == CMD_ADD_ROOM) {
				int mID = Integer.parseInt(st.nextToken());
				char[] mWord = st.nextToken().toCharArray();
				int[] mDirLen = new int[3];
				mDirLen[0] = Integer.parseInt(st.nextToken());
				mDirLen[1] = Integer.parseInt(st.nextToken());
				mDirLen[2] = Integer.parseInt(st.nextToken());
				userSolution.addRoom(mID, mWord, mDirLen);
			} else if (cmd == CMD_SET_CURRENT) {
				char[] mWord = st.nextToken().toCharArray();
				userSolution.setCurrent(mWord);
			} else if (cmd == CMD_MOVE_DIR) {
				int mDir = Integer.parseInt(st.nextToken());
				int ans = userSolution.moveDir(mDir);
				int correct = Integer.parseInt(st.nextToken());
				if (ans != correct)
					ok = false;
			} else if (cmd == CMD_CHANGE_WORD) {
				char[] mWord = st.nextToken().toCharArray();
				char[] mChangeWord = st.nextToken().toCharArray();
				int[] mDirLen = new int[3];
				mDirLen[0] = Integer.parseInt(st.nextToken());
				mDirLen[1] = Integer.parseInt(st.nextToken());
				mDirLen[2] = Integer.parseInt(st.nextToken());
				userSolution.changeWord(mWord, mChangeWord, mDirLen);
			}
		}

		return ok;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/pro/p230624/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			int score = run() ? P : 0;
			System.out.println("#" + t + " " + score);
		}

	}
}
