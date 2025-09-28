package org.mileston.ticket_platform.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Il titolo è obbligatorio")
    @Column(nullable = false)
    private String titolo;

    @NotBlank(message="La descrizione è obbligatoria")
    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @CreationTimestamp
    private LocalDateTime dataCreazione;

    @UpdateTimestamp
    private LocalDateTime dataAggiornamento;

    public Ticket() {
    }

    public Ticket(Long id, String titolo, String descrizione, LocalDateTime dataCreazione, LocalDateTime dataAggiornamento) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
        this.dataAggiornamento = dataAggiornamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public LocalDateTime getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    @Enumerated(EnumType.STRING)
    private TicketStato stato = TicketStato.DA_FARE;

    public TicketStato getStato() {
        return stato;
    }

    public void setStato(TicketStato stato) {
        this.stato = stato;
    }

    // enum TicketStato {
    //     DA_FARE, IN_CORSO, COMPLETATO
    // }

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message="La categoria è obbligatoria")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @ManyToOne
    @JoinColumn(name = "operatore_id", nullable = false)
    @NotNull(message="L'operatore è obbligatorio")
    private Operatore operatore;

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Nota> note = new ArrayList<>();

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }

    public void addNota(Nota nota) {
        note.add(nota);
        nota.setTicket(this);
    }

    public void removeNota(Nota nota) {
        note.remove(nota);
        nota.setTicket(null);
    }

}
