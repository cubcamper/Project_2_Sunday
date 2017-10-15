/**
 * @author george-patterson
 * 
 */

package project;

public class Board {

	private final int size = 8;
	private Disk[][] board;


	public Board() {
		board = new Disk[size][size]; // GameBoard constructor creates an object that contains an 8x8 array
	}
	
	
	/**
	 * 
	 * @return size of board
	 */
	public int getSize() {
		return size;
	}
	
	
	/**
	 * Fills the  board with blank disks
	 */
	public void fillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Disk(j, i);
			}
		}
	}

	/**
	 * Displays the game board on the screen
	 */
	public void displayBoard() {
		System.out.print("   ");
		for (int i = 0; i < board.length; i++) {
			System.out.print(" " + (i+1) + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(" " + (i+1) + " ");
			for (int j = 0; j < board[i].length; j++) {
				displayDisk(board[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Displays a disk on the Board
	 * @param d	disk that is being displayed
	 */
	public void displayDisk(Disk d) {
		System.out.print("|");
		if (d.getColor() == 'b') {
			System.out.print('b');
		} 
		else if (d.getColor() == 'w') {
			System.out.print('w');
		} 
		else {
			System.out.print(' ');
		}
		System.out.print("|");
	}

	
	/**
	 * 	Places initial game pieces on board in correct configuration in order to begin a game
	 */
	public void start() {
		getDisk(4,4).setColor('w');
		getDisk(5,5).setColor('w');
		getDisk(4,5).setColor('b');
		getDisk(5,4).setColor('b');
	}
	
	
	/**
	 * Checks to see if an (x,y) coordinate pair lies on the gameboard
	 * 
	 * @param x x-coordinate
	 * @param y	y-coordinate
	 * @return true if both coordinates lie in range of board
	 */
	boolean edge(int x, int y) {
		return x>size || y>size || x<1 || y<1;
	}

	/**
	 * Returns a Disk object at a specified (x,y) coordinate pair
	 * 
	 * @param x	x-coordinate
	 * @param y	y-coordinate
	 * @return	disk located at (x,y) coordinate on board
	 */
	public Disk getDisk(int x, int y) {
		if (!edge(x,y)) {
			return board[y-1][x-1];
		}
		else {
			return null;
		}
	}
	

	

}
