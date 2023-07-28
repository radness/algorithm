package expedition.day10.TEST_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

//RPG 게임
public class Main {

	static HashMap<String, Character>[] SERVER; // 서버 1번 ~ 10번 10개
	static HashMap<String, Character> hmap; // 캐릭터
	static int[] ServerIdx; // 서버별 id 생성 정보
	static PriorityQueue<Character> pqTopPlayer;
	static PriorityQueue<Character>[] pqTopServerPlayer;
	static TreeSet<Item> SHOP;

	static PriorityQueue<Character> pq1;
	static PriorityQueue<Character>[] pq2;

	// 아이템(비싼 순서대로 정렬)
	static class Item implements Comparable<Item> {
		int power;
		int price;

		public Item(int power) {
			this.power = power;
			this.price = power * 3;
		}

		@Override
		public int compareTo(Item other) {
			return Integer.compare(other.price, this.price);
		}
	}

	static class Character implements Comparable<Character> { // 캐릭터 노드
		int server; // 서버
		String job; // 직업
		String id; // id(Warrior, Magician, Rogue, Priset, Archer 5개 직업)
		int level; // 캐릭터 레벨
		int exp; // 캐릭터 경험치
		int power; // 캐릭터 전투력
		int gold; // 캐릭터 재화
		int idx; // 서버별 캐릭터 생성된 순

		public Character() {

		}

		public Character(int server, String job, String id, int level, int exp, int power, int gold, int idx) {
			this.server = server;
			this.job = job;
			this.id = id;
			this.level = level;
			this.exp = exp;
			this.power = power;
			this.gold = gold;
			this.idx = idx;
		}

		@Override
		public int compareTo(Character other) {
			// 레빌이 높은 순
			if (this.level > other.level)
				return -1;
			if (this.level < other.level)
				return 1;
			// 경험치가 높은 순
			if (this.exp > other.exp)
				return -1;
			if (this.exp < other.exp)
				return 1;
			// 전투력이 높은 순
			if (this.power > other.power)
				return -1;
			if (this.power < other.power)
				return 1;
			// 먼저 생성된 캐릭터 순
			if (this.idx < other.idx)
				return -1;
			if (this.idx > other.idx)
				return 1;

			return 0;
		}
	}

	public static void main(String[] args) throws Exception {
//		long startTime = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Q = Integer.parseInt(br.readLine()); // 쿼리

		SERVER = new HashMap[11];
		for (int i = 0; i < 11; i++) {
			SERVER[i] = new HashMap<>();
		}

		ServerIdx = new int[11];
		for (int i = 0; i < 11; i++) {
			ServerIdx[i] = 1;
		}

		pqTopPlayer = new PriorityQueue<>(); // 게임 내 랭킹
		pqTopServerPlayer = new PriorityQueue[11]; // 서버 내 랭킹
		pq1 = new PriorityQueue<>();
		pq2 = new PriorityQueue[11];
		
		for (int i = 0; i < 11; i++) {
			pqTopServerPlayer[i] = new PriorityQueue<>();
			pq2[i] = new PriorityQueue<>();
		}

		SHOP = new TreeSet<>();// 상점

		// loof 시작
		for (int tc = 0; tc < Q; tc++) {
			st = new StringTokenizer(br.readLine());

			String cmd = st.nextToken();

			if (cmd.equals("create")) {
				int server = Integer.parseInt(st.nextToken());
				String job = st.nextToken();
				String id = st.nextToken();

				create(server, job, id);
			} else if (cmd.equals("play")) {
				int server = Integer.parseInt(st.nextToken());
				String id = st.nextToken();
				int exp = Integer.parseInt(st.nextToken());
				int gold = Integer.parseInt(st.nextToken());

				play(server, id, exp, gold);
			} else if (cmd.equals("topPlayer")) {
				topPlayer();
			} else if (cmd.equals("topServerPlayer")) {
				int server = Integer.parseInt(st.nextToken());

				topServerPlayer(server);
			} else if (cmd.equals("jobPatch")) {
				String job = st.nextToken();
				int power = Integer.parseInt(st.nextToken());

				jobPatch(job, power);
			} else if (cmd.equals("addItem")) {
				int power = Integer.parseInt(st.nextToken());

				addItem(power);
			} else if (cmd.equals("buyItem")) {
				int server = Integer.parseInt(st.nextToken());
				String id = st.nextToken();

				buyItem(server, id);
			}
		}

//		long endTime = System.currentTimeMillis();
//		System.out.println("걸린시간: " + (endTime - startTime) + "ms");
	}

