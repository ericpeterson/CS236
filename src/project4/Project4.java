//REQUIRED CLASS - KEEP THIS FILE IN THE 'project4' DIRECTORY WITH 'package project4'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project4;

import datalogProgram.*; 

public class Project4 {
	public static String body(String[] args) { 
    DatalogProgram datalogProgram = new DatalogProgram(args[0]);  
    Database database = new Database(datalogProgram.getSchemes());
		String output = "";
		return output;
	}
}
