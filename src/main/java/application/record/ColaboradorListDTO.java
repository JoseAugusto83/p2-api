package application.record;

import application.model.Colaborador;

public record ColaboradorListDTO(long id_colaborador, String nome) {
    public ColaboradorListDTO(Colaborador colaborador){
        this(colaborador.getId_colaborador(), colaborador.getNome());
    }
}
