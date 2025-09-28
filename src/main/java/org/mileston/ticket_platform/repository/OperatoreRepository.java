package org.mileston.ticket_platform.repository;

import org.mileston.ticket_platform.model.Operatore;
import org.mileston.ticket_platform.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface OperatoreRepository extends JpaRepository< Operatore, Long> {

    Optional<Operatore> findByEmail(String email);
    List<Operatore> findByDisponibileTrueAndRuolo(Ruolo ruolo);
    Optional<Operatore> findByRuolo(Ruolo ruolo);
}
