package org.mileston.ticket_platform.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "operatori")
public class Operatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @Email(message="Email non valida")
    @NotBlank(message="L'email è obbligatoria")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message="La password è obbligatoria")
    private String password;

    private boolean disponibile = true;

    public Operatore(Long id, @NotBlank(message = "Il nome è obbligatorio") String nome,
            @Email(message = "Email non valida") @NotBlank(message = "Il nome è obbligatorio") String email,
            @NotBlank(message = "La password è obbligatoria") String password, boolean disponibile) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.disponibile = disponibile;
    }

    public Operatore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    @OneToMany(mappedBy = "operatore")
    private List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setOperatore(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setOperatore(null);
    }
    
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo = Ruolo.OPERATORE;

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

}
