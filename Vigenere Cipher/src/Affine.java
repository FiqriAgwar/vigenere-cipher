

public class Affine {
	private String text;
	private int m;
	private int b;
	private int n;
	
	private int[] calonM = {17, 23, 7, 25, 19, 3, 11, 21, 15, 5, 1, 9};
	
	private int modInverse(int a, int b, int target) {
		a = a % b;
		for(int i=1; i<b; i++) {
			if((a * i) % b == target) {
				return i;
			}
		}
		
		return 1;
	}
	
	public Affine(String text) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		this.m = calonM[this.text.length() % calonM.length];
		this.b = (this.text.length()) % 26;
		this.n = 26;
	}
	
	public Affine(String text, int m, int b) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		this.m = m;
		this.b = b;
		this.n = 26;
	}
	
	public Affine(String text, int b) {
		this.text = text.toLowerCase().replaceAll("[^a-z]", "");
		this.m = calonM[this.text.length() % calonM.length];
		this.b = b;
		this.n = 26;
	}
	
	public String encrypt() {
		String output = "";
		for(int i=0; i<text.length(); i++) {
			output += (char)((((int)(text.charAt(i)) - 97) * m + b) % n + 65);
		}
		
		return output;
	}
	
	public String decrypt() {
		String output = "";
		int mInv = modInverse(m, n, 1);
		for(int i=0; i<text.length(); i++) {
			output += (char)((mInv * (( (int)(text.charAt(i)) - 97) -b) % n + n) % n + 65);
		}
		
		return output;
	}
}
