package model;

public class Reservation {
    private int startDay, endDay;

    public Reservation( int startDay, int endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }
    // no need to have setters, the constructor is enough

    // this is just written for log purposes on test side
    @Override
    public String toString() {
        return "Reservation{" +
                "startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}
