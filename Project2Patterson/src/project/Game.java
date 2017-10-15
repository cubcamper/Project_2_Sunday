package project;

import java.util.Scanner;

public class Game {

	private Board board; // data members
	private Player p1;
	private Player p2;
	private Scanner s;
	private String mode;
	private int numMoves;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Scanner getScanner() {
		return s;
	}
	
	public int getNumMoves() {
		return numMoves;
	}
	
	public String getMode() {
		return mode;
	}
	
	public Game() {
		board = new Board();
		s = new Scanner(System.in);
	}
	
	/*
	public Game(Player first, Player second) {
		board = new Board();
		s = new Scanner(System.in);
	}
	*/

	public Game(String m) { // constructor
		board = new Board();
		s = new Scanner(System.in);
		mode = m;

		if (mode == "2p") {
			p1 = new Player('b', 'h'); // p1 is black (b)
			p2 = new Player('w', 'h'); //// p2 is white (w)
		} else if (mode == "1p") {
			p1 = new Player('b', 'h');
			p2 = new Player('w', 'c');
		} else if (mode == "sim") {
			p1 = new Player('b', 'c');
			p2 = new Player('w', 'c');
		}

	}
	
	public void setMode(String m) {
		this.mode = m;
	}
	
	void getModeFromUser() {
		String[] modes = {"1p", "2p", "sim"};
		System.out.println("Which mode of game would you like to play?");
		for (int i=0; i<modes.length; i++) {
			System.out.println(i + ": " + modes[i]);
		}
		int r = s.nextInt();
		if (r>0 && r<modes.length) {
			setMode(modes[r]);
			
			if (r == 0) {
				setP1(new Player('b', 'h'));
				setP2(new Player('w', 'c'));
			}
			if (r == 1) {
				setP1(new Player('b', 'h'));
				setP2(new Player('w', 'h'));
			}
			if (r == 2) {
				setP1(new Player('b', 'c'));
				setP2(new Player('w', 'c'));
			}
			
		}
		
	}

	public static void main(String[] args) {
		
		Scanner host = new Scanner(System.in);
		
		int g = 0;
		int limit = 1;
		
		while (g<limit) {
		
		
		System.out.println("Welcome to Othello!");
		System.out.println("Which mode would you like to play?");
		System.out.println("0 for sim, 1 for single player, 2 for two player");
		
		
		String[] modes = {"sim", "1p", "2p"};
		
		int mode = host.nextInt();
		
		int nSims;
		
		if (modes[mode].equals("sim")) {
			System.out.println("How many simulations?");
			nSims = host.nextInt();
			
		}
		else {
			nSims=1;
		}
		
		int[] spreads = new int[nSims];
		int mean;
		int tot=0;
			
			
			for (int i=0; i<nSims; i++) {
				Game game = new Game(modes[mode]);
				game.getBoard().fillBoard();
				game.getBoard().start();
				
				if (game.getMode() != "sim") {
					game.getBoard().displayBoard();
				}

				
				Player currPlayer = game.getP1();

				while (!game.gameIsOver()) {
					
					if (game.getBasePieces(currPlayer).length==0) {
						System.out.println(currPlayer.getColor() + " has no base pieces!");
						System.out.println(currPlayer.getColor() + " passes because they have no move!");
						currPlayer = game.getOtherPlayer(currPlayer);
					}
					
					game.conductMove(currPlayer);
					
					
					currPlayer = game.getOtherPlayer(currPlayer);
					

				}
				
				game.showScore();
				game.detWinner();
				
				spreads[i] = game.getSpread();
				tot+=spreads[i];
				
			}
			
			mean=tot/nSims;
			if (nSims != 1) {
				System.out.println("The mean spread is " + mean);
				
				int stDev;
				int varTot=0;
				for (int i=0; i<nSims; i++) {
					varTot+=Math.pow(spreads[i]-mean, 2);
				}
				stDev = (int)Math.sqrt(varTot/nSims);
				System.out.println("The standard deviation is " + stDev);
				
			}
			else {
				System.out.println("The spread is " + mean);
			}
			
			System.out.println();
			
			System.out.println("Spread: | Occurences: ");
			
			for (int i = -64; i<64; i++) {
				int counter=0;
				for (int j = 0; j < spreads.length; j++) {
					if (spreads[j] == i) {
						counter++;
					}
				}
				if (counter != 0) {
				//	System.out.println("Spread: " + i + " | " + counter + " games");
					System.out.println(i+ "\t" + counter);
				}
			}
			
			
			
			System.out.println("Would you like to play again. Give me a 1 for yes, any other number for no");
			
			int resp = host.nextInt();
			
			if (resp == 1) {
				limit++;
			}
			
			g++;
			
		}
		
		
		
		System.out.println("Thanks for playing Othello. Re-run the program if you want to start over from this point.");
			
			
			
			/*
			System.out.println("Congratulations, game number " + i + " has concluded!");
			System.out.println("Spread of game " + i + ": " + game.getSpread());
			
			spreads[i] = game.getSpread();

		}
		*/
		/*
		int[] occurencesOfEachSpread= new int [129]; 
		
		for (int i = 0; i < numGames; i++) {
			System.out.println(spreads[i]);
		}
		*/
		
			/*
		for (int i = -64; i<64; i++) {
			int counter=0;
			for (int j = 0; j < numGames; j++) {
				if (spreads[j] == i) {
					counter++;
				}
			}
			if (counter != 0) {
				System.out.println("Spread: " + i + " | " + counter + " games");
			}
		}
		*/
		
		

	}
	
