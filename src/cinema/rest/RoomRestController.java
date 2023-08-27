package cinema.rest;

import cinema.entity.*;
import cinema.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomRestController {

    private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/seats")
    public Room getSeats() {
        return roomService.getRoom();
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Seat seat) {
        return roomService.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public Ticket returnTicket(@RequestBody Token token) {
        return roomService.returnTicket(token);
    }

    @GetMapping("/stats")
    public Stats getStats(@RequestParam(required = false) String password) {
        return roomService.getStats(password);
    }
}