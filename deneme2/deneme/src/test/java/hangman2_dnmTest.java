
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hangman2_dnm.HangmanTwo;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.*;

public class hangman2_dnmTest {
    @Test
    public void testCorrect() {
        String input = "abacus";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Assertions.assertEquals(true, HangmanTwo.getPlayerGuess(in,
                "abacus", new ArrayList<>() ));
    }
    @Test
    public void testInCorrect() {
        String input = "abc";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Assertions.assertEquals(false, HangmanTwo.getPlayerGuess(in,
                "abacus", new ArrayList<>() ));
    }

}
