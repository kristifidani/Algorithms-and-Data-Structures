public class HotelManagement implements IManagementSystem {

	private Room room = new Room(); // create empty room object
	private Client clients = new Client(); // create empty client object
	Wing wing = new Wing(); // create empty wing object

	@Override
	public int addDoubleRoom(String wing, int roomNumber, String status) {
		// add double room by calling the DoubleRoom constructor
		room.addNewRoom(new DoubleRoom(roomNumber, wing, status, room.getRoomID(), "Double"));
		return room.getRoomID();
	}

	@Override
	public int addFamilyRoom(String wing, int roomNumber, String status) {
		// add family room by calling the FamilyRoom constructor
		room.addNewRoom(new FamilyRoom(roomNumber, wing, status, room.getRoomID(), "Family"));
		return room.getRoomID();
	}

	@Override
	public int addClient(String name, String emailAddress) {
		// add clients by calling the Client constructor
		clients.addNewClient(new Client(name, emailAddress, clients.getClientID()));
		return clients.getClientID();
	}

	@Override
	public void printRooms() {
		// Call FilterRooms function from the Room class
		System.out.println("All Rooms: ");
		room.FilterRooms("All");
	}

	@Override
	public void printClients() {
		// Printed from the Client class
		System.out.println("All Clients: ");
		clients.printClients();
	}

	@Override
	public int checkInDoubleRoom(int client) {
		System.out.println("Check in Double Room");
		// Get available double rooms
		Room doubleRoomAvailable = (DoubleRoom) room.getAvailableRoom("Double");
		// Get client by id
		Client c = clients.getClient(client);
		// If client does not have a room and room does not have a client we check-in.
		// Also NeedsClening function is to check whether the room needs cleaning or
		// not.
		// set room status to occupied
		if (c.getRoomID() == -1 && doubleRoomAvailable.getClientID() == -1
				&& doubleRoomAvailable.isNeedsCleaning() == false) {
			doubleRoomAvailable.setClientID(c.getClientID()); // connect room with client
			c.setRoomID(doubleRoomAvailable.getRoomID()); // connect client with the room
			doubleRoomAvailable.setStatus("Occupied");
			System.out.println(c.getName() + " has reserved room " + doubleRoomAvailable.getRoomID());
			// Reserve room but wait until is cleaned
		} else if (c.getRoomID() == -1 && doubleRoomAvailable.getClientID() == -1
				&& doubleRoomAvailable.isNeedsCleaning() == true) {
			doubleRoomAvailable.setClientID(c.getClientID());
			c.setRoomID(doubleRoomAvailable.getRoomID());
			System.out.println(c.getName() + " has reserved room " + doubleRoomAvailable.getRoomID()
					+ " Please wait until is cleaned!!");
		} else {
			System.out.println("Client is already checked-in");
		}
		return c.getClientID();
	}

	@Override
	public int checkInFamilyRoom(int client) {
		System.out.println("Check in Family Room");
		// Get available family rooms
		Room familyRoomAvailable = (FamilyRoom) room.getAvailableRoom("Family");
		// Get client by id
		Client c = clients.getClient(client);
		// If client does not have a room and room does not have a client we check-in.
		// Also NeedsClening function is to check whether the room needs clening or not.
		// set room status to occupied
		if (c.getRoomID() == -1 && familyRoomAvailable.getClientID() == -1
				&& familyRoomAvailable.isNeedsCleaning() == false) {
			familyRoomAvailable.setClientID(c.getClientID()); // connect room with client
			c.setRoomID(familyRoomAvailable.getRoomID()); // connect client with the room
			familyRoomAvailable.setStatus("Occupied");
			System.out.println(c.getName() + " has reserved room " + familyRoomAvailable.getRoomID());
			// Reserve room but wait until is cleaned
		} else if (c.getRoomID() == -1 && familyRoomAvailable.getClientID() == -1
				&& familyRoomAvailable.isNeedsCleaning() == true) {
			familyRoomAvailable.setClientID(c.getClientID());
			c.setRoomID(familyRoomAvailable.getRoomID());
			System.out.println(c.getName() + " has reserved room " + familyRoomAvailable.getRoomID()
					+ " Please wait until is cleaned!!");
		} else {
			System.out.println("Client is already checked-in");
		}
		return c.getClientID();
	}

	@Override
	public boolean checkOutRoom(int client) {
		boolean hasCheckedOut;
		// get client by ID
		Client c = clients.getClient(client);
		// get room by ID
		Room r = room.getRoom(c.getRoomID());

		// Checking out the client
		if (r == null) {
			System.out.println("Client is not checked-in");
			return false;
			// Check if client and room are connected
		} else if (r.getClientID() == c.getClientID() && c.getRoomID() == r.getRoomID()) {
			System.out.println("Check-out success");
			r.setClientID(-1); // we cannot set integer to null
			c.setRoomID(-1); // cannot set integer to null so we set to -1
			r.setStatus("CheckedOut"); // change room status
			r.setNeedsCleaning(true); // Room needs cleaning
		} else {
			System.out.println("Something went wrong");
		}

		// Check if the procedure was completed successfully
		if (c.getRoomID() == -1 && r.getClientID() == -1) {
			hasCheckedOut = true;
		} else {
			hasCheckedOut = false;
		}
		return hasCheckedOut;
	}

	@Override
	public Vector searchAvailableRooms() {
		// Call function form Room class
		return room.FilterRooms("ID");
	}

	@Override
	public void printAvailableRooms() {
		// Call function from Room class
		System.out.println("Available Rooms: ");
		room.FilterRooms("Ready");
	}

	@Override
	public void printOccupiedRooms() {
		// Call function from Room class
		System.out.println("Occupied Rooms: ");
		room.FilterRooms("Occupied");
	}

	@Override
	public void addWing(String wingName) {
		// Function from the Wing class calls the graph constructor
		wing.addWing(wingName);
	}

	@Override
	public void connectWings(String wing1, String wing2, double distance) {
		// Function from the Wing class connects wings and their distances
		wing.addEdges(wing1, wing2, distance);
	}

	@Override
	public void organizeCleaning() {

		String startWing = room.startCleaning(); // Return the wing with most rooms to be cleaned
		room.cleaningProcedure(startWing); // start cleaning from the above wing

		Comparable nextWing1 = wing.shortestPath(startWing); // find shortest path from starting wing
		room.cleaningProcedure(nextWing1); // Start cleaning of that wing

		// Repeat this procedure until all wings are cleaned

		Comparable nextWing2 = wing.shortestPath(nextWing1);
		room.cleaningProcedure(nextWing2);

		Comparable nextWing3 = wing.shortestPath(nextWing2);
		room.cleaningProcedure(nextWing3);
	}

}