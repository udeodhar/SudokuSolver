import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Grid {
	
	Cell cells[][];
	ArrayList<Cell> nodesToVisit;
	
	int rowFDigCount[][];
	int colFDigCount[][];
	int blockFDigCount[][];
	
	boolean temp; //remove this 
	
	int noDigitsFound; 
	
	boolean rowsSolved[];
	boolean colsSolved[];
	boolean blocksSolved[];
	
	public Grid() {
		cells = new Cell[9][9];
		
		rowsSolved = new boolean[9];
		colsSolved = new boolean[9];
		blocksSolved = new boolean[9];
		
		this.rowFDigCount = new int[9][9];
		this.colFDigCount = new int[9][9];
		this.blockFDigCount = new int[9][9];
		
		//Initializing the grid...
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.cells[i][j] = new Cell(i, j);
			}
		}
		
		this.nodesToVisit = new ArrayList<Cell>();
	}
	
	public void printGrid() {
		System.out.print(" ____________\n|");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print((this.cells[i][j].digit == -1) ? " " : this.cells[i][j].digit);
				if ((j == 2) || (j == 5) || (j == 8)) {
					System.out.print("|");
				}
			}
			
			if ((i == 2) || (i == 5) || (i == 8)) {
				System.out.print("\n____________\n");
				if (i != 8)
					System.out.print("|");
			}
			else
				System.out.print("\n|");
		}
	}
	
	public void getElements() {
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("How many elements do you want to enter?");
//		int totalElements = sc.nextInt();
//		for (int i = 0; i < totalElements; i++) {
//			int ip[] = new int[3];
//			for( int j = 0; j < 3; j++) {
//				ip[j] = sc.nextInt();
//			}
//			cells[ip[0]][ip[1]].digit = ip[2];
//			this.noDigitsFound++;
//			this.nodesToVisit.add(cells[ip[0]][ip[1]]);
//			System.out.println("Number "+ ip[2] + " added at: ("+ip[0]+","+ip[1]);
//		}
//		this.printGrid();
		
		Scanner sc = new Scanner(System.in);
		String source = sc.nextLine();
		
		String[] integersAsText = source.split(",");
		 
		int[] results = new int[ integersAsText.length ];
		 
		int j = 0; 
		 
		for ( String textValue : integersAsText ) {
		    results[j] = Integer.parseInt( textValue ); 
		    j++; 
		}
		  
		for (int i = 0; i < integersAsText.length; i +=3) {
			cells[results[i]][results[i+1]].digit = results[i+2];
			this.noDigitsFound++;
		  	this.nodesToVisit.add(cells[results[i]][results[i+1]]);
//		  	System.out.println("Number "+ results[i+2] + " added at: ("+results[i]+","+results[i+1]);
		}
			  
		this.printGrid();
	}
	
	public void solveSudoku() {
		//Iterator iter = this.nodesToVisit.iterator();
		
		System.out.println("In solve sudoku");
		int count = 0;
		int iteration_count = 0;
		while ((this.nodesToVisit.size() > count) && (this.noDigitsFound < 81)) {
			iteration_count++;
//			System.out.println("In for loop. \nCount: "+count);
			Cell c = (Cell) this.nodesToVisit.get(count);
//			System.out.println("*****Cell removed*****");
//			System.out.println(c);
			this.maskFalse(c);
//			this.nodesToVisit.remove(c);
//			this.printGrid();
//			System.out.println("At the end of for loop.\nSize of list: "+this.nodesToVisit.size());
			count++;
		}
		
		System.out.println("Done solving...\nNumber of iterations required: "+iteration_count);
		this.printGrid();
	}
	
	public void maskFalse(Cell c) {
//		System.out.println("In MakeFalse"+c);
		this.maskRow(c.locX, c.digit);
		this.maskCol(c.locY, c.digit);
		this.maskBlock(c.locBlock, c.digit);
	}
	
	public void maskRow(int row, int digit) {
		for (int i = 0; i < 9; i++) {
			if (this.cells[row][i].maskDigit(digit, "Calling from row")) {
				this.noDigitsFound++;
				this.nodesToVisit.add(this.cells[row][i]);
			}
		}
	}

	public void maskCol(int col, int digit) {
		for (int i = 0; i < 9; i++) {
			if (this.cells[i][col].maskDigit(digit, "Calling from col: "+col)) {
				this.noDigitsFound++;
				this.nodesToVisit.add(this.cells[i][col]);
			}
		}
	}

	public void maskBlock(int block, int digit) {
			for (int i = 0; i < 3; i++) {
				this.cells[((block/3)*3)][((block%3)*3)+i].maskDigit(digit, "Calling from blk");
				this.cells[((block/3)*3)+1][((block%3)*3)+i].maskDigit(digit, "Calling from blk");
				this.cells[((block/3)*3)+2][((block%3)*3)+i].maskDigit(digit, "Calling from blk");
				
			}		
	}

}