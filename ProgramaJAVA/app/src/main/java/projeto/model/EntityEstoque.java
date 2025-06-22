package projeto.model;

import java.util.List;

public class EntityEstoque {
    // Variáveis:
    private List<EntityTipos> tiposRoupas;
    // Setters e Getters:
    public void setTiposRoupas(List<EntityTipos> tiposRoupas) {
        this.tiposRoupas = tiposRoupas;
    }
    public List<EntityTipos> getTiposRoupas() {
        return tiposRoupas;
    }
}
