import java.util.*;

public class MemoryRobot extends Player{
	private ArrayList<ArrayList<String>> memory;
	private HashMap<String, String> mazeDic = new HashMap<>();
	private int[] playerPos;
	
	public MemoryRobot(MazeGen maze) {
		super(maze);
		
		mazeDic.put("Closed", "\u2022"); //close
		mazeDic.put("Open", " "); //open
		mazeDic.put("Traveled", ".");
		mazeDic.put("TraveledTwice", ":");
		mazeDic.put("Player", "*"); //player
		
		memory = new ArrayList<ArrayList<String>>();
		playerPos = new int[2];
		for(int i = 0; i < 3; i++) {
			memory.add(new ArrayList<String>());
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				memory.get(i).add(mazeDic.get("Open"));
			}
		}
		playerPos[0] = 1; // Y
		playerPos[1] = 1; // X
		memory.get(playerPos[0]).set(playerPos[1], mazeDic.get("Player"));
		surround();
		viewMaze();
	}
	
	public int run() {
		while(!isOnExit()) {
			move();
			viewMaze();
		}
		//System.out.println("You Win!");
		return getNumMoves();
	}
	
	private void move() {
		if((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Open"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled")))) { // UP
			moveMemory(0, "Traveled");
		} else if((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Open"))) &&
				(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled")))) { // RIGHT
			moveMemory(1, "Traveled");
		} else if((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Open"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled")))) { // DOWN
			moveMemory(2, "Traveled");
		} else if((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Open")))) { // LEFT
			moveMemory(3, "Traveled");
		} else if((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled")) || memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("TraveledTwice"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled")) || memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("TraveledTwice"))) &&
				(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Closed")) || memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled")) || memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("TraveledTwice"))) &&
				(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Closed")) || memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled")) || memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("TraveledTwice")))) { // DEAD END
			backTrack();
		} else {
			int direction = (int)(Math.random() * 4);
			
			if(direction == 0 && memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Open"))) {
				moveMemory(0, "Traveled");
			} else if(direction == 1 && memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Open"))) {
				moveMemory(1, "Traveled");
			} else if(direction == 2 && memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Open"))) {
				moveMemory(2, "Traveled");
			} else if(direction == 3 && memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Open"))) {
				moveMemory(3, "Traveled");
			}
		}
	}
		
	private void backTrack() {
		if(memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) { // UP
			moveMemory(0, "TraveledTwice");
		} else if(memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled"))) { // RIGHT
			moveMemory(1, "TraveledTwice");
		} else if(memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) { // DOWN
			moveMemory(2, "TraveledTwice");
		} else if(memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled"))) { // LEFT
			moveMemory(3, "TraveledTwice");
		}
	}
	
	private void moveMemory(int direction, String crum) {
		
		if(direction == 0) {
			this.moveUp();
			playerPos[0]--;
		} else if(direction == 1) {
			this.moveRight(); //this only moves one map
			playerPos[1]++;
		} else if(direction == 2) {
			this.moveDown();
			playerPos[0]++;
		} else if(direction == 3) {
			this.moveLeft();
			playerPos[1]--;
		}
		memory.get(playerPos[0]).set(playerPos[1], mazeDic.get("Player"));
		leaveCrum(direction, crum);
		
		if(playerPos[0] == 0 || playerPos[1] == (memory.get(0).size() - 1) || playerPos[0] == (memory.size() - 1) || playerPos[1] == 0) {
			addToMaze(direction);
		}
		surround();
	}
	
	private void surround() {
		if(!((memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) || (memory.get(playerPos[0] - 1).get(playerPos[1]).equals(mazeDic.get("TraveledTwice"))))) {
			if(this.canMoveUp()) {
				memory.get(playerPos[0] - 1).set(playerPos[1], mazeDic.get("Open"));
			} else {
				memory.get(playerPos[0] - 1).set(playerPos[1], mazeDic.get("Closed"));
			}
		}
		if(!((memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("Traveled"))) || (memory.get(playerPos[0]).get(playerPos[1] + 1).equals(mazeDic.get("TraveledTwice"))))) {
			if(this.canMoveRight()) {
				memory.get(playerPos[0]).set(playerPos[1] + 1, mazeDic.get("Open"));
			} else {
				memory.get(playerPos[0]).set(playerPos[1] + 1, mazeDic.get("Closed"));
			}
		}
		if(!((memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("Traveled"))) || (memory.get(playerPos[0] + 1).get(playerPos[1]).equals(mazeDic.get("TraveledTwice"))))) {
			if(this.canMoveDown()) {
				memory.get(playerPos[0] + 1).set(playerPos[1], mazeDic.get("Open"));
			} else {
				memory.get(playerPos[0] + 1).set(playerPos[1], mazeDic.get("Closed"));
			}
		}
		if(!((memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("Traveled"))) || (memory.get(playerPos[0]).get(playerPos[1] - 1).equals(mazeDic.get("TraveledTwice"))))) {
			if(this.canMoveLeft()) {
				memory.get(playerPos[0]).set(playerPos[1] - 1, mazeDic.get("Open"));
			} else {
				memory.get(playerPos[0]).set(playerPos[1] - 1, mazeDic.get("Closed"));
			}
		}

	}
	
	private void addToMaze(int direction) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < memory.get(0).size(); i++)
			temp.add(mazeDic.get("Open"));
		
		if(direction == 0) {
			System.out.println("added up");
			playerPos[0]++;
			memory.add(0, temp);
		} else if(direction == 1) {
			System.out.println("added right");
			for(ArrayList<String> array : memory)
				array.add(mazeDic.get("Open"));
		} else if(direction == 2) {
			System.out.println("added down");
			memory.add(temp);
		} else if(direction == 3) {
			System.out.println("added left");
			playerPos[1]++;
			for(ArrayList<String> array : memory)
				array.add(0, mazeDic.get("Open"));
		}
	}
	
	private void leaveCrum(int direction, String crum) {
		if(direction == 0) {
			memory.get(playerPos[0] + 1).set(playerPos[1], mazeDic.get(crum));
		} else if(direction == 1) {
			memory.get(playerPos[0]).set(playerPos[1] - 1, mazeDic.get(crum));
		} else if(direction == 2) {
			memory.get(playerPos[0] - 1).set(playerPos[1], mazeDic.get(crum));
		} else if(direction == 3) {
			memory.get(playerPos[0]).set(playerPos[1] + 1, mazeDic.get(crum));
		}
	}
	
	private void viewMaze() {
		for(ArrayList<String> array : memory) {
			for(String value : array) {
				System.out.print("[" + value + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
}
