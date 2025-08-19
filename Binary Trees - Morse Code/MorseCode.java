import java.util.TreeMap;

public class MorseCode {
	private static final char DOT = '.';
	private static final char DASH = '-';

	private static TreeMap<Character, String> codeMap;
	private static TreeNode decodeTree;

	public static void start() {
		codeMap = new TreeMap<Character, String>();
		decodeTree = new TreeNode(' ', null, null); // autoboxing
		// put a space in the root of the decoding tree

		addSymbol('A', ".-");
		addSymbol('B', "-...");
		addSymbol('C', "-.-.");
		addSymbol('D', "-..");
		addSymbol('E', ".");
		addSymbol('F', "..-.");
		addSymbol('G', "--.");
		addSymbol('H', "....");
		addSymbol('I', "..");
		addSymbol('J', ".---");
		addSymbol('K', "-.-");
		addSymbol('L', ".-..");
		addSymbol('M', "--");
		addSymbol('N', "-.");
		addSymbol('O', "---");
		addSymbol('P', ".--.");
		addSymbol('Q', "--.-");
		addSymbol('R', ".-.");
		addSymbol('S', "...");
		addSymbol('T', "-");
		addSymbol('U', "..-");
		addSymbol('V', "...-");
		addSymbol('W', ".--");
		addSymbol('X', "-..-");
		addSymbol('Y', "-.--");
		addSymbol('Z', "--..");
		addSymbol('0', "-----");
		addSymbol('1', ".----");
		addSymbol('2', "..---");
		addSymbol('3', "...--");
		addSymbol('4', "....-");
		addSymbol('5', ".....");
		addSymbol('6', "-....");
		addSymbol('7', "--...");
		addSymbol('8', "---..");
		addSymbol('9', "----.");
		addSymbol('.', ".-.-.-");
		addSymbol(',', "--..--");
		addSymbol('?', "..--..");
	}

	/**
	 * Inserts a letter and its Morse code string into the encoding map and calls
	 * treeInsert to insert them into the decoding tree. dot left dash right
	 */
	private static void addSymbol(char letter, String code) {
		codeMap.put(letter, code);
		treeInsert(letter, code);
	}

	/**
	 * Inserts a letter and its Morse code string into the decoding tree. Each
	 * dot-dash string corresponds to a path in the tree from the root to a node: at
	 * a "dot" go left, at a "dash" go right. The node at the end of the path holds
	 * the symbol for that code string.
	 */
	private static void treeInsert(char letter, String code) { // ------------------------DONE
		TreeNode currentNode = decodeTree;

		for (int i = 0; i < code.length(); i++) {
			if (code.substring(i, i + 1).equals(".")) {
				if (currentNode.getLeft() == null) { // creates node is there is not one already
					TreeNode blankNode = new TreeNode(' ');
					currentNode.setLeft(blankNode);
					currentNode = blankNode;
				} else { // travels to next node because its was already created
					currentNode = currentNode.getLeft();
				}
			} else if (code.substring(i, i + 1).equals("-")) {
				if (currentNode.getRight() == null) {
					TreeNode blankNode = new TreeNode(' ');
					currentNode.setRight(blankNode);
					currentNode = blankNode;
				} else {
					currentNode = currentNode.getRight();
				}
			}
		}
		currentNode.setValue(letter);
	}

	/**
	 * Converts text into a Morse code message. Adds a space after a dot-dash
	 * sequence for each letter. Other spaces in the text are transferred directly
	 * into the encoded message. Returns the encoded message.
	 */
	public static String encode(String text) { // -------------------------------------------DONE
		StringBuffer morse = new StringBuffer(400);

		for (int i = 0; i < text.length(); i++) {
			if (text.substring(i, i + 1).equals(" ")) {
				morse.insert(morse.length(), "  ");

			} else {
				morse.insert(morse.length(), codeMap.get(text.toUpperCase().charAt(i)) + " ");
			}
		}

		return morse.toString();
	}

	/**
	 * Converts a Morse code message into a text string. Assumes that dot-dash
	 * sequences for each letter are separated by one space. Additional spaces are
	 * transferred directly into text. Returns the plain text message.
	 */
	public static String decode(String morse) { // -------------------------------------------DONE
		StringBuffer text = new StringBuffer(100);

		for (int i = 0; i < morse.length(); i++) {
			TreeNode currentNode = decodeTree;
			StringBuffer morseLetter = new StringBuffer(10);
			// System.out.println((morse.indexOf(" ") == -1));
			if (morse.indexOf(" ") == -1) { // once it get to the last code it clears the string to show its done
				// System.out.println("morse: " + morse);
				morseLetter.insert(0, morse);
				morse = "";
			} else { // gets each code individually and sets morse to the rest of the code
				morseLetter.insert(0, morse.substring(0, morse.indexOf(" ")));
				morse = morse.substring(morse.indexOf(" ") + 1);
			}

			// System.out.println("morse letter: " + morseLetter + "\nmorse letter length: "+ morseLetter.length() + "\nmorse: " + morse + "\nmorse length: " + morse.length() + "\ni: " + i);

			for (int j = 0; j < morseLetter.length(); j++) { // finds the letter for each code sequence
				if (morseLetter.substring(j, j + 1).equals(".")) {
					// System.out.print("went left | ");
					currentNode = currentNode.getLeft();
				} else if (morseLetter.substring(j, j + 1).equals("-")) {
					// System.out.print("went right | ");
					currentNode = currentNode.getRight();
				}
			}

			text.insert(text.length(), currentNode.getValue());

			if (!(morse.indexOf(" ") == -1)) { // allows the for loop to break after index -1 is reached once already
				i--;
			}
			// System.out.println( text.insert(text.length(), currentNode.getValue()));
			// System.out.println(morseLetter);
			// System.out.println(currentNode.getValue());
			// System.out.println(morse);
			// System.out.println();
		}
		return text.toString();
	}
}
