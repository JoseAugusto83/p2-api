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

import application.record.ColaboradorDTO;
import application.record.ColaboradorListDTO;
import application.service.ColaboradorService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public Iterable<ColaboradorListDTO> list(){
        return colaboradorService.findAll();
    }

    @PostMapping
    public ColaboradorDTO insert(@RequestBody ColaboradorDTO colaborador){
        return colaboradorService.insert(colaborador);
    }

    @PutMapping("/{id}")
    public ColaboradorDTO update(@PathVariable long id, @RequestBody ColaboradorDTO colaborador){
        return colaboradorService.update(id, colaborador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        colaboradorService.deleteById(id);
    }

}
