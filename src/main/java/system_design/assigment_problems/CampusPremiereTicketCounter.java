package system_design.assigment_problems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CampusPremiereTicketCounter {
    interface Seat {
        String getId();
        double getPrice();
    }

    static class RegularSeat implements Seat {
        private final String id;
        RegularSeat(String id) { this.id = id; }
        public String getId() { return id; }
        public double getPrice() { return 150; }
    }

    static class PremiumSeat implements Seat {
        private final String id;
        PremiumSeat(String id) { this.id = id; }
        public String getId() { return id; }
        public double getPrice() { return 250; }
    }

    static class ReclinerSeat implements Seat {
        private final String id;
        ReclinerSeat(String id) { this.id = id; }
        public String getId() { return id; }
        public double getPrice() { return 400; }
    }

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
        String getName() { return name; }
    }

    static class Show {
        private final String name;
        private final LocalDateTime startTime;
        private final Map<String, Seat> bookedSeats = new LinkedHashMap<>();

        Show(String name, LocalDateTime startTime) {
            this.name = name;
            this.startTime = startTime;
        }

        boolean isStarted() { return !LocalDateTime.now().isBefore(startTime); }
        boolean isAvailable(Seat seat) { return !bookedSeats.containsKey(seat.getId()); }

        Booking book(Customer customer, List<Seat> seats) {
            if (seats.isEmpty() || seats.size() > 6) {
                System.out.println("A booking must contain between 1 and 6 seats.");
                return null;
            }
            for (Seat seat : seats) {
                if (!isAvailable(seat)) {
                    System.out.println("Seat " + seat.getId() + " is already booked for this show.");
                    return null;
                }
            }
            for (Seat seat : seats) {
                bookedSeats.put(seat.getId(), seat);
            }
            Booking booking = new Booking(customer, this, seats);
            System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                    customer.name, booking.getSeatIds(), booking.getTotal());
            return booking;
        }

        void releaseSeats(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeats.remove(seat.getId());
            }
        }

        String getName() { return name; }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled;

        Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
        }

        double getTotal() {
            double total = 0;
            for (Seat seat : seats) total += seat.getPrice();
            return total;
        }

        String getSeatIds() {
            List<String> ids = new ArrayList<>();
            for (Seat seat : seats) ids.add(seat.getId());
            return String.join(", ", ids);
        }

        void cancel() {
            if (cancelled) {
                System.out.println("Booking has already been cancelled.");
            } else if (show.isStarted()) {
                System.out.println("Cannot cancel: the show has already started.");
            } else {
                show.releaseSeats(seats);
                cancelled = true;
                System.out.println(customer.name + "'s booking cancelled. Seats " + getSeatIds() + " released.");
            }
        }
    }

    public static void main(String[] args) {
        Show show = new Show("7 PM show", LocalDateTime.now().plusHours(2));
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        List<Seat> ashaSeats = new ArrayList<>();
        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));
        Booking ashaBooking = show.book(asha, ashaSeats);

        List<Seat> raviBlocked = new ArrayList<>();
        raviBlocked.add(new RegularSeat("A2"));
        show.book(ravi, raviBlocked);

        List<Seat> raviSeats = new ArrayList<>();
        raviSeats.add(new ReclinerSeat("R1"));
        show.book(ravi, raviSeats);

        if (ashaBooking != null) ashaBooking.cancel();

        List<Seat> nehaSeats = new ArrayList<>();
        nehaSeats.add(new RegularSeat("A2"));
        show.book(neha, nehaSeats);
    }
}
