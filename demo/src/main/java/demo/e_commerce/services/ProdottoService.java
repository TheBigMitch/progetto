package demo.e_commerce.services;

import demo.e_commerce.entities.Categoria;
import demo.e_commerce.entities.Prodotto;
import demo.e_commerce.exceptions.ProdottoEsistenteException;
import demo.e_commerce.repositories.CategoriaRepository;
import demo.e_commerce.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService 
{
    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Prodotto> getProdotto(long id){ return prodottoRepository.findById(id); }

    @Transactional(readOnly = false)
    public Prodotto aggiungiProdotto(Prodotto prodotto) throws ProdottoEsistenteException 
    {
        if(prodottoRepository.existsByIsbn(prodotto.getIsbn())){ throw new ProdottoEsistenteException(); }
        
        long isbn= Long.parseLong(prodotto.getIsbn());
        Categoria categoria= categoriaRepository.findById(isbn).get();
        prodotto.setCategoria(categoria);
        prodottoRepository.save(prodotto);
        return prodotto;
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdotti(){ return prodottoRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdotti(int numeroPagine, int dimensionePagine, String ordine)
    {
        Pageable pagginazione = PageRequest.of(numeroPagine, dimensionePagine, Sort.by(ordine));
        Page<Prodotto> pagina = prodottoRepository.findAll(pagginazione);

        if(pagina.hasContent()){ return pagina.getContent(); }
        else{ return new ArrayList<>(); }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiPerNome(String nome){ return prodottoRepository.findByNomeContaining(nome); }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiPerIsbn(String isbn){ return prodottoRepository.findByIsbn(isbn); }

    @Transactional(readOnly = true)
    public List<Prodotto> getProdottiCategoria(Long IdCategoria){ return prodottoRepository.findByCategoria_Id(IdCategoria); }
}