package basic.priority_queue;

import java.util.ArrayList;
import java.util.Collections;

public class CompareExam2 {
	public static void main(String[] args) {
		
		ArrayList<Number> arr = new ArrayList<>();
		arr.add(new Number(1));
		arr.add(new Number(2));
		arr.add(new Number(13));
		arr.add(new Number(20));
		arr.add(new Number(5));
		arr.add(new Number(11));
		arr.add(new Number(30));
		
		Collections.sort(arr);
		
		for (Number num : arr) {
			System.out.print(num.n + " ");
		}
	}
}

class Number implements Comparable<Number> {
	int n;
	
	public Number(int n) {
		this.n = n;
	}

	@Override
	public int compareTo(Number other) {
		if ((this.n % 2) == 1 && (other.n % 2) == 0) return -1;	// 내가 홀수인데 other값이 짝수면 왼쪽으로 
		if ((this.n % 2) == 0 && (other.n % 2) == 1) return 1; 	// 내가 짝수인데 other값이 홀수면 오른쪽으로
		
		// 다음 조건 검색
		return Integer.compare(this.n, other.n);	// n이 작다면 왼쪽으로
	}
	
}
