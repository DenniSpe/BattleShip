package it.unical.asde.battleship.model;

public class Ship {
	/* Instance Variables */
	private int length;
	private int direction; // 0 horizontal ,1 vertical
	private String name; // destroyer (length 3),submarine(length 3),
							// cruiser(length3),battleship(length4),aircraft(length5)

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
	}

	public Ship() {
		super();
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