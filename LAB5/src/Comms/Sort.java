package Comms;
import GivenClasses.*;
import java.util.*;

public class Sort{
	
	public TreeSet<Worker> sort(DAO<Worker> dao){
		LinkedHashSet<Worker> bd = dao.getAll();
		TreeSet<Worker> srt = new TreeSet<Worker>();
		for(Worker w : bd) {
			srt.add(w);
		}
		return srt;
	}
}