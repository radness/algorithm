package expedition.day12.PRO_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// 끝말잇기 최강자
class Solution {
	private final static int MAXN = 50000;
	private final static int WORD_MAXLEN = 11;

	static int playerCnt;	// 플레이어 수
	// index : 0 ~ 26
	// key : 이 문자로 시작하는 단어(사전순으로 관리), value : 사용x
	static TreeMap<String, Integer>[] WORDS;
	// 변경된 단어들 기록 -> 문자열이기 때문에 HashMap
	static HashMap<String, Integer> USED;
	// index : 플레이어 id
	// value : 플레이어별 탈락 유무
	static int[] outList;
	
	// N : 참가자 수
	// M : 단어 수
	public void init(int N, int M, char[][] words) {
		playerCnt = N;
		USED = new HashMap<>();
		outList = new int[N + 1]; // 플레이어 수 만큼
			
		WORDS = new TreeMap[26];
		for (int i = 0; i < 26; i++) {
			WORDS[i] = new TreeMap<>();
		}
		
		for (int i = 0; i < M; i++) {
			String now = String.valueOf(words[i]).trim();
			char startChar = now.charAt(0);
			// 단어 pool에 넣어준다.
			WORDS[startChar - 'a'].put(now, 1);
		}
	}

	public int playGame(int playerId, char startChar) {
		// 이번 라운드에서 사용된 단어를 임시로 기록한다.
		ArrayList<String> CHANGE_WORDS = new ArrayList<>();
		
		while (true) {
			// 사용할수 있는 단어가 없는경우 : 탈출
			if (WORDS[startChar - 'a'].isEmpty()) {
				outList[playerId] = 1;
				break;
			}
			
			// 사전순으로 가장 빠른 단어 가져오기
			String now = WORDS[startChar - 'a'].firstKey();
			
			// playerId를 가진 플레이어가 now 단어 사용
			USED.put(now, 1);
			
			WORDS[startChar - 'a'].remove(now);	// 기존 단어 목록에서 삭제
			CHANGE_WORDS.add(now); // 사용한 단어들을 저장
			
			// 1. 다음 플레이어에게 순서를 넘겨준다.
			playerId++;
			
			if (playerId > playerCnt) // 플레이어 명수를 넘어가면 1로 변경
				playerId = 1;
			
			// 탈락한 경우 고려
			while (outList[playerId] == 1) { // 탈락한 플레이어라면
				playerId++;
				if (playerId > playerCnt)
					playerId = 1;
			}
			
			// 2. 다음 시작하는 문자는 지금 사용한 문자의 마지막 문자로 변경한다.
			startChar = now.charAt(now.length() - 1);
		}
		
		// 라운드가 종료될 때
		// 해당 라운드에서 사용된 단어들을 뒤집이서 WORDS 목록에 추가한다.
		for (int i = 0; i < CHANGE_WORDS.size(); i++) {
			String now = CHANGE_WORDS.get(i);
			String reverseWord = "";
			
			for (int j = now.length() - 1; j >= 0; j--) {
				reverseWord += now.charAt(j);
			}
			
			// 뒤집어진 단어를 사용한 경우 -> 추가 x
			if (USED.get(reverseWord) != null)
				continue;
			
			char reverseStart = reverseWord.charAt(0);
			WORDS[reverseStart - 'a'].put(reverseWord, 1);
				
		}
		
		// 탈락한 플레이어 id
		return playerId;
	}

}