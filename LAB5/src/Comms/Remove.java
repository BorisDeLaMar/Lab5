package Comms;
import java.util.ArrayDeque;

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
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		Remove rmv = new Remove();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(rmv);
		try {
			long id = Long.valueOf(line[1]);
			rmv.remove_by_id(dao, id);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Id should be type long");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("There should be an index argument");
		}
		return q;
	}
}
