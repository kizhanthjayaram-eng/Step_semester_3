package system_design.assigment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentSubmissionPortal {
    interface AssignmentType {
        String getTitle();
        LocalDate getDueDate();
        int getMaxMarks();
        double getDailyPenaltyRate();
    }

    static class CodingAssignment implements AssignmentType {
        private final String title;
        private final LocalDate dueDate;
        private final int maxMarks;

        CodingAssignment(String title, LocalDate dueDate, int maxMarks) {
            this.title = title;
            this.dueDate = dueDate;
            this.maxMarks = maxMarks;
        }

        public String getTitle() { return title; }
        public LocalDate getDueDate() { return dueDate; }
        public int getMaxMarks() { return maxMarks; }
        public double getDailyPenaltyRate() { return 0.10; }
    }

    static class WrittenAssignment implements AssignmentType {
        private final String title;
        private final LocalDate dueDate;
        private final int maxMarks;

        WrittenAssignment(String title, LocalDate dueDate, int maxMarks) {
            this.title = title;
            this.dueDate = dueDate;
            this.maxMarks = maxMarks;
        }

        public String getTitle() { return title; }
        public LocalDate getDueDate() { return dueDate; }
        public int getMaxMarks() { return maxMarks; }
        public double getDailyPenaltyRate() { return 0.20; }
    }

    static class Student {
        private final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
    }

    enum Status { NOT_SUBMITTED, SUBMITTED, GRADED }

    static class Submission {
        private final Student student;
        private final AssignmentType assignment;
        private LocalDate submissionDate;
        private Status status = Status.NOT_SUBMITTED;
        private double finalMarks;

        Submission(Student student, AssignmentType assignment) {
            this.student = student;
            this.assignment = assignment;
        }

        void submit(LocalDate date) {
            if (status == Status.GRADED) {
                System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
                return;
            }
            if (status == Status.SUBMITTED) {
                System.out.println("Cannot submit again before grading: '" + assignment.getTitle() + "'.");
                return;
            }
            submissionDate = date;
            status = Status.SUBMITTED;
            long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), date));
            if (lateDays == 0) {
                System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                        + "' received (on time). Status: Submitted.");
            } else {
                System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                        + "' received (" + lateDays + " days late). Status: Submitted.");
            }
        }

        void grade(double awardedMarks) {
            if (status != Status.SUBMITTED) {
                System.out.println("Cannot grade: submission has not been submitted.");
                return;
            }
            if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
                System.out.println("Invalid marks. Enter a value from 0 to " + assignment.getMaxMarks() + ".");
                return;
            }
            long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate));
            double penalty = Math.min(1.0, lateDays * assignment.getDailyPenaltyRate());
            finalMarks = awardedMarks * (1.0 - penalty);
            status = Status.GRADED;
            System.out.printf("%s graded: %.0f/%d", student.getName(), finalMarks, assignment.getMaxMarks());
            if (lateDays > 0) {
                System.out.printf(" after %.0f%% late penalty", penalty * 100);
            }
            System.out.println(". Status: Graded.");
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        AssignmentType coding = new CodingAssignment("Linked List Lab", LocalDate.of(2026, 3, 10), 50);
        AssignmentType written = new WrittenAssignment("Design Essay", LocalDate.of(2026, 3, 12), 50);

        Submission s1 = new Submission(asha, coding);
        Submission s2 = new Submission(ravi, written);
        s1.submit(LocalDate.of(2026, 3, 10));
        s2.submit(LocalDate.of(2026, 3, 14));
        s1.grade(45);
        s2.grade(40);
        s1.submit(LocalDate.of(2026, 3, 15));
    }
}
