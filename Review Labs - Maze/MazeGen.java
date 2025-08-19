import java.util.*;

public class MazeGen {
	private int length; //going up
	private int weight; //going right
	private int[][] maze;
	private int[] playerPos = new int[2];
	private int[] exitPos = new int[2];
	
	private HashMap<Integer, String> mazeDic = new HashMap<>();
	private final int outOfBounds = -1;
	private final int close = 0;
	private final int open = 1;
	private final int start = 2;
	private final int exit = 3;
	private final int player = 4;
	
	//init for all mazes
	private void init(int length, int weight, int playerPosY, int playerPosX, int exitPosY, int exitPosX) {
		this.length = length;
		this.weight = weight;
		setPlayerPos(playerPosX, playerPosY);
		setExitPos(exitPosX, exitPosY);

		
		mazeDic.put(close, "\u2022"); //close
		mazeDic.put(open, " "); //open
		mazeDic.put(start, "^"); //start
		mazeDic.put(exit, "!"); //end
		mazeDic.put(player, "*"); //player
	}
	
	public MazeGen(String num) {
		switch(num) {
			case "straight":	
				//set layout
				maze = new int[][]
						{
						{1,1,1,1,1,1,1,1,1,1},
						{1,0,0,0,0,0,0,0,0,1},
						{1,1,2,0,1,1,1,0,0,1},
						{0,0,0,0,1,0,1,1,1,1},
						{1,1,1,1,1,0,0,0,0,0},
						{1,0,0,0,0,1,1,1,1,1},
						{1,0,0,0,0,1,0,0,0,1},
						{1,1,0,1,1,1,0,1,1,1},
						{0,1,1,1,0,0,0,1,0,0},
						{0,0,0,0,3,1,1,1,0,0}
						};
						
				init(10, 10, 2, 2, 4, 9);
						
				break;
				
			case "beginner":	
				//set layout
				maze = new int[][]
						{
						{2,1,1,1,1,1,1,1,1,1},
						{0,0,1,0,0,0,0,0,1,0},
						{0,0,1,0,1,1,1,0,1,0},
						{0,0,1,0,1,0,1,0,1,1},
						{1,1,1,1,1,0,1,0,0,0},
						{1,0,1,0,0,0,1,1,1,1},
						{1,0,1,0,0,0,1,0,0,1},
						{1,1,0,1,1,1,1,0,0,3},
						{0,1,0,1,0,0,0,0,0,0},
						{0,0,0,1,1,1,1,1,1,1}
						};
						
				init(10, 10, 0, 0, 9, 7);
						
				break;
				
			case "intermediate":
				//set layout
				maze = new int[][]
						{
						{1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0},
						{1,0,1,1,1,0,1,0,1,0,1,0,0,1,0,1,1,1,1,1},
						{1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
						{1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,0,1},
						{1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1},
						{0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
						{1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
						{1,0,0,1,0,0,1,1,1,0,1,1,1,0,0,0,0,1,0,1},
						{0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,0,1},
						{1,0,0,0,1,0,0,0,0,0,1,0,1,1,1,0,0,0,0,1},
						{1,0,1,1,1,1,1,1,1,1,2,1,1,0,0,0,0,1,1,1},
						{1,0,1,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,0},
						{1,0,1,1,1,0,1,1,0,0,1,0,1,1,0,1,0,0,0,0},
						{1,0,0,0,1,0,0,1,1,0,1,0,1,0,1,1,1,1,1,1},
						{1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,1,0,0},
						{1,0,1,0,0,1,0,1,0,0,1,1,1,1,1,0,0,1,1,1},
						{1,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,1},
						{1,0,1,0,1,1,1,1,0,0,1,0,1,1,1,0,1,1,1,0},
						{1,0,1,0,1,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0},
						{1,1,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,1},};
					
				init(20, 20, 10, 10, 8, 8);
				
				break;
			
			default:
				this.length = 0;
				this.weight = 0;
				System.out.println("No Maze Loaded");
				break;
		}
	}
	
	public void PrintMaze() {
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < weight; j++) {
				System.out.print("["+mazeDic.get(maze[i][j])+"]");
				//System.out.format("["+mazeDic.get(maze[i][j])+"]");
				//System.out.print("["+maze[i][j]+"]");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public int[][] getMaze() {
		return maze;
	}
	public void setMaze(int pos1, int pos2, int dic) {
		maze[pos1][pos2] = dic;
	}
	
	public int[] getPlayerPos() { // can delete later
		return playerPos;
	}
	public void setPlayerPos(int x, int y) {
		//starting from top left
		playerPos[0] = x;
		playerPos[1] = y;
		maze[playerPos[0]][playerPos[1]] = player;
	}
	
	public int[] getExitPos() { // can delete later
		return exitPos;
	}
	public void setExitPos(int x, int y) {
		exitPos[0] = x;
		exitPos[1] = y;
		maze[exitPos[0]][exitPos[1]] = exit;
	}
	
	public int getLength() {
		return length;
	}
	public int getWeight() {
		return weight;
	}
	public int getArea() {
		return getWeight() * getLength();
	}
	
	public boolean isOnExit() {
		return exitPos[0] == playerPos[0] && exitPos[1] == playerPos[1];
	}
	
	public HashMap<Integer, String> getMazeDic() {
		return mazeDic;
	}

}
