package array_string.assigment_problems;

public class MovieReviewWordLengthProfiler {
    void classifyWordLengths(String review) {
        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        String[] words = review.trim().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("[^a-zA-Z]", "");

            if (word.length() >= 1 && word.length() <= 4) {
                shortWords++;
            } else if (word.length() >= 5 && word.length() <= 8) {
                mediumWords++;
            } else if (word.length() >= 9) {
                longWords++;
            }
        }

        System.out.println("Short: " + shortWords
                + " | Medium: " + mediumWords
                + " | Long: " + longWords);
    }

    public static void main(String[] args) {
        MovieReviewWordLengthProfiler profiler = new MovieReviewWordLengthProfiler();
        profiler.classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
