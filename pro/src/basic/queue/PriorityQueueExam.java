package basic.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/* Priority Queue(�켱���� ť)
 * �Ϲ����� ť�� �����͸� �Ͻ������� �׾Ƶα� ���� �ڷᱸ����
 * ���ð��� �ٸ��� FIFO(First In First Out)�� ������ ������.(���� ���� ���� ���� ������ ����)
 * �켱���� ť�� �켱������ ���� �����ϰ� �� �켱������ ���� ���� ���� ������ ����.
 * �켱���� ť�� ���� �̿��Ͽ� �����ϴ°��� �Ϲ����̴�.
 * �������� �߰� �� ������ �ص� �׻� ���� ���¸� �����Ѵ�.
 * Priority Queue�� �����ڸ� ���ؼ� Heap, Tree Min, Max���� ������ �� �ִ�.
 * 
 * �н� ����
 * 1. Compare method
 * 2. Sort Library
 * 3. Custom Sort
 * 4. Priority Queue
 * 
 */
public class PriorityQueueExam {
	public static void main(String[] args) throws Exception {

		// ���� �� ����
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

		// add: ť �ǵڿ� �� ����, �� �߰��� �����ϸ� true ��ȯ
		// poll: ť���� �� �տ� �ִ°� ��ȯ �� ����, ť�� ������� ��� null ��ȯ
		// peek: ť�� �� �տ� �ִ� �� ��ȯ, ť�� ������� ��� null��ȯ
		if (queue.size() != 0)
			queue.poll();

		queue.add(new Item(7, 3));
		queue.add(new Item(3, 4));

		// �׽�Ʈ ���
		for (Item item : queue) {
			System.out.println(item.val1 + " : " + item.val2);
		}

		System.out.println("-----");

		System.out.println("ť ������ : " + queue.size());
		
		// ���� Ȯ��
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
