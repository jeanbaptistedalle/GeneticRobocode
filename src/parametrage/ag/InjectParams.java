package parametrage.ag;


import java.io.BufferedReader;






import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.univ.angers.Robot;


/*
 * Classse permettant l'injection de paramètres dans un robots.java
 * ... le code ne doit pas comporter de commentaire
*/

public class InjectParams {
	
	private ArrayList<String> newLines = new ArrayList<String>();
	private String fileName = "robots/generation/Test.java";
	private String params;
	private String robotname;
	private String robotpackage;
	
	public InjectParams(String robotname, String robotpackage, String params) {
		this.robotname = robotname;
		this.robotpackage = robotpackage;
		this.params = params;
	}
	
	public void printNewLines() {
		for (int i = 0; i < newLines.size(); i++) {
			System.out.println(newLines.get(i));
		}
	}
	
	
	public void readRobocode() {
		
        // This will reference one line at a time
        String line = null;
		
		  try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            

	            while((line = bufferedReader.readLine()) != null) {
	            	
	            	
						if (line.contains("int param_a =")) {
							line = params;
						}
					
	            	newLines.add(line);
	            }   

	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
	}
	
	
	
	public void writeRobocode() {	
		
		String code = "";
		for (int i = 0; i < newLines.size(); i++) {
			code = code.concat(newLines.get(i)+"\n");
		}
		
		Robot r = new Robot();
		r.setRobotName(robotname);
		r.setRobotPackage(robotpackage);
		r.setRobotCode(code);
		
		
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			writer.write(r.getRobotCode().toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		r.makeRobot();
	
	}
	
	

}
