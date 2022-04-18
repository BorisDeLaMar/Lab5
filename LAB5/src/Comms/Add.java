package Comms;

import Exceptions.LimitException;
//import Exceptions.*;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.NullException;
import GivenClasses.Coordinates;
import GivenClasses.Organization;
import GivenClasses.OrganizationType;
import GivenClasses.Position;
import GivenClasses.Status;
import GivenClasses.Worker;

public class Add implements Commands{
	public void add(DAO<Worker> d, BufferedReader on) throws IOException{
		/** 
		 *Add function
		 *@param DAO<Worker> dao, String[] args
		 *@author BARIS  
		*/
		Worker w = new Worker();
		add_read(w, on);
		w.setCreationDate();
		try {
			w.setID(Worker.findPossibleID());
			d.appendToList(w);
		}
		catch(LimitException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Worker successfully added");
	}
	public void add_exec(DAO<Worker> dao, BufferedReader on) {
		Worker w = new Worker();
		boolean f = true;
		try {
			f = add_read_exec(w, on);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		//scam.nextLine();
		if(f == false) {
			System.out.println("Worker from file wasn't added or updated because of incorrect input");
		}
		else {
			w.setCreationDate();
			try {
				w.setID(Worker.findPossibleID());
				dao.appendToList(w);
			}
			catch(LimitException e) {
				System.out.println(e.getMessage());
			}
			//System.out.println("Worker successfully added");
		}
	}
	public void add_read(Worker w, BufferedReader on) throws IOException{
		int i = 0;
		while(i < 8) {
			if (i == 0) {
				try {
					System.out.print("Enter name: ");
					String name = on.readLine().split(" ")[0];
					i = 1;
					w.setName(name);
				}
				catch(NullException e) {
					i = 0;
					System.out.println(e.getMessage());
				}
			}
			if(i == 1) {
				try {
					System.out.print("Enter salary: ");
					String salo = on.readLine().split(" ")[0];
					Long salary = Long.valueOf(salo);
					i = 2;
					w.setSalary(salary);
				}
				catch(NumberFormatException e) {
					i = 1;
					System.out.println("Salary should be just a number");
				}
				catch(LimitException e) {
					i = 1;
					System.out.println(e.getMessage());
				}
			}
			if(i == 2) {
				try {
					System.out.print("Enter position: ");
					String posit = on.readLine().split(" ")[0];
					i = 3;
					Position pos =  Position.valueOf(posit);
					w.setPosition(pos);
				}
				catch(IllegalArgumentException e) {
					i = 2;
					System.out.println("Available values for position are: " + Position.strConvert());
				}
			}
			if(i == 3) {
				try {
					System.out.print("Enter status: ");
					String stata = on.readLine().split(" ")[0];
					//scam.nextLine();
					i = 4;
					Status state = Status.valueOf(stata);
					w.setStatus(state);
				}
				catch(IllegalArgumentException e) {
					i = 3;
					System.out.println("Available values for status are: " + Status.strConvert());
				}
				catch(NullException e) {
					i = 3;
					System.out.println(e.getMessage());
				}
			}
			if(i == 4) {
				try {
					System.out.println("Enter organization: ");
					String[] arg = on.readLine().split(" ");
					i = 6;
					Organization org = new Organization(arg[0], arg[1]);
					if(Organization.getFlag() == false) {
						i = 4;
					}
					Organization.setFlag();
					w.setOrganization(org);
				}
				catch(ArrayIndexOutOfBoundsException e) {
					i = 4;
					System.out.println("There should be two args in organization field: name and type\nAvailbale organizationtypes: " + OrganizationType.strConvert());
				}
			}
			if(i == 6) {
				try {
					System.out.print("Enter coordinates: ");
					String[] arg = on.readLine().split(" ");
					i = 8;
					Coordinates cords = new Coordinates(arg[0], arg[1]);
					if(Coordinates.getFlag() == false) {
						i = 6;
					}
					Coordinates.setFlag();
					try {
						w.setCoordinates(cords);
					}
					catch(LimitException e) {
						i = 6;
						System.out.println(e.getMessage());
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					i = 6;
					System.out.println("There should be two args in coordinates field: x and y");
				}
			}
		}
	}
	public boolean add_read_exec(Worker w, BufferedReader on) throws IOException {
		BufferedReader scam = on;
		int i = 0;
		boolean flag = true;
		while(i < 8) {
			if (i == 0) {
				try {
					String[] names = scam.readLine().split(" ");
					i = 1;
					w.setName(names[0]);
				}
				catch(NullException e) {
					i = 8;
					flag = false;
					System.out.println(e.getMessage());
				}
			}
			if(i == 1) {
				try {
					String[] salo = scam.readLine().split(" ");
					Long salary = Long.valueOf(salo[0]);
					i = 2;
					w.setSalary(salary);
				}
				catch(NumberFormatException e) {
					i = 8;
					flag = false;
					System.out.println("Salary should be just a number");
				}
				catch(LimitException e) {
					i = 8;
					flag = false;
					System.out.println(e.getMessage());
				}
			}
			if(i == 2) {
				try {
					String[] posit = scam.readLine().split(" ");
					i = 3;
					Position pos =  Position.valueOf(posit[0]);
					w.setPosition(pos);
				}
				catch(IllegalArgumentException e) {
					i = 8;
					flag = false;
					System.out.println("Available values for position are: " + Position.strConvert());
				}
			}
			if(i == 3) {
				try {
					String[] stata = scam.readLine().split(" ");
					scam.readLine();
					i = 4;
					Status state = Status.valueOf(stata[0]);
					w.setStatus(state);
				}
				catch(IllegalArgumentException e) {
					i = 8;
					flag = false;
					System.out.println("Available values for status are: " + Status.strConvert());
				}
				catch(NullException e) {
					i = 8;
					flag = false;
					System.out.println(e.getMessage());
				}
			}
			if(i == 4) {
				try {
					String[] arg = scam.readLine().split(" ");
					i = 6;
					Organization org = new Organization(arg[0], arg[1]);
					if(Organization.getFlag() == false) {
						i = 8;
						flag = false;
					}
					Organization.setFlag();
					w.setOrganization(org);
				}
				catch(ArrayIndexOutOfBoundsException e) {
					i = 8;
					flag = false;
					System.out.println("There should be two args in organization field: name and type\nAvailbale organizationtypes: " + OrganizationType.strConvert());
				}
			}
			if(i == 6) {
				try {
					String[] arg = scam.readLine().split(" ");
					i = 8;
					Coordinates cords = new Coordinates(arg[0], arg[1]);
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
				catch(ArrayIndexOutOfBoundsException e) {
					i = 8;
					flag = false;
					System.out.println("There should be two args in coordinates field: x and y");
				}
			}
		}
		return flag;
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
	public ArrayDeque<Commands> executeCommand(DAO<Worker> dao, ArrayDeque<Commands> q, BufferedReader on) throws IOException{
		q = History.cut(q);
		q.addLast(this);
		if(GistStaff.getFlag()) {
			this.add_exec(dao, on);
		}
		else {
			this.add(dao, on);
		}
		return q;
	}
}