	private void setUp() {
		
	}
	
	private void finish() {
		
	}
	
	
	
	private Player getOtherPlayer(Player p) {
		if (p == p1) {
			return p2;
		}
		else if (p == p2) {
			return p1;
		}
		else {
			return null;
		}
	}

	private void playAGame(String gameType) {

	}

	private boolean boardFull() {
		boolean retVal = true;
		for (int i = 1; i < board.getSize() + 1; i++) {
			for (int j = 1; j < board.getSize() + 1; j++) {
				if (board.getDisk(i, j).getColor() == ' ') {
					return false;
				}
			}
		}
		return retVal;
	}

	private boolean gameIsOver() {
		Player p1 = getP1();
		Player p2 = getP2();

		if (getBasePieces(p1).length == 0 && getBasePieces(p2).length == 0 || boardFull()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Prints the places on the game board in which p can place a new piece
	 * 
	 * @param p
	 *            player of whom's target pieces are printed by this method
	 */
	private void listTargetPieces(Player p) {
		// System.out.println("You can move to these pieces: ");
		String[] directions = { "up", "down", "left", "right", "up and left", "up and right", "down and left",
				"down and right" };
		Disk[] bases = getBasePieces(p);
		// int[][] directions = new int[8][8];
		for (Disk b : bases) {
			if (canMove(b, p)) {
				int[] dirs = getDirectionsForBasePiece(b);
				for (int d : dirs) {
					// System.out.println("You can move to the " + directions[d] + "(" + d + ")" + "
					// from " + b);
					int[] dir = dirToDxDy(d);
					Disk target = getTarget(b, dir[0], dir[1]);
					// System.out.println(target);
				}
			}
		}

	}

	public int[] getScores() {
		int black_pieces = 0;
		int white_pieces = 0;
		for (int i = 1; i < board.getSize() + 1; i++) {
			for (int j = 1; j < board.getSize() + 1; j++) {
				if (board.getDisk(i, j).getColor() == p1.getColor()) {
					// p1.setScore(p1.getScore()+1);
					black_pieces++;
				} else if (board.getDisk(i, j).getColor() == p2.getColor()) {
					// p2.setScore(p2.getScore()+1);
					white_pieces++;
				}
			}
		}
		int[] ret = new int [2];
		ret[0] = black_pieces;
		ret[1] = white_pieces;
		return ret;
		//System.out.println("Black: " + black_pieces + " / White: " + white_pieces);
	}
	
	public void showScore() {
		int[] scores = getScores();
		System.out.println("Black: " + scores[0] + " / White: " + scores[1]);
	}
	
	public void detWinner() {
		int[] scores = getScores();
		if (scores[0] > scores[1]) {
			System.out.println("Black wins!");
		}
		else if (scores[1] > scores[0]) {
			System.out.println("White wins!");
		}
		else if (scores[0] == scores[1]) {
			System.out.println("It's a tie!");
		}
	}

	public int getSpread() {
		int black_pieces = 0;
		int white_pieces = 0;
		for (int i = 1; i < board.getSize() + 1; i++) {
			for (int j = 1; j < board.getSize() + 1; j++) {
				if (board.getDisk(i, j).getColor() == p1.getColor()) {
					// p1.setScore(p1.getScore()+1);
					black_pieces++;
				} else if (board.getDisk(i, j).getColor() == p2.getColor()) {
					// p2.setScore(p2.getScore()+1);
					white_pieces++;
				}
			}
		}
		int spread = black_pieces - white_pieces;
		return spread;
	}

	public void cleanUpMove(Disk d, int dx, int dy) {

		int x = d.getX();
		int y = d.getY();

		int currX = x + dx;
		int currY = y + dy;

		char myColor = d.getColor();

		while (!edge(currX, currY) && board.getDisk(currX, currY).getColor() != ' ') {
			if (board.getDisk(currX, currY).getColor() == myColor) {
				// System.out.println("Flip pieces between " + d + " and " +
				// board.getDisk(currX, currY));
				flipPieces(d, board.getDisk(currX, currY), dx, dy);
				break;
			}
			currX += dx;
			currY += dy;
		}

		// }

		// }

		// after target piece is placed and pieces between base and target are flipped
		// to be base's color:
		// check in every direction (maybe, except the direction that was already
		// flipped, ok if you flip in every direction)
		// for each direction: check to see if there are pieces opposite base's color
		// all the opposite color of base until it hits one of base's colors:
		// when this is true:
		// flip all the pieces between base and "target" (the piece of opposite color)
		// when this is false:
		// leave this direction alone

		// return true;
	}

	/**
	 * Tells whether or not a new disk of same color can be placed in a specified
	 * direction away from an existing disk
	 *
	 * @param d
	 *            existing disk on board
	 * @param dx
	 *            the x direction in which the new disk is placed away from d
	 * @param dy
	 *            the y direction in which the new disk is placed away from d
	 * @return true if placing a new disk of d's color in certain direction is
	 *         allowable, false if it is not
	 */

	boolean isValidMove(Disk d, int dx, int dy) {
		int x = d.getX();
		int y = d.getY();
		char myColor = d.getColor();
		char otherColor = d.getOtherColor();

		boolean retVal = false;

		if (board.getDisk(x + dx, y + dy) != null) {

			if (board.getDisk(x, y).getColor() == ' ' || edge(x, y)
					|| board.getDisk(x + dx, y + dy).getColor() == myColor) {
				return false;
			}

			int currX = x + dx;
			int currY = y + dy;

			while (!edge(currX, currY)) {

				if (board.getDisk(currX, currY) != null) {

					if (board.getDisk(currX, currY).getColor() == myColor) {

						return false;

					}
					if (board.getDisk(currX, currY).getColor() == otherColor) {
						retVal = true;
					}
					if (board.getDisk(currX, currY).getColor() == ' ') { // board.getDisk(currX,currY) is where new
																			// piece is
																			// placed
						break;
					}

				}

				else {
					return false;
				}

				currX += dx;
				currY += dy;

				if (board.getDisk(currX, currY) == null) {
					return false;
				}

			}

		}

		else {
			return false;
		}

		return retVal;

	}

	/**
	 * Allows user to make move
	 * 
	 * @param p
	 *            player who makes move
	 */
	private void conductMove(Player p) {

		// listTargetPieces(p);
		
		if (p.getType() != 'c') {
		System.out.println("It is " + p.getColor() + "'s turn!");
		}

		displayBasePieces(p);

		int[] base = getBaseFromUser(p);
		int x = base[0];
		int y = base[1];

		// check all of the board.getDisk(x,y) to make sure that you are not calling
		// parameters out of bounds

		if (canMove(board.getDisk(x, y), p)) {

			int[] move = getMove(board.getDisk(x, y), p); // {dx, dy} direction of move

			// try:

			if (!edge(x, y)) {

				try {
					Disk target = getTarget(board.getDisk(x, y), move[0], move[1]);

					// System.out.println("The target piece is located at: " + target);

					flipPieces(board.getDisk(x, y), target, move[0], move[1]);

					target.setColor(p.getColor()); // places new piece (works)

					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							cleanUpMove(target, i, j);
						}
					}
					
					if (this.getMode() != "sim") {
						board.displayBoard();
						showScore();
						System.out.println(p.getColor() + " placed a new piece at " + target);
					}

				} catch (NullPointerException e) {

				}

				// cleanUpMove(target, 1, 0);

			}

			for (int i = 0; i < 8; i++) {
				board.getDisk(x, y).setPossibleMove(i, false);
			}
			
			
			
			
			
			// displayScore();

		} else if (canMove(board.getDisk(x, y), p) == false) {
			System.out.println("Hey, you cannot move that piece!");
		}
		
		System.out.println();

	}

