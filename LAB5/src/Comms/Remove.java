package Comms;
import GivenClasses.*;

public class Remove implements Commands{
	/** 
	 *Removes element by id
	 *@param DAO<Worker> dao, long state
	 *@author BARIS 
	*/
	public void remove_by_id(DAO<Worker> dao, long id) {
			Worker w = dao.get(id);
			if(w == null) {
				System.out.println("There's no guy with such id");
			}
			else {
				dao.delete(w);
			}
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
