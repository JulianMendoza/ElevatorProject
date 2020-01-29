package sysc3033.group9.elevatorproject.floor;

public class FloorSpan {

	private int minFloorID, maxFloorID;

	public FloorSpan(int minFloorID, int maxFloorID) {
		this.minFloorID = minFloorID;
		this.maxFloorID = maxFloorID;
	}

	public int getMinFloorID() {
		return minFloorID;
	}

	public int getMaxFloorID() {
		return maxFloorID;
	}

}
