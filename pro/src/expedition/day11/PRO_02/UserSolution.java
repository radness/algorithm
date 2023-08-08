package expedition.day11.PRO_02;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 호텔 추천 앱
public class UserSolution {
	static int HOTEL_CNT;
	static int HOTEL_ROOM_CNT; // 총 방 갯수
	static PriorityQueue<Room> pq; // 고객이 입력한 조건에 부합하는 객실들 중 가장 저렴한 객실 목록
	// key : 객실 id
	// value : 객실 정보
	static HashMap<Integer, Room>[] HOTELS;

	// 날짜별 예약 여부[방번호][날짜]
	static int[][] RESERVATION;

	static class Room implements Comparable<Room> { // 방
		int roomId; // 방 id
		int area; // 지역
		int bedCnt; // 침대 수
		int roomType; // 객실 종류
		int viewType; // 조망 종류
		int price; // 가격

		public Room(int roomId, int area, int bedCnt, int roomType, int viewType, int price) {
			this.roomId = roomId;
			this.area = area;
			this.bedCnt = bedCnt;
			this.roomType = roomType;
			this.viewType = viewType;
			this.price = price;
		}

		@Override
		public int compareTo(Room other) {
			// 1. 가장 저렴한 순으로
			if (this.price < other.price)
				return -1;
			if (this.price > other.price)
				return 1;
			// 2. 가격이 같으면 id값이 작은 순으로
			if (this.roomId < other.roomId)
				return -1;
			if (this.roomId > other.roomId)
				return 1;
			return 0;
		}
	}

	public void init(int N, int roomCnt[]) {
		HOTEL_CNT = N;
		HOTEL_ROOM_CNT = N * 100; // 최대 객실 수
		HOTELS = new HashMap[N + 1];

		for (int i = 1; i <= N; i++) {
			HOTELS[i] = new HashMap<>();
		}

		RESERVATION = new int[HOTEL_ROOM_CNT][20001]; // 1이면 예약
	}

	// 객실 추가
	// roomInfo[]
	// [0] : 지역, [1] : 침대 수, [2] : 객실 종류, [3] : 조망 종류, [4] : 가격
	public void addRoom(int hotelID, int roomID, int roomInfo[]) {
		HOTELS[hotelID].put(roomID, new Room(roomID, roomInfo[0], roomInfo[1], roomInfo[2], roomInfo[3], roomInfo[4]));
	}

	// 사용자가 원하는 요구사항을 만족하는 객실을 추천받아 예약
	// requirements[]
	// [0] : 체크인 날짜, [1] : 체크아웃 날짜, [2] : 지역, [3] : 침대 수, [4] : 객실 종류, [5] : 조망 종류
	public int findRoom(int requirements[]) {
		int roomId = -1;
		int checkIn = requirements[0];
		int checkOut = requirements[1];

		pq = new PriorityQueue<>();

		for (int i = 1; i <= HOTEL_CNT; i++) {
			for (Map.Entry<Integer, Room> entry : HOTELS[i].entrySet()) {
				boolean resFlag = false;
				Room now = entry.getValue();

				// 체크인 ~ 체크아웃 기간에 예약되어 있는 객실은 예약할 수 없다.
				for (int fromTo = checkIn; fromTo < checkOut; fromTo++) {
					if (RESERVATION[now.roomId][fromTo] == 1) {
						// 예약이 되어 있으므로
						resFlag = true;
						break;
					}
				}

				// 예약되어 있지 않으면
				if (!resFlag) {
					// 요구사항에 부합한지 확인
					if (now.area == requirements[2] && now.bedCnt == requirements[3] && now.roomType == requirements[4]
							&& now.viewType == requirements[5]) {
						// 부합하면 예약 가능 목록에 추가
						pq.add(now);
					}
				}
			}
		}

		if (!pq.isEmpty()) {
			// 조건에 부합하는 방 번호
			Room room = pq.poll();
			// 예약 처리 후
			for (int i = checkIn; i < checkOut; i++) {
				RESERVATION[room.roomId][i] = 1;
			}
			// 예약된 객실 id return
			roomId = room.roomId;
		}

//		System.out.println("방번호 : " + roomId);
		return roomId;
	}

	// hotelID를 값으로 갖는 모든 객실의 가격은 호출될 때마다 10%씩 인상된다.
	public int risePrices(int hotelID) {
		// 해당 호텔이 갖는 모든객실의 인상 후 가격의 합
		int totalPrice = 0;

		for (Map.Entry<Integer, Room> entry : HOTELS[hotelID].entrySet()) {
			Room now = entry.getValue();
			now.price = (int) Math.floor(now.price * 1.1); // 10% 인상
			totalPrice += now.price;
		}

//		System.out.println("해당 호텔이 갖는 모든 객실의 인상 후 가격의 합 : " + totalPrice);
		return totalPrice;
	}
}
