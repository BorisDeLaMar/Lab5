package Comms;
import GivenClasses.*;
import java.io.*;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class FilterStatus implements Commands{
	/** 
	 *Prints every elements which status is lower than given
	 *@param DAO<Worker> dao, Status state
	 *@author BARIS 
	*/
	public String filter_less_than_status(DAO<Worker> dao, BufferedReader on) throws IOException{
		Status state = null;
		while(true) {
			if(GistStaff.getFlag() == false) {
				System.out.print("Enter status: ");
			}
			try {
				state = Status.valueOf(on.readLine().split(" ")[0]);
				//scam.nextLine();
				break;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Available status values:" + Status.strConvert());
			}
		}
		LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
		String list = "";
		for(Worker w : bd) {
			String person = "";
			if(state.isBetter(w.getStatus())) {
				person = w.toString();
				//person = "\nName: " + w.getName() + "\nSalary: " + w.getSalary() + "\nPosition: " + w.getPosition().toString() + "\nStatus: " + w.getStatus().toString() + "\nOrganization: " + w.getOrganization().getName() + ", " + w.getOrganization().getType().toString() + "\nCoordinates: " + w.getCoordinates().getAbscissa() + ", " + w.getCoordinates().getOrdinate();;
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
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		FilterStatus st = new FilterStatus();
		q = History.cut(q);
		q.addLast(st);
		System.out.println(st.filter_less_than_status(dao, on));
		return q;
	}
}
