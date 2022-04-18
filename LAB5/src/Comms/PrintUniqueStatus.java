package Comms;
import GivenClasses.*;
import java.io.BufferedReader;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class PrintUniqueStatus implements Commands{
	/** 
	 *Prints status of each element
	 *@param DAO<Worker> dao
	 *@author BARIS 
	*/
	
	public String print_unique_status(DAO<Worker> dao) {
		LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
		String list = "";
		for(Worker w : bd) {
			String person = "";
			//person = w.toString();
			person = w.getName() + ": " + w.getStatus().toString() + "\n";
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
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on){
		PrintUniqueStatus prntu = new PrintUniqueStatus();
		q = History.cut(q);
		q.addLast(prntu);
		System.out.println(prntu.print_unique_status(dao));
		return q;
	}
}
