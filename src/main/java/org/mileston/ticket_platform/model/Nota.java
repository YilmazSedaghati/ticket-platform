package org.mileston.ticket_platform.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "note")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il contenuto della nota è obbligatorio")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String testo;

    @CreationTimestamp
    private LocalDateTime dataCreazione;

    public Nota() {
    }

    public Nota(Long id, String testo, LocalDateTime dataCreazione) {
        this.id = id;
        this.testo = testo;
        this.dataCreazione = dataCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Operatore autore;

    public Operatore getAutore() {
        return autore;
    }

    public void setAutore(Operatore autore) {
        this.autore = autore;
    }

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
