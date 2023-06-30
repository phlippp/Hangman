package hangman2_dnm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class HangmanTwo {
    static int mistakes = 0;
    static int lifes = 5;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/java/hangman2_dnm/wordlist.txt"));
        Scanner user = new Scanner(System.in);
        InputStream in = System.in;
        List<String> words = new ArrayList<String>();
        List<Character> usedLetters = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random random = new Random();
        List<Character> playerGuesses = new ArrayList<>();

        usedLetters = playerGuesses;

        boolean playAgain = true;
        do {
            mistakes =0;
            lifes = 5;
            String word = words.get(random.nextInt(words.size()));  //upper limit for the number of words in the list
            System.out.println("Welcome to Hangman!");
            System.out.println("The word has " + word.length() + " letters.");
            while (true) {
                getPlayerGuess(in, word, playerGuesses);
                if (printWordState(word, playerGuesses)) {
                    System.out.println("Congratulations! You won!");
                    break;
                } else if (mistakes == lifes) {
                    System.out.println("You have lost! The word was " + word + ".");
                    break;
                }
            }
            System.out.print("Do you want to play again? (Y/N): ");
            String response = user.nextLine();
            usedLetters.clear();
            if (!response.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }  while (playAgain);

        System.out.println("You ended the game. Goodbye!");
    }

    public static boolean getPlayerGuess(InputStream in, String word, List<Character> playerGuesses) {
Scanner user = new Scanner(in);
        System.out.println("Enter a letter: ");
        String letterGuess = user.nextLine();
        playerGuesses.add(letterGuess.charAt(0)); //0 means if the input has more than
        //one character the program will
        //use the first letter of it

        if (word.contains(letterGuess)) {
            System.out.println("Correct! Enter another letter: ");
        } else {
            mistakes++;
            System.out.println("Inorrect! You have " + (lifes - mistakes) + " tries left.");
        }
        return word.contains(letterGuess);
    }

    public static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctLetterCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {  //returns the char value at the specified index of this string
                System.out.print(word.charAt(i));
                correctLetterCount++;
            } else {
                System.out.print("_");
            }
        }
        System.out.println();
        return (word.length() == correctLetterCount);
    }
}
