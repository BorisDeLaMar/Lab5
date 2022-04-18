package Comms;
//import GivenClasses.*;

import java.util.ArrayDeque;

import GivenClasses.Worker;
import java.io.BufferedReader;
public class Exit implements Commands{
	private static boolean flag = true;
	/** 
	 *Shuts down the programm
	 *@param flag
	 *@author BARIS  
	*/
	public static void exit() {
		if(GistStaff.getFlag() == false) {
			System.out.print("Bye-bye");
		}
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
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on){
		Exit exe = new Exit();
		q = History.cut(q);
		q.addLast(exe);
		Exit.exit();
		return q;
	}
}
