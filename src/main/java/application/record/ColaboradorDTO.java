package application.record;

import java.util.Set;

import application.model.Colaborador;
import application.model.Tarefa;

public record ColaboradorDTO (long id_colaborador, String nome, Set<Tarefa> tarefas){
    public ColaboradorDTO(Colaborador colaborador){
        this(colaborador.getId_colaborador(), colaborador.getNome(), colaborador.getTarefas());
    }
}
