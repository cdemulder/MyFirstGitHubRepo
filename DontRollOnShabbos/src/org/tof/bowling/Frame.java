package org.tof.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	private List<Integer> throwsIdx=new ArrayList<Integer>();
	private Frame previous=null;
	private int frameIdx=0;
	private ThrowManager throwManager=new ThrowManager();
	private int frameScore = 0;
	
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
		return throwsIdx.size();
				
	}
	
	public int getFrameIdx()
	{
		return frameIdx;
	}
	
	public boolean isFinished()
	{
		if (throwsIdx.size()==3 || frameScore==15)
			return true;
		return false;
	}
	
	private boolean isSpare()
	{
		if (throwsIdx.size()>1 && frameScore==15)
			return true;
		return false;
	}
	
	private boolean isStrike()
	{
		if (throwsIdx.size()==1 && frameScore==15)
			return true;
		return false;
	}
	
	protected Integer getScore()
	{
		
		Integer score = null;
		Integer previousScore =this.previous!=null?this.previous.getScore():new Integer(0);
		
		if (isSpare())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(throwsIdx.get(throwsIdx.size()-1), 2);
			
			if (nextThrowsValue!=null && previousScore!=null)
				score=previousScore + frameScore + nextThrowsValue;
		}
		else if (isStrike())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(throwsIdx.get(throwsIdx.size()-1), 3);
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
		if (frameScore+throwValue>15)
			throw new OutOfBoundsException(0, 15-frameScore);
		
		frameScore +=throwValue;
		throwManager.addThrowValue(throwValue);
		throwsIdx.add(throwManager.getLastIdx());
	}

}