	// 아이템 구매
	private static void buyItem(int server, String id) {
		boolean buyFlag = false;

		Character nowCharacter = SERVER[server].get(id);

		if (nowCharacter == null) {
			System.out.println("Invalid");
			return;
		}

		Iterator<Item> now = SHOP.iterator();

		while (now.hasNext()) {
			Item item = now.next();

			if (nowCharacter.gold >= item.price) { // 살 수 있는 경우
				buyFlag = true;

				nowCharacter.power += item.power;
				nowCharacter.gold -= item.price;

				// 랭킹 시스템에 등록
				if (!pq1.isEmpty()) {
					pq1.add(nowCharacter);
					Character char1 = pq1.poll();
					pq1.clear();
					pq1.add(char1);
				} else {
					pq1.add(nowCharacter);
				}

				if (!pq2[server].isEmpty()) {
					pq2[server].add(nowCharacter);
					Character char1 = pq2[server].poll();
					pq2[server].clear();
					pq2[server].add(char1);
				} else {
					pq2[server].add(nowCharacter);
				}
				
				// 게임 내 랭킹 변경
//				pqTopPlayer = new PriorityQueue<>();
//				for (int i = 1; i < 11; i++) {
//					for (int j = 0; j < SERVER[i].size(); j++) {
//						for (Map.Entry<String, Character> entry : SERVER[server].entrySet()) {
//							Character serverCharacter = entry.getValue();
//							pqTopPlayer.add(serverCharacter); // 게임 내 랭킹 추가
//						}
//					}
//				}
//
//				// 서버 내 랭킹 변경
//				pqTopServerPlayer[server] = new PriorityQueue<>();
//				for (Map.Entry<String, Character> entry : SERVER[server].entrySet()) {
//					Character serverCharacter = entry.getValue();
//					pqTopServerPlayer[server].add(serverCharacter);
//				}

				System.out.println(nowCharacter.id + " " + nowCharacter.power);

				break;
			}
		}

		if (!buyFlag) {
			System.out.println("Fail");
			return;
		}

	}

	// 상점 업데이트
	private static void addItem(int power) {
		// power만큼의 전튜력을 가지는 아이템을 상점에 추가.
		SHOP.add(new Item(power));
	}

	// 직업 밸런스 패치
	private static void jobPatch(String job, int power) {
		// 특정 직업을 가진 모든 캐릭터가 전투력 상향 조정을 한다.
		for (int i = 1; i < 11; i++) { // 서버만큼
			for (Map.Entry<String, Character> entry : SERVER[i].entrySet()) { // 서버의 유저만큼
				Character now = entry.getValue();

				// 같은 직업 이라면
				if (now.job.equals(job)) {
					now.power += power; // 파워 상승
					
					// 랭킹 시스템에 등록
					if (!pq1.isEmpty()) {
						pq1.add(now);
						Character char1 = pq1.poll();
						pq1.clear();
						pq1.add(char1);
					} else {
						pq1.add(now);
					}

					if (!pq2[now.server].isEmpty()) {
						pq2[now.server].add(now);
						Character char1 = pq2[now.server].poll();
						pq2[now.server].clear();
						pq2[now.server].add(char1);
					} else {
						pq2[now.server].add(now);
					}
				}
			}
		}

//		pqTopPlayer = new PriorityQueue<>(); // 게임 내 랭킹 초기화
//		for (int i = 1; i < 11; i++) { // 서버 내 랭킹 초기화
//			pqTopServerPlayer[i] = new PriorityQueue<>();
//		}
//
//		// 랭킹 변경
//		for (int i = 1; i < 11; i++) {
//			for (int j = 0; j < SERVER[i].size(); j++) {
//				for (Map.Entry<String, Character> entry : SERVER[i].entrySet()) {
//					Character serverCharacter = entry.getValue(); // 게임 내 랭킹
//					pqTopPlayer.add(serverCharacter);
//					pqTopServerPlayer[i].add(serverCharacter); // 서버 내 랭킹
//				}
//			}
//		}
	}

