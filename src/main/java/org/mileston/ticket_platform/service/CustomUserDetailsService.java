package org.mileston.ticket_platform.service;

import org.mileston.ticket_platform.model.Operatore;
import org.mileston.ticket_platform.repository.OperatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Override 
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        
        Operatore operatore = operatoreRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Utente non trovato " + email));

        return User.builder().username(operatore.getEmail()).password(operatore.getPassword()).roles(operatore.getRuolo().name()).build();
        
    }

}
