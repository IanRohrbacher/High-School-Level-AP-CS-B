import java.util.*;
import java.lang.*;

public class Enigma {
	@SuppressWarnings("unused")
	private int letterLength;
	private String lookupTable = "--------------------------";

	public Enigma(int letterLength) {
		this.letterLength = letterLength;
	}

	public void setSubstitution(int i, char ch) {
		lookupTable = lookupTable.substring(0, i) + ch + lookupTable.substring(i + 1);
		//System.out.println("table:" + lookupTable + " i:" + i + " | ch:" + ch);
	}

	// look at my table and replace letter from it into 'text', if the table has '-'
	// don't replace it
	public String decode(String text) {
		StringBuffer newText = new StringBuffer(400);
		int letterNum = 0;

		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				letterNum = Character.getNumericValue(text.charAt(i)) - Character.getNumericValue('A');

				if (lookupTable.substring(letterNum, letterNum + 1).equals("-")) {
					newText.insert(i, text.substring(i, i + 1));
				} else {
					newText.insert(i, lookupTable.substring(letterNum, letterNum + 1).toUpperCase());
				}
			} else if (Character.isLowerCase(text.charAt(i))) {
				letterNum = Character.getNumericValue(text.charAt(i)) - Character.getNumericValue('a');

				if (lookupTable.substring(letterNum, letterNum + 1).equals("-")) {
					newText.insert(i, text.substring(i, i + 1));
				} else {
					newText.insert(i, lookupTable.substring(letterNum, letterNum + 1).toLowerCase());
				}
			} else {
				newText.insert(i, text.substring(i, i + 1));
			}
		}

		return newText.toString();
	}

	public String getHints(String text, String lettersByFrequency) {
		int[] numLetterArray = countLetters(text);
		String[] finalOrder = new String[letterLength];
		int lowestInt = Integer.MAX_VALUE;
		int index = 0;

		for(int i = 0; i < letterLength; i++) {
			for(int j = 0; j < letterLength; j++) {
				System.out.println("numLetterArray["+j+"]:" + numLetterArray[j] + " lowestInt:" + lowestInt);
				if(numLetterArray[j] < lowestInt) {
					lowestInt = numLetterArray[j];
					index = j;
				}
			}
			//System.out.println("lowestInt:" + lowestInt + " index:" + index);
			finalOrder[index] = lettersByFrequency.substring(i, i+1);
			numLetterArray[index] = Integer.MAX_VALUE;
			lowestInt = Integer.MAX_VALUE;
		}

		//System.out.println(String.join("", finalOrder));
		return String.join("", finalOrder);
	}
	
	private int[] countLetters(String text) {
		String newString = text.toLowerCase();
		int letterNum = 0;
		int[] intArray = new int[letterLength];
		
		
		for(int i = 0; i < newString.length(); i++) {
			if(Character.isLetter(newString.charAt(i))) {
				letterNum = Character.getNumericValue(text.charAt(i)) - Character.getNumericValue('a');
				
				intArray[letterNum] = intArray[letterNum] + 1;
				//System.out.println("letter index:" + letterNum + " amount:" + intArray[letterNum]);
				
				letterNum = 0;
			}
		}
		
		return intArray;
	}
}
