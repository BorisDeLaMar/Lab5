package Comms;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

import GivenClasses.Worker;

public class Info implements Commands{
	
	public String info(DAO<Worker> dao) {
		try {
			String filepath = System.getenv("FPATH");
			Path path = Paths.get(filepath);
			BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class); 
			return "File type: .json\n" + "Initializing date: " + attrs.creationTime() + "\n" + "Size: " + dao.getAll().size() + " workers\n";
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
}
