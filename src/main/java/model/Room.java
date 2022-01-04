package model;


import java.util.ArrayList;
import java.util.List;

public class Room {

    private int no;
    private List<Reservation> reservations;

    public Room(int no, int startDay, int endDay) {
        Reservation reservation = new Reservation(startDay, endDay);
        getReservations().add(reservation);
        this.no = no ;
    }
    // return reservations for a room
    // if none initialize new Reservation List
    public List<Reservation> getReservations(){
        if (reservations == null)
            reservations = new ArrayList<>();
        return reservations;
    }
}
