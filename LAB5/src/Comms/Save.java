package Comms;
import GivenClasses.*;
import java.io.BufferedReader;


import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.*;
//import org.json.*;
//import javax.json.*;
//import org.json.*;
//import java.io.File;

public class Save implements Commands{
	/** 
	 *Saves collection to the file
	 *@param DAO<Worker> dao, String filepath
	 *@author BARIS 
	*/
	
	public void save(DAO<Worker> dao, String filepath) {
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			//File fl = new File(filepath);
			LinkedHashSet<Worker> bd = new LinkedHashSet<Worker>(dao.getAll());
			//JsonObject arr = Json.createObjectBuilder().build();
			//JsonObject arr = Json.createObjectBuilder().build();
			out.write("{\n\t\"workers\":[");
			int i = 0;
			for(Worker w : bd) { 
				if(w.getId() > 0) {
					out.write("\n\t\t{\n\t\t\t");
					out.write("\"name\":" + "\"" +w.getName() + "\"" + ",\n\t\t\t");
					out.write("\"salary\":" + "\"" + w.getSalary() + "\"" + ",\n\t\t\t");
					out.write("\"position\":" + "\"" + w.getPosition().toString() + "\"" + ",\n\t\t\t");
					out.write("\"status\":" + "\"" + w.getStatus().toString() + "\"" + ",\n\t\t\t");
					out.write("\"organization\":[\n\t\t\t\t" + "\"" + w.getOrganization().getName() + "\"" + ",\n\t\t\t\t" + "\"" + w.getOrganization().getType().toString() + "\"" +"\n\t\t\t],\n\t\t\t");
					out.write("\"coordinates\":[\n\t\t\t\t" + "\"" + w.getCoordinates().getAbscissa() + "\"" + ",\n\t\t\t\t" + "\"" + w.getCoordinates().getOrdinate() + "\"" +"\n\t\t\t],\n\t\t\t");
					out.write("\"ID\":\"" + w.getId() + "\",\n\t\t\t");
					out.write("\"CreationDate\":\"" + w.getCreationDate() + "\"\n\t\t}");
				}
				/*
				JsonObject val = Json.createObjectBuilder()
						.add("name", w.getName())
						.add("salary", w.getSalary())
						.add("position", w.getPosition().toString())
						.add("status", w.getStatus().toString())
						.add("organization", Json.createArrayBuilder()
								.add(w.getOrganization().getName())
								.add(w.getOrganization().getType().toString())
								.build())
						.add("coordinates", Json.createArrayBuilder()
								.add(w.getCoordinates().getAbscissa())
								.add(w.getCoordinates().getOrdinate())
								.build())
						.build();*/
				if(i != bd.size()-1) {
					out.write(",");
				}
				//out.write("\n\t" + val.toString());
				i += 1;
				//arr.put("workers", val);  
				//out.newLine(); out.write("{");				
			}
			out.write("\n\t]\n}");
			out.close();
			if(GistStaff.getFlag() == false) {
				System.out.println("Collection saved to file");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getGist() {
		return "saves collection to file";
	}
	@Override
	public String getName() {
		return "save";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on){
		Save save = new Save();
		q = History.cut(q);
		q.addLast(save);
		String filepath = System.getenv("FPATH");
		save.save(dao, filepath);
		return q;
	}
}
