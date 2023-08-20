package com.broker_manager.web.stockInTicket;

import com.broker_manager.model.StockInTicket;
import com.broker_manager.model.User;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BrokerStockInTicketController.REST_URL)
public class BrokerStockInTicketController {
    static final String REST_URL = "/broker/tickets/{department}/{id}";

    @GetMapping("/{stockInTicketId}")
    public StockInTicket getStockInTicket(@PathVariable String department, @PathVariable Integer id, @PathVariable Integer stockInTicketId,
                                          @AuthenticationPrincipal AuthorizedUser authUser) {
        // Вывести stockInTicket и BankAccountTransaction данного Ticket
        return null;
    }

    @PutMapping(value = "/{stockInTicketId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StockInTicket closeStockInTicket(@PathVariable Integer id, @PathVariable Integer stockInTicketId, @Valid @RequestBody User updatedUser) {
        // Закрыть позицию, сменив статус данного StockInTicket на true
        // Сделать, чтобы по закрытию всех позиций StockInTicket автоматически закрывался Ticket и прописывалась дата закрытия в Ticket
        return null;
    }
}
