package basic;

import java.util.Stack;

/* Stack
 * 데이터를 쌓는 자료구조
 * LIFO : Last In First Out
 * 가장 나중에 들어간 값이 제일 처음으로 나오는 자료구조
 * DFS탐색, 재귀 함수를 호출할 때 사용
 * */
public class StackExam {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();	// int형 스택 선언
		Stack<String> stack2 = new Stack<>();	// String형 스택
		
		// Stack 값 추가
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		while (!stack.isEmpty()) {	// 반대의 순서로 출력
			int val = stack.pop();
			System.out.println(val);
		}
		
		stack.push(4);
		stack.push(5);
		stack.push(6);
		
		stack.pop();	// Stack 값 제거
		
		int lastVal = stack.peek();	// Stack의 가장 최근의 입력받은 값 출력(가장 상단의 값)
		System.out.println("마지막 입력 값 : " + lastVal);
		
		stack.clear();	// Stack 값 초기화
		
	}
}
