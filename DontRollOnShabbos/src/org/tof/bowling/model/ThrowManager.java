package org.tof.bowling.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsable de la gestion des lancers
 * Toutes les valeurs des lancers sont enregistrées dans une liste.
 * 
 * @author Tof
 *
 */
public class ThrowManager {
	
	private List<Integer> allThrowsValue = new ArrayList<Integer>();
	
	/**
	 * Renvoie l'addition des valeurs du tableau des nbThrows suivants par rapport à currentIdx
	 * Exemple : si le contenu de la liste est {1,2,6,15}, que currentIdx==1 et que nbThrows == 2
	 * La valeur de retour est : 21 (6+15) 
	 * 
	 * @param currentIdx
	 * @param nbThrows
	 * @return null si la taille de la liste est <= à currentIdx+nbThrows
	 */
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
	
	/** 
	 * Ajoute une valeur à la liste des lancers et retourne son indice
	 * 
	 * @param throwValue
	 */
	public int addThrowValue(int throwValue)
	{
		this.allThrowsValue.add(throwValue);
		return allThrowsValue.size()-1;
	}
	
	/**
	 * Renvoie la valeur d'un lancer pour un indice donné
	 * @param throwIdx
	 * @return null si la taille de la liste est <= à l'indice donné
	 */
	public Integer getThrowValue(int throwIdx)
	{
		if (throwIdx<0 || allThrowsValue.size()<=throwIdx)
			return null;
		return allThrowsValue.get(throwIdx);
	}

}
