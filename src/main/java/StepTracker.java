/** READ FIRST
 *
 * This is the 2019 AP Computer Science A free-response question 2.
 *
 * A StepTracker records the number of steps a person takes each day. The
 * constructor takes one int: the minimum number of steps that makes a day
 * count as "active". The class provides these methods:
 *
 *   addDailySteps(int steps)   records one day's step count
 *   activeDays()               returns how many recorded days were active
 *                              (steps >= the minimum given to the constructor)
 *   averageSteps()             returns the average steps per day over ALL
 *                              recorded days (total steps / number of days),
 *                              as a double; 0.0 if no days have been recorded
 *
 * You write the WHOLE class: the private instance variables, the
 * constructor body, and the three method bodies. The method headers below
 * are given so the tests compile; do not change them. Think about what the
 * object has to remember - you will need at least four instance variables:
 * the active threshold, the number of days recorded, the total steps, and
 * the number of active days.
 *
 * Example:
 *   Statement                            Returns   Comment
 *   StepTracker tr = new StepTracker(10000);       days with at least 10,000 steps are active
 *   tr.activeDays();                     0         nothing recorded yet
 *   tr.averageSteps();                   0.0       nothing recorded yet - no division by zero!
 *   tr.addDailySteps(9000);                        too few steps to be active
 *   tr.addDailySteps(5000);                        too few steps to be active
 *   tr.activeDays();                     0         no day reached 10,000
 *   tr.averageSteps();                   7000.0    14000 / 2
 *   tr.addDailySteps(13000);                       this day is active
 *   tr.activeDays();                     1         one of three days is active
 *   tr.averageSteps();                   9000.0    27000 / 3
 *
 * A day with EXACTLY the minimum number of steps is active. The average
 * counts every day, active or not, and must be a real double: two days of
 * 10000 and 10001 steps average 10000.5, not 10000.0.
 */
public class StepTracker {
    /* INSERT YOUR INSTANCE VARIABLES BELOW
     * They must be private.
     */



    /** COMPLETE THIS CONSTRUCTOR
     * Precondition: minSteps > 0
     * Creates a tracker with no days recorded. A day is active when its step
     * count is at least minSteps.
     * Example: new StepTracker(10000) - then activeDays() is 0 and
     * averageSteps() is 0.0.
     */
    public StepTracker(int minSteps) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Precondition: steps >= 0
     * Records one day with the given number of steps: one more day tracked,
     * steps added to the running total, and one more active day if
     * steps >= the minimum.
     * Example: after addDailySteps(9000) and addDailySteps(5000) on a
     * StepTracker(10000), averageSteps() is 7000.0 and activeDays() is 0.
     */
    public void addDailySteps(int steps) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns the number of recorded days whose step count was at least the
     * minimum. 0 before any day is recorded.
     * Example: StepTracker(10000) after 9000, 5000, 13000 -> 1.
     */
    public int activeDays() {
        // Insert your code below

        return 0;
    }

    /** COMPLETE THIS METHOD
     * Returns the average number of steps per recorded day: total steps
     * divided by number of days, as a double. Returns 0.0 if no days have
     * been recorded (do not divide by zero).
     * Example: StepTracker(10000) after 9000, 5000, 13000 -> 9000.0.
     * Hint: int / int is an int. Cast one side to double before dividing.
     */
    public double averageSteps() {
        // Insert your code below

        return 0.0;
    }

    /** PROVIDED - a driver you can run by hand. The autograder ignores it. */
    public static void main(String[] args) {
        StepTracker tr = new StepTracker(10000);
        System.out.println("Expected: 0       Result: " + tr.activeDays());
        System.out.println("Expected: 0.0     Result: " + tr.averageSteps());
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        System.out.println("Expected: 0       Result: " + tr.activeDays());
        System.out.println("Expected: 7000.0  Result: " + tr.averageSteps());
        tr.addDailySteps(13000);
        System.out.println("Expected: 1       Result: " + tr.activeDays());
        System.out.println("Expected: 9000.0  Result: " + tr.averageSteps());
    }
}
