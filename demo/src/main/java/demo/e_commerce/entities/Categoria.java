package demo.e_commerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="categorie")
@Data
public class Categoria {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Prodotto> prodotti;
}