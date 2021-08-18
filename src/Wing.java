
public class Wing {
	
	private String wingName; // wing name
	private int wingID = -1; //wing unique id
	private Graph wing = new Graph(); //create empty graph 

	//Wing constructor
	public Wing(String wingName, int wingID) {
		this.wingName = wingName;
		this.wingID = wingID;
	}
	
	//Empty wing constructor
	public Wing() {
		
	}
	
	//All getters and setters
	public String getWingName() {
		return wingName;
	}


	public void setWingName(String wingName) {
		this.wingName = wingName;
	}

	public int getWingID() {
		return wingID;
	}

	public void setWingID(int wingID) {
		this.wingID = wingID;
	}
	
	//Add wing by calling the Node constructor from Graph class
	public void addWing(Comparable newWing) {
		wing.addNode(newWing);
		wingID++;
	}
	
	//Add edges by calling Edge constructor from Graph class
	public void addEdges(String wing1, String wing2, double distance) {
		wing.addEdge(wing1, wing2, distance);
	}
	
	//Find shortest path between wings.
	//Function called from graph class
	public Comparable shortestPath(Comparable nodeName) {
		return(wing.findShortPath(nodeName));
	}
	
	// print all clients
		public void printWings() {
			System.out.println("All wings");
			wing.print();
		}
		
		//Wing printing format
		public String toString() {
			String s = "";
			s += "ID: " + wingID + " " + "Name: " + wingName;
			;
			return s;
		}
	
}
