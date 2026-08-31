import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** StepTracker.averageSteps - 2019 FRQ 2. All doubles compared with a tolerance. */
public class AverageStepsTest {
    private static final double DELTA = 0.0001;

    @DisplayName("averageSteps: 0.0 before any day is recorded (no division by zero)")
    @Test
    void averageSteps_Test01() {
        StepTracker tr = new StepTracker(10000);
        assertEquals(0.0, tr.averageSteps(), DELTA,
            "with no days recorded the average is 0.0; guard against dividing by zero");
    }

    @DisplayName("averageSteps: after 9000 and 5000 steps -> 7000.0 (14000 / 2)")
    @Test
    void averageSteps_Test02() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        assertEquals(7000.0, tr.averageSteps(), DELTA, "14000 total steps over 2 days");
    }

    @DisplayName("averageSteps: after 9000, 5000, 13000 steps -> 9000.0 (27000 / 3) (the FRQ table)")
    @Test
    void averageSteps_Test03() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        tr.addDailySteps(13000);
        assertEquals(9000.0, tr.averageSteps(), DELTA, "27000 total steps over 3 days");
    }

    @DisplayName("averageSteps: a single day of 12345 steps -> 12345.0")
    @Test
    void averageSteps_Test04() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(12345);
        assertEquals(12345.0, tr.averageSteps(), DELTA, "one day: the average is that day's count");
    }

    @DisplayName("averageSteps: 10000 and 10001 -> 10000.5, not 10000.0 (integer division trap)")
    @Test
    void averageSteps_Test05() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(10000);
        tr.addDailySteps(10001);
        assertEquals(10000.5, tr.averageSteps(), DELTA,
            "20001 / 2 must be 10000.5; if you got 10000.0 you divided int by int - cast to double first");
    }

    @DisplayName("averageSteps: 1, 2, 2 -> 1.6667 (5 / 3 as a double)")
    @Test
    void averageSteps_Test06() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(1);
        tr.addDailySteps(2);
        tr.addDailySteps(2);
        assertEquals(5.0 / 3.0, tr.averageSteps(), DELTA, "5 total steps over 3 days is 1.666...");
    }

    @DisplayName("averageSteps: counts every day, not only active days (12000 then 0 -> 6000.0)")
    @Test
    void averageSteps_Test07() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(12000);
        tr.addDailySteps(0);
        assertEquals(6000.0, tr.averageSteps(), DELTA,
            "a day with 0 steps is still a recorded day: 12000 / 2 = 6000.0, not 12000.0");
    }

    @DisplayName("averageSteps: the average changes as days are added (running total, not the last day)")
    @Test
    void averageSteps_Test08() {
        StepTracker tr = new StepTracker(10000);
        tr.addDailySteps(4000);
        assertEquals(4000.0, tr.averageSteps(), DELTA, "after one day of 4000");
        tr.addDailySteps(8000);
        assertEquals(6000.0, tr.averageSteps(), DELTA, "after 4000 and 8000: 12000 / 2");
        tr.addDailySteps(6000);
        assertEquals(6000.0, tr.averageSteps(), DELTA, "after 4000, 8000, 6000: 18000 / 3");
        tr.addDailySteps(2000);
        assertEquals(5000.0, tr.averageSteps(), DELTA, "after 4000, 8000, 6000, 2000: 20000 / 4");
    }

    @DisplayName("averageSteps: two trackers keep separate totals (instance variables, not static)")
    @Test
    void averageSteps_Test09() {
        StepTracker a = new StepTracker(10000);
        StepTracker b = new StepTracker(10000);
        a.addDailySteps(10000);
        b.addDailySteps(2000);
        assertEquals(10000.0, a.averageSteps(), DELTA, "tracker a has one day of 10000");
        assertEquals(2000.0, b.averageSteps(), DELTA, "tracker b has one day of 2000 and must not see a's data");
    }
}
