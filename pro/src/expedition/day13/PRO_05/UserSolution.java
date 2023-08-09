package expedition.day13.PRO_05;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

// 성적조회
class UserSolution {

	static HashMap<Integer, Student> hmap;
	// [학년][성별]
	static TreeSet<Info>[][] bestScore;

	static class Info implements Comparable<Info> {
		int id;
		int score;

		public Info(int id, int score) {
			this.id = id;
			this.score = score;
		}

		@Override
		public int compareTo(Info other) {
			// 1. 점수가 높은 순
			if (this.score > other.score)
				return -1;
			if (this.score < other.score)
				return 1;
			// 2. id가 큰 순 
			if (this.id > other.id)
				return -1;
			if (this.id < other.id)
				return 1;
			return 0;
		}
	}
	
	static class QueryInfo implements Comparable<QueryInfo> {
		int id;
		int score;

		public QueryInfo(int id, int score) {
			this.id = id;
			this.score = score;
		}

		@Override
		public int compareTo(QueryInfo other) {
			// 1. 점수가 낮은 순
			if (this.score < other.score)
				return -1;
			if (this.score > other.score)
				return 1;
			// 2. id가 작은 순
			if (this.id < other.id)
				return -1;
			if (this.id > other.id)
				return 1;
			return 0;
		}
	}

	static class Student {
		int grade;
		int gender;
		int score;

		public Student(int grade, int gender, int score) {
			this.grade = grade;
			this.gender = gender;
			this.score = score;
		}
	}

	public void init() {
		hmap = new HashMap<>();

		// [1, 2, 3학년][남자, 여자]
		bestScore = new TreeSet[4][2];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				bestScore[i][j] = new TreeSet<>();
			}
		}

		return;
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		int gender = String.valueOf(mGender).trim().equals("male") ? 0 : 1;

		hmap.put(mId, new Student(mGrade, gender, mScore));
		bestScore[mGrade][gender].add(new Info(mId, mScore));

//		System.out.println("점수가 가장 높은 학생의 id : " + bestScore[mGrade][gender].first().id);
		
		return bestScore[mGrade][gender].first().id;
	}

	public int remove(int mId) {
		if (hmap.get(mId) != null) {
			Student now = hmap.get(mId);
			
			bestScore[now.grade][now.gender].remove(new Info(mId, now.score));
			hmap.remove(mId);

			// 학년과 성별이 동일한 학생 중 점수가 가장 낮은 학생 id 반환
			if (bestScore[now.grade][now.gender].isEmpty()) {
//				System.out.println("remove : " + 0);
				return 0;
			}
			
//			System.out.println("remove : " + bestScore[now.grade][now.gender].last().id);
			
			return bestScore[now.grade][now.gender].last().id;
		}

		return 0;
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		TreeSet<QueryInfo> target = new TreeSet<>();

		for (int i = 0; i < mGradeCnt; i++) {
			for (int j = 0; j < mGenderCnt; j++) {
//				String gender = String.valueOf(mGender[j]);

				Iterator<Info> iter = bestScore[mGrade[i]][String.valueOf(mGender[j]).trim().equals("male") ? 0 : 1].iterator();

				while (iter.hasNext()) {
					Info now = (Info) iter.next();

					if (now.score >= mScore) {
						target.add(new QueryInfo(now.id, now.score));
					}
				}
			}
		}

		if (!target.isEmpty()) {
//			System.out.println("query : " + target.first().id);
			return target.first().id;
		}

		return 0;
	}
}