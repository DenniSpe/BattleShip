package it.unical.asde.battleship.model;

import java.util.Date;

public class Lobby
{

    private int id;

    private String name;

    private String owner;

    private String challenger;

    private boolean lobbyStarted;
    
    private String whoPlays;

    private Date startingTimeStamp;
    
    private Date endingTimeStamp;

    private String winner;
    
    public String getWinner() {
		return winner;
	}

    
    public Date getEndingTimeStamp() {
		return endingTimeStamp;
	}

	public void setEndingTimeStamp(Date endingTimeStamp) {
		this.endingTimeStamp = endingTimeStamp;
	}

	public Date getStartingTimeStamp() {
		return startingTimeStamp;
	}

	public void setStartingTimeStamp(Date startingTimeStamp) {
		this.startingTimeStamp = startingTimeStamp;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWhoPlays() {
		return whoPlays;
	}

	public void setWhoPlays(String whoPlays) {
		this.whoPlays = whoPlays;
	}

	public Lobby()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Lobby(final int id, final String name, final String owner)
    {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Lobby(final int id, final String name, final String owner, final String challenger, final boolean lobbyStarted)
    {
        super();
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.challenger = challenger;
        this.lobbyStarted = lobbyStarted;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Lobby other = (Lobby) obj;
        if (id != other.id)
        {
            return false;
        }
        return true;
    }

    public String getChallenger()
    {
        return challenger;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getOwner()
    {
        return owner;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    public boolean isFull()
    {
        return owner != null && challenger != null;
    }

    public boolean isLobbyStarted()
    {
        return lobbyStarted;
    }

    public void setChallenger(final String challenger)
    {
        this.challenger = challenger;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public void setLobbyStarted(final boolean lobbyStarted)
    {
        this.lobbyStarted = lobbyStarted;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setOwner(final String owner)
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return "id : " + id + " , name : " + name + ", owner :" + owner + ", challenger :" + challenger + "]";
    }

}
