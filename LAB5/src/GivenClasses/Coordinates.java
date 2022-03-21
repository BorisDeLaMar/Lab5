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
    		this.x = Long.valueOf(x);
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("x should be type long, y should be type double");
    	}
    }
    
    public long getAbscissa() {
    	return x;
    }
    public double getOrdinate() {
    	return y;
    }
}
