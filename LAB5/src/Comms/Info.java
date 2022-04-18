package Comms;

import java.io.IOException;
import java.io.BufferedReader;
//import java.time.format.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.time.LocalDateTime;
import java.time.ZoneId;

import GivenClasses.Worker;

public class Info implements Commands{
	/** 
	 *Prints info about collection
	 *@param DAO<Worker> dao
	 *@author BARIS 
	*/
	
	public String info(DAO<Worker> dao) {
		try {
			String filepath = System.getenv("FPATH");
			Path path = Paths.get(filepath);
			BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class); 
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime tm = attrs.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return "File type: .json\n" + "Initializing date: " + tm.format(format) + "\n" + "Size: " + dao.getAll().size() + " workers\n";
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getGist() {
		return "prints some info about collection";
	}
	@Override
	public String getName() {
		return "info";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on){
		Info inf = new Info();
		q = History.cut(q);
		q.addLast(inf);
		System.out.println(inf.info(dao));
		return q;
	}
}
