package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.TarefaDTO;
import application.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaRepo;

    @GetMapping
    public Iterable<TarefaDTO> list(){
        return tarefaRepo.findAll();
    }

    @PostMapping
    public TarefaDTO insert(@RequestBody TarefaDTO tarefa){
        return tarefaRepo.insert(tarefa);
    }

    @PutMapping("/{id}")
    public TarefaDTO update(@PathVariable long id, @RequestBody TarefaDTO tarefa){
        return tarefaRepo.update(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        tarefaRepo.deleteById(id);
    }
}
