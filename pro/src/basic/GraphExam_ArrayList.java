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

		// �׷��� �ʱ�ȭ
		public ListGraph(int size) {
			this.listGraph = new ArrayList<ArrayList<Integer>>();

			for (int i = 0; i < size + 1; i++) {
				listGraph.add(new ArrayList<Integer>());
			}
		}

		public ArrayList<ArrayList<Integer>> getGraph() {
			return this.listGraph;
		}

		// �׷����� Ư�� ��� ����
		public ArrayList<Integer> getNode(int point) {
			return this.listGraph.get(point);
		}

		// �׷��� �߰�(�����)
		public void put(int x, int y) {
			listGraph.get(x).add(y);
			listGraph.get(y).add(x);
		}

		// �׷��� �߰�(�ܹ���)
		public void putOneWay(int x, int y) {
			listGraph.get(x).add(y);
		}

		// �׷��� ���(��������Ʈ)
		public void printGraph() {
			for (int i = 1; i < listGraph.size(); i++) {
				System.out.println("���� " + i + "�� ��������Ʈ");

				for (int j = 0; j < listGraph.get(i).size(); j++) {
					System.out.print(" -> " + listGraph.get(i).get(j));
				}

				System.out.println();
			}
		}
	}
}