	/**
	 * Gets input from user in regard to which direction that he or she would like
	 * to place a new disk relative to an existing disk
	 * 
	 * @param d
	 *            existing disk
	 * @return the direction in which the user selects to place a new disk relative
	 *         to an existing disk
	 */
	int[] getMove(Disk d, Player p) {

		if (p.getType() == 'h') {
			System.out.println("Which direction would you like to move in? Give me a number.");
		}

		String[] directions = { "up", "down", "left", "right", "up and left", "up and right", "down and left",
				"down and right" };

		int[] directionOptions = getDirectionsForBasePiece(d);

		if (p.getType() == 'h') {
			for (int i = 0; i < directionOptions.length; i++) {
				System.out.println(directionOptions[i] + ": " + directions[directionOptions[i]]);
			}
		}

		int dir=0;

		if (p.getType() == 'h') {
			
			
			boolean check = false;
			
			while (check == false) {
				dir = s.nextInt();
				for (int i=0; i<directionOptions.length; i++) {
					if (dir == directionOptions[i]) {
						check = true;
						break;
					}
				}
			
				if (check == false ) {
						System.out.println("You can't do that.");
			
					System.out.println("Pick one of these directions. Enter the corresponding number.");
					for (int i = 0; i < directionOptions.length; i++) {
						System.out.println(directionOptions[i] + ": " + directions[directionOptions[i]]);
					}
				}
			}
		} 
		else if (p.getType() == 'c') {
			int choice = (int)(Math.random()*directionOptions.length);
			dir = directionOptions[choice];
		} 
		
		/*
		else {
			dir = s.nextInt();
		}
		*/
		/*
		if (p.getType() == 'h') {
			System.out.println("You have chosen to move in the " + directions[dir] + " direction.");
		}
		*/

		return dirToDxDy(dir);

	}

