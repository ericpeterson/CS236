//REQUIRED CLASS - KEEP THIS FILE IN THE 'project5' DIRECTORY WITH 'package project5'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project5;
import project4.*;
import datalogProgram.*;

public class Project5
{
	public static String body(String[] args)
	{
    DatalogProgram datalogProgram = new DatalogProgram(args[0]);
    Database database = new Database(datalogProgram);
		String output = database.evaluateRuleList(datalogProgram);

		return output;
	}
}
