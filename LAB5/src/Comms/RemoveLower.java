package Comms;
import GivenClasses.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class RemoveLower implements Commands{
	Remove rm = new Remove();
	
	public void remove_lower(DAO<Worker> dao, long id) {
		Worker w = dao.get(id);
		ArrayList<Worker> dd = new ArrayList(dao.getAll());
		for(int i = 0; i < dd.size(); i++) {
			if(dd.get(i).hashCode() < w.hashCode()) {
					rm.remove_by_id(dao, dd.get(i).getId());
			}
		}
	}
	
	@Override
	public String getGist() {
		return "deletes every element lower than given from collection";
	}
	@Override
	public String getName() {
		return "remove_lower";
	}
}
