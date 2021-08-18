
public class FamilyRoom extends Room {

	private final int capacity = 4; // amount of persons that can stay in the room
	private final int cleaningTime = 2; // amount of time room needs for cleaning

	// FamilyRoom constructor
	public FamilyRoom(int roomNumber, String wing, String status, int roomID, String type) {
		super(roomNumber, wing, status, roomID, type);
	}

	// Empty FamilyRoom constructor
	public FamilyRoom() {

	}

	// All getters
	public int getCapacity() {
		return capacity;
	}

	public int getCleaning() {
		return cleaningTime;
	}

	// Cleaning timer
	public int cleanFamilyRoom() {
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