import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** StepTracker constructor, addDailySteps and activeDays - 2019 FRQ 2. */
public class StepTrackerTest {
    @DisplayName("activeDays: 0 before any day is recorded (new StepTracker(10000))")
    @Test
    void stepTracker_Test01() {
        StepTracker tr = new StepTracker(10000);
        assertEquals(0, tr.activeDays(), "no days recorded yet, so no active days");
    }

    @DisplayName("activeDays: after 9000 and 5000 steps, still 0 (both below 10000)")
    @Test
    void stepTracker_Test02() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        assertEquals(0, tr.activeDays(), "9000 and 5000 are both under the 10000 minimum");
    }

    @DisplayName("activeDays: after 9000, 5000, 13000 steps -> 1 (the FRQ table)")
    @Test
    void stepTracker_Test03() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        tr.addDailySteps(13000);
        assertEquals(1, tr.activeDays(), "only the 13000-step day reached 10000");
    }

    @DisplayName("activeDays: exactly 10000 steps counts as active (>=, not >)")
    @Test
    void stepTracker_Test04() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(10000);
        assertEquals(1, tr.activeDays(), "a day with exactly the minimum number of steps is active; use >=");
    }

    @DisplayName("activeDays: 9999 steps does not count as active")
    @Test
    void stepTracker_Test05() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(9999);
        assertEquals(0, tr.activeDays(), "one step short of the minimum is not active");
    }

    @DisplayName("activeDays: five active days in a row -> 5 (counts every active day, not just the last)")
    @Test
    void stepTracker_Test06() {
        StepTracker tr = new StepTracker(10000);
        for (int i = 0; i < 5; i++) tr.addDailySteps(12000);
        assertEquals(5, tr.activeDays(), "all five days had 12000 >= 10000 steps");
    }

    @DisplayName("activeDays: mixed week 10000, 3000, 15000, 9999, 0, 10001, 500 -> 3")
    @Test
    void stepTracker_Test07() {
        StepTracker tr = new StepTracker(10000);
        int[] week = {10000, 3000, 15000, 9999, 0, 10001, 500};
        for (int i = 0; i < week.length; i++) tr.addDailySteps(week[i]);
        assertEquals(3, tr.activeDays(), "10000, 15000 and 10001 are active; the other four are not");
    }

    @DisplayName("activeDays: the minimum comes from the constructor, not a hard-coded 10000 (StepTracker(500))")
    @Test
    void stepTracker_Test08() {
        StepTracker low = new StepTracker(500);
        low.addDailySteps(600);
        low.addDailySteps(499);
        assertEquals(1, low.activeDays(), "with a minimum of 500, 600 is active and 499 is not");

        StepTracker high = new StepTracker(20000);
        high.addDailySteps(15000);
        assertEquals(0, high.activeDays(), "with a minimum of 20000, 15000 is not active");
    }

    @DisplayName("activeDays: two trackers keep separate counts (instance variables, not static)")
    @Test
    void stepTracker_Test09() {
        StepTracker a = new StepTracker(10000);
        StepTracker b = new StepTracker(10000);
        a.addDailySteps(12000);
        a.addDailySteps(11000);
        b.addDailySteps(1000);
        assertEquals(2, a.activeDays(), "tracker a recorded two active days");
        assertEquals(0, b.activeDays(), "tracker b recorded one inactive day and must not see a's data");
    }
}
