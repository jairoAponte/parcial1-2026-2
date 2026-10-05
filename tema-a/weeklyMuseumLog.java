import java.util.ArrayList;
import java.util.List;

public class WeeklyMuseumLog {

    private String museumName;
    private List<List<Integer>> weeklyVisitors;

    /**
     * Create a weekly log for the specified museum.
     *
     * Pre:
     * - museumName is not null.
     */
    public WeeklyMuseumLog(String museumName) {
        this.museumName = museumName;
        this.weeklyVisitors = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            weeklyVisitors.add(new ArrayList<>());
        }
    }

    public String getMuseumName() {
        return museumName;
    }

    /**
     * Records the number of visitors on the indicated day.
     *
     * Pre:
     * - day is between 0 and 6.
     * - visitors >= 0.
     */
    public void recordVisitors(int day, int visitors) {
        weeklyVisitors.get(day).add(visitors);
    }

    /**
     * Returns the total number of visitors recorded throughout the week.
     *
     * Pre:
     * - The stored records are integers greater than or equal to 0.
     */
    public int getWeeklyTotalVisitors() {
        int total = 0;
        for (List<Integer> day : weeklyVisitors) {
            for (int visitors : day) {
                total += visitors;
            }
        }
        return total;
    }

    /**
     * Returns a list containing the total number of visitors for each day.
     *
     * Pre:
     * - weeklyVisitors has exactly 7 lists, one for each day.
     */
    public List<Integer> getDailyTotals() {
        List<Integer> totals = new ArrayList<>();

        for (List<Integer> day : weeklyVisitors) {
            int total = 0;
            for (int visitors : day) {
                total += visitors;
            }
            totals.add(total);
        }
        return totals;
    }

    /**
     * Returns the index of the day with the highest total number of visitors.
     * If multiple days have the same maximum total, return the first one.
     * If there are no registered visitors during the week, it returns -1.
     *
     * Pre:
     * - weeklyVisitors has exactly 7 lists, one for each day.
     */
    public int getBusiestDay() {
        int bestDay = -1;
        int largestTotal = 0;

        for (int i = 0; i < weeklyVisitors.size(); i++) {
            int total = 0;
            for (int visitors : weeklyVisitors.get(i)) {
                total += visitors;
            }
            if (total > largestTotal) {
                largestTotal = total;
                bestDay = i;
            }
        }
        return bestDay;
    }

    /**
     * Returns the highest individual visitor count for the specified day.
     * If the day contains no records, it returns 0.
     *
     * Pre:
     * - day is between 0 and 6.
     */
    public int getLargestRecordOfDay(int day) {
        int largest = 0;

        for (int visitors : weeklyVisitors.get(day)) {
            if (visitors > largest) {
                largest = visitors;
            }
        }
        return largest;
    }

    /**
     * Returns a defensive copy of the records for the specified day.
     *
     * Pre:
     * - day is between 0 and 6.
     */
    public List<Integer> getRecordsOfDay(int day) {
        return new ArrayList<>(weeklyVisitors.get(day));
    }
}