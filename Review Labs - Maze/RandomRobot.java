import java.util.*;

public class RandomRobot extends Player{
	int num;
	
	public RandomRobot(MazeGen maze) {
		super(maze);
	}
	
	public int run() {
		while(!isOnExit()) {
			move();
			this.getMaze().PrintMaze();
		}
		System.out.println("You Win!");
		return getNumMoves();
	}
	
	private void move() {
		num = (int)(Math.random() * 4);
		
		if(num == 0) {
			moveUp();
		} else if(num == 1) {
			moveDown();
		} else if(num == 2) {
			moveLeft();
		} else if(num == 3) {
			moveRight();
		}
	}
}
