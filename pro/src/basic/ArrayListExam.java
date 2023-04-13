package basic;

import java.util.ArrayList;

/* ArrayList
 * 동적배열: 정적배열에 기능을 추가한 배열
 * 크기를 고정시키지 않고, 더 늘어나거나 줄일 수 있는 Collection
 * ArrayList<int> 불가 -> ArrayList<Integer> 가능
 * ArrayList<char> 불가 -> ArrayList<Character> 가능
 * ArrayList<long> 불가 -> ArrayList<Long> 가능
 * 
 * 시간복잡도
 * 마지막 추가, 마지막 삭제 : O(1)
 * 중간에 추가, 중간에 삭제 : O(N)
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
		// set 메서드 사용하기
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
		
		// 역순 제거
		// ArrayList 항목들으 역순으로 하나씩 출력 후 제거
		while (!arr.isEmpty()) {
			int lastIdx = arr.size() - 1;
			System.out.print(arr.get(lastIdx) + " ");
			arr.remove(lastIdx);
		}
		
		System.out.println();
		System.out.println("arr size : " + arr.size());
	}
}
