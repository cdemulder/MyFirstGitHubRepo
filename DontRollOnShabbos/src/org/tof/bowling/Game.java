package org.tof.bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.tof.bowling.exception.WrongThrowValueException;
import org.tof.bowling.model.FrameManager;

/**
 * 
 * Point d'entrée du jeu de Sobchak
 * 
 * @author Tof
 * 
 *
 */
public class Game {

	private FrameManager frameManager = new FrameManager();
	
	public void start() throws IOException
	{
		System.out.println("***********************");
		System.out.println("* Jouons au Sobchak ! *");
		System.out.println("***********************");
		System.out.println("");
		
		InputStreamReader istream = new InputStreamReader(System.in) ;
        BufferedReader bufRead = new BufferedReader(istream) ;
        boolean stop =false;
        
		while (!frameManager.isEndOfGame() && !stop)
		{
			boolean inputOK = false;
			Integer quilles = null;
			while (!inputOK)
			{
				StringBuilder stringBuilder = new StringBuilder("Frame ");
				stringBuilder.append(frameManager.getCurrentFrameNb());
				stringBuilder.append(", lancer ");
				stringBuilder.append(frameManager.getCurrentThrowNb());
				stringBuilder.append(": ");
				System.out.println(stringBuilder.toString());
			
				String input = bufRead.readLine();
				try 
				{
					if ("X".equals(input))
					{
						stop=true;
						inputOK=true;
					}
					else
					{
						quilles = new Integer(input);
						Integer score = frameManager.lancer(quilles);
						System.out.println("Score: "+ (score!=null?score:"?"));
						System.out.println("");
						inputOK = true;
					}
				}
				catch (NumberFormatException e){
					System.out.println("Veuillez entrer un nombre !");
				}
				catch (WrongThrowValueException e){
					System.out.printf("Veuillez entrer un nombre entre %d et %d !\n",e.getMinBound(),e.getMaxBound());
				}
					
			}
			
		}
		if (stop)
			System.out.println("Stop");
		else
			System.out.println("I don't roll on Shabbos ! -- Walter Sobchak (The Big Lebowski)");
		
	}
	
	
	
	
	public static void main(String[] args) {
		try {
			new Game().start();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}

}
