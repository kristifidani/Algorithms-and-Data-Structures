public class Graph {

	// Node component of the graph
	public class Node implements Comparable {
		private Comparable info; // Name of the wing
		public Vector edges; // Paths and destinations to each path implemented in a vector
		private boolean checkNode = false; // Is visited node or not

		// Node constructor
		public Node(Comparable label) {
			info = label;
			edges = new Vector(4);
		}

		// Add paths and their destinations
		public void addEdge(Edge e) {
			edges.addLast(e); // called from the Edge class
		}

		// Compare method for Comparable variable types
		public int compareTo(Object o) {
			// two nodes are equal if they have the same label
			Node n = (Node) o;
			return n.info.compareTo(info);
		}

		// All getters and setters
		public Comparable getLabel() {
			return info;
		}

		public boolean isCheckNode() {
			return checkNode;
		}

		public void setCheckNode(boolean checkNode) {
			this.checkNode = checkNode;
		}
	}

	// Edges component of the graph
	private class Edge implements Comparable {

		private Node toNode; // Destination node
		private double distance; // Distance to the destination

		// Edge constructor
		public Edge(Node to, double distance) {
			this.setToNode(to);
			this.setDistance(distance);
		}

		// All getters and setters
		private Node getToNode() {
			return toNode;
		}

		private void setToNode(Node toNode) {
			this.toNode = toNode;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		// Compare method for Comparable variable types
		public int compareTo(Object o) {
			// two edges are equal if they point
			// to the same node.
			// this assumes that the edges are
			// starting from the same node !!!
			Edge n = (Edge) o;
			return n.getToNode().compareTo(getToNode());
		}
	}

	// Graph implemented as a vector
	private Vector nodes;

	// Graph constructor
	public Graph() {
		nodes = new Vector(4);
	}

	// Add nodes
	public void addNode(Comparable label) {
		nodes.addLast(new Node(label)); // Call Node constructor
	}

	// Find a particular node of the graph and return it
	public Node findNode(Comparable nodeLabel) {
		Node res = null;
		for (int i = 0; i < nodes.size(); i++) {
			Node n = (Node) nodes.get(i);
			if (n.getLabel() == nodeLabel) // match the wing name with input
			{
				res = n;
				break;
			}
		}
		return res;
	}

	// Add edges
	public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2, double distance) {
		Node n1 = findNode(nodeLabel1); // Starting node
		Node n2 = findNode(nodeLabel2); // Destination node
		// Add distance between nodes
		n1.addEdge(new Edge(n2, distance));
		n2.addEdge(new Edge(n1, distance));
	}

	// Print all graph nodes
	public void print() {
		Node a = findNode("A");
		Node b = findNode("B");
		Node c = findNode("C");
		Node d = findNode("D");

		System.out.println("Wing: " + a.getLabel() + "\n" + "Wing: " + b.getLabel() + "\n" + "Wing: " + c.getLabel()
				+ "\n" + "Wing: " + d.getLabel());
	}

	// Sort array in ascending order and return the smallest element
	public int getSmallest(int[] array) {
		int temp, size;
		size = array.length;

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (array[i] > array[j]) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array[0]; // smallest element
	}

	// Find Shortest path between the starting node and all its destinations and
	// return that node
	public Comparable findShortPath(Comparable nodeLabel1) {
		double shortest;
		Comparable nextWing = null;

		// starting node
		Node current = findNode(nodeLabel1);
		// array for distances
		int[] edgeArray = new int[current.edges.size()];

		Edge multiEdge = null;

		// loop through edges and put distances in array
		for (int i = 0; i < current.edges.size(); i++) {
			multiEdge = (Edge) current.edges.get(i);
			edgeArray[i] = (int) multiEdge.getDistance();
		}

		// return smallest distance from above function
		shortest = getSmallest(edgeArray);

		for (int i = 0; i < current.edges.size(); i++) {
			multiEdge = (Edge) current.edges.get(i);
			// match node with smallest distance and check is node is visited before
			if (shortest == multiEdge.getDistance() && multiEdge.getToNode().isCheckNode() == false) {
				current.setCheckNode(true);
				nextWing = multiEdge.getToNode().getLabel();
				multiEdge.getToNode().setCheckNode(true);
				System.out.println("Cleaning will proceed to wing: " + multiEdge.getToNode().getLabel());
			}
		}

		return nextWing;
	}
}