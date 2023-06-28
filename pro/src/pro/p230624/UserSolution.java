package pro.p230624;

import java.util.HashMap;
import java.util.TreeSet;

public class UserSolution {

	class Room {
		int id;
		String str;
		int[] len;

		public Room(int id, String str, int[] len) {
			this.id = id;
			this.str = str;
			this.len = len.clone();
		}
	}

	int nowID;
	String[] strArr;
	HashMap<String, Room> strHash;
	HashMap<String, TreeSet<String>> frontHash;
	HashMap<String, TreeSet<String>> midHash;
	HashMap<String, TreeSet<String>> endHash;

	StringBuilder sb;

	public void init() {
		strArr = new String[30001];// id범위 (1 ~ 30000)
		strHash = new HashMap<>();
		frontHash = new HashMap<>();
		midHash = new HashMap<>();
		endHash = new HashMap<>();
	}

	public void addRoom(int mID, char[] mWord, int[] mDirLen) {
		sb = new StringBuilder();
		for (int i = 0; i < 11; i++)
			sb.append(mWord[i]);
		String str = sb.toString();
		strArr[mID] = str;
		strHash.put(str, new Room(mID, str, mDirLen));

		String fStr2 = str.substring(0, 2);
		String fStr4 = str.substring(0, 4);
		String mStr = str.substring(4, 7);
		String eStr2 = str.substring(9, 11);
		String eStr4 = str.substring(7, 11);

		if (frontHash.get(fStr2) == null) {
			frontHash.put(fStr2, new TreeSet<>());
		}
		if (frontHash.get(fStr4) == null) {
			frontHash.put(fStr4, new TreeSet<>());
		}
		if (midHash.get(mStr) == null) {
			midHash.put(mStr, new TreeSet<>());
		}
		if (endHash.get(eStr2) == null) {
			endHash.put(eStr2, new TreeSet<>());
		}
		if (endHash.get(eStr4) == null) {
			endHash.put(eStr4, new TreeSet<>());
		}
		// 각 해쉬에 추가
		frontHash.get(fStr2).add(str);
		frontHash.get(fStr4).add(str);
		midHash.get(mStr).add(str);
		endHash.get(eStr2).add(str);
		endHash.get(eStr4).add(str);
	}

	public void setCurrent(char[] mWord) {
		sb = new StringBuilder();
		for (int i = 0; i < 11; i++)
			sb.append(mWord[i]);
		Room nextRoom = strHash.get(sb.toString());
		nowID = nextRoom.id;
	}

	public int moveDir(int mDir) {
		Room nowRoom = strHash.get(strArr[nowID]);
		String str = nowRoom.str;
		if (mDir == 0) {// 앞으로 이동
			String fStr = str.substring(0, nowRoom.len[0]);
			if (endHash.get(fStr) == null || endHash.get(fStr).isEmpty())
				return 0;
			String nextString = endHash.get(fStr).first();
			if (nextString.equals(str)) { // 현재 위치와 동일하다면 그 다음 우선순위 지역으로 이동
				if (endHash.get(fStr).size() == 1) {
					return 0;
				}
				nextString = endHash.get(fStr).higher(str);
			}
			nowID = strHash.get(nextString).id;
		} else if (mDir == 1) {// 중간으로 이동
			String mStr = str.substring(4, 7);
			if (midHash.get(mStr) == null || midHash.get(mStr).isEmpty())
				return 0;
			String nextString = midHash.get(mStr).first();
			if (nextString.equals(str)) { // 현재 위치와 동일하다면 그 다음 우선순위 지역으로 이동
				if (midHash.get(mStr).size() == 1) {
					return 0;
				}
				nextString = midHash.get(mStr).higher(str);
			}
			nowID = strHash.get(nextString).id;
		} else if (mDir == 2) {// 뒤로 이동
			String eStr = str.substring(11 - nowRoom.len[2], 11);
			if (frontHash.get(eStr) == null || frontHash.get(eStr).isEmpty())
				return 0;
			String nextString = frontHash.get(eStr).first();
			if (nextString.equals(str)) { // 현재 위치와 동일하다면 그 다음 우선순위 지역으로 이동
				if (frontHash.get(eStr).size() == 1) {
					return 0;
				}
				nextString = frontHash.get(eStr).higher(str);
			}
			nowID = strHash.get(nextString).id;
		}
		return nowID;
	}

	public void changeWord(char[] mWord, char[] mChgWord, int[] wLen) {
		sb = new StringBuilder();
		for (int i = 0; i < 11; i++)
			sb.append(mWord[i]);
		Room room = strHash.remove(sb.toString());
		String fStr2 = room.str.substring(0, 2);
		String fStr4 = room.str.substring(0, 4);
		String mStr = room.str.substring(4, 7);
		String eStr2 = room.str.substring(9, 11);
		String eStr4 = room.str.substring(7, 11);
		frontHash.get(fStr2).remove(room.str);
		frontHash.get(fStr4).remove(room.str);
		midHash.get(mStr).remove(room.str);
		endHash.get(eStr4).remove(room.str);
		endHash.get(eStr2).remove(room.str);
		int mID = room.id;
		addRoom(mID, mChgWord, wLen);
	}
}
