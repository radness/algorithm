package pro.p230512;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

public class UserSolution230512Test {
	static HashMap<Integer, Node> hmap;

	public static void main(String[] args) {
		hmap = new HashMap<>();

		// add
		hmap.put(1, new Node(0, 4 + 1));
		hmap.put(2, new Node(3, 13 + 1));
		hmap.put(3, new Node(4, 10 + 1));
		hmap.put(4, new Node(11, 23 + 1));
		hmap.put(5, new Node(8, 18 + 1));

		System.out.println("map size : " + hmap.size());

		// remove
		hmap.remove(1);

		System.out.println("map size : " + hmap.size());

		// 4, 2, 2, 3
		announce(2, 2);

	}

	// 가장 빠른 방송시간을 return하며
	// 불가능하면 -1을 return.
	private static int announce(int mDuration, int M) {
		if (hmap.size() < M)
			return -1;

		TreeSet<Integer> timeSet = new TreeSet<>();

		ArrayList<Node> data = new ArrayList<>();

		for (Entry<Integer, Node> entry : hmap.entrySet()) {
			// key, value 구조에서 value(Node)를 가져옴
			Node node = entry.getValue();

			if (node.gap >= mDuration) {
				timeSet.add(node.start);
				data.add(node);
			}
		}

		if (data.size() < M)
			return -1;

		int minTime = 0;

		for (int currentTime : timeSet) {
			if (currentTime < minTime)
				continue;

			int sum = 0;
			int exSum = 0;
			int endTime = currentTime + mDuration;

			for (Node node : data) {
				if (node.start <= endTime && endTime <= node.end) {
					exSum++;
					if (node.start <= currentTime && ++sum >= M)
						return currentTime;
				}
			}

			if (exSum < M)
				minTime = endTime;
		}
		return -1;
	}

	static class Node {
		int start;
		int end;
		int gap;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
			this.gap = end - start;
		}
	}
}
