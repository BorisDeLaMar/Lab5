package Comms;
import Exceptions.LimitException;
import java.io.*;
//import Exceptions.NullException;
import GivenClasses.*;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class AddIfMin implements Commands{
	/** 
	 *Adds element if it's minimum of all the elements
	 *@param DAO<Worker> dao, String[] args
	 *@author BARIS  
	*/
	public void add_if_min(DAO<Worker> dao, BufferedReader on) throws LimitException, IOException{
		Worker w = new Worker();
		Add dd = new Add();
		dd.add_read(w, on);
		LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
		boolean f = true;
		for(Worker e : bd) {
			if(e.hashCode() < w.hashCode()) {
				f = false;
			}
		}
		if(f) {
			w.setCreationDate();
			w.setID(Worker.findPossibleID());
			dao.appendToList(w);
			System.out.println("Worker successfully added");
		}
		else {
			System.out.println("Worker is not min, so he wasn't added");
		}
	}
	public void add_if_min_exec(DAO<Worker> dao, BufferedReader on) throws LimitException, IOException{
		Worker w = new Worker();
		Add dd = new Add();
		dd.add_read_exec(w, on);
		LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
		boolean f = true;
		for(Worker e : bd) {
			if(e.hashCode() < w.hashCode()) {
				f = false;
			}
		}
		if(f) {
			w.setCreationDate();
			w.setID(Worker.findPossibleID());
			dao.appendToList(w);
		}
	}
	@Override
	public String getGist() {
		return "adds element to collection, if it's the lowest one";
	}
	@Override
	public String getName() {
		return "add_if_min";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		AddIfMin aim = new AddIfMin();
		q = History.cut(q);
		q.addLast(aim);
		try {
			if(GistStaff.getFlag()) {
				aim.add_if_min_exec(dao, on);
			}
			else {
				aim.add_if_min(dao, on);
			}
		}
		catch(LimitException e) {
			System.out.println(e.getMessage());
		}
		return q; //Проверить history. Если не робит, попробуй в try возвращать q, а здесь null
	}
}
