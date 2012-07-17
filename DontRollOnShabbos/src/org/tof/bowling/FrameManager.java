package org.tof.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * TODO : 
 * - division entre ScoreManager et FrameManager ?
 * - garder les scores de frame déja calculés dans la map ? -> éviter de recalculer inutilement
 * 
 * A chaque lancer, on vérifie que le score de la frame précédente est dans la map
 * 
 * Si oui, on additionne le nouveau lancer (sauf si spare ou strike)
 * Si pas, on calcul le score de la frame précédente
 * Comment savoir si la frame précédente était un strike ou un spare ?
 * 
 */
public class FrameManager {

	private static final int MAX_FRAMES = 5;
	private static final int MAX_THROWS_PER_FRAME = 3;
	private static final int MAX_THROWS_FOR_LAST_FRAME = 4;
	private static final int MAX_SKITTLES = 15;
	
	
	private int currentFrameIdx = 0;
	private int currentThrowIdxInFrame = 0;
	
	private int currentScore = 0;
	
	//private Map<Integer,Integer> savedFrameScores = new HashMap<Integer, Integer>();
	private List<Integer> skittlesPerThrow = new ArrayList<Integer>(); 
	
	public boolean isFinished() {
		return currentFrameIdx >= MAX_FRAMES;
	}

	public int getCurrentFrame() {
		return currentFrameIdx;
	}

	public int getCurrentThrowInFrame() {
		return currentThrowIdxInFrame;
	}
	
	public void lancer(int quilles)
	{
		if (quilles < 0 || quilles>MAX_SKITTLES)
			throw new IllegalArgumentException("Le nombre de quilles doit être entre 0 et "+String.valueOf(MAX_SKITTLES));
		skittlesPerThrow.add(quilles);
		Integer score = getNewScore();
		System.out.println("Score: "+ (score!=null?score:"?"));
		next();
		
	}
	
	private int getCurrentThrowIdx()
	{
		return skittlesPerThrow.size()-1;
	}
	
	private int getCurrentSkittles()
	{
		return skittlesPerThrow.get(getCurrentThrowIdx());
	}
	
	private boolean currentThrowIsStrike()
	{
		if (MAX_SKITTLES==getCurrentSkittles()
				&& currentThrowIdxInFrame==0)
			return true;
		return false;
			
	}
	
	private boolean currentThrowIsSpare()
	{
		return false;
	}
	
	private void next()
	{
		currentThrowIdxInFrame++;
		if (currentFrameIdx>=MAX_FRAMES-1)
		{
			if (currentThrowIdxInFrame>=MAX_THROWS_FOR_LAST_FRAME)
			{
				currentThrowIdxInFrame=0;
				currentFrameIdx++;
			}
		}
		else
		{
			if (currentThrowIdxInFrame>=MAX_THROWS_PER_FRAME)
			{
				currentThrowIdxInFrame=0;
				currentFrameIdx++;
			}
		}
			
	}
	

	Integer getNewScore()
	{
//		//Ajouter à la liste des quilles par lancer
//		
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
			
		return currentScore+=getCurrentSkittles();
		
		
	}


}
