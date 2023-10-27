package demo.e_commerce.controllers;

import demo.e_commerce.utilities.JwtUtil;
import demo.e_commerce.entities.Utente;
import demo.e_commerce.exceptions.MailAlreadyUsedException;
import demo.e_commerce.services.UtenteService;
import demo.e_commerce.utilities.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController 
{
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registrazione")
    @PreAuthorize("hasAnyAuthority('customer')")
    @SuppressWarnings(value = { "all" })
    public ResponseEntity create(@RequestBody @Valid Utente utente) 
    {
        try 
        {
            Utente added = utenteService.registerUser(utente);
            return new ResponseEntity(added, HttpStatus.OK);
        }
        catch (MailAlreadyUsedException e) 
        { return new ResponseEntity<>(new MessaggioRisposta("ERROR_MAIL_USER_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST); }
    }

    @GetMapping
    public List<Utente> getAll() { return utenteService.getUtenti(); }

    @GetMapping("/getUtente")
    @PreAuthorize("hasAnyAuthority('customer')")
    public Boolean getUtente(){ return utenteService.getUtente(JwtUtil.getEmail()); }
}