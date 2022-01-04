import model.Reservation;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {
    Hotel hotel;
    ArrayList<Reservation> reservations;
    List<List<Integer>> bookingDays;
    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        reservations = new ArrayList<>();
        bookingDays = new ArrayList<>();
    }


    @org.junit.jupiter.api.Test
    void getSetRequestedHotelSize() throws Exception {
        hotel.setRequestedHotelSize(12);
        assert hotel.getRequestedHotelSize() == 12;
    }


    @Test
    void testIsRoomAvailable() throws Exception {
        hotel.setRequestedHotelSize(1);
        // 1a/1b: Requests outside our planning period are declined (Size=1)
        bookingDays.add(new ArrayList<>(Arrays.asList(-4, 2)));
        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }

        for (Reservation reservation : reservations) {
            assertFalse(hotel.isRoomAvailable(reservation));
        }

        hotel.setRequestedHotelSize(1);
        bookingDays.add(new ArrayList<>(Arrays.asList(200, 400)));
        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }

        for (Reservation reservation : reservations) {
            assertFalse(hotel.isRoomAvailable(reservation));
        }

        //  Requests are accepted (Size=3)

        setUp();
        hotel.setRequestedHotelSize(3);
        bookingDays.add(new ArrayList<>(Arrays.asList(0, 5)));
        bookingDays.add(new ArrayList<>(Arrays.asList(7, 13)));
        bookingDays.add(new ArrayList<>(Arrays.asList(3, 9)));
        bookingDays.add(new ArrayList<>(Arrays.asList(5, 7)));
        bookingDays.add(new ArrayList<>(Arrays.asList(6, 6)));
        bookingDays.add(new ArrayList<>(Arrays.asList(0, 4)));

        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }

        for (Reservation reservation : reservations) {
            assertTrue(hotel.isRoomAvailable(reservation));
        }

        setUp();
        // Requests are declined (Size=3)
        hotel.setRequestedHotelSize(3);
        bookingDays.add(new ArrayList<>(Arrays.asList(1, 3)));
        bookingDays.add(new ArrayList<>(Arrays.asList(2, 5)));
        bookingDays.add(new ArrayList<>(Arrays.asList(1, 9)));
        bookingDays.add(new ArrayList<>(Arrays.asList(0, 15)));
        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }
        for (Reservation reservation : reservations) {
            if (reservation.getStartDay() == 0 && reservation.getEndDay() == 15)
                assertFalse(hotel.isRoomAvailable(reservation));
            else
                assertTrue(hotel.isRoomAvailable(reservation));
        }
        //Requests can be accepted after a decline (Size=3)
        setUp();
        hotel.setRequestedHotelSize(3);
        bookingDays.add(new ArrayList<>(Arrays.asList(1, 3)));
        bookingDays.add(new ArrayList<>(Arrays.asList(0, 15)));
        bookingDays.add(new ArrayList<>(Arrays.asList(1, 9)));
        bookingDays.add(new ArrayList<>(Arrays.asList(2, 5)));
        bookingDays.add(new ArrayList<>(Arrays.asList(4, 9)));

        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }
        for (Reservation reservation : reservations) {
            if (reservation.getStartDay() == 2 && reservation.getEndDay() == 5)
                assertFalse(hotel.isRoomAvailable(reservation));
            else
                assertTrue(hotel.isRoomAvailable(reservation));
        }

        // Complex Requests (Size=2)
        setUp();
        hotel.setRequestedHotelSize(2);
        bookingDays.add(new ArrayList<>(Arrays.asList(1, 3)));
        bookingDays.add(new ArrayList<>(Arrays.asList(0, 4)));
        bookingDays.add(new ArrayList<>(Arrays.asList(2, 3)));
        bookingDays.add(new ArrayList<>(Arrays.asList(5, 5)));
        bookingDays.add(new ArrayList<>(Arrays.asList(4, 10)));
        bookingDays.add(new ArrayList<>(Arrays.asList(10, 10)));
        bookingDays.add(new ArrayList<>(Arrays.asList(6, 7)));
        bookingDays.add(new ArrayList<>(Arrays.asList(8, 10)));
        bookingDays.add(new ArrayList<>(Arrays.asList(8, 9)));

        for (List<Integer> days : bookingDays) {
            reservations.add(new Reservation(days.get(0), days.get(1)));
        }
        for (Reservation reservation : reservations) {
            if (reservation.getStartDay() == 2 && reservation.getEndDay() == 3)
                assertFalse(hotel.isRoomAvailable(reservation));
            else if (reservation.getStartDay() ==4 && reservation.getEndDay() ==10)
                assertFalse(hotel.isRoomAvailable(reservation));
            else
                assertTrue(hotel.isRoomAvailable(reservation));
        }



    }

}