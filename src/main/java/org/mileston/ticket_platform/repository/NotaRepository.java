package org.mileston.ticket_platform.repository;

import java.util.List;

import org.mileston.ticket_platform.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{

    List<Nota> findByTicketIdOrderByDataCreazione(Long ticketId);

}
