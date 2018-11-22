package it.unical.asde.battleship.components.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class EventsService {
	
	@PostConstruct 
	public void init() {
		new Thread(() ->  {
			Random rand = new Random();
			try {
				while(true) {
					for(BlockingQueue<String> queue : events.values()) {
						//queue.put(new Date().toString());
						queue.put("Catee");
					}
					Thread.sleep(1000);//(rand.nextInt(10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private Map<String, BlockingQueue<String>> events = new HashMap<>();

	public String nextEvent(String id) throws InterruptedException {
		if(!events.containsKey(id)) {
			events.put(id, new LinkedBlockingQueue<>());
		}
 		return events.get(id).take();

	}
	

}
