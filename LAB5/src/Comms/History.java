package Comms;
import java.util.ArrayDeque;
import java.io.BufferedReader;

import GivenClasses.Worker;

public class History implements Commands{
	/** 
	 *Prints last seven commands
	 *@param DAO<Worker> dao, Status state
	 *@author BARIS 
	*/
	private ArrayDeque<Commands> q;
	public String history(ArrayDeque<Commands> q) {
		this.q = new ArrayDeque<Commands>(q);
		String list = ""; 
		while(this.q.peek() != null) {
			//System.out.println(this.q.pop().getName());
			Commands cmd = this.q.pop();
			list += cmd.getName() + "\n";
		}
		return list;
		
	}
	public static ArrayDeque<Commands> cut(ArrayDeque<Commands> q) {
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		return q;
	}
	
	@Override
	public String getGist() {
		return "prints last 7 commands";
	}
	@Override
	public String getName() {
		return "history";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on){
		History history = new History();
		System.out.println(history.history(q));
		q = History.cut(q);
		q.addLast(history);
		return q;
	}
}
