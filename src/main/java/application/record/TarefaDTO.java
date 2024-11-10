package application.record;

import java.time.LocalDate;
import java.util.List;

import application.model.Colaborador;
import application.model.Tarefa;

public record TarefaDTO(long id_tarefa, String titulo, String descricao, LocalDate dataCriacao,
    LocalDate dataInicio, LocalDate dataConclusao, List<Colaborador> colaboradores) {
    public TarefaDTO(Tarefa tarefa){
        this(tarefa.getId_tarefa(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao(), tarefa.getDataInicio(), tarefa.getDataConclusao(), tarefa.getColaboradores());
    }
}
