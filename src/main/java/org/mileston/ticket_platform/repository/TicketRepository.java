package org.mileston.ticket_platform.repository;

import java.util.List;

import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

    List<Ticket> findByTitoloContainingIgnoreCase(String titolo);
    List<Ticket> findByCategoriaId(Long categoriaId);
    List<Ticket> findByStato(TicketStato stato);
    List<Ticket> findByOperatoreId(Long operatoreId);
    List<Ticket> findByOperatoreIdAndStatoIn(Long operatoreId, List<TicketStato> stati);
}
