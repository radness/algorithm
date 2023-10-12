package pro.past.no38;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 물품보관
class UserSolution {
	static int SIZE;
	static int[] STORE;
	// key : position
	// value : height
	static TreeMap<Integer, Integer> tmap;
	static TreeMap<Integer, ArrayList<Integer>> tLocation;
	static ArrayList<Integer> list;
	static PriorityQueue<Node> pqMin;
	static PriorityQueue<Node> pqMax;

	static class Node implements Comparable<Node> {
		int heigit;
		int idx;

		public Node(int heigit, int idx) {
			this.heigit = heigit;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node other) {
			if (this.heigit > other.heigit)
				return -1;
			if (this.heigit < other.heigit)
				return 1;
			if (this.idx > other.idx)
				return 1;
			if (this.idx < other.idx)
				return -1;
			return 0;
		}
	}

	public void init(int N) {
		SIZE = N;
		STORE = new int[100001];
		tmap = new TreeMap<>();
		tLocation = new TreeMap<>();

		return;
	}

	public int stock(int mLoc, int mBox) {
		STORE[mLoc] += mBox;
		if (tmap.get(mLoc) != null) {
			int height = tmap.get(mLoc) + mBox;
			tmap.put(mLoc, height);
			ArrayList<Integer> delList = tLocation.get(mBox);
			for (Integer item : delList) {
				if (item == mLoc) {
					delList.remove(item);
					break;
				}
			}
			ArrayList<Integer> addList = new ArrayList<>();
			addList.add(mLoc);
			tLocation.put(height, addList);
		} else {
			tmap.put(mLoc, mBox);
			list = tLocation.get(mBox) == null ? new ArrayList<>() : tLocation.get(mBox);
			list.add(mLoc);
			tLocation.put(mBox, list);
		}

		return getSize();
	}

	// 필요한 보호 천막의 크기 반환
	private int getSize() {
		int ans = 0;
		int maxStart = 0;
		int maxEnd = 0;
		int maxHeight = 0;
		int maxIdx = 1;

		for (int i = 1; i < SIZE; i++) {
			if (STORE[i] > maxHeight) {
				maxHeight = STORE[i];
				maxIdx = i;
			}
		}

		ArrayList<Integer> list = tLocation.get(maxHeight);

		if (list == null)
			return 0;

		Collections.sort(list);

		if (list.size() == 1) {
			maxStart = maxEnd = list.get(0);
		} else {
			maxStart = list.get(0);
			maxEnd = list.get(list.size() - 1);
		}

		ans += (maxEnd - maxStart + 1) * maxHeight;

		pqMin = new PriorityQueue<>();
		pqMax = new PriorityQueue<>();

		for (Map.Entry<Integer, Integer> entry : tmap.subMap(0, true, maxStart, false).entrySet()) {
			pqMin.add(new Node(entry.getValue(), entry.getKey()));
		}
		for (Map.Entry<Integer, Integer> entry : tmap.subMap(maxEnd, false, SIZE, true).entrySet()) {
			pqMax.add(new Node(entry.getValue(), entry.getKey()));
		}

		int leftSize = getLeft(maxStart, pqMin);
		int rightSize = getRight(maxEnd, pqMax);

		ans += leftSize;
		ans += rightSize;

		return ans;
	}

	private int getRight(int maxIdx, PriorityQueue<Node> pqRight) {
		int rightSize = 0;
		while (!pqRight.isEmpty()) {
			Node now = pqRight.poll();
			if (maxIdx > now.idx)
				continue;
			int width = now.idx - maxIdx;
			maxIdx = now.idx;
			rightSize += width * now.heigit;
		}

		return rightSize;
	}

	private int getLeft(int maxIdx, PriorityQueue<Node> pqLeft) {
		int leftSize = 0;
		while (!pqLeft.isEmpty()) {
			Node now = pqLeft.poll();
			if (maxIdx < now.idx)
				continue;
			int width = maxIdx - now.idx;
			maxIdx = now.idx;
			leftSize += width * now.heigit;
		}

		return leftSize;
	}

	public int ship(int mLoc, int mBox) {
		STORE[mLoc] -= mBox;
		int height = tmap.get(mLoc) - mBox;
		if (height == 0) {
			tmap.remove(mLoc);
		} else {
			tmap.put(mLoc, height);
		}

		ArrayList<Integer> list = tLocation.get(mBox);
		for (Integer item : list) {
			if (item == mLoc) {
				list.remove(item);
				break;
			}
		}
		tLocation.remove(mBox);
		if (!list.isEmpty()) {
			tLocation.put(mBox, list);
		}

		return getSize();
	}

	public int getHeight(int mLoc) {
		return tmap.get(mLoc) == null ? 0 : tmap.get(mLoc);
	}
}