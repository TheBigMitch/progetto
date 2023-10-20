package demo.e_commerce.repositories;

import demo.e_commerce.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> 
{
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    Utente findByUsername(String username);
    boolean existsByUsername(String username);
}