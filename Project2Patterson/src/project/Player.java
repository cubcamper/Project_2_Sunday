/*
 * @author george-patterson
 */

package project;

public class Player {
	
	
	private char color;
	private int score=0;
	
	private char type;
	
	
	
	public Player(char c, char t) {		//constructor method for a Player
		color=c;
		type = t;
		//'h' for human
		//'c' for computer
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int s) {
		this.score=s;
	}
	
	public char getColor() {
		return color;
	}
	
	public char getType() {
		return type;
	}
	
	public int[] chooseBase(Disk[] myBases) {
		Disk base = myBases[0];
		int x = base.getX();
		int y = base.getY();
		int[] b = {x,y};
		return b;
	}
	
	public int chooseDirection(int[] possibleDirections) {
		if (possibleDirections.length==1) {
			return possibleDirections[0];
		}
		else {
			//choose an option randomly;
			return possibleDirections[0];
		}
	}
	

}
