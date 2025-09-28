package org.mileston.ticket_platform.controller.api;

import java.util.List;

import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.mileston.ticket_platform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TicketApiController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(@RequestParam(required=false) Long categoriaId,@RequestParam(required=false) TicketStato stato){

        List<Ticket> tickets;
        if (categoriaId != null) {
            tickets = ticketService.findByCategoria(categoriaId);
        } else if (stato != null) {
            tickets = ticketService.findByStato(stato);
        } else {
            tickets = ticketService.findAll();
        }

        return ResponseEntity.ok(tickets);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        
        return ticketService.findById(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }
    
}
