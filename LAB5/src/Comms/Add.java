package Comms;

import Exceptions.LimitException;
import Exceptions.*;
import java.util.ArrayDeque;
import Exceptions.NullException;
import GivenClasses.Coordinates;
import GivenClasses.Organization;
import GivenClasses.Position;
import GivenClasses.Status;
import GivenClasses.Worker;

public class Add implements Commands{

	public void add(DAO<Worker> d, String[] args) throws LimitException{
		/** 
		 *Add function
		 *@param DAO<Worker> dao, String[] args
		 *@author BARIS  
		*/
		Worker w = new Worker();
		boolean flag = true;
		if(args.length != 8) {
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
						flag = false;
						System.out.println(e.getMessage());
					}
				}
			}
		}
		/*try {
			w.setId(DataDAO.id);
		}
		catch(NullException e) {
			if(DataDAO.id == 0) {
				DataDAO.id += 1;
				try {
					w.setId(DataDAO.id);
				}
				catch(NullException e1) {
					System.out.println(e1.getMessage());
				}
			}
			else {
				System.out.println(e.getMessage());
			}
		}*/
		if(flag == true) {
			w.setCreationDate();
			w.setID(Worker.findPossibleID());
			d.appendToList(w);
		}
		else {
			System.out.println("So that worker wasn't added");
		}
	}
	
	@Override
	public String getGist() {
		return "adds element to collection";
	}
	@Override
	public String getName() {
		return "add";
	}
	//@SuppressWarnings("finally")
	@Override
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, String[] line) {
		Add dd = new Add();
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
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("There should be an index argument");
		}
		return q;
	}
}
