package GivenClasses;
import org.json.*;

public class Organization {
    private String fullName;
    private OrganizationType type;
    public Organization(JSONArray org) {
    	setName(org.getString(0)); //разобраться в чем разница между optString и getString
    	try {
    		setType(OrganizationType.valueOf(org.getString(1)));
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("Aavilable organization types are: " + OrganizationType.strConvert());
    	}
    }
    public Organization(String fullName, String type) {
    	setName(fullName);
    	try {
    		setType(OrganizationType.valueOf(type));
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("Aavilable organization types are: " + OrganizationType.strConvert());
    	}
    }
    
    public String getName() {
    	return fullName;
    }
    public void setName(String fullName) {
    	this.fullName = fullName;
    }
    
    public OrganizationType getType() {
    	return type;
    }
    public void setType(OrganizationType type) {
    	this.type = type;
    }
}
