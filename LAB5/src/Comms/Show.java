package Comms;

import java.util.LinkedHashSet;

import GivenClasses.Worker;

public class Show implements Commands{
	private static DAO<Worker> dao;
	
	public String show(DAO<Worker> d) {
		dao = d;
		LinkedHashSet<Worker> bd = dao.getAll();
		String list = "";
		String person;
		for(Worker w : bd) {
			person = "Name: " + w.getName() + "\nSalary: " + w.getSalary() + "\nPosition: " + w.getPosition().toString() + "\nStatus: " + w.getStatus().toString() + "\nOrganization: " + w.getOrganization().getName() + ", " + w.getOrganization().getType().toString() + "\nCoordinates: " + w.getCoordinates().getAbscissa() + ", " + w.getCoordinates().getOrdinate() + "\n";
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
}
