package Comms;
import java.util.LinkedHashSet;
public interface DAO<T> {
	/** 
	 *Interface for working with collection class in a new abstract level
	 *@author BARIS  
	*/
	void appendToList(T t);
	void delete(T t);
	void update(T t);
	T get(long id);
	LinkedHashSet<T> getAll();
	void DateRead(String filename);
}
