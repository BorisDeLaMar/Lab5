package Comms;
//import GivenClasses.*;

public class Exit implements Commands{
	private boolean flag = false;
	/** 
	 *Shuts down the programm
	 *@param flag
	 *@author BARIS  
	*/
	public boolean exit() {
		return flag;
	}
	
	@Override
	public String getGist() {
		return "shuts the programm down in a very rude way";
	}
	public String getName() {
		return "exit";
	}
}
