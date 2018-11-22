package it.unical.asde.battleship.components.controllers;

import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import it.unical.asde.battleship.components.services.PlayService;
import it.unical.asde.battleship.model.Player;
import it.unical.asde.battleship.model.Randomizer;
import it.unical.asde.battleship.model.Ship;

@Controller
public class PlayController
{

    private static String askForGuess(final Player p, final Player opp)
    {
        //		System.out.println("Viewing My Guesses:");
        //		p.oppGrid.printStatus();
        //
        //		int row = -1;
        //		int col = -1;
        //
        //		String oldRow = "Z";
        //		int oldCol = -1;
        //
        //		while (true) {
        //			System.out.print("Type in row (A-J): ");
        //			String userInputRow = reader.next();
        //			userInputRow = userInputRow.toUpperCase();
        //			oldRow = userInputRow;
        //			row = convertLetterToInt(userInputRow);
        //
        //			System.out.print("Type in column (1-10): ");
        //			col = reader.nextInt();
        //			oldCol = col;
        //			col = convertUserColToProCol(col);
        //
        //			// System.out.println("DEBUG: " + row + col);
        //
        //
        //			if (col >= 0 && col <= 9 && row != -1)
        //				break;
        //
        //			System.out.println("Invalid location!");
        //		}

        return null;

    }

    private static int convertCompColToRegular(final int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 0:
                toReturn = 1;
                break;
            case 1:
                toReturn = 2;
                break;
            case 2:
                toReturn = 3;
                break;
            case 3:
                toReturn = 4;
                break;
            case 4:
                toReturn = 5;
                break;
            case 5:
                toReturn = 6;
                break;
            case 6:
                toReturn = 7;
                break;
            case 7:
                toReturn = 8;
                break;
            case 8:
                toReturn = 9;
                break;
            case 9:
                toReturn = 10;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static String convertIntToLetter(final int val)
    {
        String toReturn = "Z";
        switch (val)
        {
            case 0:
                toReturn = "A";
                break;
            case 1:
                toReturn = "B";
                break;
            case 2:
                toReturn = "C";
                break;
            case 3:
                toReturn = "D";
                break;
            case 4:
                toReturn = "E";
                break;
            case 5:
                toReturn = "F";
                break;
            case 6:
                toReturn = "G";
                break;
            case 7:
                toReturn = "H";
                break;
            case 8:
                toReturn = "I";
                break;
            case 9:
                toReturn = "J";
                break;
            default:
                toReturn = "Z";
                break;
        }

        return toReturn;
    }

    /* HELPER METHODS */
    private static int convertLetterToInt(final String val)
    {
        int toReturn = -1;
        switch (val)
        {
            case "A":
                toReturn = 0;
                break;
            case "B":
                toReturn = 1;
                break;
            case "C":
                toReturn = 2;
                break;
            case "D":
                toReturn = 3;
                break;
            case "E":
                toReturn = 4;
                break;
            case "F":
                toReturn = 5;
                break;
            case "G":
                toReturn = 6;
                break;
            case "H":
                toReturn = 7;
                break;
            case "I":
                toReturn = 8;
                break;
            case "J":
                toReturn = 9;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static int convertUserColToProCol(final int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 1:
                toReturn = 0;
                break;
            case 2:
                toReturn = 1;
                break;
            case 3:
                toReturn = 2;
                break;
            case 4:
                toReturn = 3;
                break;
            case 5:
                toReturn = 4;
                break;
            case 6:
                toReturn = 5;
                break;
            case 7:
                toReturn = 6;
                break;
            case 8:
                toReturn = 7;
                break;
            case 9:
                toReturn = 8;
                break;
            case 10:
                toReturn = 9;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static boolean hasErrorsComp(final int row, final int col, final int dir, final Player p, final int count)
    {
        // System.out.println("DEBUG: count arg is " + count);

        final int length = p.ships[count].getLength();

        // Check if off grid - Horizontal
        if (dir == 0)
        {
            final int checker = length + col;
            // System.out.println("DEBUG: checker is " + checker);
            if (checker > 10)
            {
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir == 1) // VERTICAL
        {
            final int checker = length + row;
            // System.out.println("DEBUG: checker is " + checker);
            if (checker > 10)
            {
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col + length; i++)
            {
                // System.out.println("DEBUG: row = " + row + "; col = " + i);
                if (p.playerGrid.hasShip(row, i))
                {
                    return true;
                }
            }
        }
        else if (dir == 1) // Vertical
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = row; i < row + length; i++)
            {
                // System.out.println("DEBUG: row = " + row + "; col = " + i);
                if (p.playerGrid.hasShip(i, col))
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Autowired
    private PlayService playService;

    @GetMapping("/getEvents")
    @ResponseBody
    public DeferredResult<String> getEvents(final String cella, final HttpSession session)
    {
        final DeferredResult<String> output = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() ->
            {
                output.setResult(cella);
            });
        return output;
    }

    @GetMapping("/game")
    public String goToGame()
    {
        setupRandom(playService.getOwner());
        setupRandom(playService.getChallenger());
        System.out.println("Grid challenger");
        playService.getChallenger().playerGrid.printShips();
        System.out.println("Grid owner");
        playService.getOwner().playerGrid.printShips();
        return "game";
    }

    private void setupManually()
    {
        // to do
    }

    private void setupRandom(final Player p)
    {
        int counter = 1;
        int normCounter = 0;

        final Randomizer rand = new Randomizer();

        while (p.numOfShipsLeft() > 0)
        {
            for (final Ship s : p.ships)
            {
                int row = Randomizer.nextInt(0, 9);
                int col = Randomizer.nextInt(0, 9);
                int dir = Randomizer.nextInt(0, 1);

                while (hasErrorsComp(row, col, dir, p, normCounter)) // while the random nums make error, start again
                {
                    row = Randomizer.nextInt(0, 9);
                    col = Randomizer.nextInt(0, 9);
                    dir = Randomizer.nextInt(0, 1);
                }

                p.ships[normCounter].setLocation(row, col);
                p.ships[normCounter].setDirection(dir);
                p.playerGrid.addShip(p.ships[normCounter]);

                normCounter++;
                counter++;
            }
        }
    }
}
