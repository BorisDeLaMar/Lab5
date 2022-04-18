package Comms;
import java.util.ArrayDeque;
import java.util.Scanner;

import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.*;
import java.io.*;

public class Update implements Commands{
	/** 
	 *Updates element by id and given args
	 *@param DAO<Worker> dao, long id, String[] args
	 *@author BARIS 
	*/
	public void update_by_id(DAO<Worker> dao, BufferedReader on) throws NullException, LimitException, IOException{
		Add dd = new Add();
		while (true){
			try {
				if(GistStaff.getFlag() == false) {
					System.out.print("Enter id: ");
				}
				long id = Long.valueOf(on.readLine().split(" ")[0]);
				Worker w = dao.get(id);
				if(w == null) {
					System.out.println("There's no guy with such id");
					continue;
				}
				dd.add_read(w, on);
				System.out.println("Worker " + w.getName() + " was successfully updated");
				break;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Id should be type long");
			}
		}
	}
	
	@Override 
	public String getGist() {
		return "updates element with new arguments";
	}
	@Override
	public String getName() {
		return "update";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		Update upd = new Update();
		q = History.cut(q);
		q.addLast(upd);
		//upd.update_by_id(dao);
		try {
			upd.update_by_id(dao, on);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Id should be type long");
		}
		catch(LimitException e) {
			System.out.println(e.getMessage());
		}
		catch(NullException e) {
			System.out.println(e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("There should be an index argument");
		}
		return q;
	}
}
