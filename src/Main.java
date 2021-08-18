public class Main {

	public static void main(String[] args) {

		// TODO code application logic here
		HotelManagement hms = new HotelManagement();

		// add double rooms
		hms.addDoubleRoom("A", 10, "Occupied");
		hms.addDoubleRoom("B", 20, "Ready");
		hms.addDoubleRoom("B", 15, "Ready");
		hms.addDoubleRoom("D", 16, "CheckedOut");
		hms.addDoubleRoom("C", 17, "CheckedOut");

		// add family rooms
		hms.addFamilyRoom("D", 5, "Occupied");
		hms.addFamilyRoom("D", 6, "Ready");
		hms.addFamilyRoom("D", 7, "CheckedOut");
		hms.addFamilyRoom("A", 8, "CheckedOut");
		hms.addFamilyRoom("B", 9, "CheckedOut");
		hms.addFamilyRoom("C", 4, "CheckedOut");


		// print rooms
		hms.printRooms();

		// add clients
		hms.addClient("Kristi", "Kristi@vub.be");
		hms.addClient("Fidani", "Fidani@hotmail.com");
		hms.addClient("John", "John@hotmail");
		System.out.println("-------------");

		// print clients
		hms.printClients();
		System.out.println("-------------");

		// check in double rooms
		hms.checkInDoubleRoom(0);
		System.out.println("-------------");

		// check in family room
		hms.checkInFamilyRoom(2);
		System.out.println("-------------");

		// print available rooms after check in
		hms.printAvailableRooms();
		System.out.println("-------------");

		// print occupied rooms after check in
		hms.printOccupiedRooms();
		System.out.println("-------------");

		// check out rooms
		hms.checkOutRoom(0);
		System.out.println("-------------");
		// Reserve room with status CheckedOut
		hms.checkInDoubleRoom(1);
		System.out.println("-------------");

		// print available rooms after check out
		hms.printAvailableRooms();
		System.out.println("-------------");

		// print occupied rooms after check out
		hms.printOccupiedRooms();
		System.out.println("-------------");

		// Add wings
		hms.addWing("A");
		hms.addWing("B");
		hms.addWing("C");
		hms.addWing("D");

		// Connect wings
		hms.connectWings("A", "B", 10);
		hms.connectWings("A", "D", 30);
		hms.connectWings("C", "D", 40);
		hms.connectWings("B", "D", 50);
		hms.connectWings("B", "C", 5);

		// Organise cleaning procedure
		hms.organizeCleaning();
		System.out.println("-------------");

		// print all rooms final
		hms.printRooms();
	}
}