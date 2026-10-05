import java.util.ArrayList;
import java.util.List;

public class WeeklyStudyLog {

    private String studentName;
    private List<List<Integer>> weeklyStudyTimes;

    /**
     * Create a weekly log for the specified student.
     *
     * Pre:
     * - studentName is not null.
     */
    public WeeklyStudyLog(String studentName) {
        this.studentName = studentName;
        this.weeklyStudyTimes = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            weeklyStudyTimes.add(new ArrayList<>());
        }
    }

    public String getStudentName() {
        return studentName;
    }

    /**
     * Log a study session for the specified day.
     *
     * Pre:
     * - day is between 0 and 6.
     * - minutes > 0.
     */
    public void recordStudySession(int day, int minutes) {
        weeklyStudyTimes.get(day).add(minutes);
    }

    /**
     * Returns the total minutes studied throughout the week.
     *
     * Pre:
     * - The stored times are positive integers.
     */
    public int getWeeklyStudyTime() {
        int total = 0;
        for (List<Integer> day : weeklyStudyTimes) {
            for (int minutes : day) {
                total += minutes;
            }
        }
        return total;
    }

    /**
     * Returns a list containing the total minutes studied each day.
     *
     * Pre:
     * - weeklyStudyTimes has exactly 7 lists, one for each day
     */
    public List<Integer> getDailyStudyTimes() {
        List<Integer> dailyTimes = new ArrayList<>();
        for (List<Integer> day : weeklyStudyTimes) {
            int total = 0;
            for (int minutes : day) {
                total += minutes;
            }
            dailyTimes.add(total);
        }
        return dailyTimes;
    }

    /**
     * Returns the index of the day with the longest average session duration.
     *
     * If there is a tie across several days, the first one is returned.
     * Days without sessions are not taken into account.
     * If there are no sessions throughout the week, it returns -1.
     *
     * Pre:
     * - weeklyStudyTimes has exactly 7 lists, one for each day
     */
    public int getDayWithHighestAverage() {
        int bestDay = -1;
        double highestAverage = 0.0;

        for (int i = 0; i < weeklyStudyTimes.size(); i++) {
            List<Integer> day = weeklyStudyTimes.get(i);
            if (!day.isEmpty()) {
                int total = 0;
                for (int minutes : day) {
                    total += minutes;
                }
                double average = (double) total / day.size();
                if (bestDay == -1 || average > highestAverage) {
                    highestAverage = average;
                    bestDay = i;
                }
            }
        }
        return bestDay;
    }

    /**
     * Returns the duration of the longest session of the entire week.
     * If no sessions exist, it returns 0.
     *
     * Pre:
     * - The stored times are positive integers.
     */
    public int getLongestSession() {
        int longest = 0;
        for (List<Integer> day : weeklyStudyTimes) {
            for (int minutes : day) {
                if (minutes > longest) {
                    longest = minutes;
                }
            }
        }
        return longest;
    }

    /**
     * Returns the largest absolute difference between the durations
     * of two consecutive sessions held on the same day.
     *
     * The sessions at the end of one day and the beginning of the next
     * are not considered consecutive.
     *
     * If no day has at least two sessions, it returns 0.
     *
     * Pre:
     * - The stored times are positive integers.
     */
    public int getLargestConsecutiveDifference() {
        int largestDifference = 0;
        for (List<Integer> day : weeklyStudyTimes) {
            for (int i = 0; i < day.size() - 1; i++) {
                int difference =
                        Math.abs(day.get(i) - day.get(i + 1));
                if (difference > largestDifference) {
                    largestDifference = difference;
                }
            }
        }
        return largestDifference;
    }
}