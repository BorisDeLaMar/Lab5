package Comms;
import java.util.ArrayDeque;

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
	
	@Override
	public String getGist() {
		return "prints last 7 commands";
	}
	@Override
	public String getName() {
		return "history";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line){
		History history = new History();
		System.out.println(history.history(q));
		if(q != null && q.size() == 7) {
			q.removeFirst();
		}
		q.addLast(history);
		return q;
	}
}
