package Comms;
import GivenClasses.*;

public class Remove implements Commands{
	public void remove_by_id(DAO<Worker> dao, long id) {
			Worker w = dao.get(id);
			dao.delete(w);
	}
	
	@Override
	public String getGist() {
		return "deletes element";
	}
	@Override
	public String getName() {
		return "remove_by_id";
	}
}
