package mini_contast.PAST_01;

import java.util.TreeMap;

class UserSolution {
	// key : y
	// value : x
	TreeMap<Integer, Integer> tmap;
	
	public void init() {
		tmap = new TreeMap<>();
	}

	// 선긋기
	public void add(int x, int y) {
		// TreeMap에 좌표 추가
		tmap.put(y, x);
	}

	public int getCrossCnt(int lineID) {
		int cnt = 0;
		int nowX = lineID;
		
		for (Integer lineY : tmap.keySet()) {
			int lineX = tmap.get(lineY);
			if (nowX == lineX) { // 자기를 기준으로 오른쪽으로 넘어갈 경우
				nowX++;
				cnt++;
			} else if (nowX == lineX + 1) { // 자기를 기준으로 왼쪽으로 넘어갈 경우
				nowX--;
				cnt++;
			}
		}
		return cnt;
	}

	public int getID(int x, int y) {
		int nowY = y;
		int nowX = x;
		
		// 입력위치에서 가장 가까운 사다리 선을 탐색한다.
		while (tmap.lowerKey(nowY) != null) {
			int lineY = tmap.lowerKey(nowY);
			int lineX = tmap.get(lineY);
			
			if (nowX == lineX) { // 자기를 기준으로 오른족으로 넘어갈 경우
				nowX++;
			} else if (nowX == lineX + 1) { // 자기를 기준으로 왼쪽으로 넘어갈 경우
				nowX--;
			}
			nowY = lineY;
		}
		return nowX;
	}

	public void remove(int x, int y) {
		tmap.remove(y);
	}
}