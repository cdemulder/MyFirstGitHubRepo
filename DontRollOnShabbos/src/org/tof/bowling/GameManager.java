package org.tof.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameManager {

	private static final int MAX_FRAMES = 5;
	private static final int MAX_THROWS_PER_FRAME = 3;
	private static final int MAX_THROWS_FOR_LAST_FRAME = 4;
	private static final int MAX_SKITTLES = 15;
	

	private Frame currentFrame = new Frame(null,0);
	private ThrowManager throwManager = new ThrowManager();
	private boolean finished=false;
	
	public boolean isFinished() {
		return finished;
	}

	public int getCurrentFrame() {
		return currentFrame.getFrameIdx();
	}

	public int getCurrentThrowInFrame() {
		return currentFrame.getNextThrowIdxInFrame();
	}
	
	public void lancer(int quilles)
	{
		if (quilles < 0 || quilles>MAX_SKITTLES)
			throw new IllegalArgumentException("Le nombre de quilles doit tre entre 0 et "+String.valueOf(MAX_SKITTLES));
		
		Integer score = getScore(quilles);
		System.out.println("Score: "+ (score!=null?score:"?"));
		next();
		
	}
	
	
	private void next()
	{
//		currentThrowIdxInFrame++;
//		if (currentFrameIdx>=MAX_FRAMES-1)
//		{
//			if (currentThrowIdxInFrame>=MAX_THROWS_FOR_LAST_FRAME)
//			{
//				currentThrowIdxInFrame=0;
//				currentFrameIdx++;
//			}
//		}
//		else
//		{
//			if (currentThrowIdxInFrame>=MAX_THROWS_PER_FRAME)
//			{
//				currentThrowIdxInFrame=0;
//				currentFrameIdx++;
//			}
//		}
			
	}
	

	Integer getScore(int quilles)
	{
		throwManager.addThrowValue(quilles);
		currentFrame.addThrowIdx(throwManager.getLastIdx());
		return currentFrame.getFrameScore(throwManager);
		
	}


}
