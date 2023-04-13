package basic;

import java.util.ArrayList;

/* ArrayList
 * �����迭: �����迭�� ����� �߰��� �迭
 * ũ�⸦ ������Ű�� �ʰ�, �� �þ�ų� ���� �� �ִ� Collection
 * ArrayList<int> �Ұ� -> ArrayList<Integer> ����
 * ArrayList<char> �Ұ� -> ArrayList<Character> ����
 * ArrayList<long> �Ұ� -> ArrayList<Long> ����
 * 
 * �ð����⵵
 * ������ �߰�, ������ ���� : O(1)
 * �߰��� �߰�, �߰��� ���� : O(N)
 * */
public class ArrayListExam {
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> arr = new ArrayList<>();
		
		arr.add(1);
		arr.add(3);
		arr.add(5);
		
		arr.add(1, 100);
		
		for (Integer num : arr) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		while (!arr.isEmpty()) {
			int lastIndex = arr.size() - 1;
			System.out.print(arr.get(lastIndex) + " ");
			arr.remove(lastIndex);
		}
		
		System.out.println();
		System.out.println("arr size: " + arr.size());
		
		// SWAP
		// set �޼��� ����ϱ�
		// set(index, element)
		arr.add(100);
		arr.add(200);
		arr.add(300);
		
		int temp = arr.get(0);
		arr.set(0, arr.get(1));
		arr.set(1, temp);
		
		for (Integer num : arr) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		// ���� ����
		// ArrayList �׸���� �������� �ϳ��� ��� �� ����
		while (!arr.isEmpty()) {
			int lastIdx = arr.size() - 1;
			System.out.print(arr.get(lastIdx) + " ");
			arr.remove(lastIdx);
		}
		
		System.out.println();
		System.out.println("arr size : " + arr.size());
	}
}
