public class Client {

	private String name; // name of the client
	private String emailAddress; // email of the client
	private int clientID = 0; // unique id

	private int roomID = -1; // the room id when client checks in

	private Vector client = new Vector(100); // create a vector with capacity 100

	// Client constructor
	public Client(String name, String emailAddress, int clientID) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.clientID = clientID;
	}

	// Empty Client constructor
	public Client() {
	}

	// All getters and setters
	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return emailAddress;
	}

	public int getClientID() {
		return clientID;
	}

	// add new client to the vector
	public void addNewClient(Client c) {
		client.addLast(c);
		clientID++;
	}

	// find client by id and return him
	public Client getClient(int id) {
		for (int i = 0; i < client.size(); i++) {
			Client clients = (Client) client.get(i);
			if (clients.getClientID() == id) {
				return clients;
			} else if (clients.getClientID() != id) {
			} else {
				System.out.println("Client does not exist!");
			}
		}
		return null;
	}

	// print all clients
	public void printClients() {
		for (int i = 0; i < client.size(); i++) {
			Client clients = (Client) client.get(i);
			System.out.println(clients);
		}
	}

	// printing format
	public String toString() {
		String s = "";
		s += "ID: " + clientID + "||" + "Name: " + name + "||" + "Email: " + emailAddress;
		;
		return s;
	}
}