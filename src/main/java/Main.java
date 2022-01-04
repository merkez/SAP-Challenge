import model.Reservation;

import java.util.Scanner;

public class Main {
    private static final String INPUT_FORMAT = "ENTER YOUR RESERVATION TIME IN THIS FORMAT: startDate, endDate; 1,3 ";
    private static final String HOTEL_SIZE = "ENTER YOUR HOTEL SIZE (MAX IS 1000)";
    private static Hotel hotel = new Hotel();


    public static void main(String[] args) throws Exception {
        Scanner scanner= new Scanner(System.in);
        System.out.println(HOTEL_SIZE);
        int hotelSize = Integer.parseInt(scanner.nextLine());
        hotel.setRequestedHotelSize(hotelSize);

        while (true) {
            int startTime = 0,endTime = 0;
            System.out.println(INPUT_FORMAT);
            String reservationDays = scanner.nextLine();
            String[] days = reservationDays.split(",");
            try {
                startTime = Integer.parseInt(days[0]);
                endTime = Integer.parseInt(days[1]);
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }
            Reservation reservation = new Reservation(startTime, endTime);
            hotel.isRoomAvailable(reservation);
        }
    }
}
