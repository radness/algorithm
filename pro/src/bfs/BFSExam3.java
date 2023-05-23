package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSExam3 {
	public static void main(String[] args) {
		// Flood Fill
		// 한 지점에서 주변으로 퍼져나가는 모습으로 동작하는 알고리즘
		// ex : 미로찾기
		
		int[][] arr = {
				{1,0,0},
				{0,0,0},
				{0,0,0},
		};
		
		int[] dY = {-1, 1, 0, 0};
		int[] dX = {0, 0, -1, 1};
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		
		while (!q.isEmpty()) {
			Node now = q.poll();
			
			for (int t = 0; t < 4; t++) {
				int nY = now.y + dY[t];
				int nX = now.x + dX[t];
				
				if (nY < 0 || nX < 0 || nY >= 3 || nX >= 3) // map 범위를 벚어나면 skip.
					continue;
				
				if (arr[nY][nX] != 0)	// 이미 꽃이 핀 좌표라면 후보지 등록에서 제외.
					continue;
				
				arr[nY][nX] = arr[now.y][now.x] + 1;
				q.add(new Node(nY, nX));
			}
		}
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(arr[y][x]);
			}
			System.out.println();
		}
		
	}
	
	static class Node {
		int y;
		int x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
