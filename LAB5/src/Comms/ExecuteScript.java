package Comms;
import java.io.BufferedReader;
import java.nio.file.Path;
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
	@SuppressWarnings("finally")
	public static ArrayDeque<Commands> execute_script(DAO<Worker> dao, ArrayDeque<Commands> qu, BufferedReader in) throws IOException{
		q = qu;
		String filename = "";
		if(GistStaff.getFlag() == false) {
			System.out.print("Enter file path: ");
		}
		filename = in.readLine();
		ArrayList<Commands> cmd = Help.getLst();
		GistStaff.setFlag(true);
		/** 
		 *Executes script from file
		 *@param All the available commands
		 *@author BARIS  
		*/
		try(BufferedReader on = new BufferedReader(new FileReader(filename))){
			boolean f = true;
			for(String s : file_bd) {
				if(filename.equals(s)) {
					f = false;
					System.out.println("File with name " + filename + " was already processed. I've killed it by myself!");
				}
			}
			if(f == true) {
				file_bd.push(filename);
				while(Exit.getExit()) {
					//in.hasNext()
					String[] line = on.readLine().split(" ");
					//String[] exec = new String[1];
					String command = line[0];
					//System.out.println(cmd.size());
					//try-ем надо оборачивать сам while?
					int flag = 0;
					for(int i = 0; i < cmd.size(); i++) {
						Commands cm = cmd.get(i);
						if(cm.getName().equals(command)) {
							flag += 1;
							q = cm.executeCommand(dao, q, on);
						}
					}
					if(flag == 0) {
						System.out.println("Unknown command. Type \"help\" for the list of available commands");
					}
				}
			}
			on.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("There's no file with such name, " + filename + " does not exsist. Watch out and try function again!");
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
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
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		ExecuteScript exec = new ExecuteScript();
		q = History.cut(q);
		q.addLast(exec);
		q = ExecuteScript.execute_script(dao, q, on); //execute_script C:\vpd\PudgePudgePudgePudge.txt execute_script C:\vpd\PudgePudgePudgePudge.txt
		return q;
	}
	public static void file_bdCleaner() {
		file_bd.clear();
	}
}
