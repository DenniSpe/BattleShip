package it.unical.asde.battleship.model;

public class Grid {
	private int[][] grid;

	// Constants for number of rows and columns.
	public static final int NUM_ROWS = 11;
	public static final int NUM_COLS = 11;

	public Grid() {
		System.out.println("================= COSTRUISCO GRID ======================");
		grid = new int[NUM_ROWS][NUM_COLS];
		for (int row = 1; row < grid.length; row++) {
			for (int col = 1; col < grid[row].length; col++) {
				int tempLoc = 0;
				grid[row][col] = tempLoc;
			}
		}
	}

	// Mark a hit in this location by calling the markHit method
	// on the Location object.
	public void markHit(int row, int col) {
		grid[row][col] = 1;
	}

	// Mark a miss on this location.
	public void markMiss(int row, int col) {
		grid[row][col] = -1;
	}

	
	
	// Get the status of this location in the grid
	public int getContent(int row, int col) {
		return grid[row][col];
	}

	// Return whether or not this Location has already been guessed.
	public boolean alreadyGuessed(int row, int col) {
		if (grid[row][col] == 1 || grid[row][col] == -1) {
			return true;
		}
		return false;

	}

	public int tipeShip(int row, int col) {
		if(hasShip(row, col)) {
			return grid[row][col];
		}
		 return 0;
	}
	// Return whether or not there is a ship here
	public boolean hasShip(int row, int col) {
		switch (grid[row][col]) {
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}

	public void setShip(int row, int col, int numShip, int direction) {
		System.out.println("===== DENTRO GRID SETSHIP CON row = "+row+" col = "+col+" numship = "+numShip+" direction = "+direction);
		
		
			if (direction == 0) { // horizontal
				for(int i = col; i < col + numShip; i++) {
						grid[row][i] = numShip;
				}
			}
			if (direction == 1) { // vertical
				for(int j = row; j < row + numShip; j++) {
					grid[j][col] = numShip;
			}
		}
	}
	
	
	public void print() {
		for (int i = 1; i < grid.length; i++) {
			System.out.println();
			for (int j = 1; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
				
			}
		}
	}

}