package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepo;

        public Iterable<TarefaDTO> findAll() {
        return tarefaRepo.findAll().stream().map(TarefaDTO::new).toList();
    }

    public TarefaDTO insert(TarefaDTO tarefa) {
        Tarefa novo = new Tarefa(tarefa);
        Tarefa retorno = tarefaRepo.save(novo);
        TarefaDTO resposta = new TarefaDTO(retorno);

        return resposta;

        //return new TarefaDTO(tarefaRepo.save(new tarefa(tarefa)));
    }

    public TarefaDTO update(long id, TarefaDTO tarefa) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"  
            );
        }
        resultado.get().setTitulo(tarefa.titulo());
        resultado.get().setDescricao(tarefa.descricao());
        resultado.get().setDataInicio(tarefa.dataInicio());
        resultado.get().setDataConclusao(tarefa.dataConclusao());

        Tarefa retorno = tarefaRepo.save(resultado.get());
        TarefaDTO resposta = new TarefaDTO(retorno);
        return resposta;
    }

    public void deleteById(long id){
        if(!tarefaRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrada"
            );
        }
        tarefaRepo.deleteById(id);
    }
    
}
