package Comms;
import GivenClasses.*;
import java.util.*;

public class Sort{
	/** 
	 *Sorts the collection
	 *@param DAO<Worker> dao
	 *@author AP
	*/
	
	public TreeSet<Worker> sort(DAO<Worker> dao){
		LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
		TreeSet<Worker> srt = new TreeSet<Worker>();
		for(Worker w : bd) {
			srt.add(w);
		}
		return srt;
	}
}
