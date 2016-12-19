public class Main {
	
	public static void main(String[] args) {
		
		String morseOg = "";
		String rmMorse1 = "";
		String rmMorse2 = "";
		
		
		if (args.length == 3){
			morseOg = args[0];
			rmMorse1 = args[1];
			rmMorse2 = args[2];
		}
		
		if (morseOg.length() < 1 || rmMorse1.length() < 1 || rmMorse2.length() < 1){
			System.out.println("Please provide three non-zero length sequences as string arguments.");
			return;
		}
		
		
		UniqueSequences unique = new UniqueSequences(morseOg, rmMorse1, rmMorse2);
		
		unique.parseMorseSequence();
		
		int count = unique.getUniqueCount();
		System.out.println(count);
		
	}
}
