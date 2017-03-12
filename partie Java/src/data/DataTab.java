package data;

import java.util.Observable;

public class DataTab extends Observable
{

	private String label;
	private TimedValue[] data;
	
	
	public void addValue(TimedValue o)
	/* Sp�cifications : Ajouter une valeur � la data
	* pr�-conditions : �
	* post-conditions : � */
	{
            
            notifyObservers(o);
		
	}
	public void stop()
	/* Sp�cifications : Arreter la mise � jour de la DataTab
	* pr�-conditions : �
	* post-conditions : � */
	{
            notifyObservers(null);
	}
	
}
