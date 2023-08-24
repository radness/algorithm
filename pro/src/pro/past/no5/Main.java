package pro.past.no5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private final static int INIT = 1;
	private final static int OCCUR = 2; 
	private final static int CANCEL = 3; 
	private final static int POSITION = 4; 

	private final static Solution2 userSolution = new Solution2();

	private static BufferedReader br;
	private static StringTokenizer st;
	
	static void print(int i, String cmd, int ans, int uans, Object... obj) {
		if (ans != uans) System.out.println("---------- fail ----------");
		System.out.println("[" + i + "] " + cmd + " / " + ans + " / " + uans + " / " + Arrays.deepToString(obj));
	}

	private static boolean run() throws Exception {
		int numQuery, cmd; 
		int N, timeStamp, caseID, cityID, prior;
		int userAns, ans; 
		boolean isCorrect = false;
		int[] parent = new int[350];
		
		st = new StringTokenizer(br.readLine());
		numQuery = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < numQuery; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd)
			{
			case INIT:
				N = Integer.parseInt(st.nextToken());
				StringBuilder sb = new StringBuilder();

				for(int j = 0; j < N; j++) {
					parent[j] = Integer.parseInt(st.nextToken());
					sb.append(parent[j] + " ");
				}

				print(i, "init", 0, 0, N, parent);

				userSolution.init(N, parent); 
				
				isCorrect = true;
				break;
			case OCCUR :
				timeStamp = Integer.parseInt(st.nextToken());
				caseID = Integer.parseInt(st.nextToken());
				cityID = Integer.parseInt(st.nextToken());
				prior = Integer.parseInt(st.nextToken());
				userSolution.occur(timeStamp, caseID, cityID, prior);
				
				print(i, "occur", 0, 0, timeStamp, caseID, cityID, prior);
				
				break; 
			case CANCEL :
				timeStamp = Integer.parseInt(st.nextToken());
				caseID = Integer.parseInt(st.nextToken());
				userSolution.cancel(timeStamp, caseID);
				
				print(i, "cancel", 0, 0, timeStamp, caseID);
				
				break;
			case POSITION : 
				timeStamp = Integer.parseInt(st.nextToken());
				userAns = userSolution.position(timeStamp);
				ans = Integer.parseInt(st.nextToken());
				
				print(i, "position", ans, userAns, timeStamp);
				
				if(userAns != ans)
					isCorrect = false;
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		return isCorrect; 
	}

	public static void main(String[]args) throws Exception {
		int T, SUCCESS;
		System.setIn(new java.io.FileInputStream("src/pro/past/no5/sample_input.txt"));
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