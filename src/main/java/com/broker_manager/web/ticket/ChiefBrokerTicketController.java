package com.broker_manager.web.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.service.ticket.ChiefBrokerTicketServiceImpl;
import com.broker_manager.to.TicketTo;
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
    private final ChiefBrokerTicketServiceImpl chiefBrokerTicketService;

    public ChiefBrokerTicketController(ChiefBrokerTicketServiceImpl chiefBrokerTicketService) {
        this.chiefBrokerTicketService = chiefBrokerTicketService;
    }

    @GetMapping
    public List<Ticket> getAllTicketByDepartment(@PathVariable String department, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Вывести список Ticket без stockInTicket данного Department
//        return chiefBrokerTicketService.getAllTicketByDepartment(department, authUser.getUser());
        return null;
    }

    @GetMapping("/{id}")
    public Ticket getTicketByDepartment(@PathVariable String department, @PathVariable Integer id, @AuthenticationPrincipal AuthorizedUser authUser) {
//         Вывести Ticket с stockInTicket и BankAccountTransaction
//        return chiefBrokerTicketService.getTicketByDepartment(department,id,authUser.getUser());
        return null;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@PathVariable String department, @Valid @RequestBody TicketTo ticketTo,
                               @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать Ticket с stockInTicket
        // В сервисе сделать преобразование из TicketTo в Ticket и сохранить в БД
        // Недостающие данные при конвертации из TicketTO в Ticket заполнить значениями по умолчанию
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Integer id, @RequestParam Integer chiefId) {
        // Удалять Ticket с stockInTicket, но только если статус ticket false и статус каждого stockInTicket внутри этого Ticket false
    }
}
