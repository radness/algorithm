package basic;

import java.util.ArrayList;

public class GraphExam_ArrayList {
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {

		int size = 6;

		ListGraph listGraph = new ListGraph(size);

		listGraph.put(1, 2);
		listGraph.put(1, 3);
		listGraph.put(1, 4);
		listGraph.put(2, 3);
		listGraph.put(2, 4);
		listGraph.put(3, 1);
		listGraph.put(3, 5);
		listGraph.put(3, 6);
		listGraph.put(4, 5);
		listGraph.put(4, 6);

		listGraph.printGraph();

	}

	static class ListGraph {
		private ArrayList<ArrayList<Integer>> listGraph;

		// 그래프 초기화
		public ListGraph(int size) {
			this.listGraph = new ArrayList<ArrayList<Integer>>();

			for (int i = 0; i < size + 1; i++) {
				listGraph.add(new ArrayList<Integer>());
			}
		}

		public ArrayList<ArrayList<Integer>> getGraph() {
			return this.listGraph;
		}

		// 그래프의 특정 노드 리턴
		public ArrayList<Integer> getNode(int point) {
			return this.listGraph.get(point);
		}

		// 그래프 추가(양방향)
		public void put(int x, int y) {
			listGraph.get(x).add(y);
			listGraph.get(y).add(x);
		}

		// 그래프 추가(단방향)
		public void putOneWay(int x, int y) {
			listGraph.get(x).add(y);
		}

		// 그래프 출력(인접리스트)
		public void printGraph() {
			for (int i = 1; i < listGraph.size(); i++) {
				System.out.println("정점 " + i + "의 인접리스트");

				for (int j = 0; j < listGraph.get(i).size(); j++) {
					System.out.print(" -> " + listGraph.get(i).get(j));
				}

				System.out.println();
			}
		}
	}
}
