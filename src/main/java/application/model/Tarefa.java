package application.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;

    public Tarefa(application.record.TarefaDTO tarefa){
        this.id_tarefa = tarefa.id_tarefa();
        this.titulo = tarefa.titulo();
        this.descricao = tarefa.descricao();
        this.dataCriacao = tarefa.dataCriacao();
        this.dataInicio = tarefa.dataInicio();
        this.dataConclusao = tarefa.dataConclusao();
    }
}
