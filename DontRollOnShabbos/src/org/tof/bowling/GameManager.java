package org.tof.bowling;


/*
 * TODO : 
 * - exception si somme des lancers dépasse 15
 * - gestion de la dernière frame
 * - test unitaires
 * - refactoring pour meilleure compréhension
 * - éventuellement singleton pour le ThrowManager
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
	
	public void lancer(int quilles)
	{
		if (quilles < 0 || quilles>15)
			throw new IllegalArgumentException("Le nombre de quilles doit être entre 0 et 15");
		
		Integer score = getScore(quilles);
		System.out.println("Score: "+ (score!=null?score:"?"));
		prepareNextThrow();
		
	}
	
	
	private void prepareNextThrow()
	{
		if (!isEndOfGame())
		{
			if (currentFrame.isFinished())
			{
				currentFrame=new Frame(currentFrame);
			}	
		}
			
	}
	

	protected Integer getScore(int throwValue)
	{
		currentFrame.addThrowValue(throwValue);
		return currentFrame.getScore();
		
	}


}
