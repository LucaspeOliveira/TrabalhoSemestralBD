package projeto.model;

import java.util.List;

public class EntityEstoque {
    // Variáveis:
    private int id;
    private int idFilial;
    private List<EntityRoupa> listaRoupas;
    // Setters e Getters:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdFilial() {
        return idFilial;
    }
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }
    public List<EntityRoupa> getListaRoupas() {
        return listaRoupas;
    }
    public void setListaRoupas(List<EntityRoupa> listaRoupas) {
        this.listaRoupas = listaRoupas;
    }
}