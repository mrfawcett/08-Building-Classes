# Building Classes: StepTracker and MasterMind

**Unit 2A — Classes, Methods & Strings** · Pairs with lecture 2.1 Building a Class (Day 1–2) for `StepTracker` and 2.3 Strings (Day 1) for `MasterMind`

Up to now the class skeleton has been handed to you and you filled in a
method. This time you build **two whole classes** from a description: you
decide what the object needs to remember, declare those instance
variables, write the constructor that sets them up, and write the methods
that use them. That is the skill the AP exam's class-design free-response
question tests every single year.

- **`StepTracker`** is the **2019 AP Computer Science A free-response
  question 2**, reproduced in full. It records daily step counts and
  reports how many days were "active" and the average steps per day.
- **`MasterMind`** is a letters-only version of the code-breaking game,
  written in the style of an AP free-response question. It holds a hidden
  word and produces a hint for each guess.

Do `StepTracker` first (after 2.1). `MasterMind` needs `charAt` and
`indexOf` from 2.3.

---

## What you are given

| File | Status | Purpose |
|---|---|---|
| `src/main/java/StepTracker.java` | **you complete this** | method headers only; you add the fields and every body |
| `src/main/java/MasterMind.java` | **you complete this** | method headers only; you add the field and every body |
| `src/test/java/StepTrackerTest.java` | provided | the autograder's tests for the constructor, `addDailySteps`, `activeDays` — read them |
| `src/test/java/AverageStepsTest.java` | provided | the autograder's tests for `averageSteps` — read them |
| `src/test/java/GetHintTest.java` | provided | the autograder's tests for `MasterMind` — read them |
| `pom.xml`, `grading.json`, `.gitignore` | provided | build and grading setup — do not edit |

Both starter files have a `main` you can run. It prints `Expected: ...
Result: ...` lines from the original problem. The autograder never runs
`main`; it only runs the tests.

The method headers are already in place so that the tests compile before
you start. **Do not change them.** Every body currently does nothing (or
returns `0` / `0.0` / `""`), so every rubric line fails until you write it.

## What to write

| Class / member | Points | What it does |
|---|---|---|
| `StepTracker` instance variables | (in 30) | `private`. You need at least four — see below. |
| `StepTracker(int minSteps)` | (in 30) | Remembers the active threshold; starts with nothing recorded. |
| `void addDailySteps(int steps)` | (in 30) | Records one day: one more day, `steps` added to the total, one more active day if `steps >= minSteps`. |
| `int activeDays()` | 30 | Number of recorded days with at least `minSteps` steps. |
| `double averageSteps()` | 20 | Total steps ÷ number of days, as a `double`; `0.0` if no days recorded. |
| `MasterMind` instance variable(s) | (in 50) | `private`. The hidden word. |
| `MasterMind(String word)` | (in 50) | Stores the hidden word. |
| `String getHint(String guess)` | 50 | The hint string described below. |

### `StepTracker`

Ask: *what must the object remember between method calls?*

```
StepTracker
  private int minSteps      the threshold passed to the constructor
  private int numDays       how many days have been recorded
  private int totalSteps    the sum of all recorded steps
  private int numActive     how many recorded days had steps >= minSteps
```

(You may name them differently. You may not make them `static` — two
trackers must not share data, and the tests create two at once.)

`addDailySteps` updates three of them. `activeDays` and `averageSteps` just
report.

**Trap 1 — the boundary.** A day with *exactly* `minSteps` steps is active.
Use `>=`, not `>`.

**Trap 2 — integer division.** `totalSteps / numDays` with two `int`s
throws away the fraction: `20001 / 2` is `10000`, not `10000.5`. Cast first:
`(double) totalSteps / numDays`.

**Trap 3 — dividing by zero.** Before any day is recorded, `numDays` is 0.
`averageSteps` must return `0.0` in that case, not crash. Check for it.

**Trap 4 — the average counts every day.** A day with 0 steps is still a
day. `12000` then `0` averages to `6000.0`.

### `MasterMind.getHint`

The hidden word and the guess are capital letters of the same length. The
hint has one character per position of the guess:

| If the letter in the guess is... | the hint character is |
|---|---|
| in the hidden word **at the same position** | the letter itself |
| in the hidden word, but at a **different position** | `+` |
| **not** in the hidden word | `*` |

