package Comms;
import GivenClasses.*;

import java.util.LinkedHashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import org.json.*;
import javax.json.*;
//import org.json.*;
//import java.io.File;

public class Save implements Commands{
	
	public void save(DAO<Worker> dao, String filepath) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			//File fl = new File(filepath);
			LinkedHashSet<Worker> bd = dao.getAll();
			JsonObject arr = Json.createObjectBuilder().build();
			//JsonObject arr = Json.createObjectBuilder().build();
			out.write("{\"workers\":[");
			int i = 0;
			for(Worker w : bd) { 
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
						.build();
				if(i != 0) {
					out.write(",");
				}
				out.write(val.toString());
				i += 1;
				//arr.put("workers", val);  
				//out.newLine(); out.write("{");				
			}
			out.write("]}");
			out.close();
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
}
