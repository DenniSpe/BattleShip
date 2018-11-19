package it.unical.asde.battleship.game;

import it.unical.asde.battleship.model.User;

public class Lobby {
	
	private String name;
	private String owner;
	private String challenger;
	
	
	public Lobby() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lobby(String name, String owner) {
		super();
		this.name = name;
		this.owner = owner;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getChallenger() {
		return challenger;
	}
	public void setChallenger(String challenger) {
		this.challenger = challenger;
	}
	
	public boolean isFull() {
		return (owner != null && challenger != null);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challenger == null) ? 0 : challenger.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lobby other = (Lobby) obj;
		if (challenger == null) {
			if (other.challenger != null)
				return false;
		} else if (!challenger.equals(other.challenger))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Lobby [name=" + name + ", owner=" + owner + ", challenger=" + challenger + "]";
	}
	
	
	
	

}
