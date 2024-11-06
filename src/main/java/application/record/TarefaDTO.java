package application.record;

import java.time.LocalDate;

import application.model.Tarefa;

public record TarefaDTO(long id_tarefa, String titulo, String descricao, LocalDate dataCriacao,
    LocalDate dataInicio, LocalDate dataConclusao) {
    public TarefaDTO(Tarefa tarefa){
        this(tarefa.getId_tarefa(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao(), tarefa.getDataInicio(), tarefa.getDataConclusao());
    }
}
