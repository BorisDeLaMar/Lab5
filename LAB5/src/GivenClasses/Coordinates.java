package GivenClasses;
import org.json.*;


public class Coordinates {
    private long x; 
    private double y; 
    public Coordinates(JSONArray cord) {
    	x = cord.optLong(0);
    	y = cord.optDouble(1); //разобраться с double default value
    }
    public Coordinates(String x, String y) {
    	try {
    		this.y = Double.valueOf(y);
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("y should be type double");
    	}
    	try {
    		this.x = Long.valueOf(x);
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("x should be type long");
    	}
    }
    public Coordinates(String x, Double y) {
    	this.y = y;
    	try {
    		this.x = Long.valueOf(x);
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("x should be type long");
    	}
    }
    
    public long getAbscissa() {
    	return x;
    }
    public double getOrdinate() {
    	return y;
    }
}
