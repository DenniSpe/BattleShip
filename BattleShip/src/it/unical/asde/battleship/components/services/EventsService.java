package it.unical.asde.battleship.components.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import it.unical.asde.battleship.game.Lobby;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsService {
	
	@Autowired
	private LobbyService lobbyService;
	
	@PostConstruct 
	public void init() {
		new Thread(() ->  {
			Random rand = new Random();
			try {
				while(true) {
					for(BlockingQueue<Lobby> queue : events.values()) {
						
						for (Lobby l : lobbyService.getLobbies()) {
							queue.put(l);
						}
					}
					Thread.sleep(1000);//(rand.nextInt(10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private Map<String, BlockingQueue<Lobby>> events = new HashMap<>();

	public Lobby nextEvent(String id) throws InterruptedException {
		if(!events.containsKey(id)) {
			events.put(id, new LinkedBlockingQueue<>());
		}
 		return events.get(id).take();

	}
	

}
