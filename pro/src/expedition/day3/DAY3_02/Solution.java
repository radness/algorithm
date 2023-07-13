package expedition.day3.DAY3_02;

import java.util.Map;
import java.util.TreeMap;

class Solution {

	static TreeMap<Integer, String> tmap;

	public void init() {
		tmap = new TreeMap<>();

	}

	public void addExam(int level, String name) {
		if (tmap.get(level) == null) {
			tmap.put(level, name);
		}
	}

	public String getCustomExam(int studyAmount) {
		Integer level = tmap.floorKey(studyAmount);

		if (level == null)
			return "noExam";

		return tmap.get(level);
	}

	public int changeExamLevel(int prevLevel, int AfterLevel) {
		if (tmap.get(prevLevel) == null)
			return -1;

		String preLvlName = tmap.get(prevLevel);

		// prevLevel이 존재하는 경우
		if (tmap.get(AfterLevel) == null) {
			preLvlName = tmap.get(prevLevel);
			tmap.remove(prevLevel);
			tmap.put(AfterLevel, preLvlName);

			return AfterLevel;
		}

		// 변경
		if (prevLevel < AfterLevel) {
			while (tmap.containsKey(AfterLevel) && prevLevel < AfterLevel) {
				AfterLevel--;
			}
		} else {
			while (tmap.containsKey(AfterLevel) && prevLevel > AfterLevel) {
				AfterLevel++;
			}
		}

		if (prevLevel == AfterLevel)
			return AfterLevel;
		
		tmap.put(AfterLevel, preLvlName);
		tmap.remove(prevLevel);
		return AfterLevel;
	}

	public String getMaxExam() {
		return tmap.get(tmap.lastKey());
	}

	public String getMinExam() {
		return tmap.get(tmap.firstKey());
	}

	public int countRangeExam(int A, int B) {
		int cnt = 0;

		for (Map.Entry<Integer, String> entry : tmap.subMap(A, true, B, true).entrySet()) {
			cnt++;
		}

		return cnt;
	}
}