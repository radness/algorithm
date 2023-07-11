package expedition.day2.DAY2_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.StringTokenizer;

// 인터넷

public class Main2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayDeque<String> frontQueue;
	static ArrayDeque<String> endQueue;

	public static void main(String[] args) throws IOException {

		frontQueue = new ArrayDeque<>();// 앞으로 가기
		endQueue = new ArrayDeque<>(); // 뒤로가기

		int Q = Integer.parseInt(br.readLine());

		// 지금 접속하고 있는 사이트
		String cursite = null;

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			// 일단 맨 앞에거만 빼옵니다.
			char cmd = st.nextToken().charAt(0);
			// cmd에 대한 처리
			if (cmd == 'B') {
				// 뒤로가기에 아무것도 없으면 -> 동작하지 않습니다.
				if (endQueue.isEmpty())
					continue;
				// 현재 접속하고 있는 사이트를 앞으로가기 메모리에 저장
				frontQueue.addFirst(cursite);
				// 지금 내가 접속하고 있는 페이지 = 뒤로가기 메모리의 가장 최근 것
				// removeLast() = 삭제하면서 return
				cursite = endQueue.removeLast();
			} else if (cmd == 'F') {
				if (frontQueue.isEmpty())
					continue;
				endQueue.addLast(cursite);
				cursite = frontQueue.removeFirst();
			} else if (cmd == 'A') {
				// 앞으로가기 메모리 올 삭제
				frontQueue = new ArrayDeque<>();

				// 지금 Access할려는 사이트
				String nextsite = st.nextToken();

				// 처음 접속하는 경우가 아닐때만 => 뒤로가기 메모리에 현재 페이지 저장
				if (cursite != null)
					endQueue.addLast(cursite);

				// 처음접속시 사이트만 변경
				cursite = nextsite;

			} else if (cmd == 'C') {
				// 임시 deque
				ArrayDeque<String> temp = new ArrayDeque<>();
				// 뒤로가기 deque에 있는 요소들을 전부 하나씩 빼봅니다.
				while (!endQueue.isEmpty()) {
					// 중복이 되면 안넣어야 합니다.
					// #1. 첫번째것은 중복될일이 없다.
					if (temp.isEmpty())
						temp.addLast(endQueue.removeFirst());
					// 두번째꺼부터는 중복되는가? 를 확인해야 합니다.
					else {
						// peek()
						// 중복이라면 temp에 넣지는 않는데, 삭제는 해야 합니다.
						// 문자열이니까 equals로 비교해야 합니다.
						if (temp.getLast().equals(endQueue.getFirst())) {
							endQueue.removeFirst();
						} else
							temp.addLast(endQueue.removeFirst());
					}
				}
				endQueue = temp;
			}
		}
		// int de=1;
		// 현재 페이지 출력
		System.out.println(cursite);

		// 뒤로 가기 페이지 가장 최근 순서로 출력
		if (endQueue.isEmpty())
			System.out.println("none");
		else {
			Iterator<String> it = endQueue.descendingIterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}
			System.out.println();
		}

		// 앞으로 가기 페이지 가장 최근 순서로 출력
		if (frontQueue.isEmpty())
			System.out.println("none");
		else {
			Iterator<String> it = frontQueue.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}
			System.out.println();
		}
	}
}