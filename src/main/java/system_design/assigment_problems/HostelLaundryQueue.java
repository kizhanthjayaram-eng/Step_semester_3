package system_design.assigment_problems;

import java.util.ArrayList;
import java.util.List;

public class HostelLaundryQueue {
    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() { return 30; }
        public double getCharge() { return 20; }
        public String getName() { return "Quick"; }
    }

    static class NormalWash implements WashType {
        public int getDuration() { return 45; }
        public double getCharge() { return 30; }
        public String getName() { return "Normal"; }
    }

    static class HeavyWash implements WashType {
        public int getDuration() { return 60; }
        public double getCharge() { return 45; }
        public String getName() { return "Heavy"; }
    }

    static class Student {
        private final String name;

        Student(String name) { this.name = name; }
        String getName() { return name; }
    }

    static class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;

        WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        Student getStudent() { return student; }
        WashingMachine getMachine() { return machine; }
        WashType getWashType() { return washType; }
    }

    static class WashingMachine {
        private final String id;
        private WashCycle currentCycle;

        WashingMachine(String id) { this.id = id; }

        boolean isFree() { return currentCycle == null; }

        boolean startWash(Student student, WashType type) {
            if (!isFree()) {
                System.out.println("Machine " + id + " is currently busy.");
                return false;
            }
            currentCycle = new WashCycle(student, this, type);
            System.out.println(type.getName() + " wash started on " + id + " for "
                    + student.getName() + " (" + type.getDuration() + " min). Charge: ₹"
                    + String.format("%.2f", type.getCharge()) + ".");
            return true;
        }

        void completeCycle() {
            if (isFree()) {
                System.out.println(id + " has no active cycle.");
                return;
            }
            currentCycle = null;
            System.out.println(id + " cycle completed. " + id + " is now free.");
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeCycle();
        m1.startWash(neha, new NormalWash());
    }
}
