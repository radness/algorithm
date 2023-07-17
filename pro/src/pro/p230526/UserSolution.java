package pro.p230526;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

class UserSolution {

	boolean[] playYN; // 참여중
	int[] score; // 점수
	TreeMap<String, Integer>[] tWords; // 주제어별
	HashMap<String, Integer> hWordToId; // 주제어
	int[][] cardCnt; // 보유 카드 갯수

	static class Item implements Comparable<Item> {
		int playerCount; // 보유플레이어 수
		String word; // 단어
		int cardId; // 카드번호

		public Item(int playerCount, String word, int cardId) {
			this.playerCount = playerCount;
			this.word = word;
			this.cardId = cardId;
		}

		@Override
		public int compareTo(Item other) {
			if (this.playerCount == other.playerCount)
				return this.word.compareTo(other.word);

			return other.playerCount - this.playerCount;
		}
	}

	/* 호출 :  1
     * N : 카드 종류의 수 (1 <= N <= 10,000)
     * mWordList : 카드에 적힌 단어 리스트 (2 <= |mWordList[]| <= 10. |a| 는 문자열 a의 길이를 뜻한다)
     * mSubjectList : 카드에 적힌 주제어 리스트 (2 <= |mSubjectList[]| <= 10
     * 서로 다른 주제어의 개수는 최대 20이다.
     */
	void init(int N, char mWordList[][], char mSubjectList[][]) {
		playYN = new boolean[51];	// 참여중
		score = new int[51];		// 점수
		cardCnt = new int[51][N+1];	// 보유 카드 갯수
		tWords = new TreeMap[21];	// 주제어별
		
		for (int i = 0; i <= 20; i++) {
			tWords[i] = new TreeMap<>();
		}
		
		hWordToId = new HashMap<>();
		
		int subjectSeq = 0;
		
		for (int i = 0; i < N; i++) {
			String word = new String(mWordList[i]).trim();
			String subject = new String(mSubjectList[i]).trim();
			Integer subjectId = hWordToId.get(subject);
			
			if (subjectId == null) {
				subjectId = subjectSeq++;
				hWordToId.put(subject, subjectId);
			}
			
			tWords[subjectId].put(word, i + 1);
		}
	}

	/*
	 * 호출 :  50
	 * mID : 참가할 플레이어 ID (1 <= mID <= 50)
	 * M : 참가할 플레이어가 가진 카드의 수 (1 <= M <= 1,500)
	 * mCardList : 참가할 플레이어가 가진 카드리스트 (1 <= mCardList[] <= N)
	 */
	void join(int mID, int M, int mCardList[]) {
		playYN[mID] = true;
		
		for (int i = 0; i < M; i++) {
			int cardNumber = mCardList[i];
			cardCnt[mID][cardNumber]++;
		}
	}

	/*
	 * 호출 :  50
	 * mID : 떠날 플레이어의 ID (1 <= mID <= 50)
	 * [return] 플레이어 mID가 얻은 점수
	 */
	int leave(int mID) {
		// 1) HashSet / 2) boolean[]
		playYN[mID] = false;
		
		return score[mID];
	}

	/*
	 * 호출 :  1500
	 * mBeginStr : 시작 문자열 (1 <= |mBeginStr| <= 2)
	 * mSubject : 주제어 (2 <= |mSubject| <= 10)
	 * [return] 제출한 카드 번호 합
	 * 우선순위
	 * 1) 시작문자열 + 주제어 일치 중 플레이어 많은 카드
	 * 2) 중복, 사전 순
	 * 점수 계산 : 같은 카드를 제출 다른 플레이어 수의 제곱
	 */
	public int playRound(char mBeginStr[], char mSubject[]) {
		int ret = 0;	// 제출한 카드 번호 합
		
		String begin = new String(mBeginStr).trim();
		String subject = new String(mSubject).trim();
		
		int subjectId = hWordToId.get(subject);
		
		PriorityQueue<Item> pq = new PriorityQueue<>();

//		Set<Entry<String, Integer>> entrySet = tWords[subjectId].subMap(begin, true, begin + "{", false).entrySet();
//		for (Map.Entry<String, Integer> entry : entrySet) {
//			
//		}
		
		for (Entry<String, Integer> entry : tWords[subjectId].subMap(begin, true, begin + "{", false).entrySet()) {
			String word = entry.getKey();	// 단어
			int cardNumber = entry.getValue();	// 카드번호
			int playerCnt = 0;	// 보유플레이어 수
			
			for (int mID = 1; mID <= 50; mID++) {
				// 참여중이 아니라면 skip
				if (!playYN[mID])
					continue;
				
				if (cardCnt[mID][cardNumber] > 0)
					playerCnt++;
				
			}
			
			if (playerCnt >0)
				// 보유플레이어 수, 단어, 카드번호
				pq.add(new Item(playerCnt, word, cardNumber));
		}
		
		boolean[] submit = new boolean[51];	// 제출 여부
		
		while (!pq.isEmpty()) {
			Item now = pq.poll();
			
			ArrayList<Integer> submitPlayer = new ArrayList<>();	// 제출한 사람
			
			for (int mID = 1; mID <= 50; mID++) {
				if (!playYN[mID])	// 참여중이 아니라면 skip
					continue;
				
				if (submit[mID])	// 제출한 사람이면 skip 
					continue;
				
				// 보유 카드 갯수[아이디][현재.카드번호]
				if (cardCnt[mID][now.cardId] > 0) {
					cardCnt[mID][now.cardId]--;
					
					ret += now.cardId;
					submit[mID] = true;
					submitPlayer.add(mID);
				}
			}
			
			int submitTotal = submitPlayer.size();	// 총 제출한 인원
			
			for (int mID : submitPlayer) {
				// 점수 계산 : 동일한 카드를 제풀하면 자신을 제외한 다른 플레이어 수의 제곱
				score[mID] += (submitTotal - 1) * (submitTotal - 1); 
			}
		}
		
		return ret;
	}

}