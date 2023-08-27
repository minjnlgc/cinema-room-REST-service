package cinema.service;

import cinema.entity.*;
import cinema.rest.exception.SeatNotFoundException;
import cinema.rest.exception.TicketNotFoundException;
import cinema.rest.exception.WrongPasswordException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private static final int TOTAL_ROWS = 9;
    private static final int TOTAL_COLUMNS = 9;
    private static final int START_ROWS_COLUMNS = 1;
    private static final int BACK_PRICE = 8;
    private static final int FRONT_PRICE = 10;
    private static final int ROW_PRICE_BOUNDARY = 4;
    private static final String SUPER_SECRET = "super_secret";
    private Stats stats;

    private Room room;
    private List<Ticket> purchasedTicket;

    public RoomService() {

        this.stats = new Stats(0, TOTAL_COLUMNS * TOTAL_ROWS, 0);

        List<Seat> availableSeats = new ArrayList<>();
        this.purchasedTicket = new ArrayList<>();
        int price;

        for (int r = START_ROWS_COLUMNS; r <= TOTAL_ROWS; r++) {
            for (int c = START_ROWS_COLUMNS; c <= TOTAL_COLUMNS; c++) {
                price = r <= ROW_PRICE_BOUNDARY ? FRONT_PRICE : BACK_PRICE;
                availableSeats.add(new Seat(r, c, price));
            }
        }

        this.room = new Room(TOTAL_ROWS, TOTAL_COLUMNS, availableSeats);
    }

    public Room getRoom() {
        return this.room;
    }

    public Ticket purchaseTicket(Seat seatToPurchase) {

        if (!isSeatValid(seatToPurchase)) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        Seat purchasedSeat = null;
        Ticket ticket = null;

        for (Seat s : this.room.getAvailableSeats()) {
            if (s.getColumn() == seatToPurchase.getColumn()
                    && s.getRow() == seatToPurchase.getRow()) {

                if (!s.isAvailability()) {
                    throw new SeatNotFoundException("The ticket has been already purchased!");
                } else {
                    purchasedSeat = s;
                    s.setAvailability(false);
                }
            }
        }

        ticket = new Ticket(new Token(), purchasedSeat);
        this.purchasedTicket.add(ticket);

        updateStats(purchasedSeat.getPrice());

        return ticket;
    }

    public boolean isSeatValid(Seat seat) {
        return (seat.getRow() >= START_ROWS_COLUMNS && seat.getRow() <= TOTAL_ROWS && seat.getColumn() >= START_ROWS_COLUMNS && seat.getColumn() <= TOTAL_COLUMNS);
    }

    public Ticket returnTicket(Token token) {

        Seat returnSeat = null;
        Ticket returnTicket = null;

        for (Ticket t : this.purchasedTicket) {
            if (t.getToken().getToken().equals(token.getToken())) {
                t.getSeat().setAvailability(true);
                returnSeat = t.getSeat();
                returnTicket = t;
            }
        }

        if (returnSeat == null) {
            throw new TicketNotFoundException("Wrong token!");
        }

        this.purchasedTicket.remove(returnTicket);
        this.stats.setNumberOfPurchasedTickets(this.purchasedTicket.size());

        returnTicket.setReturnTicket(returnSeat);
        returnTicket.setToken(null);
        returnTicket.setSeat(null);

        updateStats(-returnSeat.getPrice());

        return returnTicket;
    }


    public Stats getStats(String password) {
        if (password == null || !password.equals(SUPER_SECRET)) {
            throw new WrongPasswordException("The password is wrong!");
        }
        return this.stats;
    }

    private void updateStats(int price) {
        this.stats.setNumberOfPurchasedTickets(this.purchasedTicket.size());

        int totalNumOfSeats = TOTAL_COLUMNS * TOTAL_ROWS;
        this.stats.setNumberOfAvailableSeats(totalNumOfSeats - this.purchasedTicket.size());

        this.stats.setCurrentIncome(this.stats.getCurrentIncome() + price);
    }

}