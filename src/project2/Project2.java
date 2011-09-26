//REQUIRED CLASS - KEEP THIS FILE IN THE 'project2' DIRECTORY WITH 'package project2'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'DatalogProgram' and return an ouput string
//DO NOT ADD METHODS

package project2;

public class Project2
{
	public static String body(String[] args)
	{
    String inputFile = args[0];
	  DatalogProgram dataProg = new DatalogProgram(inputFile);
    String output = "";
    
    dataProg.parse();

    // this should call the toString methods of its children in the parse tree.    
    output = "Success!\n" + dataProg.toString();

		return output;
	}
}
