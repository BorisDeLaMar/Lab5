package Comms;
import GivenClasses.*;
//import java.util.LinkedHashSet;
import java.util.ArrayList;

public class Clear implements Commands{
	
	public void clear(DAO<Worker> d) {
		/** 
		 *Clear function
		 *@param DAO<Worker> dao
		 *@author BARIS  
		*/
		ArrayList<Worker> bd = new ArrayList(d.getAll());
		for(int i = 0; i < bd.size(); i++) {
			Worker w = bd.get(i);
			d.delete(w);
		}
	}
	
	@Override
	public String getGist() {
		return "clears collection";
	}
	@Override
	public String getName() {
		return "clear";
	}
}
