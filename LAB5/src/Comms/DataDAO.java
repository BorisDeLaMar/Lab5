package Comms;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import org.json.*;
//import org.json.simple.*;

import GivenClasses.*;
//import Exceptions.*;

public class DataDAO implements DAO<Worker>{
	
	private LinkedHashSet<Worker> database = new LinkedHashSet<Worker>();
	String filepath;
	
	String hname;
	long hsalary;
	Position hpos; //h - heroe's
	Status hstatus;
	Organization horganization;		
	Coordinates hcoordinates;
	
	public DataDAO() {
		
	}
	
	/**Чтение из файла*/
	@Override
	public void DateRead(String filename) {
		filepath = filename;
		try(BufferedReader in = new BufferedReader(new FileReader(filepath))){
			JSONTokener tokener = new JSONTokener(in);
			JSONObject obj =  new JSONObject(tokener);
			try {
				JSONArray arr = obj.getJSONArray("workers");
				for(int i = 0; i < arr.length(); i++) {
					JSONObject o = arr.getJSONObject(i);
					hname = o.getString("name");
					hsalary = o.getLong("salary");
					//есть o.getEnum но я не пон как аргументы в него вставить правильно
					if(o.getString("position") == "null") {
						hpos = null;
					}
					else {
						hpos = Position.valueOf(o.getString("position"));
					}
					hstatus = Status.valueOf(o.getString("status"));
					JSONArray org = o.getJSONArray("organization");
					horganization = new Organization(org);
					JSONArray cord = o.getJSONArray("coordinates");
					hcoordinates = new Coordinates(cord);
					Worker worker = new Worker(hname, hsalary, hpos, hstatus, horganization, hcoordinates, i);
					database.add(worker);
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch(JSONException e) {
				System.out.println(e.getMessage());
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/**Реализация функций DAO*/
	Worker clerk = new Worker();
	@Override
	public void appendToList(Worker w) {
		database.add(w);
	}
	@Override
	public void delete(Worker w) {
		database.remove(w);
	}
	@Override
	public void update(Worker w){
		
	}
	@Override
	public Worker get(long id) {
		for(Worker w : database) {
			if(w.getId() == id) {
				return w;
			}
		}
		return null; //и эту ошибку обханделить потом
	}
	@Override
	public LinkedHashSet<Worker> getAll(){
		return database;
	}
}
