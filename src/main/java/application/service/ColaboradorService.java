package application.service;

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

@Service
public class ColaboradorService {
    
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public Iterable<ColaboradorListDTO> findAll(){
        return colaboradorRepo.findAll().stream().map(ColaboradorListDTO::new).toList();
    }

    public ColaboradorDTO insert(ColaboradorDTO colaborador){
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
