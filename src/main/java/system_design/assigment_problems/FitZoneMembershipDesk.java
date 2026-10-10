package system_design.assigment_problems;

public class FitZoneMembershipDesk {
    interface MembershipPlan {
        int getMonths();
        double getDiscount();
        String getName();
        default double calculateFee() {
            return 1000.0 * getMonths() * (1.0 - getDiscount());
        }
    }

    static class MonthlyPlan implements MembershipPlan {
        public int getMonths() { return 1; }
        public double getDiscount() { return 0; }
        public String getName() { return "Monthly"; }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public int getMonths() { return 3; }
        public double getDiscount() { return 0.10; }
        public String getName() { return "Quarterly"; }
    }

    static class AnnualPlan implements MembershipPlan {
        public int getMonths() { return 12; }
        public double getDiscount() { return 0.25; }
        public String getName() { return "Annual"; }
    }

    enum Status { ACTIVE, FROZEN, EXPIRED }

    static class Member {
        private final String name;
        private Membership membership;

        Member(String name) { this.name = name; }

        void buyMembership(MembershipPlan plan) {
            membership = new Membership(this, plan);
            System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                    plan.getName(), name, plan.calculateFee());
        }

        void checkIn() {
            if (membership != null && membership.checkIn()) {
                System.out.println(name + " checked in successfully.");
            } else {
                Status status = membership == null ? null : membership.getStatus();
                System.out.println("Check-in denied: " + name + "'s membership is "
                        + (status == null ? "not available" : status) + ".");
            }
        }

        void freeze() {
            if (membership != null) membership.freeze();
        }

        void unfreeze() {
            if (membership != null) membership.unfreeze();
        }

        void expire() {
            if (membership != null) membership.expire();
        }
    }

    static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private Status status = Status.ACTIVE;

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
        }

        Status getStatus() { return status; }

        boolean checkIn() { return status == Status.ACTIVE; }

        void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
            } else if (status == Status.FROZEN) {
                System.out.println(member.name + "'s membership is already Frozen.");
            } else {
                status = Status.FROZEN;
                System.out.println(member.name + "'s membership frozen. Status: Frozen.");
            }
        }

        void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
            } else if (status == Status.ACTIVE) {
                System.out.println(member.name + "'s membership is already Active.");
            } else {
                status = Status.ACTIVE;
                System.out.println(member.name + "'s membership unfrozen. Status: Active.");
            }
        }

        void expire() {
            status = Status.EXPIRED;
            System.out.println(member.name + "'s membership expired. Status: Expired.");
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        asha.buyMembership(new QuarterlyPlan());
        ravi.buyMembership(new MonthlyPlan());
        asha.checkIn();
        asha.freeze();
        asha.checkIn();
        ravi.expire();
        ravi.freeze();
    }
}
