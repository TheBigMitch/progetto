package demo.e_commerce.controllers;

import demo.e_commerce.entities.Categoria;
import demo.e_commerce.exceptions.CategoriaEsistenteException;
import demo.e_commerce.exceptions.CategoriaNonEsistenteException;
import demo.e_commerce.services.CategoriaService;
import demo.e_commerce.utilities.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorie")
@SuppressWarnings(value = { "all" })
public class CategoriaController 
{
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping()
    public List<Categoria> getCategorie(){ return categoriaService.listaCategorie(); }

    @PostMapping("/aggiungi")
    @PreAuthorize("hasAnyAuthority('seller')")
    public Boolean aggiungiCategoria(@Valid @RequestBody Categoria categoria)
    {
        try
        {
            Categoria nuovaCategoria=categoriaService.creaCategoria(categoria);
            return true;
        }
        catch(CategoriaEsistenteException e){ return false; }
    }

    @PostMapping("/aggiorna/{IDcategoria}")
    @PreAuthorize("hasAnyAuthority('seller')")
    public ResponseEntity aggiornaCategoria(@PathVariable("IDcategoria") Long IDcategoria, @Valid @RequestBody Categoria categoria)
    {
        try
        {
            Categoria aggiornata=categoriaService.aggiornaCategoria(IDcategoria, categoria);
            return new ResponseEntity(aggiornata, HttpStatus.OK);
        }
        catch(CategoriaNonEsistenteException e)
        { return new ResponseEntity<>(new MessaggioRisposta("CATEGORIA NON ESISTENTE"), HttpStatus.BAD_REQUEST); }
    }

    @GetMapping("/getCategoria/{IDcategoria}")
    public Categoria getCategoria(@PathVariable ("IDcategoria") Long IDcategoria){ return categoriaService.getCategoria(IDcategoria); }
}