	// 서버별 랭킹 시스템
	private static void topServerPlayer(int server) {
		Character now = pqTopServerPlayer[server].peek();

		// id, level, power 출력
		System.out.println(now.id + " " + now.level + " " + now.power);
	}

	// 랭킹 시스템
	private static void topPlayer() {
		// 게임에서 가장 높은 랭킹을 가진 플레이어의 id, level, power를 확인
		Character now = pqTopPlayer.peek();

		// id, level, power 출력
		System.out.println(now.id + " " + now.level + " " + now.power);

	}

	// 게임 플레이
	private static void play(int server, String id, int exp, int gold) {
		// 게임 플레이를 통해 exp와 gold를 획득

		Character now = SERVER[server].get(id);

		if (now == null) {
			System.out.println("Invalid");
			return;
		}

		now.exp += exp;
		now.gold += gold;

		// 플레이를 통한 스펙 업
		while (now.level <= now.exp) {
			now.exp -= now.level;
			now.power += now.level;
			now.level++;
		}

		// 랭킹 시스템에 등록
		if (!pq1.isEmpty()) {
			pq1.add(now);
			Character char1 = pq1.poll();
			pq1.clear();
			pq1.add(char1);
		} else {
			pq1.add(now);
		}

		if (!pq2[server].isEmpty()) {
			pq2[server].add(now);
			Character char1 = pq2[server].poll();
			pq2[server].clear();
			pq2[server].add(char1);
		} else {
			pq2[server].add(now);
		}

		// 게임 내 랭킹 변경
//		pqTopPlayer = new PriorityQueue<>();
//		for (int i = 1; i < 11; i++) {
//			for (int j = 0; j < SERVER[i].size(); j++) {
//				for (Map.Entry<String, Character> entry : SERVER[server].entrySet()) {
//					Character serverCharacter = entry.getValue();
//					pqTopPlayer.add(serverCharacter); // 게임 내 랭킹 추가
//				}
//			}
//		}
//
//		// 서버 내 랭킹 변경
//		pqTopServerPlayer[server] = new PriorityQueue<>();
//		for (Map.Entry<String, Character> entry : SERVER[server].entrySet()) {
//			Character serverCharacter = entry.getValue();
//			pqTopServerPlayer[server].add(serverCharacter);
//		}

		System.out.println(now.level + " " + now.exp + " " + now.gold);

	}

	// 캐릭터 생성
	private static void create(int server, String job, String id) {
		// 해당 서버에 중복되는 id가 있는지 찾기
		if (SERVER[server].get(id) != null && SERVER[server].get(id).id.equals(id)) {
			System.out.println("Invalid");
			return;
		}

		Character newCharacter = new Character(server, job, id, 1, 0, 1, 0, ServerIdx[server]);

		// id 추가
		SERVER[server].put(id, newCharacter);

		// 해당서버 id생성번호 증가
		ServerIdx[server]++;

		// 랭킹 시스템에 등록
		if (!pq1.isEmpty()) {
			pq1.add(newCharacter);
			Character now = pq1.poll();
			pq1.clear();
			pq1.add(now);
		} else {
			pq1.add(newCharacter);
		}

		if (!pq2[server].isEmpty()) {
			pq2[server].add(newCharacter);
			Character now = pq2[server].poll();
			pq2[server].clear();
			pq2[server].add(now);
		} else {
			pq2[server].add(newCharacter);
		}

//		pqTopPlayer.add(newCharacter); // 게임 내 랭킹
//		pqTopServerPlayer[server].add(newCharacter); // 서버 내 랭킹

		System.out.println(server + " " + id);
	}
}