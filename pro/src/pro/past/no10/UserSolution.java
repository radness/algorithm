package pro.past.no10;

import java.util.HashMap;
import java.util.TreeSet;

// 성적조회
class UserSolution {

	// 점수[학년][성별]
	// [1, 2, 3 학년][0:남자, 1:여자]
	static HashMap<Integer, Node> hmap;

	static TreeSet<Node>[][] scoreSet;

	static class Node {
		int id;
		int grade;
		int gender;
		int score;

		public Node(int id, int grade, int gender, int score) {
			this.id = id;
			this.grade = grade;
			this.gender = gender;
			this.score = score;
		}
	}

	@SuppressWarnings("unchecked")
	public void init() {
		hmap = new HashMap<>();
		scoreSet = new TreeSet[4][3];

		for (int grade = 1; grade < 4; grade++) {
			for (int gender = 1; gender < 3; gender++) {
				scoreSet[grade][gender] = new TreeSet<>((a, b) -> {
					if (a.score == b.score)
						return a.id - b.id;

					return a.score - b.score;
				});
			}
		}

		return;
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		// 신규 학생 정보 생성
		// 남자 : 1, 여자 : 2
		int gender = mGender[0] == 'm' ? 1 : 2;

		Node node = new Node(mId, mGrade, gender, mScore);

		// 대상 그룹(학생, 설별) 추가
		scoreSet[mGrade][gender].add(node);
		hmap.put(mId, node);

		return scoreSet[mGrade][gender].last().id;
	}

	public int remove(int mId) {
		if (hmap.containsKey(mId)) {
			Node node = hmap.get(mId);

			scoreSet[node.grade][node.gender].remove(node);

			if (scoreSet[node.grade][node.gender].isEmpty())
				return 0;

			return scoreSet[node.grade][node.gender].first().id;
		}

		return 0;
	}

	// 집합에 속하는 학생 중 점수가 가장 낮은 학생 ID 반환
	// 집합 -> Set
	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		Node node = new Node(0, 0, 0, mScore);
		Node ans = new Node(0, 0, 0, Integer.MAX_VALUE);

		for (int i = 0; i < mGradeCnt; i++) {
			for (int j = 0; j < mGenderCnt; j++) {
				int gender = mGender[j][0] == 'm' ? 1 : 2;
				Node now = scoreSet[mGrade[i]][gender].ceiling(node);
				if (now != null) {
					// ans와 now를 비교하여 ans에 저장
					if (now.score < ans.score) {
						ans = now;
					} else if (now.score == ans.score && now.id < ans.id) {
						ans = now;
					}
				}
			}
		}

		return ans.id;
	}

}
