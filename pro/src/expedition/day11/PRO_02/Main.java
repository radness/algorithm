package expedition.day11.PRO_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private final static int ADDROOM = 100;
	private final static int FINDROOM = 200;
	private final static int RISEPRICES = 300;
	private final static int END = 400;

	private final static UserSolution usersolution = new UserSolution();
//	private final static UserSolution2 usersolution = new UserSolution2();
	
	private static BufferedReader br;

	private static boolean run() throws Exception {

		StringTokenizer st;
		boolean isCorrect = true;
		int cmd, user_ans, correct_ans;
		int roomCnt[], hotelID = 0, roomID = 0, roomInfo[] = new int[5], requirementsInfo[] = new int[6];

		int n;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		roomCnt = new int[n];
		for (int i = 0; i < n; i++)
			roomCnt[i] = Integer.parseInt(st.nextToken());
		usersolution.init(n, roomCnt);
		for (int q = 0;; ++q) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case ADDROOM:
				hotelID = Integer.parseInt(st.nextToken());
				roomID = Integer.parseInt(st.nextToken());
				for (int i = 0; i < 5; i++)
					roomInfo[i] = Integer.parseInt(st.nextToken());
				usersolution.addRoom(hotelID, roomID, roomInfo);
				break;
			case FINDROOM:
				for (int i = 0; i < 6; i++)
					requirementsInfo[i] = Integer.parseInt(st.nextToken());
				user_ans = usersolution.findRoom(requirementsInfo);
				correct_ans = Integer.parseInt(st.nextToken());
				if (user_ans != correct_ans)
					isCorrect = false;
				break;
			case RISEPRICES:
				hotelID = Integer.parseInt(st.nextToken());
				user_ans = usersolution.risePrices(hotelID);
				correct_ans = Integer.parseInt(st.nextToken());
				if (user_ans != correct_ans)
					isCorrect = false;
				break;
			case END:
				return isCorrect;
			default:
				isCorrect = false;
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		int TC, MARK;

		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		System.setIn(new java.io.FileInputStream("src/expedition/day11/PRO_02/sample_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run() ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
		
		System.out.println("걸린 시간 : " + (System.currentTimeMillis() - startTime) + "ms");
	}
}