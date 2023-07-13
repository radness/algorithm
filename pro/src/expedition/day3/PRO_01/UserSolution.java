package expedition.day3.PRO_01;

import java.util.TreeMap;

class UserSolution {

	// key : 가로줄이 있는 행
	// value : 이 행에서 가로줄을 타면 어느 열로 가는지에 대한 정보
	static TreeMap<Integer, Integer>[] tmap;

	public void init() {
		tmap = new TreeMap[101];

		for (int i = 0; i <= 100; i++) {
			tmap[i] = new TreeMap<>();
		}

	}

	public void add(int x, int y) {
		tmap[x].put(y, x + 1);
		tmap[x + 1].put(y, x);

	}

	public int getCrossCnt(int lineID) {
		int x = lineID;
		int y = 0;

		int cnt = 0;
		
		while (true) {
			Integer nY = tmap[x].higherKey(y);
			
			// 가로줄이 없다면 break
			if (nY == null)
				break;
			
			// 
			cnt++;
			y = nY;
			x = tmap[x].get(nY);
		}

		return cnt;
	}

	public int getID(int x, int y) {
		while (true) {
			Integer nY = tmap[x].lowerKey(y);
			
			if (nY == null)
				break;
			
			y = nY;
			x = tmap[x].get(nY);
			
		}
		return x;
	}

	public void remove(int x, int y) {
		// x열에서 y행에 존재하는 가로줄 삭제
		tmap[x].remove(y);
		// x+1열에서 y행에 존재하는 가로줄 삭제
		tmap[x + 1].remove(y);

	}
}