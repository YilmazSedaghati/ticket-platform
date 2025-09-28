package org.mileston.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.mileston.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {

        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket save(Ticket ticket) {

        if (!ticket.getOperatore().isDisponibile()) {

            throw new IllegalStateException("L'operatore non è disponibile");
        }

        return ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {

        ticketRepository.deleteById(id);

    }

    public List<Ticket> searchByTitolo(String titolo) {

        return ticketRepository.findByTitoloContainingIgnoreCase(titolo);

    }

    public List<Ticket> findByCategoria(Long id) {

        return ticketRepository.findByCategoriaId(id);

    }

    public List<Ticket> findByStato(TicketStato stato) {

        return ticketRepository.findByStato(stato);

    }

    public List<Ticket> findByOperatoreId(Long operatoreId) {

        return ticketRepository.findByOperatoreId(operatoreId);

    }

    public Ticket updateStato(Long ticketId, TicketStato nuovoStato, Long operatoreId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket non trovato"));

        if (!ticket.getOperatore().getId().equals(operatoreId)) {
            throw new SecurityException("Non autorizzato a modificare questo ticket");

        }

        ticket.setStato(nuovoStato);
        return ticketRepository.save(ticket);
    }

}