	/**
	 * Determines directions in which a new disk can be placed relative to an
	 * existing disk
	 * 
	 * @param d
	 *            existing disk
	 * @return directions in which a new disk can be placed relative to an existing
	 *         disk
	 */
	private int[] getDirectionsForBasePiece(Disk d) {
		String[] directions = { "up", "down", "left", "right", "up and left", "up and right", "down and left",
				"down and right" };
		int[] myDirections;
		int size = 0;

		for (int i = 0; i < directions.length; i++) {
			if (d.getPossibleMove(i)) {
				size++;
			} else if (!d.getPossibleMove(i)) {
				directions[i] = "";
			}
		}

		myDirections = new int[size];

		int c = 0;

		for (int i = 0; i < directions.length; i++) {
			if (directions[i] != "") {
				myDirections[c] = i;
				c++;
			}
		}

		return myDirections;

	}

	/**
	 * Converts an (x-direction,y-direction) pair to a numerical value
	 * 
	 * @param dir
	 *            number that corresponds to direction that target piece is
	 *            placedaway from base piece
	 * @return retVal[] in which first int is delta X, and second int is delta Y
	 */

	private int[] dirToDxDy(int dir) {
		int dx = 0;
		int dy = 0;
		int[] retVal = new int[2];

		if (dir == 0) {
			dx = 0;
			dy = -1;
		}
		if (dir == 1) {
			dx = 0;
			dy = 1;
		}
		if (dir == 2) {
			dx = -1;
			dy = 0;
		}
		if (dir == 3) {
			dx = 1;
			dy = 0;
		}
		if (dir == 4) {
			dx = -1;
			dy = -1;
		}
		if (dir == 5) {
			dx = 1;
			dy = -1;
		}
		if (dir == 6) {
			dx = -1;
			dy = 1;
		}
		if (dir == 7) {
			dx = 1;
			dy = 1;
		}

		retVal[0] = dx;
		retVal[1] = dy;

		return retVal;
	}

