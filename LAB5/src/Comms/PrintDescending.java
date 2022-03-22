package Comms;
import java.util.*;
import GivenClasses.*;

public class PrintDescending implements Commands{
	/** 
	 *Prints elements in descending order
	 *@param DAO<Worker> dao
	 *@author BARIS 
	*/
	
	public String print_descending(DAO<Worker> dao) {
		Sort srt = new Sort();
		LinkedHashSet<Worker> bd = dao.getAll();
		bd = new LinkedHashSet<Worker>(srt.sort(dao));
		String list = "";
		ArrayList<Worker> sorted = new ArrayList<Worker>(bd);
		for(int i = bd.size() - 1; i >= 0; i--) {
			String person = "";
			Worker w = sorted.get(i);
			person = "\nName: " + w.getName() + "\nSalary: " + w.getSalary() + "\nPosition: " + w.getPosition().toString() + "\nStatus: " + w.getStatus().toString() + "\nOrganization: " + w.getOrganization().getName() + ", " + w.getOrganization().getType().toString() + "\nCoordinates: " + w.getCoordinates().getAbscissa() + ", " + w.getCoordinates().getOrdinate();
			list += person;
		}
		return list;
	}
	
	@Override
	public String getGist() {
		return "prints all the elements in dedcending order";
	}
	@Override
	public String getName() {
		return "print_descending";
	}
}
