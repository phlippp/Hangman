package hangman2_dnm;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class HangmanTwo {
    static int mistakes = 0;
    static final int lives = 5;

    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("deneme/src/main/java/hangman2_dnm/wordlist.txt"));
        Scanner user = new Scanner(System.in);


        List<String> words = new ArrayList<String>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));  //upper limit for the number of words in the list


        List<Character> playerGuesses = new ArrayList<>();
        //It is not allowed to do collections of
        //primitive types, so we cannot type List<char>

        System.out.println("Welcome to Hangman!");
        System.out.println("The word has " + word.length() + " letters.");

        String playAgain = "";
        do {
            while (true) {
                getPlayerGuess(user, word, playerGuesses);
                if (printWordState(word, playerGuesses)) {
                    System.out.println("Congratulations! You won!");
                    break;

                } else if (mistakes == lives) {
                    System.out.println("You have lost! The word was " + word + ".");

                    System.out.println("Do you want to play again? y/n");
                    playAgain = user.nextLine();
                    break;
                }

            }
        }
      
        System.out.println("You ended the game. Goodbye!");
    }

    private static boolean getPlayerGuess(Scanner user, String word, List<Character> playerGuesses) {
        System.out.println("Enter a letter: ");
        String letterGuess = user.nextLine();
        playerGuesses.add(letterGuess.charAt(0)); //0 means if the input has more than
        //one character the program will
        //use the first letter of it

        if (word.contains(letterGuess)) {
            System.out.println("Correct! Enter another letter: ");
        } else {
            mistakes++;
            System.out.println("Incorrect! You have " + (lives - mistakes) + " tries left.");
        }
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
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
