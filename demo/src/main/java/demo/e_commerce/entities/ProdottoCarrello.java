package demo.e_commerce.entities;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name="ProdottiCarrello")
public class ProdottoCarrello 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="prodottoId")
    private Prodotto prodottoId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Ordine ordine;

    private String nomeProdotto;

    private Date data;

    @Column(name="quantità")
    private int quantita;

    private double prezzo;

    @OneToOne
    private Utente utente;
}