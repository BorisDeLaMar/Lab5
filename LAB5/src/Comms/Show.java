package Comms;

import java.util.ArrayDeque;
import java.time.format.*;
import java.util.LinkedHashSet;

import GivenClasses.Worker;

public class Show implements Commands{
	/** 
	 *Prints collection
	 *@param DAO<Worker> d
	 *@author BARIS 
	*/
	private static DAO<Worker> dao;
	
	public String show(DAO<Worker> d) {
		dao = d;
		LinkedHashSet<Worker> bd = dao.getAll();
		String list = "";
		String person;
		for(Worker w : bd) {
			person = w.toString();
			//person = "Name: " + w.getName() + "\nSalary: " + w.getSalary() + "\nPosition: " + w.getPosition().toString() + "\nStatus: " + w.getStatus().toString() + "\nOrganization: " + w.getOrganization().getName() + ", " + w.getOrganization().getType().toString() + "\nCoordinates: " + w.getCoordinates().getAbscissa() + ", " + w.getCoordinates().getOrdinate() + "\n" + "ID: " + w.getId() + ",\n" + "creationDate: " + w.getCreationDate() + "\n";
			list += person;
		}
		return list;
	}
	
	@Override
	public String getName() {
		return "show";
	}
	@Override 
	public String getGist() {
		return "prints all the elements of collection";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		Show show = new Show();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(show);
		System.out.println(show.show(dao));
		return q;
	}
}
