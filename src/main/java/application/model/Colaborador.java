package application.model;

import java.util.List;
import java.util.Set;

import application.record.ColaboradorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private String nome;

    @ManyToMany
    @JoinTable(name = "colaboradores_tarefas",
        joinColumns = @JoinColumn(name="id_colaborador"),
        inverseJoinColumns = @JoinColumn(name="id_tarefa"))
    public List<Tarefa> tarefas;


    public Colaborador(ColaboradorDTO colaborador){
        this.id_colaborador = colaborador.id_colaborador();
        this.nome = colaborador.nome();
        this.tarefas = colaborador.tarefas();
    }
}
