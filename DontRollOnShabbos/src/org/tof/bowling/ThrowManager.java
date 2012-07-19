package org.tof.bowling;

import java.util.ArrayList;
import java.util.List;

public class ThrowManager {
	
	private List<Integer> allThrowsValue = new ArrayList<Integer>();
	
	public Integer getNextThrowsValue(int currentIdx,int nbThrows)
	{
		if (nbThrows<=0 || currentIdx<0 || allThrowsValue.size()<=currentIdx+nbThrows)
			return null;
		
		int value=0;
		for (int i = 1;i<=nbThrows;i++)
		{
			value+=allThrowsValue.get(currentIdx+i);
		}		
		return value;
	}
	
	public void addThrowValue(int throwValue)
	{
		this.allThrowsValue.add(throwValue);
	}
	
	public Integer getThrowValue(int throwIdx)
	{
		if (throwIdx<0 || allThrowsValue.size()<=throwIdx)
			return null;
		return allThrowsValue.get(throwIdx);
	}
	
	public Integer getLastIdx()
	{
		return allThrowsValue.size()>0?allThrowsValue.size()-1:null;
	}
	

}
