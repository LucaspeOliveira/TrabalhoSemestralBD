package projeto.entity;
import java.util.List;

public class EntityTipos {
    // Variáveis:
    private String nome;
    private List<EntityRoupa> listaRoupas;
    // Setters e Getters:
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public List<EntityRoupa> getListaRoupas() {
        return listaRoupas;
    }
    public void setListaRoupas(List<EntityRoupa> listaRoupas) {
        this.listaRoupas = listaRoupas;
    }
}
