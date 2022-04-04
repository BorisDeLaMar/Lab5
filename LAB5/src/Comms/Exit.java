package Comms;
//import GivenClasses.*;

import java.util.ArrayDeque;

import GivenClasses.Worker;

public class Exit implements Commands{
	private static boolean flag = true;
	/** 
	 *Shuts down the programm
	 *@param flag
	 *@author BARIS  
	*/
	public static void exit() {
		flag = false;
	}
	public static boolean getExit() {
		return flag;
	}
	
	@Override
	public String getGist() {
		return "shuts the programm down in a very rude way";
	}
	@Override
	public String getName() {
		return "exit";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		Exit exe = new Exit();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(exe);
		Exit.exit();
		return q;
	}
}
