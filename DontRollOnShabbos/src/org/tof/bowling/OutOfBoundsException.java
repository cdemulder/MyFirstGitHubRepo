package org.tof.bowling;

public class OutOfBoundsException extends Exception {

	private static final long serialVersionUID = -5990735170211839024L;
	private Integer minBound;
	private Integer maxBound;
	
	
	public OutOfBoundsException(Integer minBound, Integer maxBound)
	{
		super();
		this.minBound = minBound;
		this.maxBound = maxBound;
	}


	public Integer getMinBound() {
		return minBound;
	}


	public Integer getMaxBound() {
		return maxBound;
	}

}
