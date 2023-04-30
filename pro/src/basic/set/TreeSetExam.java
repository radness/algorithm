package basic.set;

import java.util.*;
import java.lang.*;
import java.io.*;

/* TreeSet
 * 나열되는 순서를 지정해주고 싶다면 TreeSet 사용한다.
 * 
 * */
public class TreeSetExam {
    public static void main(String[] args) throws Exception {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("apple");
        ts.add("core");
        ts.add("banana");

        for(String s : ts)
            System.out.println(s);

        HashSet<String> hs = new HashSet<>(ts);

        for(String s : hs)
            System.out.println(s);


        Iterator<String> iterator = hs.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.length() % 2 == 0) {
                iterator.remove();
            }
        }

        for(String s : hs)
            System.out.println(s);


    }
    
    // Ordered인 TreeSet은 사용자 정의 compare 구현이 가능하다.
    static class Node implements Comparable<Node> {
    	int n;
    	
    	/* this
    	 * 자신을 참조하는 참조변수
    	 * class  메소드 내에서만 사용 가능하다.
    	 * 생성자에서 다른 생성자 호출할 때 사용
    	 * 다른 생성하 호출 시 첫 줄에서만 사용이 가능하다.
    	 * */
    	public Node(int n) {
    		// 전달인자 부분이 객체 속성과 이름이 동일한 경우
    		// this를 사용하여 구분해야 한다.
    		this.n = n;
    	}
    	
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.n, other.n);
		}
    	
    }
}