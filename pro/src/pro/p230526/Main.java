package pro.p230526;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 단어게임
class Main {
	private final static int CMD_INIT = 100;
	private final static int CMD_JOIN = 200;
	private final static int CMD_PLAY = 300;
	private final static int CMD_LEAVE = 400;
	static int testcase = 0;
	private final static UserSolution usersolution = new UserSolution();

	static void print(int q, String cmd, int userans, int ans, Object... obj) {
//    	if(userans!=ans) System.out.println("===============================틀렸음");
//    	System.out.println("["+q+"/"+cmd+"]"+userans+" "+ans+"="+Arrays.toString(obj));

//    	if(userans!=ans) {
//    		System.out.println("===============================틀렸음");
//    		System.out.println("["+q+"/"+cmd+"]"+userans+" "+ans+"="+Arrays.toString(obj));
//    	}
//    	if(testcase==2) {
//    		if(q==58) {
//    			System.out.println("aa");
//    		}
//    		if(userans!=ans) System.out.println("===============================틀렸음");
//    		System.out.println("["+q+"/"+cmd+"]"+userans+" "+ans+"="+Arrays.toString(obj));
//    	}
	}

	private static boolean run(BufferedReader br) throws Exception {
		StringTokenizer st;

		int Q;
		int n, mid, M, mstart, mend, m;
		char[][] mWordList = new char[10000][11];
		char[][] mSubjectList = new char[10000][11];
		int[] mCardList = new int[1500];
		char[] mBeginStr = new char[3];
		char[] mSubject = new char[21];
		int cmd, ans = 0, userAns = 0;
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
				StringBuilder sb1 = new StringBuilder();
				for (int i = 0; i < n; i++) {
					char[] arr = st.nextToken().toCharArray();
					for (int x = 0; x <= 10; x++) {
						if (x < arr.length)
							mWordList[i][x] = arr[x];
						else
							mWordList[i][x] = '\0';
					}
					sb1.append(new String(arr) + "/");
				}
				StringBuilder sb2 = new StringBuilder();
				for (int i = 0; i < n; i++) {
					char[] arr = st.nextToken().toCharArray();
					for (int x = 0; x <= 10; x++) {
						if (x < arr.length)
							mSubjectList[i][x] = arr[x];
						else
							mSubjectList[i][x] = '\0';
					}
					sb2.append(new String(arr) + "/");
				}
				usersolution.init(n, mWordList, mSubjectList);
//                    isCorrect = true;
				print(q, "init", 0, 0, n, sb1, sb2);
				break;
			case CMD_JOIN:
				mid = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				for (int i = 0; i < M; i++) {
					mCardList[i] = Integer.parseInt(st.nextToken());
				}
				usersolution.join(mid, M, mCardList);
				print(q, "join", 0, 0, mid, M, Arrays.toString(mCardList));
//                    isCorrect = true;
				break;
			case CMD_PLAY:
				char[] arr = st.nextToken().toCharArray();
				for (int x = 0; x <= 2; x++) {
					if (x < arr.length)
						mBeginStr[x] = arr[x];
					else
						mBeginStr[x] = '\0';
				}
				arr = st.nextToken().toCharArray();
				for (int x = 0; x <= 10; x++) {
					if (x < arr.length)
						mSubject[x] = arr[x];
					else
						mSubject[x] = '\0';
				}
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.playRound(mBeginStr, mSubject);
				print(q, "playRound", userAns, ans, new String(mBeginStr), new String(mSubject));
				if (userAns != ans)
					isCorrect = false;
				break;
			case CMD_LEAVE:
				mid = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());
				userAns = usersolution.leave(mid);
				print(q, "leave", userAns, ans, mid);
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
		int TC, MARK;

		System.setIn(new java.io.FileInputStream("src/pro/p230526/sample_input.txt"));
//        System.setIn(new java.io.FileInputStream("단어게임_fail_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (testcase = 1; testcase <= TC; ++testcase)
//        for (int testcase = 1; testcase <= 1; ++testcase)
		{
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}