package org.tof.bowling.model;

import org.tof.bowling.exception.WrongThrowValueException;

/**
 * Repr�sente une frame d'une partie de Sobchak
 * 
 * Une frame garde une r�f�rence vers la frame pr�c�dente
 * et vers un ThrowManager
 * 
 * @author Tof
 *
 */
public class Frame {
	
	private int frameIdx=0;
	protected Frame previous=null;
	protected ThrowManager throwManager=new ThrowManager();
	
	protected int frameScore = 0; //le score courant de la frame
	protected int throwsCount = 0; //le nombre courant de lancers pour cette frame
	protected int lastThrowIdx=-1; //l'indice du dernier lancer dans le throwManager
	
	/**
	 * Constructeur par d�faut. 
	 * La frame pr�c�dente est � null et une nouvelle instance d'un ThrowManager est cr�e.
	 * L'indice de la frame est 0
	 * 
	 */
	public Frame()
	{
		
	}
	
	/**
	 * Constructeur sur base d'une frame pr�c�dente.
	 * La r�f�rence vers le ThrowManager de la frame pr�c�dente est copi�e.
	 * L'indice de la frame est l'indice de la frame pr�c�dente +1
	 * 
	 * @param previous
	 */
	public Frame(Frame previous)
	{
		this.previous = previous;
		this.frameIdx = previous.frameIdx+1;
		this.throwManager=previous.throwManager;
	}
	
	/**
	 * Retourne l'indice du lancer suivant pour cette frame
	 * @return
	 */
	public int getNextThrowIdxInFrame()
	{
		return throwsCount;
				
	}
	
	/**
	 * Retourne l'indice de la frame
	 * @return
	 */
	public int getFrameIdx()
	{
		return frameIdx;
	}
	
	/**
	 * Renvoie true si la frame est cl�tur�e 
	 * (s'il n'y a plus de lancers possibles pour cette frame)
	 * @return
	 */
	public boolean isClosed()
	{
		if (throwsCount==3 || frameScore==15)
			return true;
		return false;
	}
	/**
	 * Renvoie true si un spare a �t� effectu� dans cette frame
	 * @return
	 */
	protected boolean isSpare()
	{
		if (throwsCount>1 && frameScore==15)
			return true;
		return false;
	}
	
	/**
	 * Renvoie true si un strike a �t� effectu� pour cette frame
	 * @return
	 */
	protected boolean isStrike()
	{
		if (throwsCount==1 && frameScore==15)
			return true;
		return false;
	}
	
	/**
	 * Retourne le score actuel du jeu pour cette frame
	 * 
	 * Pour calculer le score actuel du jeu pour cette frame, 
	 * - on r�cup�re le score de la frame pr�c�dente (->r�cursivit�)
	 * - s'il existe: 
	 *		- s'il y a un spare pour la frame courante, 
	 *			- on tente de r�cup�rer la valeur des 2 lancers suivants via le ThrowManager
	 *			- si elle existe, on retourne cette valeur + le score de la frame courante + le score de la frame pr�c�dente
	 *			- sinon, on retourne null
	 *  	- s'il y a un strike pour la frame courante,
	 *  		- on tente de r�cup�rer la valeur des 3 lancers suivants via le ThrowManager
	 *  		- si elle existe, on retourne cette valeur + le score de la frame courante + le score de la frame pr�c�dente
	 *  		- sinon, on retourne null
	 *  	- sinon, on retourne le score de la frame courante + le score de la frame pr�c�dente
	 * - sinon, on retourne null
	 * 
	 * @return null si le score ne peut pas encore �tre calcul�
	 */
	protected Integer getScore()
	{
		
		Integer score = null;
		Integer previousScore =this.previous!=null?this.previous.getScore():new Integer(0);
		
		if (previousScore!=null)
		{
			if (isSpare())
			{
				Integer nextThrowsValue = throwManager.getNextThrowsValue(lastThrowIdx, 2);
			
				if (nextThrowsValue!=null)
					score=previousScore + frameScore + nextThrowsValue;
			}
			else if (isStrike())
			{
				Integer nextThrowsValue = throwManager.getNextThrowsValue(lastThrowIdx, 3);
				if (nextThrowsValue!=null)
					score=previousScore + frameScore + nextThrowsValue;
			}
			else
			{
				score=previousScore+frameScore;
			
			}
		}
		return score;
		
	}
	
	/**
	 * Retourne le nouveau score du jeu pour cette frame apr�s prise en compte 
	 * de la valeur du nouveau lancer
	 * 
	 * @param throwValue : valeur du lancer
	 * @return
	 * @throws WrongThrowValueException : en cas de valeur impossible pour le nouveau lancer
	 */
	public Integer getNewScore(int throwValue) throws WrongThrowValueException
	{
		addThrowValue(throwValue);
		return getScore();
	}
	
	/**
	 * Enregistre la valeur du nouveau lancer 
	 * 
	 * @param throwValue : valeur du lancer
	 * @throws WrongThrowValueException : en cas de valeur impossible pour le nouveau lancer
	 */
	protected void addThrowValue(int throwValue) throws WrongThrowValueException
	{
		if (isClosed())
			throw new IllegalStateException("La frame est termin�e");
		
		if (frameScore+throwValue>15 || throwValue<0)
			throw new WrongThrowValueException(0, 15-frameScore);
		
		frameScore +=throwValue;
		lastThrowIdx=throwManager.addThrowValue(throwValue);
		throwsCount++;

	}

}
