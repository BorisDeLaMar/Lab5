package Comms;

public class Help extends AbstractHelp implements Commands{
	/** 
	 *Prints available commands
	 *@author BARIS 
	*/
	
	public String help() {
		Help.addToList();
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
}
