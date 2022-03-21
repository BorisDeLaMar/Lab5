package Comms;
import java.io.BufferedReader;
import GivenClasses.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.Status;
//import java.nio.file.*;

public class ExecuteScript implements Commands{
	ArrayDeque<Commands> q;
	
	public ArrayDeque<Commands> execute_script(DAO<Worker> dao, String filename, ArrayDeque<Commands> qu){
		boolean flag = true;
		q = qu;
		
		Clear cl = new Clear();
		Exit exe = new Exit();
		Add dd = new Add();
		AddIfMin aim = new AddIfMin();
		FilterStatus st = new FilterStatus();
		Help hlp = new Help();
		Info inf = new Info();
		PrintDescending prnt = new PrintDescending();
		PrintUniqueStatus prntu = new PrintUniqueStatus();
		Remove rmv = new Remove();
		RemoveLower rmvl = new RemoveLower();
		Save save = new Save();
		Show show = new Show();
		Update upd = new Update();
		History history = new History();
		ExecuteScript exec = new ExecuteScript();
		
		try(BufferedReader on = new BufferedReader(new FileReader(filename))){
			while(flag) {
				//in.hasNext()
				try {
					String[] line = on.readLine().split(" ");
					String command = line[0];
					
					if(command.equals("exit")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(exe);
						flag = exe.exit();
					}
					else if(command.equals("clear")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(cl);
						cl.clear(dao);
					}
					else if(command.equals("add")) {
						if(q.size() == 7) {
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
						if(q.size() == 7) {
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
						if(q.size() == 7) {
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
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(hlp);
						System.out.println(hlp.help());
					}
					else if(command.equals("info")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(inf);
						System.out.println(inf.info(dao));
					}
					else if(command.equals("print_descending")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(prnt);
						System.out.println(prnt.print_descending(dao));
					}
					else if(command.equals("print_unique_status")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(prntu);
						System.out.println(prntu.print_unique_status(dao));
					}
					else if(command.equals("remove_by_id")) {
						if(q.size() == 7) {
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
						if(q.size() == 7) {
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
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(save);
						String filepath = System.getenv("FPATH");
						save.save(dao, filepath);
					}
					else if(command.equals("show")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(show);
						System.out.println(show.show(dao));
					}
					else if(command.equals("update")) {
						if(q.size() == 7) {
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
						catch(LimitException e) {
							System.out.println(e.getMessage());
						}
						catch(NullException e) {
							System.out.println(e.getMessage());
						}
					}
					else if(command.equals("history")) {
						System.out.println(history.history(q));
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(history);
					}
					else if(command.equals("execute_script")) {
						if(q.size() == 7) {
							q.removeFirst();
						}
						q.addLast(exec);
						q = exec.execute_script(dao, line[1], q);
					}
					else {
						System.out.println("Unknown command. Type \"help\" for the list of available commands");
					}
				}
				catch(NullPointerException e) {
					on.close();
					return q;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public String getGist() {
		return "executes and complete the script from given file";
	}
	@Override
	public String getName() {
		return "execute_script";
	}
}
