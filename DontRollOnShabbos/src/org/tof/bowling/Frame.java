package org.tof.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	private List<Integer> throwsIdx=new ArrayList<Integer>();
	private Frame previous;
	private int frameIdx;
	
	public Frame(Frame previous, int frameIdx)
	{
		this.previous = previous;
		this.frameIdx = frameIdx;
	}
	
	public void addThrowIdx(int throwIdx)
	{
		this.throwsIdx.add(throwIdx);
	}
	
	public int getNextThrowIdxInFrame()
	{
		return this.throwsIdx.size();
				
	}
	
	public int getFrameIdx()
	{
		return this.frameIdx;
	}
	
	private boolean spare(ThrowManager throwManager)
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
	
	private boolean strike(ThrowManager throwManager)
	{
		if (throwsIdx.size()==1)
		{
			if (throwManager.getThrowValue(throwsIdx.get(0))==15)
				return true;
		}
		return false;
	}
	
	public Integer getFrameScore(ThrowManager throwManager)
	{
		
		Integer previousScore = this.previous.getFrameScore(throwManager);
		if (previousScore==null)
			return null;
		
		if (spare(throwManager))
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(frameIdx, 2);
			if (nextThrowsValue!=null)
				return previousScore + 15 + nextThrowsValue;
		}
		if (strike(throwManager))
		{
			Integer nextThrowsValue = throwManager.getNextThrowsValue(frameIdx, 3);
			if (nextThrowsValue!=null)
				return previousScore + 15 + nextThrowsValue;
		}
		
		int currentScore = previousScore;
		for (Integer throwIdx : throwsIdx)
		{
			currentScore+=throwManager.getThrowValue(throwIdx);
		}
		return currentScore;
		
	}

}
