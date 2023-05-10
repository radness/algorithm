package bfs;

import java.util.LinkedList;
import java.util.Queue;

/* Flood Fill
 * 주어진 시작점으로부터 연결된 영역들을 찾는 알고리즘
 * 다차원 배열의 어떤 칸과 연결된 영역을 찾는 알고리즘
 * DFS 알고리즘을 이용하여 재귀 함수를 통해 구현하거나 BFS 알고리즘을 이용하여 Queue로 구현한다.
 * 
 * BFS + Direct Search
 */
public class BFSExam_FloodFill {
	public static void main(String[] args) throws Exception {
		int[][] arr = {
				{1,0,0},
				{0,0,0},
				{0,0,0}
		};
		
		int[] dY = { -1, 1, 0, 0 };
		int[] dX = { 0, 0, -1, 1 };
		
		Queue<Node> q = new LinkedList<>();
		// 시작점 넣기
		q.add(new Node(0, 0));
		
		while (!q.isEmpty()) {
			// 하나씩 뺀다
			Node now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = now.y + dY[i];
				int nx = now.x + dX[i];
				
				if (ny < 0 || nx < 0 || ny >= 3 || nx >= 3)
					continue;
				if (arr[ny][nx] != 0)
					continue;	// 이미 꽃이 핀 후보지는 제외한다.
				
				arr[ny][nx] = arr[now.y][now.x] + 1;
				// 다음 후보지 등록
				q.add(new Node(ny, nx));
			}
		}
		
		// 출력
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(arr[y][x] + " ");
			}
			System.out.println();
		}
		
	}
	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
