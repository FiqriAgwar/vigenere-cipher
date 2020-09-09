

public class Playfair {
	private String text;
	private String key;
	private char[][] playmatriks = new char[5][5];
	private int[][] alphabetIdx = new int[26][2];
	private char[][] playText;
	
	public Playfair(String text, String key) {
		this.text = text.toLowerCase().replaceAll("[j]", "i").replaceAll("[^a-z]", "");
        this.key = key.toLowerCase().replaceAll("[j]", "").replaceAll("[^a-z]", "");
        playText = new char[(int)(Math.floorDiv(text.length(), 2)) + 1][2];
	}
	
	private void initMatriks() {
		char[] alphabet = new char[26];
		char[] used = new char[26];
		int usedIterator = 0;
		
		for(int k=0; k<26; k++) {
			alphabet[k] = (char)(k + 97);
		}
		
		for(int k=0; k<key.length(); k++) {
			boolean found = false;
			int i=0;
			
			while(!found && i<usedIterator) {
				if(used[i] == key.charAt(k)) {
					found = true;
				}
				else {
					i++;
				}
			}
			
			if(!found) {
				used[usedIterator] = key.charAt(k);
				usedIterator++;
				int index = (int)(key.charAt(k)) - 97;
				alphabet[index] = 'j';
			}
		}
		
		int row = 0;
		int col = 0;
		
		for(int n=0;n<usedIterator;n++) {
			playmatriks[row][col] = used[n];
			col++;
			if(col == 5) {
				row++;
				col = 0;
			}
		}
		
		for(int n=0;n<alphabet.length;n++) {
			if(alphabet[n] != 'j') {
				playmatriks[row][col] = alphabet[n];
				col++;
				if(col == 5) {
					row++;
					col = 0;
				}
			}
		}
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				int index = (int)(playmatriks[i][j]) - 97;
				alphabetIdx[index][0] = i;
				alphabetIdx[index][1] = j;
			}
		}
	}
	
	private void initText() {
		int outRow = 0;
		int iterator = 0;
		
		while(iterator < text.length()) {
			if(iterator == text.length() - 1) {
				playText[outRow][0] = text.charAt(iterator);
				playText[outRow][1] = 'x';
				iterator++;
				outRow++;
			}
			else if(text.charAt(iterator) == text.charAt(iterator+1)) {
				playText[outRow][0] = text.charAt(iterator);
				playText[outRow][1] = 'x';
				iterator++;
				outRow++;
			}
			else {
				playText[outRow][0] = text.charAt(iterator);
				playText[outRow][1] = text.charAt(iterator+1);
				outRow++;
				iterator += 2;
			}
		}
	}
	
	public String encrypt() {
		initMatriks();
		initText();
		String output = "";
		
		for(int i=0; i<playText.length;i++) {
			String out = "";
			int index1 = (int)(playText[i][0]) - 97;
			int index2 = (int)(playText[i][1]) - 97;
			if(((index1 >= 0) && (index1 <= 25)) && ((index2 >= 0) && (index2 <= 25))) {
				int row1 = alphabetIdx[index1][0];
				int row2 = alphabetIdx[index2][0];
				int col1 = alphabetIdx[index1][1];
				int col2 = alphabetIdx[index2][1];
				
				if(row1 == row2) {
					out += String.valueOf(playmatriks[row1][((col1 + 1) % 5)]);
					out += String.valueOf(playmatriks[row2][((col2 + 1) % 5)]);
				}
				else if(col1 == col2) {
					out += String.valueOf(playmatriks[((row1 + 1) % 5)][col1]);
					out += String.valueOf(playmatriks[((row2 + 1) % 5)][col2]);
				}
				else {
					out += String.valueOf(playmatriks[row1][col2]);
					out += String.valueOf(playmatriks[row2][col1]);
				}
			}
			output += out;
		}
		
		return output.toUpperCase();
	}
	
	public String decrypt() {
		initMatriks();
		initText();
		String output = "";
		
		for(int i=0; i<playText.length;i++) {
			String out = "";
			int index1 = (int)(playText[i][0]) - 97;
			int index2 = (int)(playText[i][1]) - 97;
			if(((index1 >= 0) && (index1 <= 25)) && ((index2 >= 0) && (index2 <= 25))) {
				int row1 = alphabetIdx[index1][0];
				int row2 = alphabetIdx[index2][0];
				int col1 = alphabetIdx[index1][1];
				int col2 = alphabetIdx[index2][1];
				
				if(row1 == row2) {
					out += String.valueOf(playmatriks[row1][((col1 - 1) % 5 + 5) % 5]);
					out += String.valueOf(playmatriks[row2][((col2 - 1) % 5 + 5) % 5]);
				}
				else if(col1 == col2) {
					out += String.valueOf(playmatriks[((row1 - 1) % 5 + 5) % 5][col1]);
					out += String.valueOf(playmatriks[((row2 - 1) % 5 + 5) % 5][col2]);
				}
				else {
					out += String.valueOf(playmatriks[row1][col2]);
					out += String.valueOf(playmatriks[row2][col1]);
				}
			}
			output += out;
		}
		
		return output.toUpperCase();
	}
}
