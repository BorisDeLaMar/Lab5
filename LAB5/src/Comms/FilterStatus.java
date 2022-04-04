package Comms;
import GivenClasses.*;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class FilterStatus implements Commands{
	/** 
	 *Prints every elements which status is lower than given
	 *@param DAO<Worker> dao, Status state
	 *@author BARIS 
	*/
	
	public String filter_less_than_status(DAO<Worker> dao, Status state) {
		LinkedHashSet<Worker> bd = dao.getAll();
		String list = "";
		for(Worker w : bd) {
			String person = "";
			if(state.isBetter(w.getStatus())) {
				person = "\nName: " + w.getName() + "\nSalary: " + w.getSalary() + "\nPosition: " + w.getPosition().toString() + "\nStatus: " + w.getStatus().toString() + "\nOrganization: " + w.getOrganization().getName() + ", " + w.getOrganization().getType().toString() + "\nCoordinates: " + w.getCoordinates().getAbscissa() + ", " + w.getCoordinates().getOrdinate();;
			}
			list += person;
		}
		return list;
	}
	
	@Override
	public String getGist() {
		return "prints elements with status which is less than given";
	}
	@Override
	public String getName() {
		return "filter_less_than_status";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line) {
		FilterStatus st = new FilterStatus();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(st);
		try {
			Status state = Status.valueOf(line[1]);
			System.out.println(st.filter_less_than_status(dao, state));
		}
		catch(IllegalArgumentException e) {
			System.out.println("Available status values:" + Status.strConvert());
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("There should be an index argument");
		}
		return q; //надо в final пихать? И в др командах тоже
	}
}
