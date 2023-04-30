package basic.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/* ArrarDeque
 * Deque("덱")는 Double Ended Queue(양방향큐)의 줄임말
 * Dequeue보다 빠른 "ArrayDeque" 를 사용한다.
 * 
 * ArrayDeque 사용 용도
 * Stack, Queue 대신 사용
 * 
 *  Deque는 스레드 세이프해서 다중 스레드를 구현할 때 사용
 *  ArrayDeque는 Deque에 비해 조금 더 빠르다.
*/
public class ArrayDequeExam {
	public static void main(String[] args) throws Exception {
		Deque<Integer> stack = new ArrayDeque<>();

		stack.push(1);
		stack.push(3);
		stack.push(7);

		System.out.println("마지막 값 : " + stack.peek());

		while (!stack.isEmpty()) {
			// Stack collection과 사용법이 같다.
			// 추가 : push() - 맨 앞쪽에 추가된
			// 읽기 : peek()
			// 읽기&삭제 : pop()
			int now = stack.pop();
			System.out.print(now + " ");
		}

		System.out.println();
		
		// 가독성 있는 element 추가
		// add, push와 같은 성능 -> O(1)
		// addLast()
		// addFirst()
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		deque.addFirst(9);
		deque.addFirst(8);
		deque.addFirst(7);
		
		System.out.println(deque);	// [7, 8, 9, 1, 2, 3]
		
		// poll()과 같은 성능 -> O(1)
		// pollLast()
		// pollFirst()
		deque.pollLast();	// 뒤에 있는 것
		deque.pollFirst();	// 앞에 있는 것
		
		System.out.println(deque);	// [8, 9, 1, 2]
		
		// peek() 와 같은 성능 -> O(1)
		// peekFirst()
		// peekLast()
		System.out.println("처음 것 : " + deque.peekFirst());
		System.out.println("마지막 것 : " + deque.peekLast());
	
		// Stack과 Queue를 사용하는 대신 -> Deque
		// 중간에 저장된 element를 읽고싶으면 ArrayList
		
		
	}
}

