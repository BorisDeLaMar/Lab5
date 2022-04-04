import java.util.Scanner;
import java.util.ArrayList;
import Exceptions.*;
import Comms.*;
import GivenClasses.*;
import java.util.ArrayDeque;
//import java.nio.file.Files;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.nio.file.*;
//import java.nio.file.attribute.BasicFileAttributes; для времени инициализации, убери потом отседоваЧ
//import java.util.LinkedHashSet;

public class Gist extends GistStaff{
	private static DAO<Worker> dao = new DataDAO(); //Не забыть ко всем выводам ошибок предложить потом еще раз ввести знач
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
		dao.DateRead(filepath, dao);
		DataDAO.setFlag(true);
		//String filepath = "C:\\Рабочий стол\\GitLabs\\Lab5\\Data.json";
		
		Gist gs = new Gist();
		gs.read();
	}
	//Функции мб
	Scanner in = new Scanner(System.in);
	public void read() {
		//boolean flag = true;
		ArrayDeque<Commands> q = new ArrayDeque<Commands>();
		//Help hlp = new Help();
		//Exit exe = new Exit();
		ArrayList<Commands> cmd = Help.getLst();
		//System.out.println(cmd.toString());
		//Clear cl = new Clear();
		//Add dd = new Add();
		//AddIfMin aim = new AddIfMin();
		//FilterStatus st = new FilterStatus();
		//Help hlp = new Help();
		//Info inf = new Info();
		//PrintDescending prnt = new PrintDescending();
		//PrintUniqueStatus prntu = new PrintUniqueStatus();
		//Remove rmv = new Remove();
		//RemoveLower rmvl = new RemoveLower();
		//Save save = new Save();
		//Show show = new Show();
		//Update upd = new Update();
		//History history = new History();
		//ExecuteScript exec = new ExecuteScript();
		
		/** 
		 *Reads function
		 *@param All the available commands
		 *@author AP  
		*/
		//Scanner in = new Scanner(System.in);
		while(Exit.getExit()) {
			//in.hasNext()
			ExecuteScript.file_bdCleaner();
			String[] line = in.nextLine().split(" ");
			String command = line[0];
			//System.out.println(cmd.size());
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
			//System.out.println(command);
			/*
			if(command.equals("exit")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(exe);
				flag = exe.exit();
			}
			if(command.equals("clear")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(cl);
				cl.clear(dao);
			}
			else if(command.equals("add")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(dd);
				String[] args = new String[line.length-1];
				for(int i = 0; i < line.length-1; i++) {
					args[i] = line[i+1];
				}
				try {
					dd.add(dao, args);
				}
				catch(LimitException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(command.equals("add_if_min")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(aim);
				String[] args = new String[line.length-1];
				for(int i = 0; i < line.length-1; i++) {
					args[i] = line[i+1];
				}
				try {
					aim.add_if_min(dao, args);
				}
				catch(LimitException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(command.equals("filter_less_than_status")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(st);
				try {
					Status state = Status.valueOf(line[1]);
					System.out.println(st.filter_less_than_status(dao, state));
				}
				catch(IllegalArgumentException e) {
					System.out.println("Available status values:" + Status.strConvert());
				}
			}
			else if(command.equals("help")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(hlp);
				System.out.println(hlp.help());
			}
			else if(command.equals("info")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(inf);
				System.out.println(inf.info(dao));
			}
			else if(command.equals("print_descending")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(prnt);
				System.out.println(prnt.print_descending(dao));
			}
			else if(command.equals("print_unique_status")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(prntu);
				System.out.println(prntu.print_unique_status(dao));
			}
			else if(command.equals("remove_by_id")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(rmv);
				try {
					long id = Long.valueOf(line[1]);
					rmv.remove_by_id(dao, id);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Id should be type long");
				}
			}
			else if(command.equals("remove_lower")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(rmvl);
				try {
					long id = Long.valueOf(line[1]);
					rmvl.remove_lower(dao, id);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Id should be type long");
				}
			}
			else if(command.equals("save")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(save);
				String filepath = System.getenv("FPATH");
				save.save(dao, filepath);
			}
			else if(command.equals("show")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(show);
				System.out.println(show.show(dao));
			}
			else if(command.equals("update")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(upd);
				String[] args = new String[line.length-1];
				for(int i = 0; i < line.length-1; i++) {
					args[i] = line[i+1];
				}
				try {
					long id = Long.valueOf(args[0]);
					String[] arg = new String[args.length-1];
					for(int i = 0; i < args.length-1; i++) {
						arg[i] = args[i+1];
					}
					upd.update_by_id(dao, id, arg);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Id should be type long");
				}
				catch(LimitException e) {
					System.out.println(e.getMessage());
				}
				catch(NullException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(command.equals("history")) {
				System.out.println(history.history(q));
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(history);
			}
			else if(command.equals("execute_script")) {
				if(q != null && q.size() == 7) {
					q.removeFirst();
				}
				q.addLast(exec);
				q = exec.execute_script(dao, line[1], q);
			}*/
		}
		in.close();
	}
	
}
