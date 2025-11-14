package studystreak;

import java.time.LocalDate;
import java.util.Scanner;

public class StudyTracker {

    private LocalDate lastDate;  // stores the last day you logged studying
    private int streak;          // how many days in a row you’ve studied
    private FileManager fileManager;  // handles loading & saving streak data

    public StudyTracker() {
        fileManager = new FileManager();

        // Load previous streak info from the file (if it exists)
        lastDate = fileManager.loadLastDate();
        streak = fileManager.loadStreak();
    }

    public void start() {
        System.out.println("Welcome back to StudyStreak !!!");
        System.out.println("Current streak: " + streak + " days");
        System.out.println("Did you study today? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim().toLowerCase();

        if (input.equals("yes")) {
            updateStreak();   // update streak based on today's date
        } else {
            System.out.println("You did not study today !");
        }

        // Show final value and save the updated streak
        System.out.println("Final streak: " + streak + " days");
        fileManager.save(lastDate, streak);
    }

    private void updateStreak() {
        LocalDate today = LocalDate.now();  // get today's date

        // If this is the first time running the app (no previous date saved)
        if (lastDate == null) {
            streak = 1;
        }
        // If last logged date is exactly 1 day before today -> continue streak
        else if (lastDate.equals(today.minusDays(1))) {
            streak++;
        }
        // If you already logged today, avoid double logging
        else if (lastDate.equals(today)) {
            System.out.println("You already logged today !");
            return;
        }
        // If it's been more than a day -> reset streak
        else {
            streak = 1;
        }

        // Save today's date as the new lastDate
        lastDate = today;
        System.out.println("Streak updated !! ");
    }
}