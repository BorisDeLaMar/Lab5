package Comms;
import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.*;

public class Update implements Commands{
	
	public void update_by_id(DAO<Worker> dao, long id, String[] args) throws NullException, LimitException{
		Worker w = dao.get(id);
		if(w == null) {
			throw new NullException("There is no guy with such id");
		}
		else {
			if(args.length == 0 || args.length > 8) {
				throw new LimitException("The number of available args for update function is from 1 to 8 + u need to enter id firstly");
			}
			else {
				//System.out.println(args.length);
				for(int i = 0; i < args.length; ++i) {
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
						w.setCoordinates(cords);
					}
				}
			}
		}
	}
	
	@Override 
	public String getGist() {
		return "updates element with new arguments";
	}
	@Override
	public String getName() {
		return "update";
	}
}
