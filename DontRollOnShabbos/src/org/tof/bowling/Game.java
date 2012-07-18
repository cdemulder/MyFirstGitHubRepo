package org.tof.bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private GameManager frameManager = new GameManager();
	
	public void start() throws IOException
	{
		InputStreamReader istream = new InputStreamReader(System.in) ;
        BufferedReader bufRead = new BufferedReader(istream) ;
        
		while (!frameManager.isFinished())
		{
			boolean inputOK = false;
			Integer quilles = null;
			while (!inputOK)
			{
				StringBuilder stringBuilder = new StringBuilder("Frame ");
				stringBuilder.append(frameManager.getCurrentFrame()+1);
				stringBuilder.append(", lancer ");
				stringBuilder.append(frameManager.getCurrentThrowInFrame()+1);
				stringBuilder.append(": ");
				System.out.println(stringBuilder.toString());
			
				String input = bufRead.readLine();
				try 
				{
					quilles = new Integer(input);
					if (quilles>=0 && quilles<=15)
						inputOK = true;
				}
				catch (NumberFormatException e){}
				
				if (!inputOK)
					System.out.println("Veuillez entrer un nombre entre 0 et 15 !");
			}
			
			frameManager.lancer(quilles);
			
			
		}
		System.out.println("Bravo !");
		
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
