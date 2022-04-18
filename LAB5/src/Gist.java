//import java.util.Scanner;
import java.util.ArrayList;
//import Exceptions.*;
import Comms.*;
import GivenClasses.*;
import java.util.ArrayDeque;
import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.nio.file.*;
//import java.nio.file.attribute.BasicFileAttributes; для времени инициализации, убери потом отседоваЧ
//import java.util.LinkedHashSet;

public class Gist extends GistStaff{
	public static void main(String[] args) {
		Worker.bannedID.clear();
		Help.fillLst();
		Long c = (long) 0;
		Worker.bannedID.add(0, c);
		/** 
		 *All the main functions
		 *@author BARIS  
		*/
		String filepath = System.getenv("FPATH");
		dao.DateRead(filepath);
		DataDAO.setFlag(true);
		
		Gist gs = new Gist();
		try {
			gs.read();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	//Функции мб
	/** 
	 *Reads function
	 *@param All the available commands
	 *@author AP  
	*/
	public void read() throws IOException{
		//read_helper(in);
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(in);
		
		ArrayDeque<Commands> q = new ArrayDeque<Commands>();
		ArrayList<Commands> cmd = Help.getLst();
		
		while(Exit.getExit()) {
			GistStaff.setFlag(false);
			ExecuteScript.file_bdCleaner();
			String[] line = bf.readLine().split(" ");
			String command = line[0];
			int flag = 0;
			for(int i = 0; i < cmd.size(); i++) {
				Commands cm = cmd.get(i);
				if(cm.getName().equals(command)) {
					flag += 1;
					try {
						q = cm.executeCommand(dao, q, bf);
					}
					catch(IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			if(flag == 0) {
				System.out.println("Unknown command. Type \"help\" for the list of available commands");
			}
		}
		bf.close();
	}
}
