
public class AutoKeyVigenere {
	private String text;
    private String key;

    public AutoKeyVigenere(String text, String key){
        this.text = text.toLowerCase().replaceAll("[^a-z]", "");
        this.key = key.toLowerCase().replaceAll("[^a-z]", "");
    }

    public String encrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        String keyEncrypt = this.key;
        int textLength = this.text.length();
        int keyLength = keyEncrypt.length();
        int extraKey = 0;

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(this.text.charAt(textIdx)) - 97;
            int asciiKey = (int)(keyEncrypt.charAt(keyIdx)) - 97;

            output += (char)(((asciiText + asciiKey) % 26) + 65);

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyEncrypt += this.text.charAt(extraKey);
                keyLength = keyEncrypt.length();
                extraKey++;
            }
        }

        return output;
    }

    public String decrypt(){
        String output = "";
        int textIdx = 0;
        int keyIdx = 0;
        String keyDecrypt = this.key;
        int textLength = this.text.length();
        int keyLength = keyDecrypt.length();
        int extraKey = 0;

        for(textIdx=0; textIdx < textLength; textIdx++){
            int asciiText = (int)(this.text.charAt(textIdx)) - 97;
            int asciiKey = (int)(keyDecrypt.charAt(keyIdx)) - 97;

            output += (char)(((((asciiText - asciiKey) % 26) + 26) % 26) + 65);

            keyIdx += 1;
            if(keyIdx >= keyLength){
            	keyDecrypt += output.charAt(extraKey);
            	keyDecrypt = keyDecrypt.toLowerCase();
                keyLength = keyDecrypt.length();
                extraKey++;
            }
        }

        return output;
    }
}
