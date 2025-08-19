import java.util.*;

public class Player {
	MazeGen maze;
	private int numMoves = 0;
	
	public Player(MazeGen maze) {
		this.maze = maze;
	}
	
	// need to add if reach exit
	public boolean canMoveUp() {
		int[] up = getUpPos();
		return (up[0] != -1 && up[1] != -1) && (maze.getMaze()[up[0]][up[1]] != 0);
	}
	public boolean canMoveDown() {
		int[] down = getDownPos();
		return (down[0] != -1 && down[1] != -1) && (maze.getMaze()[down[0]][down[1]] != 0);
	}
	public boolean canMoveLeft() {
		int[] left = getLeftPos();
		return (left[0] != -1 && left[1] != -1) && (maze.getMaze()[left[0]][left[1]] != 0);
	}
	public boolean canMoveRight() {
		int[] right = getRightPos();
		return (right[0] != -1 && right[1] != -1) && (maze.getMaze()[right[0]][right[1]] != 0);
	}
	
	public int[] getUpPos() {
		int[] up = new int[2];
		up[0] = maze.getPlayerPos()[0] - 1;
		up[1] = maze.getPlayerPos()[1];
		return up;
	}
	public int[] getDownPos() {
		int[] down = new int[2];
		down[0] = maze.getPlayerPos()[0] + 1 >= maze.getLength() ? -1 : maze.getPlayerPos()[0] + 1;
		down[1] = maze.getPlayerPos()[1];
		return down;
	}
	public int[] getLeftPos() {
		int[] left = new int[2];
		left[0] = maze.getPlayerPos()[0];
		left[1] = maze.getPlayerPos()[1] - 1;
		return left;
	}
	public int[] getRightPos() {
		int[] right = new int[2];
		right[0] = maze.getPlayerPos()[0];
		right[1] = maze.getPlayerPos()[1] + 1 >= maze.getWeight() ? -1 : maze.getPlayerPos()[1] + 1;
		return right;
	}
	
	private void move(int pos1, int pos2) {
		numMoves++;
		maze.setMaze(maze.getPlayerPos()[0], maze.getPlayerPos()[1], 1);
		maze.setPlayerPos(pos1, pos2);
	}
	
	public void moveUp() {
		if(canMoveUp()) {
			move(getUpPos()[0], getUpPos()[1]);
		}
	}
	public void moveDown() {
		if(canMoveDown()) {
			move(getDownPos()[0], getDownPos()[1]);
		}
	}
	public void moveLeft() {
		if(canMoveLeft()) {
			move(getLeftPos()[0], getLeftPos()[1]);
		}
	}
	public void moveRight() {
		if(canMoveRight()) {
			move(getRightPos()[0], getRightPos()[1]);
		}
	}
	
	public int getNumMoves() {
		return numMoves;
	}
	
	public boolean isOnExit() {
		return maze.isOnExit();
	}
	
	public MazeGen getMaze() {
		return maze;
	}	
}
