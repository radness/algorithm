package bfs;

import java.util.LinkedList;
import java.util.Queue;

/* Flood Fill
 * �־��� ���������κ��� ����� �������� ã�� �˰���
 * ������ �迭�� � ĭ�� ����� ������ ã�� �˰���
 * DFS �˰����� �̿��Ͽ� ��� �Լ��� ���� �����ϰų� BFS �˰����� �̿��Ͽ� Queue�� �����Ѵ�.
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
		// ������ �ֱ�
		q.add(new Node(0, 0));
		
		while (!q.isEmpty()) {
			// �ϳ��� ����
			Node now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = now.y + dY[i];
				int nx = now.x + dX[i];
				
				if (ny < 0 || nx < 0 || ny >= 3 || nx >= 3)
					continue;
				if (arr[ny][nx] != 0)
					continue;	// �̹� ���� �� �ĺ����� �����Ѵ�.
				
				arr[ny][nx] = arr[now.y][now.x] + 1;
				// ���� �ĺ��� ���
				q.add(new Node(ny, nx));
			}
		}
		
		// ���
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
