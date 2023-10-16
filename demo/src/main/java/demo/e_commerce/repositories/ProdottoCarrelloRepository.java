package demo.e_commerce.repositories;

import demo.e_commerce.entities.ProdottoCarrello;
import demo.e_commerce.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoCarrelloRepository extends JpaRepository<ProdottoCarrello, Long> {
    void deleteCarrellosById(long carrelloId);
    List<ProdottoCarrello> findAllByUtente_Username(String username);

    void deleteAllByUtente(Utente utente);

}