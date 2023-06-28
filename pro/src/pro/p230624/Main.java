package pro.p230624;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws Exception {
		HashMap<String, TreeSet<String>> hmap = new HashMap<>();
		
		hmap.put("id1", new TreeSet<>());
		hmap.get("id1").add("aaa");
		hmap.get("id1").add("bbb");
		hmap.get("id1").add("abb");
		
		hmap.put("id2", new TreeSet<>());
		hmap.get("id2").add("ccc");
		hmap.get("id2").add("bdb");
		hmap.get("id2").add("acb");
		
		for (Map.Entry<String, TreeSet<String>> entry : hmap.entrySet()) {
			System.out.println("key : " + entry.getKey() + " value : " + entry.getValue());
		}

		HashMap<Integer, PriorityQueue<Integer>> hmap2 = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		hmap2.put(1, pq);
		pq.add(6);
		pq.add(2);
		pq.add(7);
		
		while (!hmap2.get(1).isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
		
		System.out.println();
		System.out.println("Comparator.reverseOrder()");
		
		HashMap<Integer, PriorityQueue<Integer>> hmap3 = new HashMap<>();
		pq = new PriorityQueue<>(Comparator.reverseOrder());

		hmap3.put(1, pq);
		pq.add(6);
		pq.add(2);
		pq.add(7);
		
		while (!hmap3.get(1).isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
		
		System.out.println();
		
		TreeSet<String> tset = new TreeSet<>();
		
		tset.add("sdfd");
		tset.add("dhby");
		tset.add("absd");
		tset.add("xcte");
		tset.add("onmd");

		Iterator<String> iter = tset.iterator();

		System.out.println("tset 출력");
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		
	}
}
