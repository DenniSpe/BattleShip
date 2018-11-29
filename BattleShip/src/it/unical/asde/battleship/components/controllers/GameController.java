package it.unical.asde.battleship.components.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import it.unical.asde.battleship.components.services.GameService;
import it.unical.asde.battleship.components.services.LobbyService;
import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.Grid;
import it.unical.asde.battleship.model.Tupla;
import it.unical.asde.battleship.model.User;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private LobbyService lobbyService;

	private Tupla tupla;

	public int getLength(String boatName) {
		int boatSize = 0;
		switch (boatName) {
		case "destroyer":
			boatSize = (2);
			break;
		case "submarine":
			boatSize = (3);
			break;
		case "cruiser":
			boatSize = (3);
			break;
		case "battleship":
			boatSize = (4);
			break;
		case "aircraft":
			boatSize = (5);
			break;
		default:
			break;
		}
		return boatSize;
	}

	private static boolean hasErrorOnPositioning(final int row, final int col, final int dir, final Grid grid,
			final int boatSize) {

		// Count � la size della mia barca
		System.out.println("MY DEBUG: length is " + boatSize);

		// Check if off grid - Horizontal
		if (dir == 0) {
			// final int checker = length + col;
			System.out.println("MY DEBUG HORIZONTAL : checker is " + (col + boatSize - 1));
			if (col + boatSize - 1 > 10) {
				return true;
			}
		}

		// Check if off grid - Vertical
		if (dir == 1) // VERTICAL
		{
			// final int checker = length + row;
			System.out.println("MY DEBUG VERTICAL: checker is " + (row + boatSize - 1));
			if (row + boatSize - 1 > 10) {
				return true;
			}
		}

		// Check if overlapping with another ship
		if (dir == 0) // Hortizontal
		{
			if (col + boatSize - 1 > 10) {
				System.out.println("MY DEBUG: vuoi scrivere fino a = " + (col + boatSize - 1));
				return true;
			}
			// For each location a ship occupies, check if ship is already there
			for (int i = col; i < col + boatSize; i++) {
				System.out.println("MY DEBUG: row = " + row + "; col = " + i);
				if (grid.hasShip(row, i)) {
					return true;
				}
			}
		} else if (dir == 1) // Vertical
		{

			if (row + boatSize - 1 > 10) {
				System.out.println("MY DEBUG: vuoi scrivere fino a = " + (row + boatSize - 1));

			}
			// For each location a ship occupies, check if ship is already there
			for (int i = row; i < row + boatSize; i++) {
				System.out.println("DEBUG: row = " + i + "; col = " + col);
				if (grid.hasShip(i, col)) {
					return true;
				}
			}
		}

		return false;
	}

	// TODO Cosa fa sto metodo ? Diamo nomi esplicativi. Credo restituisca la cella
	// colpita.. serve ancora??
	@PostMapping("/shoot")
	@ResponseBody
	public Map<String, Object> shoot(final String cella, final HttpSession session, String id) {
		// final DeferredResult<String> output = new DeferredResult<>();

		int row = Integer.parseInt(cella.split("-")[1]);
		int col = Integer.parseInt(cella.split("-")[2]);
		String shot = "";
		Map<String, Object> response = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		if (user != null || id != null) {
			Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(id));
			if (currentLobby.getWhoPlays().equals(user.getUsername())
					&& currentLobby.getWinner() == null/* There is no winner yet? */) {
				if (currentLobby.getOwner().equals(user.getUsername())) {
					if (!gameService.getChallengerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
						response.put("row", row);
						response.put("col", col);
						if (gameService.getChallengerGrid(currentLobby.getId()).hasShip(row, col)) {
							gameService.getChallengerGrid(currentLobby.getId()).markHit(row, col);
							// output.setResult("hit-" + row + "-" + col);

							System.out.println("CHALLENGER GRID AFTER SHOOT");
							gameService.getChallengerGrid(currentLobby.getId()).print();
							System.out.println("END CHALLENGER GRID AFTER SHOOT");

							shot = "hit-" + row + "-" + col;
							response.put("hit", true);
							if (!gameService.hasMoreShips(currentLobby.getId(), true)) {
								// OWNER WIN
								currentLobby.setWinner(user.getUsername());
								shot += "-OWNERWIN";
								response.put("youWin", true);
							}
						} else {
							gameService.getChallengerGrid(currentLobby.getId()).markMiss(row, col);
							// output.setResult("miss-" + row + "-" + col);
							shot = "miss-" + row + "-" + col;
							response.put("hit", false);
							currentLobby.setWhoPlays(currentLobby.getChallenger());
						}
					}
				} else if (currentLobby.getChallenger().equals(user.getUsername())) {
					if (!gameService.getOwnerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
						response.put("row", row);
						response.put("col", col);
						if (gameService.getOwnerGrid(currentLobby.getId()).hasShip(row, col)) {
							gameService.getOwnerGrid(currentLobby.getId()).markHit(row, col);
							// output.setResult("hit-" + row + "-" + col);

							System.out.println("OWNER GRID AFTER SHOOT");
							gameService.getOwnerGrid(currentLobby.getId()).print();
							System.out.println("END OWNER GRID AFTER SHOOT");

							shot = "hit-" + row + "-" + col;
							response.put("hit", true);
							if (!gameService.hasMoreShips(currentLobby.getId(), false)) {
								// CHALLENGER WIN
								shot += "-CHALLENGERWIN";
								currentLobby.setWinner(user.getUsername());
								response.put("youWin", true);
							}
						} else {
							gameService.getOwnerGrid(currentLobby.getId()).markMiss(row, col);
							// output.setResult("miss-" + row + "-" + col);
							shot = "miss-" + row + "-" + col;
							currentLobby.setWhoPlays(currentLobby.getOwner());
							response.put("hit", false);
						}
					}
				}
			} else {
				response.put("waitTurn", true);
				shot = "wait-turn";
			}

		}

