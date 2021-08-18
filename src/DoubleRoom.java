public class DoubleRoom extends Room {

	private final int capacity = 2; // amount of persons that can stay in the room
	private final int cleaningTime = 1; // amount of time room needs for cleaning

	// DoubleRoom constructor
	public DoubleRoom(int roomNumber, String wing, String status, int roomID, String type) {
		super(roomNumber, wing, status, roomID, type);

	}

	// Empty DoubleRoom constructor
	public DoubleRoom() {
	}

	// All getters
	public int getCapacity() {
		return capacity;
	}

	public int getCleaningTime() {
		return cleaningTime;
	}

	// Cleaning timer
	public int cleanDoubleRoom() {
		int counter = 0;

		for (int i = counter; i <= cleaningTime; i++) {
			counter = i;
			if (counter == cleaningTime) {
				counter = cleaningTime;
				System.out.println("Room is cleaned and ready!");
			} else {
				int remainingTime = cleaningTime - counter;
				//System.out.println("Please, wait " + remainingTime + "s " + " until you room is ready!");
			}
		}
		return counter;
	}
}