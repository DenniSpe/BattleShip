package it.unical.asde.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	/* Instance Variables */
	private int length;
	private int direction; // 0 horizontal ,1 vertical
	private String name; // destroyer (length 3),submarine(length 3),
							// cruiser(length3),battleship(length4),aircraft(length5)
	
	List<Tupla> occupiedCells;

	// Constructor
	public Ship(String name) {
		this.direction = 0;
		this.name = name;
		switch (name) {
		case "destroyer":
			this.length = 2;
		case "submarine":
			this.length = 3;
		case "cruiser":
			this.length = 3;
		case "battleship":
			this.length = 4;
		case "aircraft":
			this.length = 5;
		default:
			break;
		}
		
		occupiedCells = new ArrayList<>();
	}

	public Ship() {
		super();
	}

	public void setOccupiedCells(int row, int col) {
		
		Tupla tupla = new Tupla(row, col, this.length);
		
		occupiedCells.add(tupla);
	}
	
	// You hit the ship
	public void markOccupiedCells(int row, int col) {
		
		for(int i = 0; i < occupiedCells.size(); i++) {
			if(occupiedCells.get(i).getRow() == row && occupiedCells.get(i).getCol() == col) {
				occupiedCells.get(i).setValue(1);
			}
		}
	}
	
	public boolean isOccupiedCell(int row, int col) {
				
		for( int i = 0; i < occupiedCells.size(); i++) {
			if(occupiedCells.get(i).getRow() == row && occupiedCells.get(i).getCol() == col) {
				return true;
			}
		}
		return false;
	}
	
	
	public void clearOccupiedCells() {
		occupiedCells.clear();
	}
		
	public boolean isDestroyed() {
		for(int i = 0; i < occupiedCells.size(); i++) {
			if(occupiedCells.get(i).getValue() != 1) {
				return false;
			}
		}
		return true;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}