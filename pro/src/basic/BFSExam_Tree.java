package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
   0
   /\	
  1  4
  /\  \
 2  3  5
*/
public class BFSExam_Tree {
	public static void main(String[] args) throws Exception {

		// 인접리스트 초기화
		ArrayList<Integer>[] alist = new ArrayList[6];
		alist[0] = new ArrayList<>(Arrays.asList(1, 4));
		alist[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist[2] = new ArrayList<>();
		alist[3] = new ArrayList<>();
		alist[4] = new ArrayList<>(Arrays.asList(5));
		alist[5] = new ArrayList<>();

		for (ArrayList<Integer> item : alist) {
			System.out.println(item);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		
		while (!q.isEmpty()) {
			Integer now = q.poll();
			System.out.print(now + " "); // 탐색 순서대로 노드 번호 출력
			
			for (Integer next : alist[now]) {
				q.add(next);
			}
		}
	}

}
