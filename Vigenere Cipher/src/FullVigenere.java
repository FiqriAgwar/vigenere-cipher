
public class FullVigenere {
	private String text;
	private String key;
	
	private char[] secretMatrix = {'P', 'X', 'J', 'L', 'M', 'K', 'E', 'F', 'Y', 'O', 'S', 'N', 'R', 'Q', 'C', 'V', 'U', 'B', 'D', 'Z', 'G', 'H', 'A', 'W', 'I', 'T'};
			
	public FullVigenere(String text, String key) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
        this.key = key.toLowerCase().replaceAll("[^a-z]", "");
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

            output += secretMatrix[(asciiText + asciiKey) % 26];

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }

        return output;
    }

    public String decrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        int textLength = this.text.length();
        int keyLength = this.key.length();

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(this.text.charAt(textIdx)) - 97;
            int asciiKey = (int)(this.key.charAt(keyIdx)) - 97;

            for(int i=0;i<26;i++) {
            	if(secretMatrix[(asciiKey + i) % 26] == (char)(asciiText + 65)) {
            		output += (char)(i + 65);
            		break;
            	}
            }

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }

        return output;
    }
}
