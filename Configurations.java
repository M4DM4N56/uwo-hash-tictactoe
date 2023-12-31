
public class Configurations {
	
	private int board_size;
	private int lengthtoWin;
	private int max_levels;
	private char[][] gameBoard;

	
	// contructor method
	public Configurations (int board_size, int lengthtoWin, int max_levels) {
		this.board_size = board_size;
		this.lengthtoWin = lengthtoWin;
		this.max_levels = max_levels;
		
		// creates a board with the given dimensions
		gameBoard = new char[board_size][board_size];
		
		// fills in each space with an empty ' ' by default
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				gameBoard[row][col] = ' ';
			} // for col
		} // for row 	
		
	} // constructor
	
	
	// create the hash dictionary
	public HashDictionary createDictionary() {
		return new HashDictionary(board_size);
	} // method HashDictionary
	
	
	public int repeatedConfiguration(HashDictionary hashTable) {
		String configuration = "";
		
		// concatenate the elements of the gameboard into a string
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				configuration += gameBoard[row][col];
			} // for
		} // for
		
		return hashTable.get(configuration);
	} // method repeatedConfiguration
	
	
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		String configuration = "";
		
		// concatenate the elements of the gameboard into a string
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				configuration += gameBoard[row][col];
			} // for
		} // for
		
		hashDictionary.put(new Data(configuration, score));
	} // method addConfiguration
	
	
	// sets the given tile as the symbol that has been placed there
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	} // method savePlay
	
	
	// checks if the tile is empty or taken
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] ==  ' ') {
			return true;
		} // if
		return false;
	} // method squareIsEmpty
	
	
	// check if computer or human has won
	public boolean wins(char symbol) {
		int counter = 0;
		
		
		for (int row = 1; row < board_size-1; row++) {
			for ( int col = 1; col < board_size-1; col++) {				
				if (gameBoard[row][col] ==  symbol) {
					
					
					// CHECKING FOR CROSS SHAPES
					// checks if there is at least one tile directly above, below, left and right of the center (a valid cross)
					if (gameBoard[row][col+1] ==  symbol && gameBoard[row][col-1] ==  symbol && gameBoard[row+1][col] ==  symbol && gameBoard[row-1][col] ==  symbol) {
						counter += 5;
						
						// the next 4 for loops increment in all 4 directions and count how many consecutive symbols there are in that direction
						
						
						// Climb up from the center piece
						for (int upper = 1; upper < board_size - 1; upper++) {
						    if (row - upper >= 0 && gameBoard[row - upper][col] == symbol) {counter += 1;} 
						    else {break;}
						}

						// Climb down from the center piece
						for (int lower = 1; lower < board_size - 1; lower++) {
						    if (row + lower < board_size && gameBoard[row + lower][col] == symbol) {counter += 1;} 
						    else {break;}
						}

						// Move left from the center piece
						for (int left = 1; left < board_size - 1; left++) {
						    if (col - left >= 0 && gameBoard[row][col - left] == symbol) {counter += 1;} 
						    else {break;}
						}

						// Move right from the center piece
						for (int right = 1; right < board_size - 1; right++) {
						    if (col + right < board_size && gameBoard[row][col + right] == symbol) {counter += 1;} 
						    else {break;}
						}

					} // CROSS IF

					
					// CHECKING FOR X SHAPES
					// checks if there is at least one tile directly upright, downright, downleft (a valid cross)
					if (gameBoard[row+1][col+1] ==  symbol && gameBoard[row-1][col-1] ==  symbol && gameBoard[row+1][col-1] ==  symbol && gameBoard[row-1][col+1] ==  symbol) {
						
						// Move up-right from the center piece
					    for (int upRight = 1; upRight < board_size - 1; upRight++) {
					        if (row - upRight >= 0 && col + upRight < board_size && gameBoard[row - upRight][col + upRight] == symbol) {counter += 1;} 
					        else {break;}
					    }

					    // Move up-left from the center piece
					    for (int upLeft = 1; upLeft < board_size - 1; upLeft++) {
					        if (row - upLeft >= 0 && col - upLeft >= 0 && gameBoard[row - upLeft][col - upLeft] == symbol) {counter += 1;} 
					        else {break;}
					    }

					    // Move down-right from the center piece
					    for (int downRight = 1; downRight < board_size - 1; downRight++) {
					        if (row + downRight < board_size && col + downRight < board_size && gameBoard[row + downRight][col + downRight] == symbol) {counter += 1;} 
					        else {break;}
					    }

					    // Move down-left from the center piece
					    for (int downLeft = 1; downLeft < board_size - 1; downLeft++) {
					        if (row + downLeft < board_size && col - downLeft >= 0 && gameBoard[row + downLeft][col - downLeft] == symbol) {counter += 1;} 
					        else {break;}
					    }
					       
					} // DIAGONAL IF
					
				} // CENTERPIECE IF
				
			} // for
		} // for
		
		// if there are >= the minimum required pieces for a win, return win
		if (counter >=  lengthtoWin) {
			return true;
		} // if
	
		return false;		
		
	} // method wins
	
	
	// checking if there is a draw
	public boolean isDraw() {
		// checks if there are any empty spaces
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				
				// will only draw is the whole board is filled
				if (gameBoard[i][j] == ' ') {return false;}
				
			} //for
		} // for
		
		return true;
	} // method isDraw
	
	
	// goes through the wins and draw functions to see if anyone has won or drawn yet
	public int evalBoard() {
		if (wins('O')) {return 3;} 
		else if (wins('X')) {return 0;} 
		else if (wins('O') == false && wins('X') == false && isDraw()) {return 2;}
	
		return 1;
	} // method evalBoard	
	
	
	
} // class Configurations

