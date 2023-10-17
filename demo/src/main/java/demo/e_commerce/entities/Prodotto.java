package demo.e_commerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Table(name="prodotti")
public class Prodotto 
{
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String isbn;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name="id_categoria", nullable = false)
    Categoria categoria;

    @Setter
    @Getter
    @Basic
    @Column(name="nome", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Basic
    @Column(name="immagine", nullable = true)
    private String UrlImmagine;

    @Basic
    @Setter
    @Getter
    @Column(name="prezzo", nullable = false)
    private double prezzo;

    @Basic
    @Setter
    @Getter
    @Column(name="descrizione", nullable = true)
    private String descrizione;

    @Basic
    @Setter
    @Getter
    @Column(name="quantità", nullable = false)
    private int quantita;
}