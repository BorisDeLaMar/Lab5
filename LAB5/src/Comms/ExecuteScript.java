package Comms;
import java.io.BufferedReader;
import java.util.Stack;


import GivenClasses.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import Exceptions.LimitException;
import Exceptions.NullException;

import java.io.FileNotFoundException;

public class ExecuteScript implements Commands{
	protected static ArrayDeque<Commands> q;
	private static Stack<String> file_bd = new Stack<String>();
	//private Stack<String> bin = new Stack<String>();
	@SuppressWarnings("finally")
	public static ArrayDeque<Commands> execute_script(DAO<Worker> dao, String filename, ArrayDeque<Commands> qu){
		q = qu;
		//ArrayDeque<Commands> q = new ArrayDeque<Commands>();
		//Exit exe = new Exit();
		ArrayList<Commands> cmd = Help.getLst();
		/** 
		 *Executes script from file
		 *@param All the available commands
		 *@author BARIS  
		*/
		try(BufferedReader on = new BufferedReader(new FileReader(filename))){
			boolean f = true;
			for(String s : file_bd) {
				//System.out.println(file_bd.toString());
				if(filename.equals(s)) {
					//String gg = file_bd.pop();
					f = false;
					System.out.println("File with name " + filename + " was already processed. I've killed it by myself!");
					//on.close();
				}
			}
			if(f == true) {
				file_bd.push(filename);
				while(Exit.getExit()) {
					//in.hasNext()
					String[] line = on.readLine().split(" ");
					String command = line[0];
					//System.out.println(cmd.size());
					//try-ем надо оборачивать сам while?
					int flag = 0;
					for(int i = 0; i < cmd.size(); i++) {
						Commands cm = cmd.get(i);
						if(cm.getName().equals(command)) {
							flag += 1;
							q = cm.executeCommand(dao, q, line);
						}
					}
					if(flag == 0) {
						System.out.println("Unknown command. Type \"help\" for the list of available commands");
					}
				}
			}
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("There's no file with such name." + filename + " does not exsist. Watch out!");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally{
			return q;
		}
	}
	
	
	@Override
	public String getGist() {
		return "executes and complete the script from given file";
	}
	@Override
	public String getName() {
		return "execute_script";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line) {
		if(line.length == 1) {
			System.out.println("You need to write the file path. With one \"space\" btw");
		}
		else {
			ExecuteScript exec = new ExecuteScript();
			if(q != null && q.size() == 7) {
				q.removeFirst();
			}
			q.addLast(exec);
			q = ExecuteScript.execute_script(dao, line[1], q); //execute_script C:\vpd\PudgePudgePudgePudge.txt execute_script C:\vpd\PudgePudgePudgePudge.txt
		}
		return q;
	}
	public static void file_bdCleaner() {
		file_bd.clear();
	}
}
