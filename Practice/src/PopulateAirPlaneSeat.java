import java.util.ArrayList;
import java.util.List;

public class PopulateAirPlaneSeat {

	public List<Seat[][]> seatMap;
	public int maxRowIndex;
	public SeatList asileList;
	public SeatList windowList;
	public SeatList normalList;

	public PopulateAirPlaneSeat(int[][][] input) {
		init(input);
	}

	public void init(int[][][] input) {

		seatMap = new ArrayList<Seat[][]>();
		asileList = new SeatList(100);
		windowList = new SeatList(100);
		normalList = new SeatList(100);
		initSeatMap(input, seatMap);
		for (int index = 0; index < seatMap.size(); index++) {
			fillSeatType(seatMap.get(index), index, seatMap.size());
		}
		fillSeatList();
	}

	public void initSeatMap(int[][][] input, List<Seat[][]> seatMap) {

		maxRowIndex = input[0].length;
		for (int x = 0; x < input.length; x++) {
			if (input[x].length > maxRowIndex)
				maxRowIndex = input[x].length;
			Seat[][] seatZone = new Seat[input[x].length][input[x][0].length];
			for (int i = 0; i < seatZone.length; i++) {
				for (int j = 0; j < seatZone[0].length; j++) {
					seatZone[i][j] = new Seat();
				}
			}
			seatMap.add(seatZone);
		}
	}

	public void fillSeatList() {

		for (int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
			for (int zoneIndex = 0; zoneIndex < seatMap.size(); zoneIndex++) {
				if (rowIndex >= seatMap.get(zoneIndex).length)
					continue;
				for (int columnIndex = 0; columnIndex < seatMap.get(zoneIndex)[0].length; columnIndex++) {

					if (seatMap.get(zoneIndex)[rowIndex][columnIndex].isAsile) {
						asileList.add(seatMap.get(zoneIndex)[rowIndex][columnIndex]);
					} else if (seatMap.get(zoneIndex)[rowIndex][columnIndex].isWindow) {
						windowList.add(seatMap.get(zoneIndex)[rowIndex][columnIndex]);
					} else {
						normalList.add(seatMap.get(zoneIndex)[rowIndex][columnIndex]);
					}
				}
			}
		}

	}

	public void fillSeatType(Seat[][] seatSection, int index, int mapLength) {

		if (index == 0) {
			for (int i = 0; i < seatSection.length; i++) {
				for (int j = 0; j < seatSection[0].length; j++) {
					if (j == 0) {
						seatSection[i][j].isWindow = true;
					} else if (j == seatSection[0].length - 1) {
						seatSection[i][j].isAsile = true;
					}
					seatSection[i][j].row = i;
					seatSection[i][j].column = j;
					seatSection[i][j].zone = index;
				}
			}
		} else if (index == mapLength - 1) {
			for (int i = 0; i < seatSection.length; i++) {
				for (int j = 0; j < seatSection[0].length; j++) {
					if (j == 0) {
						seatSection[i][j].isAsile = true;
					} else if (j == seatSection[0].length - 1) {
						seatSection[i][j].isWindow = true;
					}
					seatSection[i][j].row = i;
					seatSection[i][j].column = j;
					seatSection[i][j].zone = index;
				}
			}
		}

		else {
			for (int i = 0; i < seatSection.length; i++) {
				for (int j = 0; j < seatSection[0].length; j++) {
					if (j == 0 || j == seatSection[0].length - 1)
						seatSection[i][j].isAsile = true;

					seatSection[i][j].row = i;
					seatSection[i][j].column = j;
					seatSection[i][j].zone = index;
				}
			}
		}
	}

	public void populateSeatSection(int zoneIndex, int rowIndex, int columnIndex, int customerNumber) {

		seatMap.get(zoneIndex)[rowIndex][columnIndex].customer = customerNumber;
		seatMap.get(zoneIndex)[rowIndex][columnIndex].isOccupied = true;
	}

	public void populateSeat(int customerNumber, int maxRowIndex) {

		for (int i = 1; i <= customerNumber; i++) {
			if (asileList.vacantSize != 0) {
				for (int j = 0; j < asileList.size; j++) {
					if (!asileList.seatArray[j].isOccupied) {
						asileList.seatArray[j].isOccupied = true;
						asileList.seatArray[j].customer = i;
						asileList.vacantSize--;
						populateSeatSection(asileList.seatArray[j].zone, asileList.seatArray[j].row, asileList.seatArray[j].column, i);
						break;
					}
				}
			} else if (windowList.vacantSize != 0) {
				for (int j = 0; j < windowList.size; j++) {
					if (!windowList.seatArray[j].isOccupied) {
						windowList.seatArray[j].isOccupied = true;
						windowList.seatArray[j].customer = i;
						windowList.vacantSize--;
						populateSeatSection(windowList.seatArray[j].zone, windowList.seatArray[j].row, windowList.seatArray[j].column, i);
						break;
					}
				}
			} else {
				for (int j = 0; j < normalList.vacantSize; j++) {
					if (!normalList.seatArray[j].isOccupied) {
						normalList.seatArray[j].isOccupied = true;
						normalList.seatArray[j].customer = i;
						normalList.vacantSize--;
						populateSeatSection(normalList.seatArray[j].zone, normalList.seatArray[j].row, normalList.seatArray[j].column, i);
						break;
					}
				}

			}
		}
	}

	public void displaySeatMap() {

		for (int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
			for (int zoneIndex = 0; zoneIndex < seatMap.size(); zoneIndex++) {
				if (rowIndex >= seatMap.get(zoneIndex).length) {
					for (int m = 0; m < seatMap.get(zoneIndex)[0].length; m++) {
						System.out.print("X");
						System.out.print("  ");
					}
				} else {
					for (int columnIndex = 0; columnIndex < seatMap.get(zoneIndex)[0].length; columnIndex++) {
						System.out.print(seatMap.get(zoneIndex)[rowIndex][columnIndex].customer);
						System.out.print(" ");
					}
				}

				System.out.print("   ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		int[][] zoneOne = new int[3][3];
		int[][] zoneTwo = new int[4][4];
		int[][] zoneThree = new int[2][2];
		int[][] zoneFour = new int[5][3];

		int[][][] input = { zoneOne, zoneTwo, zoneThree, zoneFour };

		PopulateAirPlaneSeat populateAirPlaneSeat = new PopulateAirPlaneSeat(input);
		populateAirPlaneSeat.populateSeat(30, populateAirPlaneSeat.maxRowIndex);
		populateAirPlaneSeat.displaySeatMap();

	}

}

class SeatList {

	public int size;
	public Seat seatArray[];
	public int vacantSize;

	public SeatList(int size) {
		seatArray = new Seat[size];
		size = 0;
	}

	public void add(Seat seat) {
		seatArray[size++] = seat;
		vacantSize++;
	}

}

class Seat {

	public boolean isAsile;
	public boolean isWindow;
	public boolean isOccupied;
	public int row;
	public int column;
	public int zone;
	public int customer;

	public Seat() {

		isAsile = false;
		isWindow = false;
		isOccupied = false;
		customer = 0;
		row = 0;
		column = 0;
		zone = 0;
	}

}
