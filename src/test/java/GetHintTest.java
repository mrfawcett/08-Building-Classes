import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** MasterMind constructor + getHint. */
public class GetHintTest {
    @DisplayName("getHint: LIGHT / \"TTTTT\" -> \"++++T\" (T is present; only position 4 is in place)")
    @Test
    void getHint_Test01() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("++++T", puzzle.getHint("TTTTT"),
            "T is in LIGHT at position 4: '+' for the first four, 'T' for the last");
    }

    @DisplayName("getHint: LIGHT / \"MOUNT\" -> \"****T\" (M, O, U, N are absent)")
    @Test
    void getHint_Test02() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("****T", puzzle.getHint("MOUNT"), "letters not in LIGHT become '*'");
    }

    @DisplayName("getHint: LIGHT / \"HABIT\" -> \"+**+T\" (H and I present but misplaced)")
    @Test
    void getHint_Test03() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("+**+T", puzzle.getHint("HABIT"),
            "H and I are in LIGHT at other positions ('+'); A and B are absent ('*'); T is in place");
    }

    @DisplayName("getHint: LIGHT / \"FIGHT\" -> \"*IGHT\" (four letters in place, F absent)")
    @Test
    void getHint_Test04() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("*IGHT", puzzle.getHint("FIGHT"), "F is absent; I, G, H, T match their positions");
    }

    @DisplayName("getHint: LIGHT / \"LIGHT\" -> \"LIGHT\" (perfect guess; check same-position BEFORE present-elsewhere)")
    @Test
    void getHint_Test05() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("LIGHT", puzzle.getHint("LIGHT"),
            "every letter is in place, so the hint is the word itself, not \"+++++\"");
    }

    @DisplayName("getHint: LIGHT / \"ZZZZZ\" -> \"*****\" (nothing present)")
    @Test
    void getHint_Test06() {
        MasterMind puzzle = new MasterMind("LIGHT");
        assertEquals("*****", puzzle.getHint("ZZZZZ"), "no Z in LIGHT, so five stars");
    }

    @DisplayName("getHint: hint has the same length as the guess (CODE / \"DECO\" -> \"++++\")")
    @Test
    void getHint_Test07() {
        MasterMind puzzle = new MasterMind("CODE");
        String hint = puzzle.getHint("DECO");
        assertEquals(4, hint.length(), "a 4-letter guess gets a 4-character hint; do not hard-code 5");
        assertEquals("++++", hint, "D, E, C, O are all in CODE but none is in its own position");
    }

    @DisplayName("getHint: CODE / \"CAKE\" -> \"C**E\" (first and last in place)")
    @Test
    void getHint_Test08() {
        MasterMind puzzle = new MasterMind("CODE");
        assertEquals("C**E", puzzle.getHint("CAKE"), "C and E match their positions; A and K are absent");
    }

    @DisplayName("getHint: repeated letters in the guess - JAVA / \"AAAA\" -> \"+A+A\"")
    @Test
    void getHint_Test09() {
        MasterMind puzzle = new MasterMind("JAVA");
        assertEquals("+A+A", puzzle.getHint("AAAA"),
            "positions 1 and 3 hold A in JAVA, so they show 'A'; positions 0 and 2 show '+'. "
            + "Compare charAt(i) to charAt(i); do not rely on indexOf(letter) == i");
    }

    @DisplayName("getHint: repeated letters in the hidden word - MOON / \"NOON\" -> \"+OON\"")
    @Test
    void getHint_Test10() {
        MasterMind puzzle = new MasterMind("MOON");
        assertEquals("+OON", puzzle.getHint("NOON"), "N at position 0 is present elsewhere; O, O, N are in place");
    }

    @DisplayName("getHint: APPLE / \"PEARS\" -> \"+++**\" (a different hidden word; the word comes from the constructor)")
    @Test
    void getHint_Test11() {
        MasterMind puzzle = new MasterMind("APPLE");
        assertEquals("+++**", puzzle.getHint("PEARS"),
            "P, E, A are in APPLE but misplaced; R and S are absent. The hidden word must be stored by the constructor");
    }

    @DisplayName("getHint: one-letter word - A / \"A\" -> \"A\" and A / \"B\" -> \"*\"")
    @Test
    void getHint_Test12() {
        MasterMind puzzle = new MasterMind("A");
        assertEquals("A", puzzle.getHint("A"), "single matching letter");
        assertEquals("*", puzzle.getHint("B"), "single absent letter");
    }
}
