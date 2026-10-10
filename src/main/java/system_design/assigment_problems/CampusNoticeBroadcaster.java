package system_design.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CampusNoticeBroadcaster {
    interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[Email → " + student.getName() + "] " + notice.getTitle());
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[SMS → " + student.getName() + "] " + notice.getTitle());
        }
    }

    static class AppChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[App → " + student.getName() + "] " + notice.getTitle());
        }
    }

    static class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> channels = new ArrayList<>();

        Student(String name, String department, NotificationChannel... channels) {
            this.name = name;
            this.department = department;
            this.channels.addAll(Arrays.asList(channels));
        }

        String getName() { return name; }
        String getDepartment() { return department; }
        List<NotificationChannel> getChannels() { return channels; }
    }

    static class Notice {
        private final String title;
        private final Set<String> departments;

        Notice(String title, Set<String> departments) {
            this.title = title == null ? "" : title.trim();
            this.departments = new LinkedHashSet<>(departments);
        }

        String getTitle() { return title; }
        Set<String> getDepartments() { return departments; }

        boolean isValid() {
            return !title.isEmpty() && !departments.isEmpty();
        }

        String departmentList() {
            return String.join(", ", departments);
        }
    }

    static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        void addStudent(Student student) {
            students.add(student);
        }

        void postNotice(Notice notice) {
            if (!notice.isValid()) {
                if (notice.getTitle().isEmpty()) {
                    System.out.println("Cannot post notice: A title is required.");
                } else {
                    System.out.println("Cannot post notice: At least one target department is required.");
                }
                return;
            }

            System.out.println("Notice '" + notice.getTitle() + "' posted to "
                    + notice.departmentList() + ".");
            for (Student student : students) {
                if (notice.getDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        board.addStudent(new Student("Asha", "CSE", new EmailChannel(), new AppChannel()));
        board.addStudent(new Student("Ravi", "ECE", new SmsChannel()));

        board.postNotice(new Notice("Lab Closed Tomorrow", new LinkedHashSet<>(Arrays.asList("CSE"))));
        board.postNotice(new Notice("Fee Deadline Extended",
                new LinkedHashSet<>(Arrays.asList("CSE", "ECE"))));
        board.postNotice(new Notice("Sports Day", new LinkedHashSet<>()));
    }
}
