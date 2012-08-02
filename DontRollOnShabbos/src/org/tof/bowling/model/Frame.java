package org.tof.bowling.model;

import org.tof.bowling.exception.WrongThrowValueException;

/**
 * Représente une frame d'une partie de Sobchak
 * 
 * Une frame garde une référence vers la frame précédente
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
	 * Constructeur par défaut. 
	 * La frame précédente est à null et une nouvelle instance d'un ThrowManager est crée.
	 * L'indice de la frame est 0
	 * 
	 */
	public Frame()
	{
		
	}
	
	/**
	 * Constructeur sur base d'une frame précédente.
	 * La référence vers le ThrowManager de la frame précédente est copiée.
	 * L'indice de la frame est l'indice de la frame précédente +1
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
	 * Renvoie true si la frame est clôturée 
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
	 * Renvoie true si un spare a été effectué dans cette frame
	 * @return
	 */
	protected boolean isSpare()
	{
		if (throwsCount>1 && frameScore==15)
			return true;
		return false;
	}
	
	/**
	 * Renvoie true si un strike a été effectué pour cette frame
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
	 * - on récupère le score de la frame précédente (->récursivité)
	 * - s'il existe: 
	 *		- s'il y a un spare pour la frame courante, 
	 *			- on tente de récupérer la valeur des 2 lancers suivants via le ThrowManager
	 *			- si elle existe, on retourne cette valeur + le score de la frame courante + le score de la frame précédente
	 *			- sinon, on retourne null
	 *  	- s'il y a un strike pour la frame courante,
	 *  		- on tente de récupérer la valeur des 3 lancers suivants via le ThrowManager
	 *  		- si elle existe, on retourne cette valeur + le score de la frame courante + le score de la frame précédente
	 *  		- sinon, on retourne null
	 *  	- sinon, on retourne le score de la frame courante + le score de la frame précédente
	 * - sinon, on retourne null
	 * 
	 * @return null si le score ne peut pas encore être calculé
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
	 * Retourne le nouveau score du jeu pour cette frame après prise en compte 
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
			throw new IllegalStateException("La frame est terminée");
		
		if (frameScore+throwValue>15 || throwValue<0)
			throw new WrongThrowValueException(0, 15-frameScore);
		
		frameScore +=throwValue;
		lastThrowIdx=throwManager.addThrowValue(throwValue);
		throwsCount++;

	}

}
