package demo.e_commerce.repositories;

import demo.e_commerce.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> { List<Ordine> findAllByUtente(String utente); }