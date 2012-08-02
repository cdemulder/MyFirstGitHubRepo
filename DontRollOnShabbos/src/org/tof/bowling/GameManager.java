package org.tof.bowling;


/*
 * TODO : 
 * - test unitaires
 * - refactoring pour meilleure compréhension
 * - javadoc + commentaires
 * - singleton pour ThrowManager ?
 * - int ou Integer ?
 */
public class GameManager {

	private Frame currentFrame = new Frame();
	
	public boolean isEndOfGame() {
		return currentFrame.getFrameIdx()==4 && currentFrame.isFinished();
	}

	public int getCurrentFrameNb() {
		return currentFrame.getFrameIdx()+1;
	}

	public int getCurrentThrowNb() {
		return currentFrame.getNextThrowIdxInFrame()+1;
	}
	
	public void lancer(int quilles) throws OutOfBoundsException
	{
		Integer score = currentFrame.getNewScore(quilles);
		System.out.println("Score: "+ (score!=null?score:"?"));
		if (!isEndOfGame())
			prepareNextThrow();
		
	}
	
	private void prepareNextThrow()
	{
		if (currentFrame.isFinished())
		{
			if (currentFrame.getFrameIdx()==3)
				currentFrame=new LastFrame(currentFrame);
			else
				currentFrame=new Frame(currentFrame);
			
		}
	}

}
