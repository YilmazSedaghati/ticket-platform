package org.mileston.ticket_platform.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mileston.ticket_platform.model.Operatore;
import org.mileston.ticket_platform.model.Ruolo;
import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.mileston.ticket_platform.repository.OperatoreRepository;
import org.mileston.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatoreService {

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public List<Operatore> findAll() {

        return operatoreRepository.findAll();

    }

    public Optional<Operatore> findById(Long id) {

        return operatoreRepository.findById(id);

    }

    public Operatore save(Operatore operatore) {

        return operatoreRepository.save(operatore);

    }

     public Optional<Operatore> findByEmail(String email) {

        return operatoreRepository.findByEmail(email);

    }

    public List<Operatore> findOperatoriDisponibili() {

        return operatoreRepository.findByDisponibileTrueAndRuolo(Ruolo.OPERATORE);

    }

    public boolean canSetNonDisponibile(Long operatoreId){

        List<TicketStato> statiAttivi = Arrays.asList(TicketStato.DA_FARE, TicketStato.IN_CORSO);

        List<Ticket> ticketAttivi = ticketRepository.findByOperatoreIdAndStatoIn(operatoreId, statiAttivi);
        
        return ticketAttivi.isEmpty();
    }

    public void updateDisponibilita(Long operatoreId, boolean disponibile){
        Operatore operatore = operatoreRepository.findById(operatoreId).orElseThrow(() -> new RuntimeException("Operatore non trovato"));
        operatore.setDisponibile(disponibile);
        operatoreRepository.save(operatore);
    }

}
