package application.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tarefas")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tarefa;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    @Column(nullable = false)
    private LocalDate dataCriacao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;

    @ManyToMany
    @JoinTable(name = "tarefas_colaboradores",
        joinColumns = @JoinColumn(name="id_tarefa"),
        inverseJoinColumns = @JoinColumn(name="id_colaborador"))
    public List<Colaborador> colaboradores;

    public Tarefa(application.record.TarefaDTO tarefa){
        this.id_tarefa = tarefa.id_tarefa();
        this.titulo = tarefa.titulo();
        this.descricao = tarefa.descricao();
        this.dataCriacao = tarefa.dataCriacao();
        this.dataInicio = tarefa.dataInicio();
        this.dataConclusao = tarefa.dataConclusao();
        this.colaboradores = tarefa.colaboradores();
    }
}
