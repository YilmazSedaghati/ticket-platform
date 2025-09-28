package org.mileston.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.mileston.ticket_platform.model.Nota;
import org.mileston.ticket_platform.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> findAll(){

        return notaRepository.findAll();
        
    }

    public Optional<Nota> findById(Long id){

        return notaRepository.findById(id);

    }

    public Nota save(Nota nota){

        return notaRepository.save(nota);

    }

    public void deleteById(Long id){

        notaRepository.deleteById(id);
        
    }

    public List<Nota> findByTicketId(Long ticketId){
        
        return notaRepository.findByTicketIdOrderByDataCreazione(ticketId);

    }

}
