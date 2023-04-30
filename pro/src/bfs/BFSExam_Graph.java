package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* Graph BFS
 * 한번 예약을 걸었던 노드는 중복 예약이 걸리지 않도록
 * used 처리해준다.
 * */
public class BFSExam_Graph {
	public static void main(String[] args) {
		// 인접리스트 초기화
		ArrayList<Integer>[] alist = new ArrayList[4];
		alist[0] = new ArrayList<>(Arrays.asList(1, 2));
		alist[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist[2] = new ArrayList<>(Arrays.asList(0, 3));
		alist[3] = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();
		q.add(0);

		int[] used = { 1, 0, 0, 0 };
		
		while (!q.isEmpty()) {
			Integer now = q.poll();	// 뽑아와서
			System.out.println("현재 값 : " + now);
			
			for (Integer next : alist[now]) {
				if (used[next] == 1)
					continue;
				
				used[next] = 1;	// 방문 처리
				q.add(next);	// 큐에 추가
				
			}
		}
	}
}
