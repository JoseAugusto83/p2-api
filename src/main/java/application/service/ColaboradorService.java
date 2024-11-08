package application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.model.Tarefa;
import application.record.ColaboradorDTO;
import application.record.ColaboradorListDTO;
import application.repository.ColaboradorRepository;
import application.repository.TarefaRepository;

@Service
public class ColaboradorService {
    
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    @Autowired
    private TarefaRepository tarefaRepo;

    public Iterable<ColaboradorDTO> findAll(){
        return colaboradorRepo.findAll().stream().map(ColaboradorDTO::new).toList();
    }

    public ColaboradorDTO insert(ColaboradorDTO colaborador){
        List<Long> lista = new ArrayList<>();
        for (int i = 0; i < colaborador.tarefas().size() ; i++){
            if(!tarefaRepo.existsById(colaborador.tarefas().get(i).getId_tarefa())) {
                lista.add(colaborador.tarefas().get(i).getId_tarefa());
            }
        }

        if(lista.size() != 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefas de id " + lista + " não encontradas"
            );
        }

        return new ColaboradorDTO(colaboradorRepo.save(new Colaborador(colaborador)));
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaborador) {
        Optional<Colaborador> resultado = colaboradorRepo.findById(id);
        resultado.get().setNome(colaborador.nome());
        resultado.get().setTarefas(colaborador.tarefas());

        Colaborador retorno = colaboradorRepo.save(resultado.get());
        ColaboradorDTO resposta = new ColaboradorDTO(retorno);
        return resposta;
    }

    public void deleteById(long id){
        if(!colaboradorRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaborador Não Encontrada"
            );
        }
        colaboradorRepo.deleteById(id);
    }
}
