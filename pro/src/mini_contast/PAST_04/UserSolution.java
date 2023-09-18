package mini_contast.PAST_04;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 인터프리터 IDE2
class UserSolution {

	String cmd; // 현재 커서가 가르키는 명령문
	// 이미 처리가 된 명령문들
	Deque<String> preCmds;
	// 처리된 명령문마다 실행후의 변수값들
	Deque<int[]> memories;
	// 아직 실행되지 않은 명령뭄ㄴ들
	Deque<String> postCmds;

	void init() {
		cmd = null;
		preCmds = new ArrayDeque<>();
		postCmds = new ArrayDeque<>();
		memories = new ArrayDeque<>();
		memories.add(new int[26]); // 변수의 총 갯수 : A, B , C, ... Z(총 26개)
	}

	void destroy() {

	}

	int addCommand(char mCommand[]) {
		if (cmd != null)
			postCmds.addFirst(cmd);
		
		cmd = String.valueOf(mCommand).trim();
		
		return preCmds.size() + 1;
	}

	int moveCursor(int mPos) {
		while (mPos > 0) {
			if (cmd == null) {
				mPos = 0;
				continue;
			}
			
			memories.addLast(execute(cmd, memories.peekLast()));
			preCmds.addLast(cmd);
			
			if (postCmds.isEmpty()) {
				cmd = null;
			} else {
				cmd = postCmds.pollFirst();
			}
			mPos--;
		}
		
		while (mPos < 0) {
			if (preCmds.isEmpty()) {
				mPos = 0;
				continue;
			}
			
			// 취소할 명령문이 있다.
			if (cmd != null) {
				postCmds.addFirst(cmd);
			}
			
			cmd = preCmds.pollLast();
			memories.pollLast();
			
			mPos++;
		}
		
		return preCmds.size() + 1;
	}

	private int[] execute(String cmd, int[] memory) {
		int[] ans = new int[26];
		
		System.arraycopy(memory, 0, ans, 0, 26);
		
		// 변수, 변수, 변수... = 수식, 수식, 수식...
		StringTokenizer st = new StringTokenizer(cmd, "=");
		String variables = st.nextToken();
		String expressins = st.nextToken();
		
		st = new StringTokenizer(variables, ",");
		StringTokenizer st2 = new StringTokenizer(expressins, ",");
		
		while (st.hasMoreTokens()) {
			char var = st.nextToken().charAt(0);
			ans[var - 'A'] = calc(st2.nextToken(), memory); // 수식
			
		}
		
		
		
		return ans;
	}

	private int calc(String expression, int[] memory) {
		// expression () */-+ 0~9 A~Z
		int result = 0;
		int operand = 0;
		char operator = '+'; // 기본 연산자
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') { // 연산자
				result = calc2(operator, result, operand);
				operator = c;
				continue;
			}
			if (c >= '0' && c <= '9') { // 숫자
				operand = c - '0';
				continue;
			}
			if (c >= 'A' && c <= 'Z') { // 변수
				operand = memory[c - 'A'];
				continue;
			}
			if (c == '(') {
				int openCnt = 1;
				
				for (int j = i + 1; j < expression.length(); j++) {
					c = expression.charAt(j);
					if (c == '(') {
						openCnt++;
						continue;
					}
					if (c == ')') {
						openCnt--;
						if (openCnt == 0) {
							operand = calc(expression.substring(i + 1, j), memory);
							i = j;
							break;
						}
					}
				}
			}
		}
		
		result = calc2(operator, result, operand);
		
		
		return result;
	}

	// 사칙연산
	private int calc2(char operator, int operand1, int operand2) {
		int ans = 0;
		
		switch (operator) {
			case '+':
				ans = operand1 + operand2;
				break;
			case '-':
				ans = operand1 - operand2;
				if (ans < 0)
					ans = 0;
				break;
			case '*':
				ans = operand1 * operand2;
				break;
			case '/':
				if (operand2 == 0) {
					ans = 0;
					break;
				}
				ans = operand1 / operand2;
				break;
			default:
				break;
		}
		
		return ans;
	}

	void eraseCommand() {
		if (postCmds.isEmpty()) {
			cmd = null;
		} else {
			cmd = postCmds.pollFirst();
		}
	}

	int getValue(char mVariable) {
		// int[26]
		return memories.peekLast()[mVariable - 'A'];
	}
}