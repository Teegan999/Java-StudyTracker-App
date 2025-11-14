package studystreak;

import java.io.*;
import java.time.LocalDate;

public class FileManager {

    // The file where we store the streak data
    private static final String FILE_NAME = "streak.txt";

    // Saves the last study date + streak number into the file
    public void save(LocalDate lastDate, int streak) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            // First line: last date the user studied
            pw.println(lastDate);

            // Second line: the streak count
            pw.println(streak);

        } catch (IOException e) {
            System.out.println("Error saving streak.");
        }
    }

    // Loads the last study date from the file
    public LocalDate loadLastDate() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            // Read the first line (the stored date)
            String dateStr = br.readLine();

            // Convert the string back into a LocalDate
            return LocalDate.parse(dateStr);

        } catch (Exception e) {
            // If the file doesn't exist or is empty -> return null
            return null;
        }
    }

    // Loads the streak count from the file
    public int loadStreak() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            // Skip the first line (the date)
            br.readLine();

            // Second line is the streak number
            return Integer.parseInt(br.readLine());

        } catch (Exception e) {
            // If file missing or invalid -> start at 0
            return 0;
        }
    }
}