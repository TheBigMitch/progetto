package demo.e_commerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="categorie")
@Data
public class Categoria 
{
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="categoria", nullable = true, length = 200)
    private String descrizione;

    @Basic
    @Column(name="immagine", nullable = true)
    private String urlImmagine;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Prodotto> prodotti;
}