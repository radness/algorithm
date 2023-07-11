package expedition.day2.DAY2_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	static ArrayDeque<String> frontQueue;
	static ArrayDeque<String> endQueue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		endQueue = new ArrayDeque<>();
		frontQueue = new ArrayDeque<>();

		int Q = Integer.parseInt(br.readLine());
		String currentSite = null;

		for (int tc = 0; tc < Q; tc++) {
			st = new StringTokenizer(br.readLine());

			char type = st.nextToken().charAt(0);

			if (type == 'B') { // 뒤로 가기
				if (endQueue.isEmpty())
					continue;

				frontQueue.addFirst(currentSite);
				
				currentSite = endQueue.removeLast();
			} else if (type == 'F') { // 앞으로 가기
				if (frontQueue.isEmpty())
					continue;

				endQueue.addLast(currentSite);
				
				currentSite = frontQueue.removeFirst();
			} else if (type == 'A') { // i 사이트 접속
				// 앞으로 가기 메모리 전체 삭제
				frontQueue = new ArrayDeque<>();

				String nextSite = st.nextToken();

				if (currentSite != null)
					endQueue.addLast(currentSite);

				currentSite = nextSite;

			} else if (type == 'C') { // 압출 행
				ArrayDeque<String> tmpQueue = new ArrayDeque<>();

				while (!endQueue.isEmpty()) {
					// 중복되는 경우 체크
					if (tmpQueue.isEmpty()) {
						tmpQueue.addLast(endQueue.removeFirst());
					} else {
						if (tmpQueue.getLast().equals(endQueue.getFirst())) {
							endQueue.removeFirst();
						} else {
							tmpQueue.addLast(endQueue.removeFirst());
						}
					}
				}
				endQueue = tmpQueue;
			}
		}

		System.out.println(currentSite);

		if (!endQueue.isEmpty()) {
			Iterator<String> iter = endQueue.descendingIterator();

			while (iter.hasNext()) {
				System.out.print(iter.next() + " ");
			}
			System.out.println();
		} else {
			System.out.println("none");
		}

		if (!frontQueue.isEmpty()) {
			Iterator<String> iter = frontQueue.iterator();

			while (!iter.hasNext()) {
				System.out.println(iter.next() + " ");
			}
			System.out.println();
		} else {
			System.out.println("none");
		}
	}
}
