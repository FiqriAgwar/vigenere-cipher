
import Jama.Matrix;

public class Hill {
	private String text;
	private int[][] matrix;
	private int[][] reservedMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}}; //GYB NQK URP
	
	public Hill(String text) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		this.matrix = reservedMatrix;
	}
	
	public Hill(String text, int[][] matrix) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		this.matrix = matrix;
	}
	
	public Hill(String text, String key) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		key = key.toLowerCase().replaceAll("[^a-z]", "");
		
		int y = (int)Math.sqrt(key.length());
		if(key.length() == y * y) {
			this.matrix = new int[y][y];
			int row = 0;
			int col = 0;
			
			for(int i=0; i<key.length(); i++) {
				this.matrix[row][col] = (int)(key.charAt(i)) - 97;
				
				col++;
				if(col == y) {
					row++;
					col = 0;
				}
			}
			
			if(this.text.length() % y != 0) {
				for(int i=0; i<this.text.length() % y; i++) {
					this.text += "z";
				}
			}
		}
		else {
			this.matrix = reservedMatrix;
			if(this.text.length() % 3 != 0) {
				for(int i=0; i<this.text.length() % 3; i++) {
					this.text += "z";
				}
			}
		}
	}
	
	public double[][] findCofactor(double[][] input){
		double[][] output = new double[input.length][input[0].length];
		
		for(int i=0;i<input.length;i++) {
			for(int j=0;j<input[i].length;j++) {
				double[][] determinantArray = new double[input.length - 1][input[i].length - 1];
				int row = 0;
				int col = 0;
				
				for(int m=0; m<input.length; m++) {
					for(int n=0; n<input[m].length; n++) {
						if((m != i) && (n != j)) {
							determinantArray[row][col] = input[m][n];
							col++;
							
							if(col == input.length - 1) {
								row++;
								col = 0;
							}
						}
					}
				}
				
				Matrix determinantMatrix = Matrix.constructWithCopy(determinantArray);
				double determinant = determinantMatrix.det();
				
				output[i][j] = determinant * Math.pow(-1, (i+j+2));
			}
		}
		
		return output;
	}
	
	public double[][] transpose(double[][] input){
		double[][] output = new double[input.length][input[0].length];
		
		for(int i=0;i<input.length;i++) {
			for(int j=0;j<input[0].length;j++) {
				output[j][i] = input[i][j];
			}
		}
		
		return output;
	}
	
	public String encrypt() {
		String output = "";
		int size = matrix.length;
		
		int i=0;
		while(i<text.length()) {
			for(int j=0;j<size;j++) {
				int result = 0;
				
				for(int k=0;k<size;k++) {
					int value = (int)(text.charAt(k + i)) - 97;
					result += matrix[j][k] * value;
				}
				
				output += String.valueOf((char)(result % 26 + 65));
			}
			
			i += size;
		}
		
		return output;
	}
	
	public String decrypt() {
		String output = "";
		int size = matrix.length;
		double[][] matrixInDouble = new double[size][size];
		double[][] matrixInIdentity = new double[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				matrixInDouble[i][j] = (double)(matrix[i][j]);
			}
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(i == j) {
					matrixInIdentity[i][j] = (double)(1);
				}
				else {
					matrixInIdentity[i][j] = (double)(0);
				}
			}
		}
		
		Matrix matrixDouble = Matrix.constructWithCopy(matrixInDouble);
		
		int mDoubleDet = (int)(Math.round(matrixDouble.det())) % 26;
		
		double[][] adjoint = findCofactor(matrixInDouble);
		
		for(int i=0;i<adjoint.length;i++) {
			for(int j=0;j<adjoint[i].length;j++) {
				adjoint[i][j] *= mDoubleDet;
				adjoint[i][j] = (adjoint[i][j] % 26 + 26) % 26;
			}
		}
		
		double[][] matrixOutput = transpose(adjoint);
		
		int i=0;
		while(i<text.length()) {
			for(int j=0;j<size;j++) {
				int result = 0;
				
				for(int k=0;k<size;k++) {
					int value = (int)(text.charAt(k + i)) - 97;
					result += (int)Math.round(matrixOutput[j][k]) * value;
				}
				
				output += String.valueOf((char)(result % 26 + 65));
			}
			
			i += size;
		}
		
		return output;
	}
}
