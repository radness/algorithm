package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSExam2 {
	public static void main(String[] args) throws Exception {
		/*
		 * BFS(너비 우선 탐색)
		 * -> 큐 자료구조 Queue 값을 쉽게 저장할 수 있지만 넣은 값을 모두 읽으면 안된다.
		 * 오로지 가장 처음에 저장했던 데이터만 읽을 수 있다.
		 * 오로지 가장 처름에 저장했던 데이터만 삭제할 수 있다.
		 */

		// Tree BFS 탐색 코드
		ArrayList<Integer>[] alist = new ArrayList[6];

		alist[0] = new ArrayList<>(Arrays.asList(1, 4));
		alist[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist[2] = new ArrayList<>();
		alist[3] = new ArrayList<>();
		alist[4] = new ArrayList<>(Arrays.asList(5));
		alist[5] = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();

		q.add(0);

		while (!q.isEmpty()) {
			Integer now = q.poll();

			System.out.print(now + " "); // 탐색 순서대로 노드 번호 출력

			for (Integer next : alist[now]) {
				q.add(next);
			}
		}
		
		// Graph BFS
		// 한번 예약을 걸었던 노드는 중복 예약이 걸리지 않도록 used 처리해준다.
		ArrayList<Integer>[] alist2 = new ArrayList[5];
		alist2[0] = new ArrayList<>(Arrays.asList(1, 2));
		alist2[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist2[2] = new ArrayList<>(Arrays.asList(0, 3));
		alist2[3] = new ArrayList<>();
		
		Queue<Integer> q2 = new LinkedList<>();
		int[] used = new int[3];
		
		q2.add(0);
		used[0] = 1;
		
		while (!q2.isEmpty()) {
			Integer now = q2.poll();
			
			System.out.print(now + " ");
			
			for(Integer next : alist2[now]) {
				if (used[next] == 1)	// 방문했다면 넘어간다.
					continue;
				
				used[next] = 1;	// 방문 처리
				q.add(next);	// 큐에 추가
			}
		}
 	}
}
