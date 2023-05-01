package basic.queue;

import java.util.LinkedList;
import java.util.Queue;

/* Queue : 줄을 지어 순서대로 처리하는 자료구조
 * FIFO : First In First Out
 * 먼저 들어온 데이터가 가장 먼저 나간다.
 * BFS탐색, 버퍼 처리할 때 사용
 * */
public class QueueExam {
	public static void main(String[] args) {
		// 자바에서 큐는 LinkedList를 사용해서 구현
		Queue<Integer> queue = new LinkedList<>();
		Queue<String> queue2 = new LinkedList<>();
		
		// 큐에 값 추가
		queue.add(1);
		queue.add(2);
		queue.add(3);
		
		while (!queue.isEmpty()) {
			int val = queue.poll();
			System.out.println(val);
		}
		
		queue.poll();	// 큐에 가장먼저 들어간 값을 반환하고 비어있다면 null 반환
		queue.remove();	// 큐에 첫번째 값(가장 먼저 들어간 값) 제거
		queue.clear();	// 큐 초기화
		
		queue.peek();	// 큐의 첫번째 값 참조
		
		
	}
}
