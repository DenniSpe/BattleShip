package it.unical.asde.battleship.components.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.asde.battleship.components.services.UtilService;
import it.unical.asde.battleship.model.Match;
import it.unical.asde.battleship.model.User;

@Controller
public class HistoryController {

	@Autowired
	private UtilService utilService;

	@GetMapping("/userHistory")
	public String userhistory(HttpSession session, Model model, @RequestParam Optional<String> page,
			@RequestParam Optional<String> amount) {

		if (session.getAttribute("user") != null) {

			User user = (User) session.getAttribute("user");
			
			utilService.clearGrid(user.getUsername());
			
			long totalRows = utilService.getCountMatches(user);
			int pageNo = 1;
			int count = 20;
			if (amount.isPresent()) {
				try {
					count = Integer.parseInt(amount.get());
					if (count < 1 || count > 100) {
						count = 20;
					}
				} catch (Exception e) {
					e.printStackTrace();
					count = 20;
				}
			}

			int totalPages = (int) ((totalRows % count == 0) ? totalRows / count : totalRows / count + 1);
			if (page.isPresent()) {
				try {
					pageNo = Integer.parseInt(page.get());
					if (pageNo < 1) {
						return "redirect:/userHistory";
					} else if (pageNo > totalPages) {
						return "redirect:/userHistory?page=" + totalPages;
					}
				} catch (Exception e) {
					e.printStackTrace();
					pageNo = 1;
				}
			}

			List<Match> matches = utilService.getUserMatches(user, pageNo, count);

			model.addAttribute("matches", matches);
			model.addAttribute("page", pageNo);
			model.addAttribute("amount", count);
			model.addAttribute("totalPages", totalPages);

			return "user_history";
		}
		return "redirect:/";
	}
	
	@PostMapping("/winLooseData")
	@ResponseBody
	public List<Long> winLooseData(HttpSession session) {

		User user = (User) session.getAttribute("user");

		if (user != null) {
			long looses = utilService.getUserLooses(user);
			long wins = utilService.getUserWins(user);
			List<Long> response = new ArrayList<Long>();
			response.add(wins);
			response.add(looses);
			return response;
		}

		return new ArrayList();
	}

	@PostMapping("/matchesDuration")
	@ResponseBody
	public Map<String, Object> matchesDuration(HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Object[]> values = utilService.matchesDuration(user);
			int sum = 0;
			List<Integer> times = new LinkedList<>();
			List<String> labels = new LinkedList<>();
			for (Object[] tupla : values) {
					times.add((Integer)tupla[0]);
					sum += ((int)tupla[0]);
					labels.add((String)tupla[1]);
			}			
			int avg = sum/times.size();
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("times", times);
			response.put("labels", labels);
			response.put("avg", avg);

			return response;
		}

		return new HashMap<String, Object>();
	}

}
