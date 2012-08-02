package org.tof.bowling.model;

import org.tof.bowling.exception.WrongThrowValueException;


/**
 * Responsable de la gestion d'une partie de Sobchak
 * Référence la frame courante
 * 
 * @author Tof
 *
 */
public class FrameManager {

	private Frame currentFrame = new Frame();
	
	/**
	 * Retourne true si la partie est terminée 
	 * 
	 * @return
	 */
	public boolean isEndOfGame() {
		return currentFrame.getFrameIdx()==4 && currentFrame.isClosed();
	}

	/**
	 * Retourne le numéro de la frame courante (pour affichage) 
	 * @return
	 */
	public int getCurrentFrameNb() {
		return currentFrame.getFrameIdx()+1;
	}

	/**
	 * Retourne le numéro du lancer suivant (pour affichage)
	 * @return
	 */
	public int getCurrentThrowNb() {
		return currentFrame.getNextThrowIdxInFrame()+1;
	}
	
	/**
	 * Renvoie le résultat du lancer suivant pour ce joueur
	 * @param quilles donne le nombre de quilles abattues par ce lancer
	 * @return le score de la partie après ce lancer. 
	 * 		   null si le score ne peut pas encore être calculé (strike ou spare)
	 * @throws WrongThrowValueException si la valeur est en dehors des limites possibles
	 */
	public Integer lancer(int quilles) throws WrongThrowValueException
	{
		if (isEndOfGame())
			throw new IllegalStateException("Le jeu est terminé");
		
		Integer score = currentFrame.getNewScore(quilles);
		
		if (!isEndOfGame())
			prepareNextThrow();
		return score;
		
	}
	
	/*
	 * Prépare les frames pour le lancer suivant.
	 * Si la frame courante est clôturée, on crée un nouvelle frame sur base de la frame courante
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
