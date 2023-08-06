package expedition.day4.PRO_02;

import java.util.ArrayList;
import java.util.HashMap;

// 당근마켓
class UserSolution {

	// KEY : 한개의 tag
	// VALUE : 이 태그를 보유한 당근들 (ID)
	static HashMap<String, ArrayList<Integer>> singletag;

	// KEY : 3개의 tag 조합 = hashcode
	// VALUE : 이 3개의 태그에 부합하는 당근들 (ID)
	static HashMap<Integer, ArrayList<Integer>> tripletag;

	// index : 당근 번호 (당근은 최대 3만개까지 있을수 있다)
	// value : 이 당근의 가격 (update에서 singletag에서 한 가격변동이 tripletag에도 미칠수 있도록 연동)
	static int[] PRICE;

	// index : 당근 번호
	// value : 이 당근이 팔렸는가? (0, 1)
	static int[] sold;

	static int carrotID; // 당근의 ID

	static int tagnum; // 태그의 순서를 관리하기 위한 변수

	// KEY : tag
	// VALUE : hashcode를 생성하기 위한 tag번호
	static HashMap<String, Integer> hm;

	public void init(int N) {
		carrotID = 1; // 당근 번호는 1번부터
		singletag = new HashMap<>();
		tripletag = new HashMap<>();
		PRICE = new int[30001];
		sold = new int[30001];
		hm = new HashMap<>();
		tagnum = 1;
	}

	static int HashFunc(String A, String B, String C) {
		// 공통적인 규칙으로 관리 => A, B, C 중 가장 먼저 생성되었던 tag를 기준으로 결합
		// tag의 순서를 관리
		Integer a = hm.get(A);
		Integer b = hm.get(B);
		Integer c = hm.get(C);

		// System.out.println(a + " " + b + " " + c);

		// first second third
		int first = Math.min(Math.min(a, b), c);
		int third = Math.max(Math.max(a, b), c);

		int second = 0;
		if (first == a && third == b || first == b && third == a)
			second = c;
		if (first == a && third == c || first == c && third == a)
			second = b;
		if (first == b && third == c || first == c && third == b)
			second = a;

		// hashcode 겹치지 않도록 해서 생성해서 return
		// 302928
		return first * 10000 + second * 100 + third;
	}

	public void addCarrot(int price, int tagCnt, String tagName[]) {
		// 이 price를 가진, tagCnt개수의 tag를 가진 당근이 더해진다
		PRICE[carrotID] = price;

		// 이 당근은 tagCnt 개수의 tag를 가지고 있습니다.
		for (int i = 0; i < tagCnt; i++) {
			String tag = tagName[i];
			// 이 당근은 이 tag를 소유하고 있다 -> singletag에 저장
			// 처음 들어오는 tag라면
			if (singletag.get(tag) == null) {
				// 활성화
				singletag.put(tag, new ArrayList<>());
				// 이 태그에 대해 순번 부여
				hm.put(tag, tagnum);
				tagnum++;
			}
			singletag.get(tag).add(carrotID);
		}

		// 3개의 tag조합을 만들어서 tripletag에 저장
		for (int i = 0; i < tagCnt; i++) {
			for (int j = i + 1; j < tagCnt; j++) {
				for (int k = j + 1; k < tagCnt; k++) {
					// 이 3개의 tag에 대한 hashcode 생성
					int hashcode = HashFunc(tagName[i], tagName[j], tagName[k]);
					if (tripletag.get(hashcode) == null)
						// 활성화
						tripletag.put(hashcode, new ArrayList<>());
					tripletag.get(hashcode).add(carrotID);
				}
			}
		}
		carrotID++; // 다음 carrot은 하나 번호 +
		int de = 1;
	}

	public int sellCarrot(String tag1, String tag2, String tag3) {

		// tag1, tag2, tag3 중에 애초부터 등록되지도 않은 tag가 있을수도 있다.
		// 하나라도 등록되지 않은 tag라면 => 당연히 3개 조합 등장할수 없다.
		if (hm.get(tag1) == null || hm.get(tag2) == null || hm.get(tag3) == null)
			return -1;

		// tag1, tag2, tag3에 대한 hashcode만 생성하면
		// tripletag쪽에서 바로 이 3개의 tag를 보유한 carrot들을 다 볼 수 있는 거죠?
		int hashcode = HashFunc(tag1, tag2, tag3);

		// 이 3개의 tag에 부합하는 당근 자체가 없다면
		if (tripletag.get(hashcode) == null)
			return -1;

		int MIN = Integer.MAX_VALUE;
		int MINID = -1;

		// 3개의 tag를 보유한 모든 carrot의 ID
		ArrayList<Integer> carrots = tripletag.get(hashcode);
		for (int i = 0; i < carrots.size(); i++) {
			int ID = carrots.get(i);
			// 이게 팔린 당근이냐?
			if (sold[ID] == 1)
				continue;

			// 이게 최저가냐?
			if (PRICE[ID] < MIN) {
				MIN = PRICE[ID];
				MINID = ID;
			}
		}

		// MINID = -1에서 변하지 않아서 생기는 문제 => 팔수 있는 당근이 없을수 있다. 다 팔려서 없다
		if (MINID == -1)
			return -1;
		// 이 가장 저렴한 carrot은 팔린다!
		sold[MINID] = 1;
		return MIN;
	}

	public void updatePrice(String tag1, int addPrice) {
		// tag1을 보유한 모든 carrot의 가격이 addPrice만큼 변동
		ArrayList<Integer> carrots = singletag.get(tag1); // tag1을 보유한 모든 carrot
		for (int i = 0; i < carrots.size(); i++) {
			int ID = carrots.get(i);
			PRICE[ID] += addPrice;
		}
	}
}