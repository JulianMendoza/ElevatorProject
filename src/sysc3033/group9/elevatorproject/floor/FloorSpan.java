package sysc3033.group9.elevatorproject.floor;

/**
 * FloorSpan class sets the minimum and maximum floors in the elevator
 * 
 * @Kelly Harrison
 */
public class FloorSpan {

	private int minFloorID, maxFloorID;

	/**
	 * Constructor for FloorSpan class
	 * 
	 * @param minFloorID is an integer of the lowest floor
	 * @param maxFloorID is an integer of the highest floor
	 */
	public FloorSpan(int minFloorID, int maxFloorID) {
		this.minFloorID = minFloorID;
		this.maxFloorID = maxFloorID;
	}

	/**
	 * getter for the minimum floor of the elevator
	 * 
	 * @return the Minimum floor as an integer
	 */
	public int getMinFloorID() {
		return minFloorID;
	}

	/**
	 * getter for the maximum floor
	 * 
	 * @return the maximum floor as an integer
	 */
	public int getMaxFloorID() {
		return maxFloorID;
	}

}
