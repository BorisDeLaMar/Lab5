package Comms;
//import java.util.LinkedHashSet;

import java.util.Scanner;

import GivenClasses.*;

public abstract class GistStaff{
	String hname;
	long hsalary;
	Position hpos; //h - heroe's
	Status hstatus;
	Organization horganization;		
	Coordinates hcoordinates;	
	/** 
	 *Just a help class for Gist
	 *@param hname, hsalary, hpos, hstatus, horganization, hcoordinates
	 *@author BARIS
	*/
	protected static DAO<Worker> dao = new DataDAO();
	private static boolean isExecute = false;
	public static boolean getFlag() {
		return isExecute;
	}
	public static void setFlag(boolean isExecute) {
		GistStaff.isExecute = isExecute;
	}
	//LinkedHashSet database = new LinkedHashSet<>();
}
