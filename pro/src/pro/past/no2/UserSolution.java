package pro.past.no2;

class UserSolution {
	static char[][] map;
	static String initStr;
	static StringBuilder initSb;
	static int height, width;
	static int cY, cX;

	// 100
	void init(int H, int W, char mStr[]) {
		cY = 0;
		cX = 0;
		height = H;
		width = W;
		initStr = new String();
		initSb = new StringBuilder();

		for (int i = 0; i < mStr.length; i++) {
			initSb.append(mStr[i]);
		}

		initStr = initSb.toString().trim();
		map = new char[H][W + 1];
		int idx = 0;
		int initStrLength = initStr.length();
		map[0][0] = '|';

		for (int y = 0; y < H; y++) {

			if (y == 0) {
				for (int x = 1; x < W + 1; x++) {
					if (initStrLength > idx) {
						map[y][x] = initStr.charAt(idx++);
					}
				}
			} else {
				for (int x = 0; x < W; x++) {
					if (initStrLength > idx) {
						map[y][x] = initStr.charAt(idx++);
					}
				}
			}
		}

		initStr = "|" + initStr;
		initSb.insert(0, "|");

//		System.out.println(map);
	}

	// 200
	void insert(char mChar) {
		int idx = initStr.indexOf("|");
		initSb.insert(idx, mChar);
		initStr = initSb.toString().trim();
		
		for (int i = width; i > cX; i--) {
			map[cY][i] = map[cY][i - 1];
		}
		map[cY][cX] = mChar;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width + 1; x++) {
				if (map[y][x] == '|') {
					cY = y;
					cX = x;
				}
			}
		}

//		System.out.println("200 initStr : " + map[cY][cX - 1]);
	}

	// 300
	char moveCursor(int mRow, int mCol) {
		// 커서 빼기
		map[cY][cX] = 0;
		
		// 커서 빼고 정렬
		for (int x = cX; x < width; x++) {
			map[cY][x] = map[cY][x + 1];
		}
		map[cY][width] = 0;
					
		if (map[mRow - 1][mCol - 1] == 0) {
//			System.out.println("비어있는 경우");
			for (int i = 0; i < width; i++) {
				if (map[mRow - 1][i] == 0) {
					map[mRow - 1][i] = '|';
					cY = mRow - 1;
					cX = i;
					break;
				}
			}
		} else {
			// 커서 넣을 row 정렬
			for (int x = width; x > mCol - 1; x--) {
				map[mRow - 1][x] = map[mRow - 1][x - 1];
			}
			
			// 커서 넣기
			map[mRow - 1][mCol - 1] = '|';
			
			cY = mRow - 1;
			cX = mCol - 1;
		}
		
		initSb = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x <= width; x++) {
				if(map[y][x] != 0) {
					initSb.append(map[y][x]);
				}
			}
		}
		
		initStr = initSb.toString().trim();
		char ans = '$';

		if (map[cY][cX + 1] == 0) {
//			System.out.println("300 ans : " + ans);
			return ans;
		}
		
		for (int i = 0; i < initStr.length(); i++) {
			if ('|' == initStr.charAt(i)) {
				if (initStr.charAt(i + 1) != 0) {
					ans = initStr.charAt(i + 1);
				}
			}
		}

//		System.out.println("300 ans : " + ans);
		
		return ans;
	}

	// 400
	int countCharacter(char mChar) {
		int cnt = 0;
		int idx = 0;

		for (int i = 0; i < initStr.toString().length(); i++) {
			if ('|' == initStr.charAt(i)) {
				idx = i;
			}
		}

		for (int i = idx; i < initStr.toString().length(); i++) {
			if (mChar == initStr.charAt(i)) {
				cnt++;
			}
		}

//		System.out.println("400 cnt : " + cnt);

		return cnt;
	}
}