package Comms;
import GivenClasses.*;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class PrintUniqueStatus implements Commands{
	/** 
	 *Prints status of each element
	 *@param DAO<Worker> dao
	 *@author BARIS 
	*/
	
	public String print_unique_status(DAO<Worker> dao) {
		LinkedHashSet<Worker> bd = dao.getAll();
		String list = "";
		for(Worker w : bd) {
			String person = "";
			person = w.toString();
			//person = "\n" + w.getName() + ": " + w.getStatus().toString();
			list += person;
		}
		return list;
	}
	
	@Override
	public String getGist() {
		return "prints status of each element from collection";
	}
	@Override
	public String getName() {
		return "print_unique_status";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		PrintUniqueStatus prntu = new PrintUniqueStatus();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(prntu);
		System.out.println(prntu.print_unique_status(dao));
		return q;
	}
}
