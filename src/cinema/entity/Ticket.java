package cinema.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {
    @JsonUnwrapped
    private Token token;
    private Seat seat;
    private Seat returnedTicket;

    public Ticket() {

    }

    public Ticket(Token token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @JsonProperty("returned_ticket")
    public Seat getReturnTicket() {
        return returnedTicket;
    }

    public void setReturnTicket(Seat returnTicket) {
        this.returnedTicket = returnTicket;
    }
}
