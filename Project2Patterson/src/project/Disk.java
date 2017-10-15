/*
 * @author george-patterson
 */

package project;

public class Disk {
	
	private char color;
	private int row;
	private int column;
	private boolean possibleMoves[];
	
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Disk(int x, int y) {
		color=' ';
		row=y;
		column=x;
		possibleMoves=new boolean[8];
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[] getPossibleMoves() {
		return possibleMoves;
	}
	
	public boolean getPossibleMove(int i) {
		return possibleMoves[i];
	}
	
	public void setPossibleMove(int i, boolean bool) {
		if (bool==true) {
			possibleMoves[i]=true;
		}
		else if (bool==false) {
			possibleMoves[i]=false;
		}
	}
	
	public void flip() {
		
		for (int i=0; i<possibleMoves.length; i++) {
			possibleMoves[i] = false;
		}
		
		if (color=='b') {
			color='w';
		}
		else if (color=='w') {
			color='b';
		}
		
		
	}
	
	public char getColor() {
		return color;
	}
	
	public char getOtherColor() {
		if (color=='b') {
			return 'w';
		}
		else if (color=='w') {
			return 'b';
		}
		else {
			return ' ';
		}
	}
	
	public int getX()  {
		return column+1;
	}
	
	public int getY() {
		return row+1;
	}
	
	public void setColor(char c) {
		color=c;
	}
	
	public String toString() {
		return color + " at (x,y): (" + getX() + "," + getY() + ")";
	}
}
