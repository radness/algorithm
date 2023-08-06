package expedition.day4.PRO_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class UserSolution2 {

	static HashMap<ArrayList<String>, Carrot> hmap;
	static ArrayList<String> tagList;

	static TreeSet<DeleteCarrot> priceList;

	static class DeleteCarrot implements Comparable<DeleteCarrot> {
		ArrayList<String> tagList;
		int price;

		@Override
		public int compareTo(DeleteCarrot other) {
			return Integer.compare(this.price, other.price);
		}
	}

	static class Carrot {
		int tagCnt;
		int price;

		public Carrot() {

		}

		public Carrot(int tagCnt, int price) {
			this.tagCnt = tagCnt;
			this.price = price;
		}

//		@Override
//		public int compareTo(Carrot other) {
//			return Integer.compare(this.price, other.price);
//		}
	}

	public void init(int N) {
		hmap = new HashMap<>();
	}

	public void addCarrot(int price, int tagCnt, String tagName[]) {
		ArrayList<String> tagList = new ArrayList<>();

		for (int i = 0; i < tagName.length; i++) {
			tagList.add(tagName[i]);
		}

		hmap.put(tagList, new Carrot(tagCnt, price));

	}

	public int sellCarrot(String tag1, String tag2, String tag3) {
		int ans = 0;

		priceList = new TreeSet<>();
		ArrayList<String> deleteInfo = new ArrayList<>();
		DeleteCarrot delCarrot;
		
		for (Map.Entry<ArrayList<String>, Carrot> entry : hmap.entrySet()) {
			ArrayList<String> arr = entry.getKey();

			if (arr.contains(tag1) && arr.contains(tag2) && arr.contains(tag3)) {
				delCarrot = new DeleteCarrot();
				delCarrot.price = entry.getValue().price;
				delCarrot.tagList = entry.getKey();

				priceList.add(delCarrot);
			}
		}

		if (priceList.isEmpty()) {
//			System.out.println("-1");
			return -1;
		}

		
		DeleteCarrot now = priceList.pollFirst(); 
		hmap.remove(now.tagList);
		ans = now.price;

//		System.out.println("sellCarrot , " + ans);

		return ans;
	}

	public void updatePrice(String tag1, int addPrice) {
		for (Map.Entry<ArrayList<String>, Carrot> entry : hmap.entrySet()) {
			ArrayList<String> arr = entry.getKey();

			if (arr.contains(tag1)) {
				entry.getValue().price += addPrice;
			}
		}
	}
}