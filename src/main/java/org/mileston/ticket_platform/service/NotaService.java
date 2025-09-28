package org.mileston.ticket_platform.service;

import java.util.List;

import org.mileston.ticket_platform.model.Nota;
import org.mileston.ticket_platform.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota save(Nota nota){

        return notaRepository.save(nota);

    }

    public List<Nota> findByTicketId(Long ticketId){
        
        return notaRepository.findByTicketIdOrderByDataCreazione(ticketId);

    }

}
