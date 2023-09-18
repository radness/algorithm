package mini_contast.PAST_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_MOVE = 300;
	private final static int CMD_ERASE = 400;
	private final static int CMD_GET = 500;

	private static int Score = 0;
	private static BufferedReader br;
	private static StringTokenizer st;
	private static UserSolution usersolution = new UserSolution();

	static final int MAXL = 200;

	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i)
			b[i] = s.charAt(i);
		for (int i = n; i < MAXL; ++i)
			b[i] = '\0';
	}

	private static void cmd_init() throws Exception {
		usersolution.init();
	}

	private static void cmd_add() throws Exception {
		char[] command = new char[200];
		String2Char(st.nextToken(), command);
		int result, check;
		result = usersolution.addCommand(command);
		check = Integer.parseInt(st.nextToken());
		if (result != check)
			Score = 0;
	}

	private static void cmd_move() throws Exception {
		int pos, result, check;
		pos = Integer.parseInt(st.nextToken());
		result = usersolution.moveCursor(pos);
		check = Integer.parseInt(st.nextToken());
		if (result != check)
			Score = 0;
	}

	private static void cmd_erase() throws Exception {
		usersolution.eraseCommand();
	}

	private static void cmd_get() throws Exception {
		int result, check;
		char variable;
		String inputStr = st.nextToken();
		variable = inputStr.charAt(0);
		result = usersolution.getValue(variable);
		check = Integer.parseInt(st.nextToken());
		if (result != check)
			Score = 0;
	}

	private static int run() throws Exception {
		int N;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Score = 1;
		for (int i = 0; i < N; ++i) {
			int cmd;
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case CMD_INIT:
				cmd_init();
				break;
			case CMD_ADD:
				cmd_add();
				break;
			case CMD_MOVE:
				cmd_move();
				break;
			case CMD_ERASE:
				cmd_erase();
				break;
			case CMD_GET:
				cmd_get();
				break;
			default:
				break;
			}
		}
		usersolution.destroy();
		return Score;
	}

	public static void main(String[] args) throws Exception {
		int T, MARK;
		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new java.io.FileInputStream("src/mini_contast/PAST_04/sample_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			if (run() == 1)
				System.out.println("#" + tc + " " + MARK);
			else
				System.out.println("#" + tc + " " + 0);
		}
		br.close();
	}
}