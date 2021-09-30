import java.util.Scanner;

/**
 * This is an encryption machine.
 * It uses Caesar Cipher to encrypt conversations between two
 * parties, enforcing privacy and preventing third parties from reading
 * or modifying any secret information
 * @author zillahadahman
 * @version 1.0.0
 * @date Sept. 28, 2021
 *
 */
public class EncryptionMachine {
	private String _key = "";
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static final int SHIFT = 3;

	/**
	 * The main function
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		EncryptionMachine encryptionMachine = new EncryptionMachine();
		encryptionMachine.EncryptionRequest();
	}
	
	/**
	 * Default constructor
	 */
	public EncryptionMachine() {
		WelcomeMessage();
	}
	
	/**
	 * Retrieves the encryption key
	 * @return returns the encryption key
	 */
	public String GetEncryptedKey() {
		return EncryptWord(_key);
	}
	
	/**
	 * Sets the encryption key
	 * @param key - the plain text of the key
	 */
	private void SetKey(String key) {
		_key = key;		
	}
	
	/**
	 * Processes the user's string input
	 * @param inputScanner - Scanner object to prompt user's input
	 * @param displayMsg - message to display to the user before input
	 * @return the user's input
	 */
	public String UserInputProcessor(Scanner inputScanner,String displayMsg) {
		System.out.println(displayMsg);
		return inputScanner.next();
	}
	
	/**
	 * Processes the users encryption request
	 */
	private void EncryptionRequest() {
		Scanner userInputScanner = new Scanner(System.in);

		SetKey(UserInputProcessor(userInputScanner, "Enter key: ")); // prompts user for a key & sets the value
		PrintEncryption(_key, GetEncryptedKey()); // prints the encrypted key to the user

		System.out.println("How many words are in your message? ");
		int numberOfWords = userInputScanner.nextInt();
		
		for (int index = 0; index < numberOfWords; index++) {
			String eachWord = UserInputProcessor(userInputScanner, "Enter next word: ");
			String encryptedWord = EncryptWord(eachWord);

			PrintEncryption(eachWord, encryptedWord);
		}
		userInputScanner.close();
		EncryptionCompleteMessage();
	}
	
	/**
	 * Prints the encryption message showing the plain text and cipher text
	 * @param previousValue - the plain text
	 * @param newValue - the cipher text
	 */
	public void PrintEncryption(String previousValue, String newValue) {
		System.out.printf("The value, %s, has been encrypted to the ciphertext, %s\n", previousValue, newValue);
	}

	/**
	 * Prints a welcome message to the users and a simple description of the program
	 */
	private void WelcomeMessage() {
		System.out.println("Welcome to the Encryption Machine.\nThis program encrypts your messages"
				+ " to promote private communication between two parties.");
	}
	
	/**
	 * Prints a completion message, once all the words are encrypted
	 */
	private void EncryptionCompleteMessage() {
		System.out.println("\nEncryption Completed.");	
	}
	
	/**
	 * Encrypts a letter
	 * Assumption always lower cased
	 * @param letter - a letter of the alphabet
	 * @return the encrypted letter
	 */
	public static char EncryptLetter(char letter) {
		int letterIndex = ALPHABET.indexOf(letter);
		int shiftedIndex = letterIndex + SHIFT;
		
		if (shiftedIndex >= ALPHABET.length()) {
			shiftedIndex %= ALPHABET.length(); 
		}
		
		return ALPHABET.charAt(shiftedIndex);
	}
	
	/**
	 * Encrypts a word
	 * @param word - an English word to be encrypted
	 * @return the encrypted word
	 */
	public static String EncryptWord(String word) {
		char[] wordToCharArray = word.toCharArray();
		
		for (int index=0; index < word.length(); index++) {
			wordToCharArray[index] = EncryptLetter(wordToCharArray[index]);
		}

		return String.valueOf(wordToCharArray);
	}
}
