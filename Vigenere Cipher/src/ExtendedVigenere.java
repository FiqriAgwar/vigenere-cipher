

public class ExtendedVigenere {
	private String text;
    private String key;

    public ExtendedVigenere(String text, String key){
        this.text = text;
        this.key = key;
    }

    public String encrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        int textLength = this.text.length();
        int keyLength = this.key.length();

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(this.text.charAt(textIdx));
            int asciiKey = (int)(this.key.charAt(keyIdx));

            output += (char)(((asciiText + asciiKey) % 256));
            
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
            int asciiText = (int)(this.text.charAt(textIdx));
            int asciiKey = (int)(this.key.charAt(keyIdx));

            output += (char)(((((asciiText - asciiKey) % 256) + 256) % 256));

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }

        return output;
    }
}
