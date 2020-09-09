

public class SuperEncryption {
	private String text;
    private String key;
    private int matrixLength;

    public SuperEncryption(String text, String key, int matrixLength){
        this.text = text.toLowerCase().replaceAll("[^a-z]", "");
        this.key = key.toLowerCase().replaceAll("[^a-z]", "");
        this.matrixLength = matrixLength;
    }
    
    public SuperEncryption(String text, String key){
        this.text = text.toLowerCase().replaceAll("[^a-z]", "");
        this.key = key.toLowerCase().replaceAll("[^a-z]", "");
        this.matrixLength = 3;
    }
    
    private String transpositionEncrypt(String input, int length) {
    	String output = "";
    	char[][] transposition = new char[(int)(Math.ceil(input.length() / length)) + 1][length];
    	
    	int row = 0, col = 0;
    	
    	for(int i=0; i<input.length(); i++) {
    		transposition[row][col] = input.charAt(i);
    		col++;
    		if(col == transposition[0].length) {
    			col = 0;
    			row++;
    		}
    	}
    	
    	for(int i=0;i<transposition[0].length;i++) {
    		for(int j=0; j<transposition.length;j++) {
    			int ascii = (int)(transposition[j][i]) - 65;
    			if((ascii >= 0) && (ascii <= 25)) {
    				output += transposition[j][i];
    			}
    		}
    	}
    	
    	return output;
    }
    
    private String transpositionDecrypt(String input, int length) {
    	String output = "";
    	char[][] transposition = new char[length][(int)(Math.ceil(input.length() / length)) + 1];
    	
    	int row = 0, col = 0;
    	
    	for(int i=0; i<input.length(); i++) {
    		transposition[row][col] = input.charAt(i);
    		col++;
    		if(col == transposition[0].length) {
    			col = 0;
    			row++;
    		}
    	}
    	
    	for(int i=0;i<transposition[0].length;i++) {
    		for(int j=0; j<transposition.length;j++) {
    			int ascii = (int)(transposition[j][i]) - 97;
    			if((ascii >= 0) && (ascii <= 25)) {
    				output += transposition[j][i];
    			}
    		}
    	}
    	
    	return output;
    }

    public String encrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        int textLength = this.text.length();
        int keyLength = this.key.length();

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(this.text.charAt(textIdx)) - 97;
            int asciiKey = (int)(this.key.charAt(keyIdx)) - 97;

            output += (char)(((asciiText + asciiKey) % 26) + 65);

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }
        
        output = transpositionEncrypt(output, matrixLength);

        return output;
    }

    public String decrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        int textLength = this.text.length();
        int keyLength = this.key.length();
        
        String transposed = transpositionDecrypt(text, matrixLength);

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(transposed.charAt(textIdx)) - 97;
            int asciiKey = (int)(this.key.charAt(keyIdx)) - 97;

            output += (char)(((((asciiText - asciiKey) % 26) + 26) % 26) + 65);

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }

        return output;
    }
}
