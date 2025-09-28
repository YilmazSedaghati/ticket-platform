package org.mileston.ticket_platform.controller;

import java.util.Optional;

import org.mileston.ticket_platform.model.Nota;
import org.mileston.ticket_platform.model.Operatore;
import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.mileston.ticket_platform.service.NotaService;
import org.mileston.ticket_platform.service.OperatoreService;
import org.mileston.ticket_platform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/operatore")
public class OperatoreWebController {

    @Autowired
    private OperatoreService operatoreService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private NotaService notaService;

    @GetMapping
    public String dashboard(Authentication authentication, Model model) {
        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        model.addAttribute("operatore", operatore);
        model.addAttribute("tickets", ticketService.findByOperatoreId(operatore.getId()));

        return "operatore/dashboard";
    }

    @GetMapping("/tickets/{id}")
    public String ticketDetail(@PathVariable Long id, Authentication authentication, Model model) {
        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isEmpty() || !ticket.get().getOperatore().getId().equals(operatore.getId())) {

            return "redirect:/operatore?error=unauthorized";
        }

        model.addAttribute("ticket", ticket.get());
        model.addAttribute("newNote", new Nota());
        return "operatore/ticket-detail";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Operatore operatoreForm,
            Authentication authentication) {
        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        operatore.setNome(operatoreForm.getNome());
        operatore.setEmail(operatoreForm.getEmail());

        operatoreService.save(operatore);

        return "redirect:/operatore?success=profileUpdated";
    }

    @PostMapping("/disponibilita")
    public String updateDisponibilita(@RequestParam boolean disponibile, Authentication authentication) {
        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        if (!disponibile && !operatoreService.canSetNonDisponibile(operatore.getId())) {

            return "redirect:/operatore?error=hasActiveTickets";
        }

        operatore.setDisponibile(disponibile);
        operatoreService.save(operatore);

        return "redirect:/operatore?success=availabilityUpdated";
    }

    @PostMapping("/tickets/{id}/notes")
    public String addNote(@PathVariable Long id,
            @ModelAttribute("newNote") Nota newNote,
            Authentication authentication) {

        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        Ticket ticket = ticketService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trovato"));

        if (!ticket.getOperatore().getId().equals(operatore.getId())) {
            return "redirect:/operatore?error=unauthorized";
        }

        newNote.setAutore(operatore);
        newNote.setTicket(ticket);

        notaService.save(newNote);

        return "redirect:/operatore/tickets/" + id;
    }

    @PostMapping("/tickets/{id}/stato")
    public String updateStato(@PathVariable Long id,
            @RequestParam TicketStato stato,
            Authentication authentication) {

        Operatore operatore = operatoreService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Operatore non trovato"));
        ticketService.updateStato(id, stato, operatore.getId());

        return "redirect:/operatore/tickets/" + id;
    }

}
