public class Vector {
	private Object data[]; // array of data
	private int count; // keep tract of array size

	// Vector constructor
	public Vector(int capacity) {
		data = new Object[capacity];
		count = 0;
	}

	// size of vector
	public int size() {
		return count;
	}

	// check if vector is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	// gets element at given position
	public Object get(int index) {
		return data[index];
	}

	// update data at given index
	public void set(int index, Object obj) {
		data[index] = obj;
	}

	// check whether element is there or not
	public boolean contains(Object obj) {
		for (int i = 0; i < count; i++) {
			if (data[i] == obj)
				return true;
		}
		return false;
	}

	// Add element in the beginning
	public void addFirst(Object item) {
		if (count == data.length) { // check if array is full
			System.out.println("It is full capacity");
		} else {
			for (int i = count; i > 0; i--) { // moves everything to the right so we can add starting from the latest el
				data[i] = data[i - 1];
			}
			data[0] = item;
			count++;
		}
	}

	// Add element in the end
	public void addLast(Object N) {
		if (count == data.length) {
			System.out.println("It is full capacity ");
		} else {
			for (int i = 0; i > count; i++) {
				data[i] = data[i + 1];
			}
			data[count] = N;
			count++;
		}
	}

	// Get first element of vector
	public Object getFirst() {
		return data[0];
	}

	// Get last element of the vector
	public Object getLast() {
		return data[count - 1];
	}

	// Remove last element
	public void removeLast() {
		data[count - 1] = null;
		count--;
	}

	// Remove first element
	public void removeFirst() {
		// moves data to the left
		for (int i = 0; i < data.length - 1; i++) {
			data[i] = data[i + 1];
		}
		count--;
	}

	// Print vector
	public void getVector() {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);

		}
	}

	// Binary search
	public boolean binarySearch(int key) {
		int start = 0;
		int end = count - 1;
		while (start <= end) // as long as start is more to the left of end we need to narrow them closer
		{ // until it becomes 1 cell
			int middle = (start + end + 1) / 2; // element right to the middle
			System.out.println("middle is " + middle);
			if (key < (int) data[middle]) {
				end = middle - 1; // if < move end
				System.out.println("End is " + end);
			} else if (key > (int) data[middle]) {
				start = middle + 1; // if > move start
				System.out.println("Start is " + start);
			} else
				return true; // if key == middle
		}
		return false;
	}
}