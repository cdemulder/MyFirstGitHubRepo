package org.tof.bowling.model;

import org.tof.bowling.exception.WrongThrowValueException;


/**
 * Responsable de la gestion d'une partie de Sobchak
 * R�f�rence la frame courante
 * 
 * @author Tof
 *
 */
public class FrameManager {

	private Frame currentFrame = new Frame();
	
	/**
	 * Retourne true si la partie est termin�e 
	 * 
	 * @return
	 */
	public boolean isEndOfGame() {
		return currentFrame.getFrameIdx()==4 && currentFrame.isClosed();
	}

	/**
	 * Retourne le num�ro de la frame courante (pour affichage) 
	 * @return
	 */
	public int getCurrentFrameNb() {
		return currentFrame.getFrameIdx()+1;
	}

	/**
	 * Retourne le num�ro du lancer suivant (pour affichage)
	 * @return
	 */
	public int getCurrentThrowNb() {
		return currentFrame.getNextThrowIdxInFrame()+1;
	}
	
	/**
	 * Renvoie le r�sultat du lancer suivant pour ce joueur
	 * @param quilles donne le nombre de quilles abattues par ce lancer
	 * @return le score de la partie apr�s ce lancer. 
	 * 		   null si le score ne peut pas encore �tre calcul� (strike ou spare)
	 * @throws WrongThrowValueException si la valeur est en dehors des limites possibles
	 */
	public Integer lancer(int quilles) throws WrongThrowValueException
	{
		if (isEndOfGame())
			throw new IllegalStateException("Le jeu est termin�");
		
		Integer score = currentFrame.getNewScore(quilles);
		
		if (!isEndOfGame())
			prepareNextThrow();
		return score;
		
	}
	
	/*
	 * Pr�pare les frames pour le lancer suivant.
	 * Si la frame courante est cl�tur�e, on cr�e un nouvelle frame sur base de la frame courante
	 * 
	 */
	private void prepareNextThrow()
	{
		if (currentFrame.isClosed())
		{
			if (currentFrame.getFrameIdx()==3)
				currentFrame=new LastFrame(currentFrame);
			else
				currentFrame=new Frame(currentFrame);
			
		}
	}

}
