/** READ FIRST
 *
 * Mastermind is a code-breaking game for two players: the code-maker picks
 * a secret code and the code-breaker gets a limited number of guesses, with
 * a hint after each one. You are writing a letters-only version, in the
 * style of an AP Computer Science A free-response question.
 *
 * The hidden word contains only capital letters. A guess contains only
 * capital letters and has the SAME length as the hidden word. After a guess,
 * the player gets a hint of the same length. Each position in the hint
 * describes the letter in the same position of the guess:
 *
 *   If the letter in the guess is...                 the hint character is
 *   in the hidden word at the SAME position          the letter itself
 *   in the hidden word but at a DIFFERENT position   '+'
 *   not in the hidden word at all                    '*'
 *
 * Only these three cases exist. Check "same position" first, then "anywhere
 * in the hidden word", then "not present". Repeated letters get no special
 * treatment: every position of the guess is judged on its own.
 *
 * Example: MasterMind puzzle = new MasterMind("LIGHT");
 *   Call                        Returns
 *   puzzle.getHint("TTTTT")     "++++T"     T is in LIGHT (position 4); only the last T is in place
 *   puzzle.getHint("MOUNT")     "****T"     M, O, U, N are not in LIGHT
 *   puzzle.getHint("HABIT")     "+**+T"     H and I are in LIGHT but in other positions
 *   puzzle.getHint("FIGHT")     "*IGHT"     F is absent; I, G, H, T are all in place
 *   puzzle.getHint("LIGHT")     "LIGHT"     a perfect guess echoes the word
 *
 * You write the WHOLE class: the private instance variable(s), the
 * constructor body and the getHint body. The headers below are given so the
 * tests compile; do not change them. You may assume every guess has the
 * same length as the hidden word.
 */
public class MasterMind {
    /* INSERT YOUR INSTANCE VARIABLES BELOW
     * They must be private.
     */



    /** COMPLETE THIS CONSTRUCTOR
     * Precondition: word is not null and contains only capital letters
     * Stores word as the hidden word.
     * Example: new MasterMind("LIGHT")
     */
    public MasterMind(String word) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Precondition: guess.length() == the hidden word's length; capital letters only
     * Returns a hint String of the same length as guess. For each index i:
     * the letter itself if guess.charAt(i) equals the hidden word's charAt(i);
     * otherwise '+' if that letter occurs anywhere in the hidden word;
     * otherwise '*'.
     * Example: new MasterMind("LIGHT").getHint("HABIT") is "+**+T".
     * Hint: build the result one character at a time with String concatenation;
     * hidden.indexOf(guess.charAt(i)) is -1 exactly when the letter is absent.
     */
    public String getHint(String guess) {
        // Insert your code below

        return "";
    }

    /** PROVIDED - a driver you can run by hand. The autograder ignores it. */
    public static void main(String[] args) {
        MasterMind puzzle = new MasterMind("LIGHT");
        System.out.println("Expected: ++++T   Result: " + puzzle.getHint("TTTTT"));
        System.out.println("Expected: ****T   Result: " + puzzle.getHint("MOUNT"));
        System.out.println("Expected: +**+T   Result: " + puzzle.getHint("HABIT"));
        System.out.println("Expected: *IGHT   Result: " + puzzle.getHint("FIGHT"));
        System.out.println("Expected: LIGHT   Result: " + puzzle.getHint("LIGHT"));
    }
}