	/**
	 * Returns the disks that p can make a move on the basis of
	 * 
	 * @param p
	 *            player of whom the method determines which disks p can make a move
	 *            on the basis of
	 * @return the disks that p can make a move on the basis of
	 */

	private Disk[] getBasePieces(Player p) {
		int size = 0;

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (canMove(board.getDisk(i, j), p)) {
					// System.out.println(board.getDisk(i, j));
					size++;
				}
			}
		}

		Disk[] baseOptions = new Disk[size];
		int a = 0;

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (canMove(board.getDisk(i, j), p)) {
					baseOptions[a] = board.getDisk(i, j);
					a++;
				}
			}
		}

		return baseOptions;

	}

	private void displayBasePieces(Player p) {
		Disk[] myBases = getBasePieces(p);

		if (p.getType() == 'h') {
			for (int i = 0; i < myBases.length; i++) {
				System.out.println(i + ": " + myBases[i]);
			}
		}
	}

	/**
	 * Asks the user which disk he or she would like to place a new disk based off
	 * 
	 * @param p
	 *            player that is asked which disk he or she would like to make his
	 *            move on the basis of
	 * @return the (x,y) coordinate pair of the base disk selected by player p
	 */

	private int[] getBaseFromUser(Player p) {
		if (p.getType() == 'h') {
			//System.out.println("It is " + p.getColor() + "\'s turn");
			System.out.println(p.getColor() + ", which disk would you like to move from?\nGive me a number listed above.");
		}

		Disk[] basePieces = getBasePieces(p);
		int size = basePieces.length;

		boolean q = false;
		int n = 0;

		while (q == false) {
			if (p.getType() == 'h') {
				n = s.nextInt();
			}

			else if (p.getType() == 'c') {
				// n=0; //code this to make random, not always pick zero
				n = (int)( Math.random() * (size-1));
				while (n<0 || n>size-1) {
					n = (int)( Math.random() * (size-1));
				}

			}

			if (n < 0 || n > size - 1) {
				System.out.println("Hey. That's not an option! Give me one of the numbers listed above!");
			} 
			else if (n >= 0 && n < size) {
				q = true;
			}
		}

		Disk b = basePieces[n];

		/*
		  if (p.getType()=='c') {
			  System.out.println("Computer player chooses: " + b);
		  }
		  */
		

		int[] base = { b.getX(), b.getY() };
		return base;
	}

	/**
	 * Flips all of the disk between two disks
	 * 
	 * @param base
	 *            one of the existing disks that will have pieces flipped between
	 *            itseslf and another disk
	 * @param target
	 *            another existing disk that will have pieces flipped between itself
	 *            and the other disk
	 * @param dx
	 *            x direction in which target is located relative to base
	 * @param dy
	 *            y direction in which target is located relative to base
	 */

	void flipPieces(Disk base, Disk target, int dx, int dy) {
		// Disk curr=board.getDisk(base.getX()+dx, base.getY()+dy);
		int x = dx;
		int y = dy;

		// System.out.println(board.getDisk(base.getX()+x, base.getY()+dy));

		while (board.getDisk(base.getX() + x, base.getY() + y).getColor() != target.getColor()) {

			// System.out.println("Disk in between: " + board.getDisk(base.getX()+x,
			// base.getY()+y));
			board.getDisk(base.getX() + x, base.getY() + y).flip();
			x += dx;
			y += dy;
		}

	}

	/**
	 * Returns true if disk can have a new disk of p's color placed in a direction
	 * away from existing disk of p's color If true, this method records which
	 * directions away from p that the new disk of p's color may be placed
	 * 
	 * @param d
	 *            the existing disk that a new disk could be placed in a
	 *            directionaway from
	 * @param p
	 *            the player who wants to place a new disk
	 * @return if a new disk of same color can be placed a direction away from
	 *         existing disk of p's color
	 * 
	 */
	boolean canMove(Disk d, Player p) { // comparable to the boolean validateMove() method, returns true if this piece
										// can move
		boolean retVal = false;
		if (p.getColor() != d.getColor()) { // player can only move their pieces
			return retVal;
		} else {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (isValidMove(d, i, j) && !edge(d.getX() + i, d.getY() + j)) {

						if (i == -1) {
							if (j == -1) {
								d.setPossibleMove(4, true); // move to up left
							} else if (j == 0) {
								d.setPossibleMove(2, true); // move to left
							} else if (j == 1) {
								d.setPossibleMove(6, true); // move down left
							}
						}
						if (i == 0) {
							if (j == -1) {
								d.setPossibleMove(0, true); // move up
							}
							if (j == 1) {
								d.setPossibleMove(1, true); // move down
							}
						}
						if (i == 1) {
							if (j == -1) {
								d.setPossibleMove(5, true); // move to up right
							} else if (j == 0) {
								d.setPossibleMove(3, true); // move right
							} else if (j == 1) {
								d.setPossibleMove(7, true); // move down right
							}
						}

						retVal = true;
					}
				}
			}
		}
		return retVal;
	}

	/*
	 * private Disk getTarget(Disk d, int dx, int dy) { //d is base piece int x =
	 * d.getX(); int y = d.getY();
	 * 
	 * 
	 * int currX=x+dx; int currY=y+dy;
	 * 
	 * if (edge(currX,currY)) { return null; }
	 * 
	 * while (board.getDisk(currX, currY).getColor() != ' ' && !edge(currX, currY))
	 * { currX+=dx; currY+=dy; }
	 * 
	 * 
	 * 
	 * if (board.getDisk(currX,currY).getColor()==' ') {
	 * //board.getDisk(currX,currY) is where new piece is placed return
	 * board.getDisk(currX, currY);
	 * 
	 * } else { return null; }
	 * 
	 * }
	 */

	/**
	 * Returns a "blank" disk that can be placed in a specified direction away from
	 * an existing disk
	 *
	 * @param d
	 *            an existing disk that will have a new disk of same color placed a
	 *            distance d away from it
	 * @param dx
	 *            the x direction in which the new disk is placed away from d
	 * @param dy
	 *            the y direction in which the new disk is placed away from d
	 * @return the "blank" disk that can be placed in a specified direction away
	 *         from an existing disk
	 */
	private Disk getTarget(Disk d, int dx, int dy) { // d is base piece

		// this is the method to fix, make it not call board.getDisk(), with input out
		// of bounds
		// have it handle this problem

		int x = d.getX();
		int y = d.getY();

		int currX = x + dx;
		int currY = y + dy;

		if (!edge(currX, currY)) {

			while (board.getDisk(currX, currY).getColor() != ' ') {
				currX += dx;
				currY += dy;
			}

			if (board.getDisk(currX, currY).getColor() == ' ') { // board.getDisk(currX,currY) is where new piece is

				return board.getDisk(currX, currY);

			}

		}

		return null;

	}

	/**
	 * Tells if an (x,y) coordinate pair exists on the board
	 * 
	 * @param x
	 *            x coordinate that is being checked to see if it exists on board
	 * @param y
	 *            y coordinate that is being checked to see if it exists on board
	 * @return true if the (x,y) coordinate pair exists on the board, false if (x,y)
	 *         coordinate does not exist on board
	 */
	boolean edge(int x, int y) {
		return x > 8 || y > 8 || x < 1 || y < 1;
	}

}
