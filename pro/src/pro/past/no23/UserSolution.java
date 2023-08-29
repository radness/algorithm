package pro.past.no23;

import java.util.Arrays;

class UserSolution {

	static int[][] map;
	static int sizeY;
	static int sizeX;
	
	public void init(int C) {
		map = new int[20][C];
		sizeY = 20;
		sizeX = C;
		
		return;
	}

	Main.Result dropBlocks(int mCol, int mHeight, int mLength) {
		
		int[] arr = new int[sizeX];
		Arrays.fill(arr, 0);
		
		for (int x = mCol; x < mCol + mLength; x++) {
//			searchBlock();
		}
		
		searchBlock(mCol, mHeight, mLength, sizeY - 1, 0);
		
		Main.Result ret = new Main.Result();

		int totalCnt = 0;
		
		for (int y = sizeY - 1; y >= 0; y--) {
			for (int x = sizeX - 1; x >= 0; x--) {
				if (map[y][x] == 1) {
					totalCnt++;
				}
			}
		}
		
		ret.count = totalCnt;
		ret.top = findTop(sizeY, sizeX, 0);
		
		return ret;

	}

	private void searchBlock(int mCol, int mHeight, int mLength, int startY, int startX) {
		for (int x = mCol; x < mCol + mLength; x++) {
			for (int y = startY; y > startY - mHeight; y--) {
				if (map[y][x] == 1) {
					searchBlock(mCol, mHeight, mLength, startY - 1, startX);
				}
				
				fillBlock(x, startY, mHeight);
				break;
				
			}
		}
		
	}

	private void fillBlock(int startX, int startY, int mHeight) {
		for (int i = startY; i > startY - mHeight; i--) {
			map[i][startX] = 1;
		}
	}

	private int findTop(int sizeY, int sizeX, int cnt) {
		for (int x = 0; x <= sizeX - 1; x++) {
			int tmpCnt = 0;
			for (int y = sizeY - 1; y >= 0; y--) {
				if (map[y][x] == 1) {
					tmpCnt = y;
				}
			}
			
			if (cnt < tmpCnt) {
				cnt = tmpCnt;
			}
		}
		
		return sizeY - cnt;
	}
}