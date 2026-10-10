package array_string.assigment_problems;

public class TypingSpeedTestAccuracyChecker {
    void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int length = Math.min(original.length(), typed.length());
        int firstMismatch = -1;

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy;
        if (original.length() == 0 && typed.length() == 0) {
            accuracy = 100.0;
        } else {
            accuracy = (double) matched / Math.max(original.length(), typed.length()) * 100;
        }

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ",
                matched, Math.max(original.length(), typed.length()), accuracy);

        if (firstMismatch == -1 && original.length() == typed.length()) {
            System.out.println("No Mismatches");
        } else if (firstMismatch != -1) {
            System.out.println("First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch)
                    + "' vs '" + typed.charAt(firstMismatch) + "')");
        } else {
            System.out.println("First Mismatch at position " + (length + 1)
                    + " (string lengths differ)");
        }
    }

    public static void main(String[] args) {
        TypingSpeedTestAccuracyChecker checker = new TypingSpeedTestAccuracyChecker();
        checker.checkTypingAccuracy("hello world", "hello worlt");
    }
}
