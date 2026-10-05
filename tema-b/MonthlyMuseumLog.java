import java.util.ArrayList;
import java.util.List;

public class MonthlyMuseumLog {

    private String museumName;
    private List<List<Integer>> monthlyVisitors;

    /**
     * Create a monthly log for the specified museum.
     *
     * Pre:
     * - museumName is not null.
     */
    public MonthlyMuseumLog(String museumName) {
        this.museumName = museumName;
        this.monthlyVisitors = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            monthlyVisitors.add(new ArrayList<>());
        }
    }

    public String getMuseumName() {
        return museumName;
    }

    /**
     * Records the number of visitors on the indicated day.
     *
     * Pre:
     * - day is between 0 and 29.
     * - visitors >= 0.
     */
    public void recordVisitors(int day, int visitors) {
        monthlyVisitors.get(day).add(visitors);
    }

    /**
     * Returns the total number of records created during the month.
     *
     * Pre:
     * - monthlyVisitors has exactly 30 lists, one for each day.
     */
    public int getNumberOfRecords() {
        int count = 0;
        for (List<Integer> day : monthlyVisitors) {
            count += day.size();
        }
        return count;
    }

    /**
     * Returns a list containing the average number of registered visitors per day.
     * If there are no records for a given day, it returns 0.0 for that position.
     *
     * Pre:
     * - monthlyVisitors has exactly 30 lists, one for each day.
     */
    public List<Double> getDailyAverages() {
        List<Double> averages = new ArrayList<>();

        for (List<Integer> day : monthlyVisitors) {
            double sum = 0;
            for (int visitors : day) {
                sum += visitors;
            }
            if (day.isEmpty()) {
                averages.add(0.0);
            } else {
                averages.add(sum / day.size());
            }
        }
        return averages;
    }

    /**
     * Returns the index of the day with the highest number of records.
     * If there is a tie across several days, the first one is returned.
     * If there are no records for the entire month, it returns -1.
     *
     * Pre:
     * - monthlyVisitors has exactly 30 lists, one for each day.
     */
    public int getDayWithMostRecords() {
        int bestDay = -1;
        int largestNumberOfRecords = 0;
        for (int i = 0; i < monthlyVisitors.size(); i++) {
            int numberOfRecords = monthlyVisitors.get(i).size();
            if (numberOfRecords > largestNumberOfRecords) {
                largestNumberOfRecords = numberOfRecords;
                bestDay = i;
            }
        }
        return bestDay;
    }

    /**
     * Count how many records for the entire month are strictly greater than the limit.
     *
     * Pre:
     * - The stored records are non-negative integers.
     */
    public int countRecordsAbove(int limit) {
        int count = 0;
        for (List<Integer> day : monthlyVisitors) {
            for (int visitors : day) {
                if (visitors > limit) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns a single list with all records for the month,
     * maintaining the order of the days and the order of recording
     * within in day.
     *
     * Pre:
     * - monthlyVisitors has exactly 30 lists, one for each day.
     */
    public List<Integer> getAllRecords() {
        List<Integer> allRecords = new ArrayList<>();
        for (List<Integer> day : monthlyVisitors) {
            for (int visitors : day) {
                allRecords.add(visitors);
            }
        }
        return allRecords;
    }
}