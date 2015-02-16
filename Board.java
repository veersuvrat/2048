import java.util.LinkedList;

public class Board {

	// The 4 by 4 board for 2048 represented as an array of arrays. myBoard[0][0] is
	// the bottom left block.
	private BlockFor2048[][] myBoard;
	private int ScoreValue;

	/**
	 * Starts up the GUI.
	 */
	public static void main(String[] args) {
		GUI.initGUI();
	}

	/**
	 * When the New Game button is clicked, a new board is constructed using this constructor.
	 * Look at GUI.doNewGame() which is the ActionListener class linked to the New Game button.
	 * Initialises the board with two blocks of value 2 each in random locations.
	 */
	public Board() {
		this.myBoard = new BlockFor2048[4][4];
		int randomInserted = 0;
		int colInd = 0;
		int rowInd = 0;
		while (randomInserted != 2){
			colInd = (int)(Math.random() * 4);
			rowInd = (int)(Math.random() * 4);
			if (this.myBoard[colInd][rowInd] == null){
				this.myBoard[colInd][rowInd] = new BlockFor2048(2);
				randomInserted++;
			}
		}		
		this.ScoreValue = 0;
	}

	/**
	 * Returns the array representation of the board. (Data is used by GUI).
	 */
	
	public BlockFor2048[][] getBoard() {
		return this.myBoard;
	}
	
	/**
	 * Method called when the Left button is pressed on the GUI resulting in moving blocks towards the left on the board.
	 * Called through GUI.swipeLEFT actionListener.
	 * 
	 * The 3 methods that follow correspond similarly with right movement, downward movement and upward movement
	 * respectively.
	 */
	public void SwipeLeft(){
		boolean SwipeSuccess = false;
		for(int row = 0; row <= 3; row++){
			LinkedList<BlockFor2048> BlocksInRow = new LinkedList<BlockFor2048>();
			LinkedList<BlockFor2048> Checker = new LinkedList<BlockFor2048>();
			for(int col = 0; col <= 3; col++){
				if(this.myBoard[col][row] != null){
					BlocksInRow.add(this.myBoard[col][row]);
				}
				Checker.add(this.myBoard[col][row]);
			}
			LinkedList<BlockFor2048> MergedForRow = MergeBlocks(BlocksInRow);

			while (MergedForRow.size() < 4){
				MergedForRow.add(null);
			}
			if (! (MergedForRow.equals(Checker)) ){
				SwipeSuccess = true;
			}
			for(int col = 0; col <= 3; col++){
				this.myBoard[col][row] = MergedForRow.pop();
			}
		}
		if (SwipeSuccess){
			this.AddRandom2();
		}
	}
	
	public void SwipeRight(){
		boolean SwipeSuccess = false;
		for(int row = 0; row <= 3; row++){
			LinkedList<BlockFor2048> BlocksInRow = new LinkedList<BlockFor2048>();
			LinkedList<BlockFor2048> Checker = new LinkedList<BlockFor2048>();
			for(int col = 3; col >= 0; col--){
				if(this.myBoard[col][row] != null){
					BlocksInRow.add(this.myBoard[col][row]);
				}
				Checker.add(this.myBoard[col][row]);
			}
			LinkedList<BlockFor2048> MergedForRow = MergeBlocks(BlocksInRow);
			
			while (MergedForRow.size() < 4){
				MergedForRow.add(null);
			}
			if (! (MergedForRow.equals(Checker)) ){
				SwipeSuccess = true;
			}
			for(int col = 3; col >= 0; col--){
				this.myBoard[col][row] = MergedForRow.pop();
			}
		}
		if (SwipeSuccess){
			this.AddRandom2();
		}
	}
	
	
	public void SwipeDown(){
		boolean SwipeSuccess = false;
		for(int col = 0; col <= 3; col++){
			LinkedList<BlockFor2048> BlocksInCol = new LinkedList<BlockFor2048>();
			LinkedList<BlockFor2048> Checker = new LinkedList<BlockFor2048>();
			for(int row = 0; row <= 3; row++){
				if(this.myBoard[col][row] != null){
					BlocksInCol.add(this.myBoard[col][row]);
				}
				Checker.add(this.myBoard[col][row]);
			}
			LinkedList<BlockFor2048> MergedForCol = MergeBlocks(BlocksInCol);
			
			while (MergedForCol.size() < 4){
				MergedForCol.add(null);
			}
			if (! (MergedForCol.equals(Checker)) ){
				SwipeSuccess = true;
			}
			for(int row = 0; row <= 3; row++){
				this.myBoard[col][row] = MergedForCol.pop();
			}
		}
		if (SwipeSuccess){
			this.AddRandom2();
		}
	}
	
	public void SwipeUp(){
		boolean SwipeSuccess = false;
		for(int col = 0; col <= 3; col++){
			LinkedList<BlockFor2048> BlocksInCol = new LinkedList<BlockFor2048>();
			LinkedList<BlockFor2048> Checker = new LinkedList<BlockFor2048>();
			for(int row = 3; row >= 0; row--){
				if(this.myBoard[col][row] != null){
					BlocksInCol.add(this.myBoard[col][row]);
				}
				Checker.add(this.myBoard[col][row]);
			}
			LinkedList<BlockFor2048> MergedForCol = MergeBlocks(BlocksInCol);
			
			while (MergedForCol.size() < 4){
				MergedForCol.add(null);
			}
			if ( !(MergedForCol.equals(Checker)) ){
				SwipeSuccess = true;
			}
			for(int row = 3; row >= 0; row--){
				this.myBoard[col][row] = MergedForCol.pop();
			}
		}
		if (SwipeSuccess){
			this.AddRandom2();
		}
	}
	
	
	/**
	 * Method to add a random block with value 2 to an empty spot on the board.
	 */
	
	public void AddRandom2(){
		boolean randomInserted = false;
		while (!(randomInserted)){
			int colInd = (int)(Math.random() * 4);
			int rowInd = (int)(Math.random() * 4);
			if (this.myBoard[colInd][rowInd] == null){
				this.myBoard[colInd][rowInd] = new BlockFor2048(2);
				randomInserted = true;
			}
		}
	}
	
	/**
	 * Method to merge blocks when called from within the 4 swipe methods above.
	 * Also increments the score variable when merging two blocks of the same value.
	 */
	public LinkedList<BlockFor2048> MergeBlocks(LinkedList<BlockFor2048> original){
		LinkedList<BlockFor2048> Merged = new LinkedList<BlockFor2048>();
		while(original.size() > 0){
			BlockFor2048 first = original.pop();
			if(original.size() == 0){
				Merged.add(first);
			}
			else if(original.getFirst().isSameVal(first)){
				original.pop();
				first.doubleValue();
				this.ScoreValue += first.getValue();
				Merged.add(first);
			}
			else{
				Merged.add(first);
			}
		}
		return Merged;
	}


	/**
	 * Returns if the board is in a state of game over. The game is over if there
	 * are no possible moves left.
	 */
	public boolean isGameOver() {
		return false;
	}

	/**
	 * Returns the current score, which is called by the GUI when it needs to
	 * update the display of the score.
	 */
	public int getScore() {
		return this.ScoreValue;
	}
	

	/**Prints the the board for testing purposes. */
	public void printBoard() {
		for (int colIndex = 0; colIndex < 4; colIndex++){
			for (int rowIndex = 0; rowIndex < 4; rowIndex++){
				System.out.print(this.myBoard[colIndex][rowIndex] + " ");
			}
			System.out.println();
		}
	}
}
