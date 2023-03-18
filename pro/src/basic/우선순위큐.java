package basic;

import java.util.Comparator;
import java.util.PriorityQueue;

/* Prioritval2 Queue(우선순위 큐)
 * 일반적인 큐는 데이터를 일시적으로 쌓아두기 위한 자료구조로
 * 스택과는 다르게 FIFO(First In First Out)의 구조를 가진다.(먼저 들어온 값이 먼저 나가는 구조)
 * 우선순위 큐는 우선순위를 먼저 결정하고 그 우선순위가 높은 값이 먼저 나가는 구조.
 * 우선순위 큐는 힙을 이용하여 구현하는것이 일반적이다.
 * 데이터의 추가 및 삭제를 해도 항상 정렬 상태를 유지한다.
 * Prioritval2 Queue의 생성자를 통해서 Heap, Tree Min, Maval1등을 구현할 수 있다.
 */
public class 우선순위큐 {
	public static void main(String[] args) throws Exception {

		// 생성 및 정렬
		PriorityQueue<Item> queue = new PriorityQueue<>(new Comparator<Item>() {

			@Override
			public int compare(Item i1, Item i2) {
				if (i1.val1 == i2.val1) {
					return Integer.compare(i1.val2, i1.val1);
				} else {
					return Integer.compare(i1.val1, i1.val2);
				}
			}
		});

		queue.add(new Item(1, 2));
		queue.add(new Item(3, 4));
		queue.add(new Item(5, 6));
		queue.add(new Item(7, 8));
		queue.add(new Item(9, 1));

		// add: 큐 맨뒤에 값 삽입, 값 추가를 성공하면 true 반환
		// poll: 큐에서 맨 앞에 있는값 반환 후 삭제, 큐가 비어있을 경우 null 반환
		// peek: 큐의 맨 앞에 있는 값 반환, 큐가 비어있을 경우 null반환
		if (queue.size() != 0)
			queue.poll();

		queue.add(new Item(7, 3));
		queue.add(new Item(3, 4));

		// 테스트 출력
		for (Item item : queue) {
			System.out.println(item.val1 + " : " + item.val2);
		}

		System.out.println("-----");

		System.out.println("큐 사이즈 : " + queue.size());
		
		// 정렬 확인
		while (!queue.isEmpty()) {
			Item item = queue.poll();	// or queue.remove()
			System.out.println("val1 : " + item.val1 + " val2: " + item.val2);
		}

	}

	private static class Item {
		int val1;
		int val2;

		Item(int val1, int val2) {
			this.val1 = val1;
			this.val2 = val2;
		}
	}
}
