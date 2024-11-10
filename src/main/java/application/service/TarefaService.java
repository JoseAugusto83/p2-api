package application.service;

import java.util.ArrayList;
import java.util.List;
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
        if(tarefa.titulo().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Título não pode ser nulo"  
            );
        }
        if(tarefa.dataCriacao() == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Data de criação não pode ser nulo"  
            );
        }
        List<Long> lista = new ArrayList<>();
        for (int i = 0; i < tarefa.colaboradores().size() ; i++){
            if(!tarefaRepo.existsById(tarefa.colaboradores().get(i).getId_colaborador())) {
                lista.add(tarefa.colaboradores().get(i).getId_colaborador());
            }
        }

        if(lista.size() != 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaboradores de id " + lista + " não encontrados"
            );
        }
        Tarefa novo = new Tarefa(tarefa);
        Tarefa retorno = tarefaRepo.save(novo);
        TarefaDTO resposta = new TarefaDTO(retorno);

        return resposta;
    }

    public TarefaDTO update(long id, TarefaDTO tarefa) {
        if(tarefa.titulo().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Título não pode ser nulo"  
            );
        }
        if(tarefa.dataCriacao() == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Data de criação não pode ser nulo"  
            );
        }
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"  
            );
        }
        List<Long> lista = new ArrayList<>();
        for (int i = 0; i < tarefa.colaboradores().size() ; i++){
            if(!tarefaRepo.existsById(tarefa.colaboradores().get(i).getId_colaborador())) {
                lista.add(tarefa.colaboradores().get(i).getId_colaborador());
            }
        }

        if(lista.size() != 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaboradores de id " + lista + " não encontrados"
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
