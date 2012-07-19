package org.tof.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	private List<Integer> throwsIdx=new ArrayList<Integer>();
	private Frame previous=null;
	private int frameIdx=0;
	private ThrowManager throwManager=new ThrowManager();
	
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
		if (isSpare() || isStrike())
			return true;
		if (throwsIdx.size()==3)
			return true;
		return false;
	}
	
	private boolean isSpare()
	{
		if (throwsIdx.size()>1)
		{
			int frameScore = 0;
			for (Integer throwIdx : throwsIdx)
			{
				frameScore+=throwManager.getThrowValue(throwIdx);
			}
			if (frameScore==15)
				return true;
		}
		return false;
	}
	
	private boolean isStrike()
	{
		if (throwsIdx.size()==1)
		{
			if (throwManager.getThrowValue(throwsIdx.get(0))==15)
				return true;
		}
		return false;
	}
	
	public Integer getScore()
	{
		
		Integer score = null;
		Integer previousScore =this.previous!=null?this.previous.getScore():new Integer(0);
		
		if (isSpare())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(throwsIdx.get(throwsIdx.size()-1), 2);
			
			if (nextThrowsValue!=null && previousScore!=null)
				score=previousScore + 15 + nextThrowsValue;
		}
		else if (isStrike())
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(throwsIdx.get(throwsIdx.size()-1), 3);
			if (nextThrowsValue!=null && previousScore!=null)
				score=previousScore + 15 + nextThrowsValue;
		}
		else
		{
			if (previousScore!=null)
			{
				score=previousScore;
				for (Integer throwIdx : throwsIdx)
				{
					score+=throwManager.getThrowValue(throwIdx);
				}
			}
		}
		return score;
		
	}
	
	public void addThrowValue(int throwValue)
	{
		throwManager.addThrowValue(throwValue);
		throwsIdx.add(throwManager.getLastIdx());
	}

}
