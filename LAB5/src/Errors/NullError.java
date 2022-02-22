package Errors;

public class NullError extends Exception{
	
	private String message = "Это поле не может быть null. Хватит баловаться!";
	public String getMessage() {
		return message;
	}
	
}
