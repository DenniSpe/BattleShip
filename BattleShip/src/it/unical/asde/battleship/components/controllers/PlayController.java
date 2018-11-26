package it.unical.asde.battleship.components.controllers;
/*
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unical.asde.battleship.model.Player;
import it.unical.asde.battleship.model.Randomizer;
import it.unical.asde.battleship.model.Ship;
/*
@Controller
public class PlayController
{

 

    private static boolean hasErrorOnPositioning(final int row, final int col , final int dir, final Player p, final int boatSize) {

    	//final int length = p.ships[count].getLength();
        
    	// Count è la size della mia barca
        System.out.println("MY DEBUG: length is "+boatSize);

        // Check if off grid - Horizontal
        if (dir == 0)
        {
            //final int checker = length + col;
             System.out.println("MY DEBUG HORIZONTAL : checker is " + (row + boatSize - 1));
            if (row + (boatSize -1)  > 10)
            {
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir == 1) // VERTICAL
        {
           // final int checker = length + row;
             System.out.println("MY DEBUG VERTICAL: checker is " + (col + boatSize - 1));
            if (col + (boatSize -1) > 10)
            {
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
        	if(col + (boatSize-1) > 10) {
        		System.out.println("MY DEBUG: vuoi scrivere fino a = " + (col + boatSize -1) );
        			return true;
        	}
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col + boatSize; i++)
            {
                System.out.println("MY DEBUG: row = " + row + "; col = " + i);
                if (p.playerGrid.hasShip(row, i))
                {
                    return true;
                }
            }
        }
        else if (dir == 1) // Vertical
        {
        	
        		if((row + boatSize -1) > 10 )
        		{
        			System.out.println("MY DEBUG: vuoi scrivere fino a = " + (row + boatSize -1) );
        			
        		}
            // For each location a ship occupies, check if ship is already there
            for (int i = row; i < row + boatSize; i++)
            {
                 System.out.println("DEBUG: row = " + i + "; col = " + col);
                if (p.playerGrid.hasShip(i, col))
                {
                    return true;
                }
            }
        }

        return false;
    }


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
    
    @GetMapping("/putBoat")
    @ResponseBody
    public DeferredResult<String> putBoat(final String cella,  String dir, String size, final HttpSession session, Model model)
    {

    	int row = Integer.parseInt(cella.split("-")[1]);
    	int col = Integer.parseInt(cella.split("-")[2]);
    	
    	int direction = dir.split("\\([^0-9]*")[1].split("deg")[0].equals("0") ? Ship.HORIZONTAL : Ship.VERTICAL;
    	
    	int boatSize = Integer.parseInt(size);
    	
    	// I extract boat-size from the id of the img tag in the jsp
  
    	
    	
    	final DeferredResult<String> output = new DeferredResult<>();
    	
    	if(hasErrorOnPositioning(row - 1 , col - 1, direction, playService.getOwner(), boatSize-1)) {
    		System.out.println("==========================ERROREEEEE, SFORI");
    		ForkJoinPool.commonPool().submit(() ->
            {
            	output.setResult("ERROR");
            });
    	}
    	else
    	{
    		ForkJoinPool.commonPool().submit(() ->
            {
    		output.setResult(row+" , "+col+" , "+direction+" , "+boatSize);
            });
    	}

        
            
        return output;
    }

    @GetMapping("/boatPositioning")
    public String goToGame()
    {
       // setupRandom(playService.getOwner());
        //setupRandom(playService.getChallenger());
       // System.out.println("Grid challenger");
      //  playService.getChallenger().playerGrid.printShips();
    	
        System.out.println("Grid owner");
        playService.getOwner().playerGrid.printShips();
        return "boatPositioning";
    }

    
 

  
}
*/