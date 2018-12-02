package it.unical.asde.battleship.model;

import java.util.LinkedList;
import java.util.List;

public class Grid {
	private int[][] grid;
	private List<Tupla> hitteds;
	private List<Tupla> misseds;

	// Constants for number of rows and columns.
	public static final int NUM_ROWS = 11;
	public static final int NUM_COLS = 11;

	public Grid() {
		grid = new int[NUM_ROWS][NUM_COLS];
		for (int row = 1; row < grid.length; row++) {
			for (int col = 1; col < grid[row].length; col++) {
				int tempLoc = 0;
				grid[row][col] = tempLoc;
			}
		}
		
		hitteds = new LinkedList<Tupla>();
		misseds = new LinkedList<Tupla>();
	}
	
	public void addHitCell(Tupla cell) {
		hitteds.add(cell);
	}
	
	public void addMissedCell(Tupla cell) {
		misseds.add(cell);
	}
	
	public List<Tupla> getHitteds() {
		return hitteds;
	}
	
	public List<Tupla> getMisseds() {
		return misseds;
	}

	// Mark a hit in this location
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
	
	public void deleteShip(int row, int col, int numShip, int direction) {
		
		if (direction == 0) { // horizontal
			for(int i = col; i < col + numShip; i++) {
					grid[row][i] = 0;
			}
		}
		if (direction == 1) { // vertical
			for(int j = row; j < row + numShip; j++) {
				grid[j][col] = 0;
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