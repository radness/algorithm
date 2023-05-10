package graph;

import java.util.ArrayList;
import java.util.List;

// 배열 + 리스트
public class AdjacencyListExam {
	public static void main(String[] args) {
		int [][] edges = new int[][] {
			{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 5}, {4, 5}
		};
		
		int N = 5;
		
		List<Integer>[] list = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int[] edge : edges) {
			list[edge[0]].add(edge[1]);
			list[edge[1]].add(edge[0]);
		}
		
		// 출력
		for (int i = 1; i <= N; i++) {
			System.out.print("정점 " + i + "의 인접 노드 : ");
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j < list[i].size(); j++) {
				sb.append(list[i].get(j) + " ");
//				System.out.println(list[i].get(j));
			}
			System.out.println(sb);
			System.out.println();
		}
	}
}
