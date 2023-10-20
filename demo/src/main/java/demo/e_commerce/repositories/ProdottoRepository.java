package demo.e_commerce.repositories;

import demo.e_commerce.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> 
{
    List<Prodotto> findByNomeContaining(String nome);
    List<Prodotto> findByCodice(String codice);
    List<Prodotto> findByCategoria_Id(Long id);
    boolean existsByCodice(String codice);
}