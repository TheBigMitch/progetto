package demo.e_commerce.repositories;

import demo.e_commerce.entities.ProdottoOrdinato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdottoOrdinatoRepository extends JpaRepository<ProdottoOrdinato, Long> {}