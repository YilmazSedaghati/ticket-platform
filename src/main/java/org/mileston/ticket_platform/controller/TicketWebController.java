package org.mileston.ticket_platform.controller;

import java.util.List;

import org.mileston.ticket_platform.model.Nota;
import org.mileston.ticket_platform.model.Operatore;
import org.mileston.ticket_platform.model.Ticket;
import org.mileston.ticket_platform.model.TicketStato;
import org.mileston.ticket_platform.service.CategoriaService;
import org.mileston.ticket_platform.service.NotaService;
import org.mileston.ticket_platform.service.OperatoreService;
import org.mileston.ticket_platform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/admin/tickets")
@PreAuthorize("hasRole('ADMIN')")
public class TicketWebController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private OperatoreService operatoreService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private NotaService notaService;

    @GetMapping
    public String listTickets(@RequestParam(required = false) String search, Model model) {

        List<Ticket> tickets;
        if (search != null && !search.trim().isEmpty()) {
            tickets = ticketService.searchByTitolo(search);
        } else {
            tickets = ticketService.findAll();
        }

        model.addAttribute("tickets", tickets);
        model.addAttribute("search", tickets);
        return "admin/tickets/list";
    }

    @GetMapping("/new")
    public String newTicketForm(Model model) {

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("categorie", categoriaService.findAll());
        model.addAttribute("operatori", operatoreService.findOperatoriDisponibili());

        return "admin/tickets/form";
    }

    @GetMapping("/{id}")
    public String ticketDetail(@PathVariable Long id, Model model) {

        Ticket ticket = ticketService.findById(id).orElseThrow(() -> new RuntimeException("Ticket non trovato"));

        model.addAttribute("ticket", ticket);
        model.addAttribute("newNote", new Nota());

        return "admin/tickets/detail";
    }

    @PostMapping("/{id}/notes")
    public String addNote(@PathVariable Long id, @ModelAttribute Nota newNote, Authentication authentication) {

        Ticket ticket = ticketService.findById(id).orElseThrow(() -> new RuntimeException("Ticket non trovato"));

        Operatore autore = operatoreService.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Operatore non trovato"));

        newNote.setAutore(autore);
        newNote.setTicket(ticket);
        notaService.save(newNote);

        return "redirect:/admin/tickets/" + id;
    }

    @PostMapping("/{id}/stato")
    public String updateStato(@PathVariable Long id, @RequestParam TicketStato stato) {

        Ticket ticket = ticketService.findById(id).orElseThrow(() -> new RuntimeException("Ticket non trovato"));

        ticket.setStato(stato);
        ticketService.save(ticket);

        return "redirect:/admin/tickets/" + id;

    }

    @GetMapping("/{id}/edit")
    public String editTicketForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
        
        model.addAttribute("ticket", ticket);
        model.addAttribute("categorie", categoriaService.findAll());
        model.addAttribute("operatori", operatoreService.findOperatoriDisponibili());
        return "admin/tickets/form";
    }

    @PostMapping("/{id}/edit")
    public String updateTicket(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        Ticket existingTicket = ticketService.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
        
        existingTicket.setTitolo(ticket.getTitolo());
        existingTicket.setDescrizione(ticket.getDescrizione());
        existingTicket.setCategoria(ticket.getCategoria());
        existingTicket.setOperatore(ticket.getOperatore());
        
        ticketService.save(existingTicket);
        return "redirect:/admin/tickets/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
        return "redirect:/admin/tickets";
    }

}
