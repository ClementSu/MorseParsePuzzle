import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueSequences {
	
	//Using HashMaps so we don't have to worry about duplicates
	private Map<String, Object> sequences1;
	private Map<String, Object> sequences2;
	
	private String morseOg; //original string
	private String rmMorse1; //first removal
	private String rmMorse2; //second removal
	
	public UniqueSequences(String stringOg, String rmMorse1, String rmMorse2) {
		
		this.sequences1 = new HashMap<String, Object>();
		this.sequences2 = new HashMap<String, Object>();
		this.morseOg = stringOg;
		this.rmMorse1 = rmMorse1;
		this.rmMorse2 = rmMorse2;
	}

	private void findSequences(StringBuilder stringOg, int startIndex, String rmStr, int rmCharIndex, Map<String, Object> sequences){
				
		//base case: we've gone through each character in rmStr without returning, which means there was a match.
		if (rmCharIndex == rmStr.length()){
			sequences.put(stringOg.toString(), null);
			return;
		} 
		
		//get the next char we want to find
		char searchChar = rmStr.charAt(rmCharIndex);
		
		//find index of occurrences of char
		List<Integer> indexes = getMorseIndex(stringOg, searchChar, startIndex);
		
		//if we have char matches, then create each substring, and search for the next char in rmStr
		if (indexes.size() > 0){
			for (int i = 0; i < indexes.size(); i++){
				StringBuilder sb = new StringBuilder(stringOg);
			    sb.deleteCharAt(indexes.get(i));
			    
			    int nextSearchIndex = rmCharIndex + 1;
			    findSequences(sb, indexes.get(i), rmStr, nextSearchIndex, sequences);    
			}
		} 
		return;
	}
	
	public void parseMorseSequence(){
		
		//Step One remove morse sequence
		StringBuilder sbMorseOg = new StringBuilder(this.morseOg);
		int startIndex = 0;
		int rmCharIndex = 0;
		findSequences(sbMorseOg, startIndex, this.rmMorse1, rmCharIndex, this.sequences1);
		
		//Step Two remove morse sequence 
		for(Map.Entry<String, Object> entry : sequences1.entrySet()) {
			
			String key = entry.getKey();
			StringBuilder sbMorseOg2 = new StringBuilder(key);
			rmCharIndex = 0;
			startIndex = 0;
			findSequences(sbMorseOg2, startIndex, this.rmMorse2, rmCharIndex, this.sequences2);
		}	
	}
	
	public static List<Integer> getMorseIndex(StringBuilder morseStr, char searchChar, int strOgStartIndex){
		
		List<Integer> charIndexes = new ArrayList<Integer>();
		
		for (int i = strOgStartIndex; i < morseStr.length(); i++){
			if (morseStr.charAt(i) == searchChar){
				charIndexes.add(i);
			}
		}
		
		return charIndexes;
	}
	
	//returns the number of unique sequences after both
	// messages have been removed.
	public int getUniqueCount(){
		return sequences2.size();
	}

}
