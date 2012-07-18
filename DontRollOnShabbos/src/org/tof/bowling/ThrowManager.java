package org.tof.bowling;

import java.util.ArrayList;
import java.util.List;

public class ThrowManager {
	
	private List<Integer> allThrowsValue = new ArrayList<Integer>();
	
	public Integer getNextThrowsValue(int currentIdx,int nbThrows)
	{
		return null;
	}
	
	public void addThrowValue(int throwValue)
	{
		this.allThrowsValue.add(throwValue);
	}
	
	public Integer getThrowValue(int throwIdx)
	{
		return null;
	}
	
	public Integer getLastIdx()
	{
		return allThrowsValue.size()>0?allThrowsValue.size()-1:null;
	}
	

}
