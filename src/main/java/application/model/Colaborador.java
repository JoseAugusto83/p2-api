package application.model;


import application.record.ColaboradorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="colaboradores")
@Getter
@Setter
@NoArgsConstructor

public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_colaborador;
    @Column(nullable = false)
    private String nome;


    public Colaborador(ColaboradorDTO colaborador){
        this.id_colaborador = colaborador.id_colaborador();
        this.nome = colaborador.nome();
    }
}
