package Comms;
import GivenClasses.*;
import java.util.LinkedHashSet;

public class FilterStatus implements Commands{
	
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
}
