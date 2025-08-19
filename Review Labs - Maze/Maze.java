import java.util.*;

public class Maze {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] map = new String[] {"straight", "beginner" , "intermediate"};
		MazeGen mazeGen;

		int mazeStyle = -1;
		int playStyle = -1;
		String answer = "";
		choises: while(true) {
			while(!(playStyle >= 1 && playStyle <= 4) ) {
				System.out.print("Pick Number For A Play Style: \n1)User Controlled 2)Random AI 3)Right Hand Rule AI 4)Learning AI : ");
				playStyle = input.nextInt();
			}
			while(!(mazeStyle >= 1 && mazeStyle <= 3) ) {
				System.out.print("Pick Number For A Maze Style: \n1)Straight 2)Beginner 3)Intermediate : ");
				mazeStyle = input.nextInt();
			}
			input.nextLine();
			System.out.println();
			mazeGen = new MazeGen(map[mazeStyle - 1]);
			mazeGen.PrintMaze();
			System.out.print("Are You Sure? Y/N : ");
			answer = input.nextLine();
			if(answer.equals("Y")) {
				break choises;
			} else if(answer.equals("N")) {
				mazeStyle = -1;
				playStyle = -1;
				System.out.println();
			}
		}

		if(playStyle == 1) {
			Player player = new Player(mazeGen);
			
			String move;
			game: while(!player.isOnExit()) {
				mazeGen.PrintMaze();
				System.out.println("WASD To Move / \"quit\" To Stop: ");
				move = input.nextLine();
				switch(move) {
				case "w":
					player.moveUp();
					break;
					
				case "d":
					player.moveRight();
					break;
					
				case "s":
					player.moveDown();
					break;
					
				case "a":
					player.moveLeft();
					break;
					
				case "quit":
					break game;
					
				default:
					break;
				}
				//System.out.println("is on start: " + mazeGen.isOnStart());
			}
			mazeGen.PrintMaze();
			System.out.println("Player Took " + player.getNumMoves() + " Moves!");
		} else if(playStyle == 2) {
			RandomRobot rRobot = new RandomRobot(mazeGen);
			System.out.println("Took " + rRobot.run() + " Moves");
		} else if(playStyle == 3) {
			RightHandRuleRobot rhrRobt = new RightHandRuleRobot(mazeGen);
			System.out.println("Took " + rhrRobt.run() + " Moves");
		} else if(playStyle == 4) {
			MemoryRobot mRobot = new MemoryRobot(mazeGen);
			System.out.println("Took " + mRobot.run() + " Moves");
		}
	}
}
