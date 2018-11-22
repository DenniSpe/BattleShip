package it.unical.asde.battleship.game;


public class Lobby {
	
	private int id;
	private String name;
	private String owner;
	private String challenger;
	
	
	public Lobby() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lobby(int id, String name, String owner) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;

	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "id : " + id +" , name : " + name + ", owner :" + owner + ", challenger :" + challenger + "]";
	}
	
	
	
	

}
