package demo.e_commerce.repositories;

import demo.e_commerce.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> 
{
    Categoria findByNome(String nome);
}