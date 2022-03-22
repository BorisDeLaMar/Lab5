package Comms;
import GivenClasses.*;
import java.util.LinkedHashSet;

public class PrintUniqueStatus implements Commands{
	/** 
	 *Prints status of each element
	 *@param DAO<Worker> dao
	 *@author BARIS 
	*/
	
	public String print_unique_status(DAO<Worker> dao) {
		LinkedHashSet<Worker> bd = dao.getAll();
		String list = "";
		for(Worker w : bd) {
			String person = "";
			person = "\n" + w.getName() + ": " + w.getStatus().toString();
			list += person;
		}
		return list;
	}
	
	@Override
	public String getGist() {
		return "prints status of each element from collection";
	}
	@Override
	public String getName() {
		return "print_unique_status";
	}
}
