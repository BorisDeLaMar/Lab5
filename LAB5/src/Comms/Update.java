package Comms;
import Exceptions.LimitException;
import Exceptions.NullException;
import GivenClasses.*;

public class Update implements Commands{
	/** 
	 *Updates element by id and given args
	 *@param DAO<Worker> dao, long id, String[] args
	 *@author BARIS 
	*/
	
	public void update_by_id(DAO<Worker> dao, long id, String[] args) throws NullException, LimitException{
		Worker w = dao.get(id);
		if(id < 1) {
			throw new NullException("Id should be more than zero at least");
		}
		else if(w == null) {
			throw new NullException("There is no guy with such id");
		}
		else {
			if(args.length == 0 || args.length > 8) {
				throw new LimitException("The number of available args for update function is from 1 to 8. You also need to enter id firstly. NULL for position and organization type are possible, null for company name\nOnly one space between arguments");
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
							System.out.println("Salary should be just a number. The real number, not the miracle one");
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
					if((i == 6) && (i+1 < args.length)) {
						Coordinates cords = new Coordinates(args[i], args[i+1]);
						w.setCoordinates(cords);
					}
					else if((i == 6) && (i+1 >= args.length)){
						Coordinates cords = new Coordinates(args[i], w.getCoordinates().getOrdinate());
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
