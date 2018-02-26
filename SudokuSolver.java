/**
 * @author Unmesh Mahendra Deodhar
 *
 */

public class SudokuSolver {
	public static void main(String[] args) {
		System.out.println("Hello World");
		Grid grid = new Grid();
		grid.printGrid();
		grid.getElements();
		grid.solveSudoku();
	}

}
