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
import it.unical.asde.battleship.model.Grid;
import it.unical.asde.battleship.model.Lobby;
import it.unical.asde.battleship.model.Tupla;
import it.unical.asde.battleship.model.User;

@Controller
public class GameController
{

    private static boolean hasErrorOnPositioning(final int row, final int col, final int dir, final Grid grid, final int boatSize)
    {

        // If the direction is Horizontal (0)
        if (dir == 0)
        {
            if (col + boatSize - 1 > 10)
            {
                return true;
            }
        }

        // If the direction is Vertical (1)
        if (dir == 1)
        {
            if (row + boatSize - 1 > 10)
            {
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
            //			if (col + boatSize - 1 > 10) {
            //				return true;
            //			}
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col + boatSize; i++)
            {
                if (grid.hasShip(row, i))
                {
                    return true;
                }
            }
        }
        else if (dir == 1) // Vertical
        {

            //			if (row + boatSize - 1 > 10) {
            //				return true;
            //			}
            // For each location a ship occupies, check if ship is already there
            for (int i = row; i < row + boatSize; i++)
            {
                if (grid.hasShip(i, col))
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Autowired
    private GameService gameService;

    @Autowired
    private LobbyService lobbyService;

    @PostMapping("/checkAlive")
    @ResponseBody
    public boolean checkPlayerAreStillAlive(final Model model, final HttpSession session, @RequestParam final String lobby_id)
    {
        final int id = Integer.parseInt(lobby_id);
        final Lobby lobbyStillExist = lobbyService.getLobby(id);

        if (lobbyStillExist == null)
        {
            return false;
        }

        return true;

    }

    @PostMapping("/checkTurn")
    @ResponseBody
    public Map<String, Object> checkTurn(final Optional<Integer> lobbyid, final HttpSession session)
    {

        final User user = (User) session.getAttribute("user");
        final Lobby current = lobbyService.getLobby(lobbyid.get());
        if (user != null && lobbyid.isPresent())
        {
            if (current.getWinner() != null && !current.getWinner().isEmpty())
            {
                if (current.getWinner().equals(user.getUsername()))
                {
                    return Collections.singletonMap("youWin", true);
                }
                else
                {
                    return Collections.singletonMap("youWin", false);
                }
            }
            final Map<String, Object> response = new HashMap<>();
            if (current.getWhoPlays() != null && current.getWhoPlays().equals(user.getUsername()))
            {
                response.put("turn", true);
                //					return Collections.singletonMap("turn", true);
            }
            else
            {
                response.put("turn", false);
                //					return Collections.singletonMap("turn", false);
            }

            // ====================================================================
            final boolean isOwner = current.getOwner().equals(user.getUsername());

            List<Tupla> refreshGrid = new ArrayList<>();
            List<Tupla> refreshGridI = new ArrayList<>();
            
            refreshGrid.addAll(gameService.getGrid(current.getId(), isOwner).getHitteds());
            refreshGrid.addAll(gameService.getGrid(current.getId(), isOwner).getMisseds());
            refreshGridI.addAll(gameService.getGrid(current.getId(), !isOwner).getHitteds());
           	refreshGridI.addAll(gameService.getGrid(current.getId(), !isOwner).getMisseds());

//                      
//            for (int i = 1; i <= 10; i++)
//            {
//                for (int j = 1; j <= 10; j++)
//                {
//                    if (gameService.getGrid(current.getId(), isOwner).getContent(i, j) == 1)
//                    {
//                        list.add(new Tupla(i, j, 1));
//                    }
//                    else if (gameService.getGrid(current.getId(), isOwner).getContent(i, j) == -1)
//                    {
//                        list.add(new Tupla(i, j, -1));
//                    }
//                }
//            }

            response.put("refreshGrid", refreshGrid);
            response.put("refreshGridI", refreshGridI);
            // ========================================================================

            //++ REFACTORED ++
            //			if (current.getOwner().equals(user.getUsername())) {
            //				List<Tupla> ownerList = new ArrayList<>();
            //				for (int i = 1; i <= 10; i++) {
            //					for (int j = 1; j <= 10; j++) {
            //						if (gameService.getOwnerGrid(current.getId()).getContent(i, j) == 1) {
            //							Tupla tupla = new Tupla(i, j, 1);
            //							ownerList.add(tupla);
            //						} else if (gameService.getOwnerGrid(current.getId()).getContent(i, j) == -1) {
            //							Tupla tupla = new Tupla(i, j, -1);
            //							ownerList.add(tupla);
            //						}
            //					}
            //				}
            //				response.put("refreshGrid", ownerList);
            //			}
            //			else {
            //				List<Tupla> challengerList = new ArrayList<>();
            //				for (int i = 1; i <= 10; i++) {
            //					for (int j = 1; j <= 10; j++) {
            //						if (gameService.getChallengerGrid(current.getId()).getContent(i, j) == 1) {
            //							Tupla tupla = new Tupla(i, j, 1);
            //							challengerList.add(tupla);
            //						} else if (gameService.getChallengerGrid(current.getId()).getContent(i, j) == -1) {
            //							Tupla tupla = new Tupla(i, j, -1);
            //							challengerList.add(tupla);
            //						}
            //					}
            //				}
            //				response.put("refreshGrid", challengerList);
            //			}
            //++ REFACTORED ++
            return response;
        }

        return Collections.singletonMap("turn", false);
    }

    @PostMapping("/deleteBoatFromGrid")
    @ResponseBody
    public boolean deleteBoatFromGrid(@RequestParam final String boatID, @RequestParam final String ID,
            @RequestParam final String row, @RequestParam final String col, @RequestParam final String dir,
            final HttpSession session)
    {

        final int boatRow = Integer.parseInt(row);
        final int boatCol = Integer.parseInt(col);
        final int length = getLength(boatID);
        final int direction = Integer.parseInt(dir);
        final Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(ID));
        final User user = (User) session.getAttribute("user");

        Grid grid = new Grid();

        try
        {
            final boolean isOwner = user.getUsername().equals(currentLobby.getOwner());

            grid = gameService.getGrid(currentLobby.getId(), isOwner);

            grid.deleteShip(boatRow, boatCol, length, direction);

            //++REFACTORED++
            //			if(user.getUsername().equals(currentLobby.getOwner())) {
            //				grid = gameService.getOwnerGrid(currentLobby.getId());
            //
            //				grid.deleteShip(boatRow, boatCol, length, direction);
            //				System.out.println("CANCELLO LA BARCA DI OWNER ED OTTENGO");
            //				grid.print();
            //
            //			}
            //			else {  //If you are the challenger
            //				grid = gameService.getChallengerGrid(currentLobby.getId());
            //
            //				grid.deleteShip(boatRow, boatCol, length, direction);
            //				System.out.println("CANCELLO LA BARCA DI CHALLENGER ED OTTENGO");
            //				grid.print();
            //			}
            //++REFACTORED++
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int getLength(final String boatName)
    {
        int boatSize = 0;

        switch (boatName)
        {
            case "destroyer":
                boatSize = 2;
                break;
            case "submarine":
                boatSize = 3;
                break;
            case "cruiser":
                boatSize = 3;
                break;
            case "battleship":
                boatSize = 4;
                break;
            case "aircraft":
                boatSize = 5;
                break;
            default:
                break;
        }
        return boatSize;
    }

    @GetMapping("/boatPositioning")
    public String goToGame()
    {
        return "boatPositioning";
    }

    @GetMapping("/leaveGame")
    public String leaveGame(final Model model, final HttpSession session, @RequestParam final String lobby_id)
    {

        final String userWhoDelete = ((User) session.getAttribute("user")).getUsername();

        lobbyService.deleteLobby(Integer.parseInt(lobby_id));
        return "index";
    }

    @GetMapping("/game")
    public String playGame(final Model model, final HttpSession session, final String id)
    {

        final User user = (User) session.getAttribute("user");
        if (user != null && id != null)
        {
            final Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(id));
            final boolean isOwner = currentLobby.getOwner().equals(user.getUsername());

            if (currentLobby.getOwner() != null && isOwner)
            {

                model.addAttribute("grid", gameService.getGrid(currentLobby.getId(), isOwner));
                if (currentLobby.getWhoPlays() == null || currentLobby.getWhoPlays().isEmpty())
                {
                    currentLobby.setWhoPlays(currentLobby.getOwner());
                }
            }
            else if (currentLobby.getChallenger() != null && !isOwner)
            {
                model.addAttribute("grid", gameService.getGrid(currentLobby.getId(), isOwner));
            }

            //++REFACTORED++
            //			if (currentLobby.getOwner()!=null && currentLobby.getOwner().equals(user.getUsername())) {
            //				model.addAttribute("grid", gameService.getOwnerGrid(currentLobby.getId()));
            //				if (currentLobby.getWhoPlays() == null || currentLobby.getWhoPlays().isEmpty()) {
            //					currentLobby.setWhoPlays(currentLobby.getOwner());
            //				}
            //			} else if (currentLobby.getChallenger()!=null && currentLobby.getChallenger().equals(user.getUsername())) {
            //				model.addAttribute("grid", gameService.getChallengerGrid(currentLobby.getId()));
            //			}
            //++REFACTORED++
            model.addAttribute("lobby", currentLobby);
            return "game";
        }
        return "redirect:/";
    }

    @GetMapping("/putBoat")
    @ResponseBody
    public DeferredResult<String> putBoat(final String ID, final String cella, final HttpSession session, final String dir,
            final String boatName)
    {

        final int lobbyID = Integer.parseInt(ID);
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        final int row = Integer.parseInt(cella.split("-")[1]);
        final int col = Integer.parseInt(cella.split("-")[2]);

        final int direction = dir.split("\\([^0-9]*")[1].split("deg")[0].equals("0") ? 0 : 1;

        final DeferredResult<String> output = new DeferredResult<>();

        final String username = (String) session.getAttribute("username");

        final boolean isOwner = username.equals(currentLobby.getOwner());

        //++REFACTORED++
        //		if (username.equals(currentLobby.getOwner())) {
        //			isOwner = true;
        //		}
        //++REFACTORED++

        // ================================================
        if (hasErrorOnPositioning(row, col, direction, gameService.getGrid(lobbyID, isOwner), getLength(boatName)))
        {
            ForkJoinPool.commonPool().submit(() ->
                {
                    output.setResult("ERROR");
                });
        }
        else
        {
            gameService.putShip(lobbyID, row, col, getLength(boatName), direction, isOwner);
            System.out.println(isOwner ? "OWNER" : "CHALLENGER" + " GRID ==========");
            gameService.getGrid(lobbyID, isOwner).print();
            System.out.println("============= FINE GRID ==========");
            ForkJoinPool.commonPool().submit(() ->
                {
                    output.setResult(row + " , " + col + " , " + direction + " , " + getLength(boatName));
                });
        }
        // ================================================

        //++REFACTORED++
        //		if (isOwner) {
        //			if (hasErrorOnPositioning(row, col, direction, gameService.getOwnerGrid(lobbyID), getLength(boatName))) {
        //				System.out.println("==========================ERROREEEEE, SFORI");
        //				ForkJoinPool.commonPool().submit(() -> {
        //					output.setResult("ERROR");
        //				});
        //			} else {
        //				// ++REFACTORED++
        //				//gameService.putShipOwner(lobbyID, row, col, getLength(boatName), direction);
        //				// ++REFACTORED++
        //
        //				gameService.putShip(lobbyID, row, col, getLength(boatName), direction, isOwner);
        //
        //				System.out.println("============= OWNER GRID ==========");
        //				gameService.getOwnerGrid(lobbyID).print();
        //				System.out.println("============= FINE OWNER GRID ==========");
        //				ForkJoinPool.commonPool().submit(() -> {
        //					output.setResult(row + " , " + col + " , " + direction + " , " + getLength(boatName));
        //				});
        //			}
        //		} else {// if is not owner
        //			if (hasErrorOnPositioning(row, col, direction, gameService.getChallengerGrid(lobbyID),
        //					getLength(boatName))) {
        //				System.out.println("==========================ERROREEEEE, SFORI");
        //				ForkJoinPool.commonPool().submit(() -> {
        //					output.setResult("ERROR");
        //				});
        //			} else {
        //				// ++REFACTORED++
        //				//gameService.putShipChallenger(lobbyID, row, col, getLength(boatName), direction);
        //				// ++REFACTORED++
        //
        //				gameService.putShip(lobbyID, row, col, getLength(boatName), direction, isOwner);
        //
        //				System.out.println("============= CHALLENGER GRID ==========");
        //				System.out.println("ID = " + lobbyID + " row = " + row + " col = " + col + " boatSize = "
        //						+ getLength(boatName) + " direction = " + direction);
        //				gameService.getChallengerGrid(lobbyID).print();
        //				System.out.println("============= FINE CHALLENGER GRID ==========");
        //				ForkJoinPool.commonPool().submit(() -> {
        //					output.setResult(row + " , " + col + " , " + direction + " , " + getLength(boatName));
        //				});
        //			}
        //		}
        //++REFACTORED++
        return output;
    }

    @PostMapping("/shoot")
    @ResponseBody
    public Map<String, Object> shoot(final String cella, final HttpSession session, final String id)
    {

        final int row = Integer.parseInt(cella.split("-")[1]);
        final int col = Integer.parseInt(cella.split("-")[2]);

        final Map<String, Object> response = new HashMap<String, Object>();
        final User user = (User) session.getAttribute("user");
        if (user != null || id != null)
        {
            final Lobby currentLobby = lobbyService.getLobby(Integer.parseInt(id));
            if (currentLobby.getWhoPlays().equals(user.getUsername())
                    && currentLobby.getWinner() == null/* There is no winner yet? */)
            {

                final boolean isOwner = currentLobby.getOwner().equals(user.getUsername());

                if (!gameService.getGrid(currentLobby.getId(), !isOwner).alreadyGuessed(row, col))
                {
                    response.put("row", row);
                    response.put("col", col);
                    if (gameService.getGrid(currentLobby.getId(), !isOwner).hasShip(row, col))
                    {

                        gameService.getGrid(currentLobby.getId(), !isOwner).markHit(row, col);
                        gameService.getGrid(currentLobby.getId(), !isOwner).addHitCell(new Tupla(row, col, 1));
                        System.out.println(isOwner ? "CHALLANGER" : "OWNER" + "GRID AFTER SHOOT");
                        gameService.getGrid(currentLobby.getId(), !isOwner).print();
                        System.out.println(isOwner ? "CHALLANGER" : "OWNER" + "END GRID AFTER SHOOT");

                        response.put("hit", true);
                        if (!gameService.hasMoreShips(currentLobby.getId(), isOwner))
                        {
                            // OWNER WIN
                            currentLobby.setWinner(user.getUsername());
                            response.put("youWin", true);
                        }
                    }
                    else
                    {
                        gameService.getGrid(currentLobby.getId(), !isOwner).markMiss(row, col);
                        gameService.getGrid(currentLobby.getId(), !isOwner).addHitCell(new Tupla(row, col, -1));
                        response.put("hit", false);
                        currentLobby.setWhoPlays(isOwner ? currentLobby.getChallenger() : currentLobby.getOwner());
                    }
                }
                //++REFACTORED
                //				if (currentLobby.getOwner().equals(user.getUsername())) {
                //					if (!gameService.getChallengerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
                //						response.put("row", row);
                //						response.put("col", col);
                //						if (gameService.getChallengerGrid(currentLobby.getId()).hasShip(row, col)) {
                //							gameService.getChallengerGrid(currentLobby.getId()).markHit(row, col);
                //							// output.setResult("hit-" + row + "-" + col);
                //
                //							System.out.println("CHALLENGER GRID AFTER SHOOT");
                //							gameService.getChallengerGrid(currentLobby.getId()).print();
                //							System.out.println("END CHALLENGER GRID AFTER SHOOT");
                //
                //							shot = "hit-" + row + "-" + col;
                //							response.put("hit", true);
                //							if (!gameService.hasMoreShips(currentLobby.getId(), true)) {
                //								// OWNER WIN
                //								currentLobby.setWinner(user.getUsername());
                //								shot += "-OWNERWIN";
                //								response.put("youWin", true);
                //							}
                //						} else {
                //							gameService.getChallengerGrid(currentLobby.getId()).markMiss(row, col);
                //							// output.setResult("miss-" + row + "-" + col);
                //							shot = "miss-" + row + "-" + col;
                //							response.put("hit", false);
                //							currentLobby.setWhoPlays(currentLobby.getChallenger());
                //						}
                //					}
                //				} else if (currentLobby.getChallenger().equals(user.getUsername())) {
                //					if (!gameService.getOwnerGrid(currentLobby.getId()).alreadyGuessed(row, col)) {
                //						response.put("row", row);
                //						response.put("col", col);
                //						if (gameService.getOwnerGrid(currentLobby.getId()).hasShip(row, col)) {
                //							gameService.getOwnerGrid(currentLobby.getId()).markHit(row, col);
                //							// output.setResult("hit-" + row + "-" + col);
                //
                //							System.out.println("OWNER GRID AFTER SHOOT");
                //							gameService.getOwnerGrid(currentLobby.getId()).print();
                //							System.out.println("END OWNER GRID AFTER SHOOT");
                //
                //							shot = "hit-" + row + "-" + col;
                //							response.put("hit", true);
                //							if (!gameService.hasMoreShips(currentLobby.getId(), false)) {
                //								// CHALLENGER WIN
                //								shot += "-CHALLENGERWIN";
                //								currentLobby.setWinner(user.getUsername());
                //								response.put("youWin", true);
                //							}
                //						} else {
                //							gameService.getOwnerGrid(currentLobby.getId()).markMiss(row, col);
                //							// output.setResult("miss-" + row + "-" + col);
                //							shot = "miss-" + row + "-" + col;
                //							currentLobby.setWhoPlays(currentLobby.getOwner());
                //							response.put("hit", false);
                //						}
                //					}
                //				}
                //++REFACTORED
            }
            else
            {
                response.put("waitTurn", true);
            }

        }

        return response;
    }

    @GetMapping("/startPositioning")
    public String startPositioning(final Model model, final HttpSession session, @RequestParam final String ID)
    {

        final User user = (User) session.getAttribute("user");
        final Lobby lobb = lobbyService.getLobby(Integer.parseInt(ID));
        if (lobb.getWhoPlays() != null && !lobb.getWhoPlays().isEmpty())
        {
            return "redirect:/game?id=" + lobb.getId();
        }
        if (user != null && lobb != null)
        {
            if (user.getUsername().equals(lobb.getChallenger()) || user.getUsername().equals(lobb.getOwner()))
            {
                if (user.getUsername().equals(lobb.getOwner()))
                {
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
    public DeferredResult<String> waitingStart(@RequestParam final String ID, final HttpSession session)
    {

        final int lobbyID = Integer.parseInt(ID);
        final DeferredResult<String> output = new DeferredResult<>();
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        final String username = (String) session.getAttribute("username");
        final boolean isOwner = username.equals(currentLobby.getOwner());

        gameService.userIsReady(lobbyID, isOwner);

        // ++ REFACTORED ++
        //		if (isOwner) {
        //			gameService.ownerIsReady(lobbyID);
        //		} else {
        //			//gameService.challengerIsReady(lobbyID);
        //
        //		}
        // ++ REFACTORED ++

        if (gameService.usersAreReady(lobbyID))
        {

            lobbyService.getLobby(lobbyID).setStartingTimeStamp(new Date());

            ForkJoinPool.commonPool().submit(() ->
                {
                    output.setResult("game");
                });
            ;
        }

        else
        {
            ForkJoinPool.commonPool().submit(() ->
                {
                    output.setResult("boatPositioning");
                });
        }

        return output;
    }

}
