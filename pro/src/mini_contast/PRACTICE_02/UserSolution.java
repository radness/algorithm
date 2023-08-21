package mini_contast.PRACTICE_02;

import java.util.PriorityQueue;

// Worm's Life
class UserSolution {

	static class Worm implements Comparable<Worm> {
		int id;
		int life;

		public Worm(int id, int life) {
			this.id = id;
			this.life = life;
		}

		@Override
		public int compareTo(Worm other) {
			return Integer.compare(this.life, other.life);
		}
	}

	static PriorityQueue<Worm> pq;
	static int[] worms;
	static int YEAR;
	
	public void init() {
		pq = new PriorityQueue<>();
		worms = new int[100001];
		YEAR = 0;
	}

	public void add(int id, int life) {
		// 생존하고 있는 지렁이
		if (worms[id] != 0)
			return;
		
		// 추가
		worms[id] = life + YEAR;
		
		pq.add(new Worm(id, life + YEAR));
	}

	public int span(int year) {
		YEAR += year;
		
		// 시간이 지나 죽는 지렁이들 삭제
		while (!pq.isEmpty()) {
			Worm now = pq.peek();
			
			if (now.life > YEAR)
				break;
			
			pq.remove();
			worms[now.id] = 0;
		}
		
		return pq.size();
	}

	public int getLife(int id) {
		if (worms[id] < 1)
			return -1;
		
		return worms[id] - YEAR;
	}
}