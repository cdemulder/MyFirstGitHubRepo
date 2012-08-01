package org.tof.bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private GameManager gameManager = new GameManager();
	
	public void start() throws IOException
	{
		InputStreamReader istream = new InputStreamReader(System.in) ;
        BufferedReader bufRead = new BufferedReader(istream) ;
        
		while (!gameManager.isEndOfGame())
		{
			boolean inputOK = false;
			Integer quilles = null;
			while (!inputOK)
			{
				StringBuilder stringBuilder = new StringBuilder("Frame ");
				stringBuilder.append(gameManager.getCurrentFrameNb());
				stringBuilder.append(", lancer ");
				stringBuilder.append(gameManager.getCurrentThrowNb());
				stringBuilder.append(": ");
				System.out.println(stringBuilder.toString());
			
				String input = bufRead.readLine();
				try 
				{
					quilles = new Integer(input);
					gameManager.lancer(quilles);
					inputOK = true;
				}
				catch (NumberFormatException e){
					System.out.println("Veuillez entrer un nombre !");
				}
				catch (OutOfBoundsException e){
					System.out.printf("Veuillez entrer un nombre entre %d et %d !\n",e.getMinBound(),e.getMaxBound());
				}
					
			}
			
		}
		System.out.println("Vous avez gagn� !");
		
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("I don't roll on Shabbos ! -- Walter Sobchak (The Big Lebowski)");
		try {
			new Game().start();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}

}
