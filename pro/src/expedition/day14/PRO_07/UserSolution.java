package expedition.day14.PRO_07;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;

// 문자열 관리 프로그램
class UserSolution {
	
	// 문자열 앞, 뒤로 넣을 수 있는 자료구조
	static ArrayDeque<Character> dq;
	// substring에 대한 갯수 관리(1~4 길이)
	static HashMap<String, Integer> hmap;
	// 방향(0 = 정방향(뒤에서 추가됨), 1 = 역방향(앞에서 추가됨))
	static int dir;
	
	public void init(char[] mainStr) {
		dq = new ArrayDeque<>();
		hmap = new HashMap<>();
		dir = 0;
		
		// 초기에  mainStr(최대 30000 길이) 문자열이 들어온다.
		// 문자열을 1~4 길이로 나눠서 관리
		for (int i = 0; i < mainStr.length; i++) {
			char now = mainStr[i];
			dq.add(now);
			manage(1);
		}
		
	}

	// 문자가 추가되면 문자를 토대로 1~4 길이의 substring을 만들면서
	// hmap에 관리한다.
	private void manage(int cnt) {
		// 만들어봐야하는 substring의 길이
		// 4개를 만들 수 있는 경우
		// 4개 이하를 만드는 경우(dq에 존재하는 문자의 갯수가 4개 이하) -> 만들 수 있을 때까지
		int len = Math.min(dq.size(), 4);
		
//		StringBuilder sb = new StringBuilder();
		String str = "";
		
		if (dir == 0) {	// 정방향
			// E
			// ABCD + E
			// E
			// DE
			// CDE..
			// dq에 있는문자들을 뒤에서부터 하나씩 빼와서 앞에다가 붙여준다.
			Iterator<Character> iter = dq.descendingIterator();
			
			for (int i = 0; i < len; i++) {
				str = iter.next() + str;
				if (hmap.get(str) == null)
					hmap.put(str, cnt);
				else
					hmap.put(str, hmap.get(str) + cnt);
			}
			
		} else if (dir == 1) { // 역방향
			// E + ABCD
			// E
			// EA
			// EAB..
			// dq에 존재하는 문자들을 앞에서부터 하나씩 빼서 뒤에다 붙여준다.
			Iterator<Character> iter = dq.iterator();
			
			for (int i = 0; i < len; i++) {
				str = str + iter.next();
				
				if (hmap.get(str) == null)
					hmap.put(str, cnt);
				else 
					hmap.put(str, hmap.get(str) + cnt);
			}
		}
	}

	public void pushBack(char[] newStr) {
		for (int i = 0; i < newStr.length; i++) {
			if (dir == 0) { // 정방향 - 뒤에 추가
				dq.addLast(newStr[i]);
				manage(1);
			} else if (dir == 1) { // 역방향
				dq.addFirst(newStr[i]);
				manage(1);
			}
		}
	}

	public void popBack(int n) {
		for (int i = 0; i < n; i++) {
			if (dir == 0) { // 정방향 - 뒤에 추가
				manage(-1);
				dq.removeLast();
			} else if (dir == 1) { // 역방향
				manage(-1);
				dq.removeFirst(); // == remove()
			}
		}
	}

	public void reverseStr() {
		if (dir == 1)
			dir = 0;
		else 
			dir = 1;
	}

	public int getCount(char[] subStr) {
		// 방향에 따라 뒤집을지 정한다.
		String str = "";
		
		if (dir == 0) {
			str = String.valueOf(subStr).trim();
		} else if (dir == 1) {
			// 뒤집기
			for (int i = subStr.length - 1; i >= 0; i--) {
				str += subStr[i];
			}
			
			if (hmap.get(str) == null)
				return 0;
		}
		
		return hmap.get(str);
	}
}