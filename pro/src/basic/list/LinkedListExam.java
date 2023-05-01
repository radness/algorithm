package basic;

import java.util.LinkedList;

// LinkedList
// 데이터 + 다음 노드 정보 저장
public class LinkedListExam {
	public static void main(String[] args) throws Exception {
		
		LinkedList<Integer> list = new LinkedList<>();
		
		// 값 추가
		list.add(1);
		list.add(2);
		list.add(3);
		
		System.out.println(list);
		
		// 인덱스 1번에 데이터 10 추가
		list.add(1, 10);
		
		// 값 삭제
		list.remove(2);
		System.out.println(list);
		
		// 맨 앞에 값 추가
		list.addFirst(100);
		System.out.println(list);
		
		// 맨 뒤에 값 추가
		list.addLast(300);
		System.out.println(list);
		
		// 값 확인
		// 값이 존재하면 true, 없으면 false
		System.out.println(list.contains(1));
		
	}
}