//		ForkJoinPool.commonPool().submit(() -> {
//				output.setResult(shot);
//		});
		return response;
	}

	@GetMapping("/boatPositioning")
	public String goToGame() {
		return "boatPositioning";
	}

	@GetMapping("/putBoat")
	@ResponseBody
	public DeferredResult<String> putBoat(final String ID, final String cella, final HttpSession session,
			final String dir, final String boatName) {
		boolean isOwner = false;

		final int lobbyID = Integer.parseInt(ID);
		final Lobby currentLobby = lobbyService.getLobby(lobbyID);
		System.out.println("LOOOOOOOOBBY" + lobbyID);

		final int row = Integer.parseInt(cella.split("-")[1]);
		final int col = Integer.parseInt(cella.split("-")[2]);

		final int direction = dir.split("\\([^0-9]*")[1].split("deg")[0].equals("0") ? 0 : 1;

		// TODO I extract boat-size from the id of the img tag in the jsp

		final DeferredResult<String> output = new DeferredResult<>();

		final String username = (String) session.getAttribute("username");

		if (username.equals(currentLobby.getOwner())) {
			isOwner = true;
		}

		if (isOwner) {
			if (hasErrorOnPositioning(row, col, direction, gameService.getOwnerGrid(lobbyID), getLength(boatName))) {
				System.out.println("==========================ERROREEEEE, SFORI");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult("ERROR");
				});
			} else {
				gameService.putShipOwner(lobbyID, row, col, getLength(boatName), direction);
				System.out.println("============= OWNER GRID ==========");
				gameService.getOwnerGrid(lobbyID).print();
				System.out.println("============= FINE OWNER GRID ==========");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult(row + " , " + col + " , " + direction + " , " + getLength(boatName));
				});
			}
		} else {// if is not owner
			if (hasErrorOnPositioning(row, col, direction, gameService.getChallengerGrid(lobbyID),
					getLength(boatName))) {
				System.out.println("==========================ERROREEEEE, SFORI");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult("ERROR");
				});
			} else {
				gameService.putShipChallenger(lobbyID, row, col, getLength(boatName), direction);
				System.out.println("============= CHALLENGER GRID ==========");
				System.out.println("ID = " + lobbyID + " row = " + row + " col = " + col + " boatSize = "
						+ getLength(boatName) + " direction = " + direction);
				gameService.getChallengerGrid(lobbyID).print();
				System.out.println("============= FINE CHALLENGER GRID ==========");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult(row + " , " + col + " , " + direction + " , " + getLength(boatName));
				});
			}
		}

		return output;
	}

