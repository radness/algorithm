package basic.list;

import java.util.ArrayList;

public class ArrayListExam_Swap {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		
		arr.add(1);
		arr.add(3);
		
		System.out.print("Swap Àü : ");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + " ");
		}
		
		int tmp = arr.get(0);
		arr.set(0, arr.get(1));
		arr.set(1, tmp);
		
		System.out.print("\nSwap ÈÄ : ");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + " ");
		}
		
		
		
	}
}
