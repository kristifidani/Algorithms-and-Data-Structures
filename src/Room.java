public class Room {

	private int roomID = 0; // room unique id
	private int roomNumber; // room number
	private String wing; // wing where the room is located
	private String status; // status of the room in order to keep track of operations like cleaning and
							// check-in/checkout
	private boolean needsCleaning = false; // Check if room needs cleaning

	private String type; // distinguish between room types

	// Create vector with 100 capacity
	private Vector rooms = new Vector(100);
	private int clientID = -1; // client id when a client checks in a room.

	// Room constructor
	Room(int roomNumber, String wing, String status, int roomID, String type) {
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.wing = wing;
		this.status = status;
		this.type = type;
	}

	// empty constructor to call it without having to assign a complete room
	public Room() {

	}

	// auto generated getters and setters
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String roomStatus) {
		this.status = roomStatus;
	}

	public boolean isNeedsCleaning() {
		return needsCleaning;
	}

	public void setNeedsCleaning(boolean needsCleaning) {
		this.needsCleaning = needsCleaning;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// Get room by id and return it
	public Room getRoom(int ID) {
		for (int i = 0; i < rooms.size(); i++) {
			Room r = (Room) rooms.get(i);
			if (r.getRoomID() == ID) {
				return r;
			}
		}
		return null;
	}

	// Add new rooms
	public void addNewRoom(Room r) {
		// Add family room
		if (r.getType().equals("Family")) {
			rooms.addLast((FamilyRoom) r);
			roomID++;
			// Add double room
		} else {
			rooms.addLast((DoubleRoom) r);
			roomID++;
		}
	}

	/**
	 * This method is used to filter the rooms by the desired status
	 *
	 * @param Status The status of the rooms (READY, CHECKED-OUT, OCCUPIED)
	 * @return Vector containing all the rooms filtered
	 */
	public Vector FilterRooms(String Status) {
		Vector Rooms = new Vector(100);
		switch (Status) {
		case "Ready":
		case "Occupied":
		case "Checked-Out":
			for (int i = 0; i < rooms.size(); i++) {
				Room r = (Room) rooms.get(i);
				if (r.getStatus().equals(Status)) {
					Rooms.addFirst(r);
					System.out.println("Room(" + r.getRoomID() + "):" + r.getRoomNumber() + "~" + "" + r.getWing()
							+ " ---> " + r.getStatus());
				}
			}
			break;
		case "All":
			for (int i = 0; i < rooms.size(); i++) {
				Room r = (Room) rooms.get(i);
				System.out.println(r.toString());
			}
			break;
		case "ID":
			for (int i = 0; i < rooms.size(); i++) {
				Room r = (Room) rooms.get(i);
				if (r.getStatus().equals("Ready")) {
					Rooms.addFirst(r.getRoomID());
					System.out.println(r.toString());
				}
			}
			break;
		default:
			System.out.println("Problem filtering rooms!");
			break;
		}
		return Rooms;
	}

	/**
	 * This method is used to get an available room for check-in
	 *
	 * @param type The type of the room (Family/Double)
	 * @return The type of room specified that is available
	 */
	public Room getAvailableRoom(String type) {
		Room room = null;
		for (int i = 0; i < rooms.size(); i++) {
			room = (Room) rooms.get(i);
			if (room.getStatus().equals("Ready") && room.getType().equals(type)) {
				return room;
			} else if (room.getStatus().equals("CheckedOut") && room.getType().equals(type)) {
				return room;
			} else if (!room.getType().equals(type) || !room.getStatus().equals("Ready")
					|| !room.getStatus().equals("CheckedOut")) {
				continue;
			} else {
				System.out.println("There is no room available!");
			}
		}
		return room;
	}

	// Find the wing with most rooms to be cleaned and start the cleaning procedure
	public String startCleaning() {
		Room room = null;
		int countA = 0, countB = 0, countC = 0, countD = 0; // counter for each wing

		// loop through rooms and increment corresponding wing counter
		for (int i = 0; i < rooms.size(); i++) {
			room = (Room) rooms.get(i);

			if (room.getStatus().equals("CheckedOut") && room.getWing().equals("A")) {
				countA++;
			} else if (room.getStatus().equals("CheckedOut") && room.getWing().equals("B")) {
				countB++;
			} else if (room.getStatus().equals("CheckedOut") && room.getWing().equals("C")) {
				countC++;
			} else if (room.getStatus().equals("CheckedOut") && room.getWing().equals("D")) {
				countD++;
			}
		}

		// compare counters
		if (countA > countB && countA > countC && countA > countD) {
			wing = "A";
		} else if (countB > countC && countB > countD) {
			wing = "B";
		} else if (countC > countD) {
			wing = "C";
		} else {
			wing = "D";
		}

		System.out.println("Cleaning will start from wing: " + wing);
		// return wing with most rooms to be cleaned
		return wing;
	}

	// Cleaning procedure function
	public void cleaningProcedure(Comparable startingWing) {
		// create empty objects of room and its types
		Room room = null;
		DoubleRoom droom = new DoubleRoom();
		FamilyRoom froom = new FamilyRoom();

		boolean check = false; // helping variable to put priority of family rooms over double rooms

		// loop through rooms
		for (int i = 0; i < rooms.size(); i++) {
			room = (Room) rooms.get(i);

			// clean all family rooms first
			if (room.getWing().equals(startingWing) && room.getType().equals("Family")
					&& room.getStatus().equals("CheckedOut")) {
				int timer = froom.cleanFamilyRoom();
				if (timer == 2) {
					room.setStatus("Ready");
				}
			}
			if (!room.getStatus().equals("CheckedOut") && room.getType().equals("Family")
					&& room.getWing().equals(startingWing)) {
				check = true;
			}
		}

		for (int i = 0; i < rooms.size(); i++) {
			room = (Room) rooms.get(i);

			// clean double rooms after all family rooms are finished
			if (check == true && room.getWing().equals(startingWing) && room.getType().equals("Double")
					&& room.getStatus().equals("CheckedOut")) {
				int timer = droom.cleanDoubleRoom();
				if (timer == 1) {
					room.setStatus("Ready");
				}
			}
		}
	}

	// print room format
	public String toString() {
		String s = "";
		s += "Type: " + type + "||" + "ID: " + roomID + "||" + "Wing: " + wing + "||" + "Number: " + roomNumber + "||"
				+ "Status: " + status;
		return s;
	}

}