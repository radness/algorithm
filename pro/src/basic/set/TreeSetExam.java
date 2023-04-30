package basic.set;

import java.util.*;
import java.lang.*;
import java.io.*;

/* TreeSet
 * �����Ǵ� ������ �������ְ� �ʹٸ� TreeSet ����Ѵ�.
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
    
    // Ordered�� TreeSet�� ����� ���� compare ������ �����ϴ�.
    static class Node implements Comparable<Node> {
    	int n;
    	
    	/* this
    	 * �ڽ��� �����ϴ� ��������
    	 * class  �޼ҵ� �������� ��� �����ϴ�.
    	 * �����ڿ��� �ٸ� ������ ȣ���� �� ���
    	 * �ٸ� ������ ȣ�� �� ù �ٿ����� ����� �����ϴ�.
    	 * */
    	public Node(int n) {
    		// �������� �κ��� ��ü �Ӽ��� �̸��� ������ ���
    		// this�� ����Ͽ� �����ؾ� �Ѵ�.
    		this.n = n;
    	}
    	
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.n, other.n);
		}
    	
    }
}