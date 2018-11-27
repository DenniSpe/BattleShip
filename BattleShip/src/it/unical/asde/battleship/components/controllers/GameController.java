package it.unical.asde.battleship.components.controllers;

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
import it.unical.asde.battleship.model.Ship;
import it.unical.asde.battleship.model.User;

@Controller
public class GameController {

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

	@Autowired
	GameService gameService;

	@Autowired
	LobbyService lobbyService;

	// TODO Cosa fa sto metodo ? Diamo nomi esplicativi. Credo restituisca la cella
	// colpita.. serve ancora??
	@PostMapping("/shoot")
	@ResponseBody
	public DeferredResult<String> shoot(final String cella, final HttpSession session, String id) {
		final DeferredResult<String> output = new DeferredResult<>();

		ForkJoinPool.commonPool().submit(() -> {

			int row = Integer.parseInt(cella.split("-")[1]);
			int col = Integer.parseInt(cella.split("-")[2]);
			User user = (User) session.getAttribute("user");
			if (user != null || id != null) {
				Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(id));
				if (currentLobby.getOwner().equals(user.getUsername())) {
					if (!gameService.getChallengerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
						if (gameService.getChallengerGrid(currentLobby.getId()).hasShip(row, col)) {
							gameService.getChallengerGrid(currentLobby.getId()).markHit(row, col);
							output.setResult("hit-" + row + "-" + col);
						} else {
							gameService.getChallengerGrid(currentLobby.getId()).markMiss(row, col);
							output.setResult("miss-" + row + "-" + col);

						}
					}
				} else if (currentLobby.getChallenger().equals(user.getUsername())) {
					if (!gameService.getOwnerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
						if (gameService.getOwnerGrid(currentLobby.getId()).hasShip(row, col)) {
							gameService.getOwnerGrid(currentLobby.getId()).markHit(row, col);
							output.setResult("hit-" + row + "-" + col);
						} else {
							gameService.getOwnerGrid(currentLobby.getId()).markMiss(row, col);
							output.setResult("miss-" + row + "-" + col);

						}
					}
				}

			}
		});
		return output;
	}

	@GetMapping("/boatPositioning")
	public String goToGame() {
		return "boatPositioning";
	}

	@GetMapping("/putBoat")
	@ResponseBody
	public DeferredResult<String> putBoat(final String ID, final String cella, final HttpSession session,
			final String dir, final String size) {
		boolean isOwner = false;

		final int lobbyID = Integer.parseInt(ID);
		final Lobby currentLobby = lobbyService.getLobby(lobbyID);
		System.out.println("LOOOOOOOOBBY" + lobbyID);

		final int row = Integer.parseInt(cella.split("-")[1]);
		final int col = Integer.parseInt(cella.split("-")[2]);

		final int direction = dir.split("\\([^0-9]*")[1].split("deg")[0].equals("0") ? Ship.HORIZONTAL : Ship.VERTICAL;

		final int boatSize = Integer.parseInt(size);

		// TODO I extract boat-size from the id of the img tag in the jsp

		final DeferredResult<String> output = new DeferredResult<>();

		final String username = (String) session.getAttribute("username");

		if (username.equals(currentLobby.getOwner())) {
			isOwner = true;
		}

		if (isOwner) {
			if (hasErrorOnPositioning(row, col, direction, gameService.getOwnerGrid(lobbyID), boatSize)) {
				System.out.println("==========================ERROREEEEE, SFORI");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult("ERROR");
				});
			} else {
				gameService.putShipOwner(lobbyID, row, col, boatSize, direction);
				System.out.println("============= OWNER GRID ==========");
				gameService.getOwnerGrid(lobbyID).print();
				System.out.println("============= FINE OWNER GRID ==========");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult(row + " , " + col + " , " + direction + " , " + boatSize);
				});
			}
		} else {// if is not owner
			if (hasErrorOnPositioning(row, col, direction, gameService.getChallengerGrid(lobbyID), boatSize)) {
				System.out.println("==========================ERROREEEEE, SFORI");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult("ERROR");
				});
			} else {
				gameService.putShipChallenger(lobbyID, row, col, boatSize, direction);
				System.out.println("============= CHALLENGER GRID ==========");
				System.out.println("ID = " + lobbyID + " row = " + row + " col = " + col + " boatSize = " + boatSize
						+ " direction = " + direction);
				gameService.getChallengerGrid(lobbyID).print();
				System.out.println("============= FINE CHALLENGER GRID ==========");
				ForkJoinPool.commonPool().submit(() -> {
					output.setResult(row + " , " + col + " , " + direction + " , " + boatSize);
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
		Lobby lobb = lobbyService.getLobby(Integer.parseInt(ID));
		User user = (User) session.getAttribute("user");
		if (user.getUsername().equals(lobb.getOwner())) {
			lobb.setLobbyStarted(true);
			gameService.startGame(lobb.getId());
		}
		model.addAttribute("lobby", lobb);
		return "boatPositioning";

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
			} else if (currentLobby.getChallenger().equals(user.getUsername())) {
				model.addAttribute("grid", gameService.getChallengerGrid(currentLobby.getId()));
			}
			model.addAttribute("lobbyId", id);
			return "game";
		}
		return "redirect:/";
	}

}
