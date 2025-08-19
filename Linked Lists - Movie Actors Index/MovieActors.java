import java.io.*;

public class MovieActors {

	public static void main(String[] args) throws Exception {
		LinkedListFromFile movies = new LinkedListFromFile("C:\\Users\\23rohrbacheri\\Desktop\\Eclipse IDE Projects\\Linked Lists - Movie Actors Index\\src\\textFile\\movies.txt");
		LinkedListFromFile actors = new LinkedListFromFile("C:\\Users\\23rohrbacheri\\Desktop\\Eclipse IDE Projects\\Linked Lists - Movie Actors Index\\src\\textFile\\actors.txt");
		FileWriter output = new FileWriter("C:\\Users\\23rohrbacheri\\Desktop\\Eclipse IDE Projects\\Linked Lists - Movie Actors Index\\src\\textFile\\output.txt");
		
		output.write("-------------------------------------------------------------------------------------------------------------------------\n");
		
		for (int i = 0; i < actors.size(); i++) {
			System.out.println(actors.get(i));
			output.write(actors.get(i)+"\n\n");
			for (int j = movies.size()-1; 0 <= j; j--) {
				if(movies.get(j).subSequence(38, 84).toString().contains(actors.get(i))) {
					System.out.println(movies.get(j));
					output.write(movies.get(j)+"\n");
				}
			}
			System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
			output.write("\n-------------------------------------------------------------------------------------------------------------------------\n");
		}
		output.close();
	}
}
