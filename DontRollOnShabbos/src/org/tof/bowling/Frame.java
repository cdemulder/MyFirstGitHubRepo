package org.tof.bowling;


public class Frame {
	
	protected int lastThrowIdx=-1;
	protected Frame previous=null;
	private int frameIdx=0;
	protected ThrowManager throwManager=new ThrowManager();
	protected int frameScore = 0;
	protected int throwsCount = 0;
	
	public Frame()
	{
		
	}
	
	public Frame(Frame previous)
	{
		this.previous = previous;
		this.frameIdx = previous.frameIdx+1;
		this.throwManager=previous.throwManager;
	}
	
	
	public int getNextThrowIdxInFrame()
	{
		return throwsCount;
				
	}
	
	public int getFrameIdx()
	{
		return frameIdx;
	}
	
	public boolean isFinished()
	{
		if (throwsCount==3 || frameScore==15)
			return true;
		return false;
	}
	
	protected boolean isSpare()
	{
		if (throwsCount>1 && frameScore==15)
			return true;
		return false;
	}
	
	protected boolean isStrike()
	{
		if (throwsCount==1 && frameScore==15)
			return true;
		return false;
	}
	
	protected Integer getScore()
	{
		
		Integer score = null;
		Integer previousScore =this.previous!=null?this.previous.getScore():new Integer(0);
		
		if (isSpare())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(lastThrowIdx, 2);
			
			if (nextThrowsValue!=null && previousScore!=null)
				score=previousScore + frameScore + nextThrowsValue;
		}
		else if (isStrike())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(lastThrowIdx, 3);
			if (nextThrowsValue!=null && previousScore!=null)
				score=previousScore + frameScore + nextThrowsValue;
		}
		else
		{
			if (previousScore!=null)
			{
				score=previousScore+frameScore;
			}
		}
		return score;
		
	}
	
	public Integer getNewScore(int throwValue) throws OutOfBoundsException
	{
		addThrowValue(throwValue);
		return getScore();
	}
	
	
	protected void addThrowValue(int throwValue) throws OutOfBoundsException
	{
		if (isFinished())
			throw new IllegalStateException("La frame est terminŽe");
		
		if (frameScore+throwValue>15 || throwValue<0)
			throw new OutOfBoundsException(0, 15-frameScore);
		
		frameScore +=throwValue;
		throwManager.addThrowValue(throwValue);
		throwsCount++;
		lastThrowIdx=throwManager.getLastIdx();
	}

}
