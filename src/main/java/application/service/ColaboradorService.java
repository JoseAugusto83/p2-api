package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.record.ColaboradorDTO;

import application.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
    
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public Iterable<ColaboradorDTO> findAll(){
        return colaboradorRepo.findAll().stream().map(ColaboradorDTO::new).toList();
    }

    public ColaboradorDTO insert(ColaboradorDTO colaborador){
        if(colaborador.nome().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Nome não pode ser nulo"  
            );
        }
        return new ColaboradorDTO(colaboradorRepo.save(new Colaborador(colaborador)));
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaborador) {
        Optional<Colaborador> resultado = colaboradorRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaborador não encontrado"  
            );
        }
        if(colaborador.nome().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Nome não pode ser nulo"  
            );
        }
        resultado.get().setNome(colaborador.nome());


        Colaborador retorno = colaboradorRepo.save(resultado.get());
        ColaboradorDTO resposta = new ColaboradorDTO(retorno);
        return resposta;
    }

    public void deleteById(long id){
        if(!colaboradorRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaborador Não Encontrado"
            );
        }
        colaboradorRepo.deleteById(id);
    }
}
