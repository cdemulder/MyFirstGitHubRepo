package org.tof.bowling.model;

import org.tof.bowling.exception.WrongThrowValueException;

/**
 * Implémentation particulière pour la dernière frame :
 * Lancers additionnels possibles pour complèter un strike ou un spare
 * 
 * @author Tof
 *
 */
public class LastFrame extends Frame {

	private int additionalThrowsCount=0; //le nombre de lancers supplémentaires suite à un spare ou un strike
	
	public LastFrame(Frame previous) {
		super(previous);
	}

	@Override
	public boolean isClosed() {
		if (throwsCount==3 && frameScore<15)
			return true;
		
		if (isStrike() && additionalThrowsCount==3)
			return true;
		
		if (isSpare() && additionalThrowsCount==2)
			return true;
		return false;
	}

	@Override
	public int getNextThrowIdxInFrame() {
		if (isStrike() || isSpare())
			return throwsCount+additionalThrowsCount;
		else
			return throwsCount;
	}

	@Override
	protected void addThrowValue(int throwValue) throws WrongThrowValueException {
		
		if (isClosed())
			throw new IllegalStateException("La frame est terminée");
		
		if (isStrike() || isSpare())
		{
			if (throwValue>15 || throwValue<0)
				throw new WrongThrowValueException(0, 15);
			/*
			 * ici, ce n'est pas nécessaire de mettre à jour ni la valeur du lastThrowIdx, ni le frameScore
			 * Cela permet de garder la fonctionnement de getScore() 
			 * Les 2 ou 3 lancers additionnels ne sont utiles que dans le ThrowManager (puisque découle d'un spare ou d'un strike)
			 */
			throwManager.addThrowValue(throwValue); 
			additionalThrowsCount++;
		}
		else
		{
			if (frameScore+throwValue>15 || throwValue<0)
				throw new WrongThrowValueException(0, 15-frameScore);
			
			frameScore +=throwValue;
			lastThrowIdx=throwManager.addThrowValue(throwValue);
			throwsCount++;
		}
	}
	
}
