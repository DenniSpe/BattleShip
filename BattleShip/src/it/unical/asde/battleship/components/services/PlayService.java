package it.unical.asde.battleship.components.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import it.unical.asde.battleship.model.Player;

@Service
public class PlayService {

	
	private Player owner;
	
	public Player getOwner() {
		return owner;
	}


	public void setOwner(Player owner) {
		this.owner = owner;
	}


	public Player getChallenger() {
		return challenger;
	}


	public void setChallenger(Player challenger) {
		this.challenger = challenger;
	}


	private Player challenger;
	
	
	@PostConstruct
	public void init() {
		owner= new Player();	
		challenger = new Player();
	}
}
