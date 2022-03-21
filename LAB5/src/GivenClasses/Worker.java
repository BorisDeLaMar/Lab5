package GivenClasses;

//import java.lang.reflect.*;
import Exceptions.*;
import java.time.LocalDateTime;

public class Worker implements Comparable<Worker>{
	
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long salary; //Значение поля должно быть больше 0
    private Position position; //Поле может быть null
    private Status status; //Поле не может быть null
    private Organization organization; //Поле может быть null 
    
    private int x;
    private int y;
    private int moneymoney;
    
    public Worker(String name, long salary, Position pos, Status state, Organization org, Coordinates cords, int i) {
    	try {
    		setName(name);
    		setSalary(salary);
    		setPosition(pos);
    		setStatus(state);
    		setOrganization(org);
    		setCoordinates(cords);
    		setId(i+1);
    		setCreationDate(creationDate.now());
    	   	moneymoney = (int) salary/1000000000;
    		x = (int) coordinates.getAbscissa();
    		y = (int) coordinates.getOrdinate();
    	}
    	catch(NullException e) {
    		System.out.println(e.getMessage());
    	}
    	catch(LimitException e) {
    		System.out.println(e.getMessage());
    	}
    }
    public Worker() {}
	    
    
    public long getId() {
    	return id;
    }
    public void setId(long id) throws NullException{
    	if(id == 0) {
    		throw new NullException("id не может быть равен 0");
    	}
    	else {
    		this.id = id;
    	}
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) throws NullException{
    	if(name == null || name == "") {
    		throw new NullException("Поле name не может быть ни null, ни пустой строкой. Хватит баловаться!");
    	}
    	else {
    		this.name = name;
    	}
    }
    
    public Coordinates getCoordinates() {
    	return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) throws LimitException{
    	if(coordinates == null || coordinates.getAbscissa() > 176 || coordinates.getOrdinate() > 729) {
    		throw new LimitException("Поле coordinates не может быть null, x не может быть больше 176, а y не должен превышать 729");
    	}
    	else {
    		this.coordinates = coordinates;
    	}
    }
    
    public LocalDateTime getCreationDate() {
    	return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) throws NullException{
    	if(creationDate == null) {
    		throw new NullException("Поле creationDate не может быть null");
    	}
    	else {
    		this.creationDate = creationDate;
    	}
    }
    
    public long getSalary() {
    	return salary;
    }
    public void setSalary(long salary) throws LimitException{
    	if(salary <= 0) {
    		throw new LimitException("Не платить зарплату?? Оставить человека еще и должным компании?? Профсоюз в ярости, будем созывать президиум для дальнейших разбирательств. Деменций, неси свиней\nПоле salary должно быть строго положительным, да побольше!");
    	}
    	else {
    		this.salary = salary;
    	}
    }
    
    public Position getPosition() {
    	return position;
    }
    public void setPosition(Position position) {
    	this.position = position;
    }
    
    public Status getStatus() {
    	return status;
    }
    public void setStatus(Status status) throws NullException{
    	if(status == null) {
    		throw new NullException("Status couldn't be null");
    	}
    	else {
    		this.status = status;
    	}
    }
    
    public Organization getOrganization() {
    	return organization;
    }
    public void setOrganization(Organization organization) {
    	this.organization = organization;
    }
    
    
    @Override
    public int hashCode() {
    	
    	return 31*name.length() + 29*x*x + 31*y*y + moneymoney; 
    }
    @Override
    public boolean equals(Object obj) {
    	Worker w  = (Worker) obj;
    	if((name == w.getName()) && (x == w.coordinates.getAbscissa()) && (y == w.coordinates.getOrdinate()) && (moneymoney == w.moneymoney)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    @Override
    public int compareTo(Worker w) {
    	if(this.hashCode() > w.hashCode()) {
    		return 1;
    	}
    	else if(this.hashCode() == w.hashCode()) {
    		return 0;
    	}
    	else {
    		return -1;
    	}
    }
}
