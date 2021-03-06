package Comms;
import GivenClasses.*;


import java.util.ArrayDeque;
import java.util.ArrayList;
//import java.util.LinkedHashSet;
import java.io.*;
public class RemoveLower implements Commands{
	Remove rm = new Remove();
	/** 
	 *Removes all the elements lower than given
	 *@param DAO<Worker> dao, long id
	 *@author BARIS 
	*/
	
	public void remove_lower(DAO<Worker> dao, long id) {
		Worker w = dao.get(id);
		try {
			ArrayList<Worker> dd = new ArrayList<Worker>(dao.getAll());
			for(int i = 0; i < dd.size(); i++) {
				if(dd.get(i).hashCode() < w.hashCode()) {
					if(GistStaff.getFlag() == false) {
						System.out.println("Worker successfully removed from collection");
					}
					rm.remove_by_id(dao, dd.get(i).getId());
				}
			}
		}
		catch(NullPointerException e) {
			System.out.println("There's no guy with such id");
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
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		RemoveLower rmvl = new RemoveLower();
		q = History.cut(q);
		q.addLast(rmvl);
		if(GistStaff.getFlag() == false) {
			System.out.print("Enter id: ");
		}
		try {
			long id = Long.valueOf(on.readLine().split(" ")[0]);
			rmvl.remove_lower(dao, id);
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
