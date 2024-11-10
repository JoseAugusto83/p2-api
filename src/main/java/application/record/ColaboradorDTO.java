package application.record;

import application.model.Colaborador;

public record ColaboradorDTO (long id_colaborador, String nome){
    public ColaboradorDTO(Colaborador colaborador){
        this(colaborador.getId_colaborador(), colaborador.getNome());
    }
}
