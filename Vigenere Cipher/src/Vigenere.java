
public class Vigenere{
    private String text;
    private String key;

    public Vigenere(String text, String key){
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

            output += (char)(((asciiText + asciiKey) % 26) + 65);

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

            output += (char)(((((asciiText - asciiKey) % 26) + 26) % 26) + 65);

            keyIdx += 1;
            if(keyIdx >= keyLength){
                keyIdx = 0;
            }
        }

        return output;
    }
}