import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class Gist {
	
	
	public static void main(String[] args) {
		LinkedHashSet database = new LinkedHashSet<>();
		
		/** Чтение из файла */
		String filepath = "C:\\Рабочий стол\\GitLabs\\Lab5\\Data.json";
		try(BufferedReader in = new BufferedReader(new FileReader(filepath))){
			String line = in.readLine();
			while(line != null) {
				continue;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
