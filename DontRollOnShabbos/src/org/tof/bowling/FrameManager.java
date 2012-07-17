package org.tof.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameManager {

	private static final int MAX_FRAMES = 5;
	private static final int MAX_THROWS_PER_FRAME = 3;
	private static final int MAX_THROWS_FOR_LAST_FRAME = 4;
	private static final int MAX_SKITTLES = 15;
	
	
	private int currentFrame = 0;
	private int currentThrowInFrame = 0;
	private int currentThrow = 0;
	
	private int currentScore = 0;
	
//	private Map<Integer,Integer> savedFrameScores = new HashMap<Integer, Integer>();
//	private List<Integer> skittlesPerThrow = new ArrayList<Integer>(); 
	
	public boolean isFinished() {
		return currentFrame >= MAX_FRAMES;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public int getCurrentThrowInFrame() {
		return currentThrowInFrame;
	}
	
	public void lancer(int quilles)
	{
		if (quilles < 0 || quilles>MAX_SKITTLES)
			throw new IllegalArgumentException("Le nombre de quilles doit être entre 0 et "+String.valueOf(MAX_SKITTLES));
		
		Integer score = getNewScore(quilles);
		System.out.println("Score: "+ (score!=null?score:"?"));
		next();
		
	}
	
	private void next()
	{
		currentThrowInFrame++;
		currentThrow++;
		if (currentFrame>=MAX_FRAMES-1)
		{
			if (currentThrowInFrame>=MAX_THROWS_FOR_LAST_FRAME)
			{
				currentThrowInFrame=0;
				currentFrame++;
			}
		}
		else
		{
			if (currentThrowInFrame>=MAX_THROWS_PER_FRAME)
			{
				currentThrowInFrame=0;
				currentFrame++;
			}
		}
			
	}
	

	Integer getNewScore(int quilles)
	{
//		//Ajouter à la liste des quilles par lancer
//		skittlesPerThrow.add(quilles);
//		
//		//Récupération du score déjà enregistré pour la frame courante
//		Integer currentFrameScore = savedFrameScores.get(currentFrame);
//		
//		if (currentFrameScore==null)
//		{
//			currentFrameScore = new Integer(quilles);
//			savedFrameScores.put(currentFrame, currentFrameScore);
//		}
//		else
//			currentFrameScore = currentFrameScore + quilles;
			
		return currentScore+=quilles;
		
		
	}


}
