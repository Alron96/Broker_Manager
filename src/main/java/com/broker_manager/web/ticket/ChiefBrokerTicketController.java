package com.broker_manager.web.ticket;

import com.broker_manager.model.StockInTicket;
import com.broker_manager.model.Ticket;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChiefBrokerTicketController.REST_URL)
public class ChiefBrokerTicketController {
    static final String REST_URL = "/chief_broker/tickets/{department}";

    @GetMapping
    public List<Ticket> getAllTicketByDepartment(@PathVariable String department) {
        // Вывести список Ticket без stockInTicket данного Department
        return null;
    }

    @GetMapping("/{id}")
    public Ticket getTicketByDepartment(@PathVariable String department, @PathVariable Integer id, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Вывести Ticket с stockInTicket и BankAccountTransaction
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@PathVariable String department, @Valid @RequestBody Ticket ticket,
                               @Valid @RequestBody List<StockInTicket> stocksInTicket, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать Ticket с stockInTicket
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Integer id, @RequestParam Integer chiefId) {
        // Удалять Ticket с stockInTicket, но только если статус ticket false и статус каждого stockInTicket внутри этого Ticket false
    }
}
