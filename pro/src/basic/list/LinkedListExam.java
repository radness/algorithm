package basic.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListExam {
	public static void main(String[] args) throws Exception {
		LinkedList<Integer> list = new LinkedList<>();

		list.add(1);
		list.add(0, 10);

		System.out.println(list);

		ArrayList<Integer> list2 = new ArrayList<>();

		list2.add(1);
		list2.add(0, 10);

		System.out.println(list2);
		
	}
}
