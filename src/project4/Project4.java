//REQUIRED CLASS - KEEP THIS FILE IN THE 'project4' DIRECTORY WITH 'package project4'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project4;

public class Project4 {
	public static String body(String[] args) {
    Database database = new Database();
		String output = database.evaluate(args[0]);
		return output;
	}
}
