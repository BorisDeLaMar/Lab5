package GivenClasses;
import Errors.*;

public class Worker {
	
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long salary; //Значение поля должно быть больше 0
    private Position position; //Поле может быть null
    private Status status; //Поле не может быть null
    private Organization organization; //Поле может быть null 
    
    public Worker(String name) {
    	this.name = name;
    	if( (this.name == "") || (this.name == null) ) {
    		throw new NullError();
    	}
    }
    
}
