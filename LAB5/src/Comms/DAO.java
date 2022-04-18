package Comms;
import java.util.Set;

/** 
 *Interface for working with collection class in a new abstract level
 *@author AP
*/
public interface DAO<T> {
	void appendToList(T t);
	void delete(T t);
	T get(long id);
	// TODO
	Set<T> getAll();
	// TODO
	void DateRead(String filename);
	
	
	void saveCollection(String s);
}
