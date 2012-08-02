package org.tof.bowling.exception;

/**
 * Exception utilisée en cas de valeur imposible pour les lancers
 * 
 * @author Tof
 *
 */
public class WrongThrowValueException extends Exception {

	private static final long serialVersionUID = -5990735170211839024L;
	private Integer minBound;
	private Integer maxBound;
	
	/**
	 * Constructeur 
	 * 
	 * @param minBound : valeur minimale possible
	 * @param maxBound : valeur maximale possible
	 */
	public WrongThrowValueException(Integer minBound, Integer maxBound)
	{
		super();
		this.minBound = minBound;
		this.maxBound = maxBound;
	}


	/**
	 * Retourne la valeur minimale possible
	 * @return
	 */
	public Integer getMinBound() {
		return minBound;
	}

	/**
	 * Retourne la valeur maximale possible
	 * @return
	 */
	public Integer getMaxBound() {
		return maxBound;
	}

}
