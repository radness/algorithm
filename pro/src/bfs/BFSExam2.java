package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSExam2 {
	public static void main(String[] args) throws Exception {
		/* BFS(너비 우선 탐색) -> 큐 자료구조
		 * Queue
		 * 값을 쉽게 저장할 수 있지만 넣은 값을 모두 읽으면 안된다.
		 * 오로지 가장 처음에 저장했던 데이터만 읽을 수 있다.
		 * 오로지 가장 처름에 저장했던 데이터만 삭제할 수 있다.
		 * 
		 * */
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		q.add(3);
		q.add(5);
		
		System.out.println(q.peek());// 읽기
		
		System.out.println(q.poll());// 읽기 + 삭제
		
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}
}
