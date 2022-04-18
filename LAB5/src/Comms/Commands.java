package Comms;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.IOException;

//import java.util.Scanner;
import GivenClasses.*;

public interface Commands {
	/** 
	 *Interface which connects functions
	 *@author AP  
	*/
	String getName();
	String getGist();
	ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException;
	//ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, Scanner scn);
	//ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q);
}
