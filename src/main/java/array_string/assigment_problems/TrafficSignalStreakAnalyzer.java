package array_string.assigment_problems;

public class TrafficSignalStreakAnalyzer {
    void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) {
            System.out.println("No signal readings");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestLength = 1;
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentLength++;
            } else {
                currentLength = 1;
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + longestColor
                + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        TrafficSignalStreakAnalyzer analyzer = new TrafficSignalStreakAnalyzer();
        analyzer.findLongestStreak("RRGGGYRR");
    }
}
