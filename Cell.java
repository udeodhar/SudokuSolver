public class Cell {
	
	int locX, locY, locBlock;
	int digit;
	boolean possibilities[];
	int countFalse;
	
	public Cell() {
		this.locX = -1;
		this.locY = -1;
		this.possibilities = new boolean[9];
		for (int i = 0; i < 9; i++) {
			this.possibilities[i] = true;
		}
		this.countFalse = 0;
		this.digit = -1;
	}
	
	public Cell(int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
		
		//Calculting block
		/*
		-------
		-0-1-2-
		-------
		-3-4-5-
		-------
		-6-7-8-
		-------
		*/
		
		int temp = 0;
		
		this.locBlock = ((this.locX / 3)*3 ) + ((this.locY / 3)); 
		
		this.possibilities = new boolean[9];
		
		for (int i = 0; i <= 8; i++) {
			this.possibilities[i] = true;
		}
		this.countFalse = 0;
		this.digit = -1;
	}
	
	public boolean maskDigit(int digit, String str) {
		if (this.digit != -1)
			return false;
//		if((this.locX == 1) && (this.locY == 3) && (digit == 6)) {
//			System.out.println(str);
//			System.exit(0);
//		}
			
		if (this.possibilities[digit-1]) {
			this.possibilities[digit-1] = false;
			this.countFalse++;
			if (this.countFalse == 8) {
				int dig = 0;
				for (int i = 0; i < 9; i++) {
					if(this.possibilities[i]) {
						this.digit = i+1;
//						for (int k = 0; k < 9; k++)
//							System.out.print(possibilities[k]+",");
//						System.out.println(this);
//						System.exit(0);
						break;
					}
				}
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public String toString() {
		return "Cell location:\nx: "+this.locX+
				"\ny: "+this.locY+
				"\nBlock: "+this.locBlock+
				"\nDigit: "+this.digit;
	}
}