Check the cases **in that order**. A letter that is in place is also
"somewhere in the word", so if you test "present anywhere" first, a perfect
guess comes back as `+++++` instead of the word.

Build the answer one character at a time:

```java
String hint = "";
for (int i = 0; i < guess.length(); i++) {
    char c = guess.charAt(i);
    if (/* c is at position i of the hidden word */)        hint += c;
    else if (/* c occurs anywhere in the hidden word */)    hint += "+";
    else                                                    hint += "*";
}
return hint;
```

`hidden.indexOf(c) != -1` tells you whether `c` occurs anywhere.

**Trap:** do not use `hidden.indexOf(c) == i` to decide "in place". If the
hidden word repeats a letter — `JAVA` — `indexOf('A')` is always `1`, so the
`A` at position 3 would be judged misplaced. Compare
`guess.charAt(i) == hidden.charAt(i)` directly.

Repeated letters get **no** special handling beyond that. In real
Mastermind a letter can only be "used" once; here every position of the
guess is judged on its own, which is why `TTTTT` against `LIGHT` is
`++++T` and not `****T`.

## Examples

`StepTracker tr = new StepTracker(10000);`

| Statement | Returns | Comment |
|---|---|---|
| `tr.activeDays()` | `0` | nothing recorded yet |
| `tr.averageSteps()` | `0.0` | nothing recorded yet |
| `tr.addDailySteps(9000)` | | not active |
| `tr.addDailySteps(5000)` | | not active |
| `tr.activeDays()` | `0` | no day reached 10,000 |
| `tr.averageSteps()` | `7000.0` | 14000 / 2 |
| `tr.addDailySteps(13000)` | | active |
| `tr.activeDays()` | `1` | one of three days |
| `tr.averageSteps()` | `9000.0` | 27000 / 3 |

`MasterMind puzzle = new MasterMind("LIGHT");`

| Call | Returns | Why |
|---|---|---|
| `puzzle.getHint("TTTTT")` | `"++++T"` | T is in LIGHT; only the last one is in place |
| `puzzle.getHint("MOUNT")` | `"****T"` | M, O, U, N are absent |
| `puzzle.getHint("HABIT")` | `"+**+T"` | H and I present but misplaced; A and B absent |
| `puzzle.getHint("FIGHT")` | `"*IGHT"` | F absent; I, G, H, T in place |
| `puzzle.getHint("LIGHT")` | `"LIGHT"` | perfect guess |

One more to trace: `new MasterMind("JAVA").getHint("AAAA")` is `"+A+A"`.

## Running the tests

`mvn test` runs everything; `mvn test -Dtest=<ClassName>` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `StepTrackerTest` | StepTracker: constructor, `addDailySteps`, `activeDays` | 30 |
| `AverageStepsTest` | `StepTracker.averageSteps` | 20 |
| `GetHintTest` | MasterMind: constructor and `getHint` | 50 |

The autograder awards a rubric line only when **every** test in that class
passes. `AverageStepsTest` includes the `10000.5` case, so integer division
costs the whole 20 points, not one test.

## Suggested order

1. `StepTracker`: declare the four fields, write the constructor. Run
   `StepTrackerTest` — the "0 before any day" test should pass already.
2. `addDailySteps` and `activeDays`. Run `StepTrackerTest`. If the
   "exactly 10000" test fails, fix `>` to `>=`.
3. `averageSteps` with the zero-days guard and the cast. Run
   `AverageStepsTest`. Run `main` and compare with the table.
4. `MasterMind`: one field, one-line constructor, then `getHint` with the
   three-way `if`. Run `GetHintTest`. If `LIGHT` gives `+++++`, your cases
   are in the wrong order. If `JAVA`/`AAAA` fails, you used `indexOf` to
   test "in place".

## Rules of the road

- AP Java subset only: `int`, `double`, `String`, `charAt`, `indexOf`,
  `length`, `if`/`else`, `for`, String concatenation. No `StringBuilder`,
  no arrays, no `ArrayList` — neither class needs a collection.
- All instance variables `private`. None `static`.
- Do not change the method headers or the provided `main` methods.
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.
- `StepTracker` is a released exam question. Write it from the description
  above without looking up the scoring guidelines — that is the practice
  you need for May.
