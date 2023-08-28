package pro.past.no17;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 6G 보급망
class UserSolution {

	static HashMap<Integer, Node> hmap;
	static TreeMap<Integer, Integer> tmap;
	static TreeMap<Integer, Node> buildingMap;

	static PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
		@Override
		public int compare(Node node1, Node node2) {
			return Math.abs(node1.location - node2.location);
		}
	});

	static class Node {
		int id;
		int location;

		public Node(int id, int location) {
			this.id = id;
			this.location = location;
		}
	}

	public void init(int N, int[] mId, int[] mLocation) {
		hmap = new HashMap<>();

		for (int i = 0; i < N; i++) {
			hmap.put(mId[i], new Node(mId[i], mLocation[i]));
		}
	}

	public int add(int mId, int mLocation) {
		if (hmap.containsKey(mId)) {
			hmap.put(mId, new Node(mId, mLocation));
		} else {
			hmap.put(mId, new Node(mId, mLocation));
		}

		return hmap.size();
	}

	public int remove(int mStart, int mEnd) {
		for (int i = 0; i < hmap.size(); i++) {
			Node now = hmap.get(i);

			if (now.location >= mStart && now.location <= mEnd) {
				hmap.remove(now.id);
			}
		}

		return hmap.size();
	}

	public int install(int M) {
		int ans = Integer.MAX_VALUE;

		tmap = new TreeMap<>();
		buildingMap = new TreeMap<>();

		for (Map.Entry<Integer, Node> entry : hmap.entrySet()) {
			tmap.put(entry.getValue().location, entry.getKey());
		}

		buildingMap.put(tmap.firstEntry().getValue(),
				new Node(tmap.firstEntry().getValue(), tmap.firstEntry().getKey()));
		buildingMap.put(tmap.lastEntry().getValue(), new Node(tmap.lastEntry().getValue(), tmap.lastEntry().getKey()));

		int minPosition = tmap.firstEntry().getKey();
		int maxPosition = tmap.lastEntry().getKey();

		ans = maxPosition - minPosition;

		tmap.remove(minPosition);
		tmap.remove(maxPosition);

		M = M - 2;

		if (M > 0) {
			int buildingSize = hmap.size() - 2;

			for (int i = 0; i < buildingSize; i++) {
				int tmp = setBuilding(M);
				if (ans < tmp) {
					ans = tmp;
				}
			}

		}

		return ans;
	}

	private int setBuilding(int M) {
		int val = 0;
		int buildingSize = hmap.size() - 2;

		boolean[] visited = new boolean[tmap.size()];
		combination(visited, 0, tmap.size(), buildingSize);

		return val;
	}

	static void combination(boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			for (Map.Entry<Integer, Node> entry : buildingMap.entrySet()) {
				System.out.print(entry.getKey() + " ");
			}

			System.out.println("");
			return;
			
		}
		for (int i = start; i < n; i++) {
			visited[i] = true;

			combination(visited, i + 1, n, r - 1);

			visited[i] = false;
			combination(visited, i + 1, n, r);
		}

	}
}