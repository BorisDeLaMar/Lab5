package GivenClasses;
import org.json.*;


public class Coordinates {
    private long x; 
    private double y; 
    private static boolean flag = true;
    public static boolean getFlag() {
    	return flag;
    }
    public static void setFlag() {
    	flag = true;
    }
    public Coordinates(JSONArray cord) {
    	try {
	    	x = cord.optLong(0);
	    	y = cord.optDouble(1); //разобраться с double default value
    	}
    	catch(Exception e) {
    	    flag = false;
    		e.printStackTrace();
    	}
    }
    public Coordinates(String x, String y) {
    	try {
    		this.y = Double.valueOf(y);
    	}
    	catch(IllegalArgumentException e) {
    		flag = false;
    		System.out.println("y should be type double");
    	}
    	try {
    		this.x = Long.valueOf(x);
    	}
    	catch(IllegalArgumentException e) {
    		flag = false;
    		System.out.println("x should be type long");
    	}
    }
    public Coordinates(String x, Double y) {
    	this.y = y;
    	try {
    		this.x = Long.valueOf(x);
    	}
    	catch(IllegalArgumentException e) {
    	    flag = false;
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
