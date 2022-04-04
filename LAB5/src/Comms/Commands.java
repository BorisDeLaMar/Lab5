package Comms;
import java.util.ArrayDeque;
import GivenClasses.*;

public interface Commands {
	/** 
	 *Interface which connects functions
	 *@author AP  
	*/
	String getName();
	String getGist();
	ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] args);
}
