import model.Reservation;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static final String ACCEPT = "ACCEPT";
    private static final String DECLINE = "DECLINE";
    private static final String RESERVATION_TIME_LIMIT_EXCEEDS = "MAX RESERVATION TIME IS 365 DAYS";
    private static final int MAX_NUMBER_OF_ROOMS = 1000;
    private static final int MAX_NUMBER_OF_DAYS = 365;
    public List<Room> rooms;
    public int requestedHotelSize;

    // make sure that reservation cannot exceed 365 days
    // and day value cannot be negative value
    private boolean isReservationValid(Reservation reservation) {
        if (reservation.getStartDay() < 0 ||
            reservation.getStartDay() > MAX_NUMBER_OF_DAYS &&
            reservation.getEndDay() < 0 ||
            reservation.getEndDay() > MAX_NUMBER_OF_DAYS) {

            System.out.println(RESERVATION_TIME_LIMIT_EXCEEDS);

            return false;
        }
        return true;
    }

    public boolean isRoomAvailable(Reservation requestedReservation) {
        if (isReservationValid(requestedReservation)){  // check whether there is a problem with reservation date or not
                                                        // if there is no problem with it proceed to next steps inside if statement
            for (Room room: getRooms()) {
                if (room.getReservations().isEmpty()) {  // if empty which means no reservation
                    room.getReservations().add(requestedReservation); // occupy given reservation for the room
                    System.out.println(ACCEPT);                     // print "ACCEPT"
                    return true;
                }
                boolean isRoomAvailable = true;
                for (Reservation existsReservation: room.getReservations()) {
                    if(!checkAvailabilityOfReservation(requestedReservation,existsReservation)){ // compare starting and ending times of requested and existing reservation
                        isRoomAvailable = false;
                    }
                }
                if (isRoomAvailable) {  // if room is available for further reservations, reverse the dates
                    room.getReservations().add(requestedReservation);
                    System.out.println(ACCEPT);
                    return true;
                }
            }
            if (getRequestedHotelSize() < getRooms().size()+1) {
                System.out.println(DECLINE);
                return false;
            }
            bookRoom(requestedReservation);
            return true;
        }
        // if there is a problem regarding reservation time return false, do not proceed
        return false;
    }

    // return size of the hotel
    public int getRequestedHotelSize() {
        return requestedHotelSize;
    }

    // throw an exception if max size is given as bigger than 1000
    public void setRequestedHotelSize(int requestedHotelSize) throws Exception {
        if (requestedHotelSize > MAX_NUMBER_OF_ROOMS) {
            throw new Exception("MAX SIZE IS 1000");
        }
        this.requestedHotelSize = requestedHotelSize;
    }

    // check requested reservation times against existing one
    private boolean checkAvailabilityOfReservation(Reservation requestedReservation, Reservation existsReservation ) {
        return   (requestedReservation.getStartDay() > existsReservation.getStartDay() &&
                requestedReservation.getStartDay() > existsReservation.getEndDay() &&
                requestedReservation.getEndDay() > existsReservation.getStartDay() &&
                requestedReservation.getEndDay() > existsReservation.getEndDay()) ||
                (requestedReservation.getStartDay() < existsReservation.getStartDay() &&
                        requestedReservation.getStartDay() < existsReservation.getEndDay() &&
                        requestedReservation.getEndDay() < existsReservation.getStartDay() &&
                        requestedReservation.getEndDay() < existsReservation.getEndDay());
    }

    // return rooms if none, initialize list of rooms
    public List<Room> getRooms() {
        if (rooms==null){
            rooms = new ArrayList<>();
        }
        return rooms;
    }

    // reserve a room for given dates
    // increment room number based on size of existing rooms
    private void bookRoom(Reservation reservation) {
        Room newRoom = new Room(getRooms().size()+1,
                reservation.getStartDay(),
                reservation.getEndDay());
        getRooms().add(newRoom);
        System.out.println(ACCEPT);
    }
}
