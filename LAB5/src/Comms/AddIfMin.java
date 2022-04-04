package Comms;
import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.*;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class AddIfMin implements Commands{
	
	public void add_if_min(DAO<Worker> dao, String[] args) throws LimitException{
		/** 
		 *Adds element if it's minimum of all the elements
		 *@param DAO<Worker> dao, String[] args
		 *@author BARIS  
		*/
		boolean flag = true;
		Worker w = new Worker();
		if(args.length < 8) {
			//System.out.println(args.length);
			throw new LimitException("There should be 8 args for add function. Null for organization type or position is NULL. Use null for the company name if there's not one\nOnly one space between arguments");
		}
		else {
			for(int i = 0; i < args.length; i++) {
				if (i == 0) {
					try {
						w.setName(args[i]);
					}
					catch(NullException e) {
						flag = false;
						System.out.println(e.getMessage());
					}
				}
				if(i == 1) {
					try {
						Long salary = Long.valueOf(args[i]);
						w.setSalary(salary);
					}
					catch(NumberFormatException e) {
						flag = false;
						System.out.println("Salary should be just a number");
					}
					catch(LimitException e) {
						flag = false;
						System.out.println(e.getMessage());
					}
				}
				if(i == 2) {
					try {
						Position pos =  Position.valueOf(args[i]);
						w.setPosition(pos);
					}
					catch(IllegalArgumentException e) {
						flag = false;
						System.out.println("Available values for position are: " + Position.strConvert());
					}
				}
				if(i == 3) {
					try {
						Status state = Status.valueOf(args[i]);
						w.setStatus(state);
					}
					catch(IllegalArgumentException e) {
						flag = false;
						System.out.println("Available values for status are: " + Status.strConvert());
					}
					catch(NullException e) {
						flag = false;
						System.out.println(e.getMessage());
					}
				}
				if(i == 4) {
					Organization org = new Organization(args[i], args[i+1]);
					if(Organization.getFlag() == false) {
						flag = false;
					}
					Organization.setFlag();
					w.setOrganization(org);
				}
				if(i == 6) {
					Coordinates cords = new Coordinates(args[i], args[i+1]);
					if(Coordinates.getFlag() == false) {
						flag = false;
					}
					Coordinates.setFlag();
					try {
						w.setCoordinates(cords);
					}
					catch(LimitException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		if(flag == true) {
			LinkedHashSet<Worker> bd = dao.getAll();
			boolean f = true;
			for(Worker e : bd) {
				if(e.hashCode() < w.hashCode()) {
					f = false;
				}
			}
			if(f) {
				w.setCreationDate();
				w.setID(Worker.findPossibleID());
				dao.appendToList(w);
			}
		}
		else {
			System.out.println("So that it wasn't added to bd");
		}
	}
	
	@Override
	public String getGist() {
		return "adds element to collection, if it's the lowest one";
	}
	@Override
	public String getName() {
		return "add_if_min";
	}
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line) {
		AddIfMin aim = new AddIfMin();
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
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("There should be an index argument");
		}
		return q; //Проверить history. Если не робит, попробуй в try возвращать q, а здесь null
	}
}
