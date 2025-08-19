import java.util.*;

public class RightHandRuleRobot extends Player{
	private int currentFace = 0;

	public RightHandRuleRobot(MazeGen maze) {
		super(maze);
	}
	
	public int run() {
		while(!isOnExit()) {
			move();
			this.getMaze().PrintMaze();
		}
		//System.out.println("You Win!");
		return getNumMoves();
	}
	
	private void move() {
		//System.out.println(currentFace);
		if(openRight()) {
			//System.out.println("Saw Open Right");
			turnRight();
			moveForward();
		} else if(openForward()) {
			//System.out.println("Went Forward");
			moveForward();
		} else {
			//System.out.println("Turned Around");
			turnRight();
			turnRight();
		}
	}
	
	private int getCurrentFace() {
		return currentFace;
	}
	
	private void setCurrentFace(int currentFace) {
		this.currentFace = currentFace;
	}
	
	private void turnRight() {
		if(getCurrentFace() == 0) {
			setCurrentFace(1);
		} else if(getCurrentFace() == 1) {
			setCurrentFace(2);
		} else if(getCurrentFace() == 2) {
			setCurrentFace(3);
		} else if(getCurrentFace() == 3) {
			setCurrentFace(0);
		}
		//System.out.println("face after:" + currentFace);
	}
	private void turnLeft() {
		if(getCurrentFace() == 0) {
			setCurrentFace(3);
		} else if(getCurrentFace() == 1) {
			setCurrentFace(0);
		} else if(getCurrentFace() == 2) {
			setCurrentFace(1);
		} else if(getCurrentFace() == 3) {
			setCurrentFace(2);
		}
		//System.out.println("face after:" + currentFace);
	}
	private boolean openRight() {
		if(this.getCurrentFace() == 0) {
			return canMoveRight();
		} else if(this.getCurrentFace() == 1) {
			return canMoveDown();
		} else if(this.getCurrentFace() == 2) {
			return canMoveLeft();
		} else if(this.getCurrentFace() == 3) {
			return canMoveUp();
		}
		return false;
	}
	private boolean openLeft() {
		if(this.getCurrentFace() == 0) {
			return canMoveLeft();
		} else if(this.getCurrentFace() == 1) {
			return canMoveUp();
		} else if(this.getCurrentFace() == 2) {
			return canMoveRight();
		} else if(this.getCurrentFace() == 3) {
			return canMoveDown();
		}
		return false;
	}
	private boolean openForward() {
		if(this.getCurrentFace() == 0) {
			return canMoveUp();
		} else if(this.getCurrentFace() == 1) {
			return canMoveRight();
		} else if(this.getCurrentFace() == 2) {
			return canMoveDown();
		} else if(this.getCurrentFace() == 3) {
			return canMoveLeft();
		}
		return false;
	}
	private void moveForward() {
		if(getCurrentFace() == 0) {
			//System.out.println("move up");
			moveUp();
		} else if(getCurrentFace() == 1) {
			//System.out.println("move right");
			moveRight();
		} else if(getCurrentFace() == 2) {
			//System.out.println("move down");
			moveDown();
		} else if(getCurrentFace() == 3) {
			//System.out.println("move left");
			moveLeft();
		}
	}
}
