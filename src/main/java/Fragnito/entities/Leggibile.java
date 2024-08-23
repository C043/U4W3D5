package Fragnito.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipologia")
public abstract class Leggibile {
    @Id
    @GeneratedValue
    private UUID isbn;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private Integer anno;

    @Column(nullable = false)
    private Integer pagine;

    public Leggibile() {
    }

    public Leggibile(String titolo, Integer anno, Integer pagine) {
        this.titolo = titolo;
        this.anno = anno;
        this.pagine = pagine;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getPagine() {
        return pagine;
    }

    public void setPagine(Integer pagine) {
        this.pagine = pagine;
    }

    @Override
    public String toString() {
        return "Leggibile{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", pagine=" + pagine +
                '}';
    }
}
