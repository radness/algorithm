package graph;

import java.util.ArrayList;
import java.util.Arrays;

// 0번에서 3번 노드까지 갈 수 있는 경우의 수
public class GraphExam3 {
	static ArrayList<Integer>[] arr = new ArrayList[5];
	static int[] visited = new int[4];
	static int result = 0 ;
	
	public static void main(String[] args) throws Exception {
		run();
	}

	static void run() {
		arr[0] = new ArrayList<>(Arrays.asList(1, 2, 3));
		arr[1] = new ArrayList<>(Arrays.asList(2, 2));
		arr[2] = new ArrayList<>(Arrays.asList(0, 3));
		arr[3] = new ArrayList<>();

		visited[0] = 1;
		
		dfs(0);
		
		System.out.println("가는 방법의 합 : " + result + "개");
		
	}

	private static void dfs(int now) {
		if (now == 3) {
			result++;
			return;
		}
			
		for (int i = 0; i < arr[now].size(); i++) {
			int next = arr[now].get(i);
			
			if (visited[next] == 1)
				continue;
			
			visited[next] = 1;
			dfs(next);
			visited[next] = 0;
		}
	}
}
