package demo.e_commerce.controllers;

import demo.e_commerce.entities.Ordine;
import demo.e_commerce.exceptions.QuantitaNonDisponibileException;
//import demo.e_commerce.exceptions.UtenteNonTrovatoException;
import demo.e_commerce.exceptions.VariazionePrezzoException;
import demo.e_commerce.services.OrdineService;
//import demo.e_commerce.utilities.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @PostMapping("/creazione")
    @PreAuthorize("hasAnyAuthority('customer')")
    @SuppressWarnings(value = { "unused" })
    public Boolean create(@RequestBody @Valid Ordine ordine){
        try{
            Ordine nuovoOrdine=ordineService.aggiungiOrdine(ordine);
            return true;
        }catch(QuantitaNonDisponibileException e){
            return false;
        }catch (VariazionePrezzoException e){
            return false;
        }
    }
    @GetMapping("/effettuati")
    @PreAuthorize("hasAnyAuthority('customer')")
    public List<Ordine> mostraOrdini(){
        List<Ordine> listaOrdini= ordineService.mostraOrdiniUtente();
        return listaOrdini;
    }
/* 
    @PostMapping ("/{utente}")
    @PreAuthorize("hasAnyAuthority('customer')")
    public ResponseEntity mostraOrdini(@Valid @PathVariable("utente") String utente){
        try{
            List<Ordine> listaOrdini= ordineService.mostraOrdiniUtente(utente);
            return new ResponseEntity<>(listaOrdini, HttpStatus.OK);
        }catch(UtenteNonTrovatoException e){
            return new ResponseEntity<>(new MessaggioRisposta("Utente Sconosciuto"), HttpStatus.BAD_REQUEST);
        }
    }*/
}