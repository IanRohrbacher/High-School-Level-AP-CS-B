import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class LinkedListFromFile extends java.util.LinkedList<String> {

	public LinkedListFromFile(String file) throws FileNotFoundException{
		super();
		File openfile = new File(file);
		Scanner fileScanner = new Scanner(openfile);
		while ((fileScanner.hasNextLine()))
			super.add(fileScanner.nextLine());
		fileScanner.close();
	}
}