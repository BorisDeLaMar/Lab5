package Comms;

import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.Coordinates;
import GivenClasses.Organization;
import GivenClasses.Position;
import GivenClasses.Status;
import GivenClasses.Worker;

public class Add implements Commands{
	private static DAO<Worker> dao;

	public void add(DAO<Worker> d, String[] args) throws LimitException{
		Worker w = new Worker();
		if(args.length != 8) {
			throw new LimitException("There should be 8 args for add function. Type space one time for null argument (only for Position and Organization possible)");
		}
		else {
			for(int i = 0; i < args.length; i++) {
				if (i == 0) {
					try {
						w.setName(args[i]);
					}
					catch(NullException e) {
						System.out.println(e.getMessage());
					}
				}
				if(i == 1) {
					try {
						Long salary = Long.valueOf(args[i]);
						w.setSalary(salary);
					}
					catch(NumberFormatException e) {
						System.out.println("Salary should be just a number");
					}
					catch(LimitException e) {
						System.out.println(e.getMessage());
					}
				}
				if(i == 2) {
					try {
						Position pos =  Position.valueOf(args[i]);
						w.setPosition(pos);
					}
					catch(IllegalArgumentException e) {
						System.out.println("Available values for position are: " + Position.strConvert());
					}
				}
				if(i == 3) {
					try {
						Status state = Status.valueOf(args[i]);
						w.setStatus(state);
					}
					catch(IllegalArgumentException e) {
						System.out.println("Available values for status are: " + Status.strConvert());
					}
					catch(NullException e) {
						System.out.println(e.getMessage());
					}
				}
				if(i == 4) {
					Organization org = new Organization(args[i], args[i+1]);
					w.setOrganization(org);
				}
				if(i == 6) {
					Coordinates cords = new Coordinates(args[i], args[i+1]);
					try {
						w.setCoordinates(cords);
					}
					catch(LimitException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		d.appendToList(w);
	}
	
	@Override
	public String getGist() {
		return "adds element to collection";
	}
	@Override
	public String getName() {
		return "add";
	}
}