//	@GetMapping("/startGame")
//	public String startGame(@RequestParam final String id) { // TODO prendere l'id della lobby dalla jsp
//		final int lobbyID = Integer.parseInt(id);
//		gameService.startGame(lobbyID);
//		return "game";
//	}

	@GetMapping("/startPositioning")
	public String startPositioning(final Model model, final HttpSession session, @RequestParam final String ID) {
		User user = (User) session.getAttribute("user");
		Lobby lobb = lobbyService.getLobby(Integer.parseInt(ID));
		if (user != null && lobb != null) {
			if (user.getUsername().equals(lobb.getChallenger()) || user.getUsername().equals(lobb.getOwner())) {
				if (user.getUsername().equals(lobb.getOwner())) {
					lobb.setLobbyStarted(true);
					gameService.startGame(lobb.getId());
				}
				model.addAttribute("lobby", lobb);
				return "boatPositioning";
			}
		}
		return "redirect:/";

	}

	@GetMapping("/waitingStart")
	@ResponseBody
	public DeferredResult<String> waitingStart(@RequestParam final String ID, final HttpSession session) {
		final int lobbyID = Integer.parseInt(ID);
		final DeferredResult<String> output = new DeferredResult<>();
		final Lobby currentLobby = lobbyService.getLobby(lobbyID);

		boolean isOwner = false;

		final String username = (String) session.getAttribute("username");
		if (username.equals(currentLobby.getOwner())) {
			isOwner = true;
		}

		if (isOwner) {
			gameService.ownerIsReady(lobbyID);
		} else {
			gameService.challengerIsReady(lobbyID);
		}

		if (gameService.usersAreReady(lobbyID)) {

			lobbyService.getLobby(lobbyID).setStartingTimeStamp(new Date());
			System.out.println("LA PARTITA STA INIZIANDO " + lobbyService.getLobby(lobbyID).getStartingTimeStamp());

			ForkJoinPool.commonPool().submit(() -> {
				output.setResult("game");
			});
			;
		}

		else {
			ForkJoinPool.commonPool().submit(() -> {
				output.setResult("boatPositioning");
			});
		}

		return output;
	}

	@GetMapping("/game")
	public String playGame(Model model, HttpSession session, String id) {
		User user = (User) session.getAttribute("user");
		if (user != null || id != null) {
			Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(id));
			if (currentLobby.getOwner().equals(user.getUsername())) {
				model.addAttribute("grid", gameService.getOwnerGrid(currentLobby.getId()));
				if (currentLobby.getWhoPlays() == null || currentLobby.getWhoPlays().isEmpty()) {
					currentLobby.setWhoPlays(currentLobby.getOwner());
				}
			} else if (currentLobby.getChallenger().equals(user.getUsername())) {
				model.addAttribute("grid", gameService.getChallengerGrid(currentLobby.getId()));
			}
			model.addAttribute("lobby", currentLobby);
			return "game";
		}
		return "redirect:/";
	}

	@PostMapping("/checkTurn")
	@ResponseBody
	public Map<String, Object> checkTurn(Optional<Integer> lobbyid, HttpSession session) {

		User user = (User) session.getAttribute("user");
		Lobby current = lobbyService.getLobby(lobbyid.get());
		if (user != null && lobbyid.isPresent()) {
			if (current.getWinner() != null && !current.getWinner().isEmpty()) {
				if (current.getWinner().equals(user.getUsername())) {
					return Collections.singletonMap("youWin", true);
				} else {
					return Collections.singletonMap("youWin", false);
				}
			}
			Map<String, Object> response = new HashMap<>();
			if (current.getWhoPlays() != null && current.getWhoPlays().equals(user.getUsername())) {
				response.put("turn", true);
//					return Collections.singletonMap("turn", true);
			} else {
				response.put("turn", false);
//					return Collections.singletonMap("turn", false);
			}
			if (current.getOwner().equals(user.getUsername())) {
				List<Tupla> ownerList = new ArrayList<>();
				for (int i = 1; i <= 10; i++) {
					for (int j = 1; j <= 10; j++) {
						if (gameService.getOwnerGrid(current.getId()).getContent(i, j) == 1) {
							Tupla tupla = new Tupla(i, j, 1);
							ownerList.add(tupla);
						} else if (gameService.getOwnerGrid(current.getId()).getContent(i, j) == -1) {
							Tupla tupla = new Tupla(i, j, -1);
							ownerList.add(tupla);
						}
					}
				}
				response.put("refreshGrid", ownerList);
			}
			else {
				List<Tupla> challengerList = new ArrayList<>();
				for (int i = 1; i <= 10; i++) {
					for (int j = 1; j <= 10; j++) {
						if (gameService.getChallengerGrid(current.getId()).getContent(i, j) == 1) {
							Tupla tupla = new Tupla(i, j, 1);
							challengerList.add(tupla);
						} else if (gameService.getChallengerGrid(current.getId()).getContent(i, j) == -1) {
							Tupla tupla = new Tupla(i, j, -1);
							challengerList.add(tupla);
						}
					}
				}
				response.put("refreshGrid", challengerList);
			}
			return response;
		}
		
		return Collections.singletonMap("turn", false);
	}

}
