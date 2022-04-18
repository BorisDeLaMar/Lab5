package Comms;
import GivenClasses.*;
import java.io.*;

import java.util.ArrayDeque;
//import java.util.LinkedHashSet;
import java.util.ArrayList;

public class Clear implements Commands{
	
	public void clear(DAO<Worker> d) {
		/** 
		 *Clear function
		 *@param DAO<Worker> dao
		 *@author BARIS  
		*/
		ArrayList<Worker> bd = new ArrayList(d.getAll());
		for(int i = 0; i < bd.size(); i++) {
			Worker w = bd.get(i);
			d.delete(w);
		}
	}
	
	@Override
	public String getGist() {
		return "clears collection";
	}
	@Override
	public String getName() {
		return "clear";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) {
		Clear cl = new Clear();
		q = History.cut(q);
		q.addLast(cl);
		cl.clear(dao);
		return q;
	}
}
