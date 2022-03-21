package Comms;
import java.util.ArrayDeque;

public class History implements Commands{
	private ArrayDeque<Commands> q;
	public String history(ArrayDeque<Commands> q) {
		this.q = q;
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
}
