
public class EightQueens {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EightQueens instance = new EightQueens();
		instance.enumerate(0);
		System.out.println("count: " + instance.count);

	}
	
	public static final int QUEENS = 8;
	public int count = 0;
	
	/*
	 *  -------------------------
	 *  |  |  |  |  |  |  |  |  |
	 *  -------------------------
	 *
	 * 
	 */
	
	protected int[] chessPanel = new int[QUEENS];

	/**
	 * could we put the queen on local (col, row)
	 * @param col , the subscript of the element
	 * @param row , the value of the element
	 * @return
	 */
	public boolean couldPutDown(int col, int row) {
		for (int i = 0; i < col; i++) {
			if (chessPanel[i] == row
			|| Math.abs(col - i) == Math.abs(row - chessPanel[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public void enumerate (int col) {
		if (QUEENS == col) {
			count++;
			printChessPanel();
			return;
		}
		
		for (int i = 0; i < QUEENS; i++) {
			if (couldPutDown(col, i)) {
				chessPanel[col] = i;
				enumerate(col + 1);
			}
		}
		
	}
	
	
	public void printChessPanel() {
		for (int i = 0; i < QUEENS; i++) {
		    System.out.print("  " + chessPanel[i]);
		}
		System.out.println("");
	}

}
