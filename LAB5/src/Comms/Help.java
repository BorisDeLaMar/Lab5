package Comms;
import java.util.ArrayDeque;
import java.util.ArrayList;

import GivenClasses.Worker;

public class Help extends AbstractHelp implements Commands{
	/** 
	 *Prints available commands
	 *@author BARIS 
	*/
	public String help(ArrayList<Commands> lst) {
		String list = "";
		for(Commands cm : lst) {
			String person = "";
			person = cm.getName() + ": " + cm.getGist() + "\n";
			list += person;
		}
		return list; //добавить команд в lst
	}
	
	@Override
	public String getGist() {
		return "shows the information about available commands";
	}
	@Override
	public String getName() {
		return "help";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		Help hlp = new Help();
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(hlp);
		System.out.println(hlp.help(Help.getLst()));
		return q;
	}
	
	public static ArrayList<Commands> getLst() {
		return lst;
	}
	public static void fillLst() {
		Help.addToList();
	}
}
