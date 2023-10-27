package demo.e_commerce.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ordini")
@Getter
@Setter
@EqualsAndHashCode
public class Ordine 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private Date data;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.MERGE)
    private List<ProdottoOrdinato> prodottiOrdinati;
}