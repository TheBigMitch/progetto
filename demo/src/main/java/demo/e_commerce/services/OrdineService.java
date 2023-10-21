package demo.e_commerce.services;

import demo.e_commerce.entities.*;
import demo.e_commerce.exceptions.QuantitaNonDisponibileException;
import demo.e_commerce.exceptions.VariazionePrezzoException;
import demo.e_commerce.repositories.*;
import demo.e_commerce.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*import javax.persistence.EntityManager;*/
import java.util.Date;
import java.util.List;

@Service
public class OrdineService 
{
    @Autowired
    private OrdineRepository ordineRepository;

    /*@Autowired
    private ProdottoOrdinatoRepository prodottoOrdinatoRepository;*/

    @Autowired
    private ProdottoCarrelloRepository prodottoCarrelloRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    /* @Autowired
    private EntityManager entityManager;*/

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {QuantitaNonDisponibileException.class, VariazionePrezzoException.class})
    public Ordine aggiungiOrdine(Ordine ordine) throws VariazionePrezzoException, QuantitaNonDisponibileException 
    {
        String username = JwtUtil.getEmail();
        Utente utente= utenteRepository.findByUsername(username);
        Ordine nOrdine= new Ordine();

        nOrdine.setData(new Date(System.currentTimeMillis()));
        nOrdine.setUtente(utente);
        nOrdine.setProdottiOrdinati(ordine.getProdottiOrdinati());

        Ordine ordineS = ordineRepository.save(nOrdine);
        utenteRepository.save(utente);

        for(ProdottoOrdinato prodottoOrdinato : ordineS.getProdottiOrdinati())
        {
            prodottoOrdinato.setOrdine(ordineS);
            /*ProdottoOrdinato aggiunto=prodottoOrdinatoRepository.save(prodottoOrdinato);*/
            Prodotto prodotto= prodottoRepository.findById(prodottoOrdinato.getProdotto().getId()).get();

            if(prodotto.getPrezzo()>prodottoOrdinato.getPrezzo()){ throw new VariazionePrezzoException(); }

            int nuovaQuantità=prodotto.getQuantita()-prodottoOrdinato.getQuantita();
            
            if( nuovaQuantità<0){ throw new QuantitaNonDisponibileException(); }
            prodotto.setQuantita(nuovaQuantità);
        }

        prodottoCarrelloRepository.deleteAllByUtente(utente);
        return ordineS;
    }

    @Transactional(readOnly = true)
    public List<Ordine> mostraOrdiniUtente() 
    {
        String nome = JwtUtil.getEmail();
        return ordineRepository.findAllByUtente(nome);
    }

    @Transactional(readOnly = true)
    public List<Ordine> mostraOrdini(){ return ordineRepository.findAll(); }
}