package pro.past.no17;

import java.util.TreeMap;

public class MainExam {
	static int[] path = new int[10];
	static int[] used = new int[10];

	static class Node {
		int id;
		int location;

		public Node(int id, int location) {
			this.id = id;
			this.location = location;
		}
	}

	public static void main(String[] args) throws Exception {
//		run(0);
		
		TreeMap<Integer, Node> tmap = new TreeMap<>();
		
		tmap.put(1, new Node(1, 100));
		
		Node node = tmap.get(2);
		
		if (node != null) {
			System.out.println("id : " + node.id + " location : " + node.location);
		} else {
			System.out.println("null�Դϴ�.");
		}
		
		
		
	}

	private static void run(int level) {
		if (level == 3) {
			for (int i = 0; i < level; i++) {
				System.out.print(path[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < 6; i++) {
			if (used[i] == 1)
				continue;

			used[i] = 1;
			path[level] = i + 1;

			run(level + 1);

			path[level] = 0;
			used[i] = 0;

		}

	}
}
