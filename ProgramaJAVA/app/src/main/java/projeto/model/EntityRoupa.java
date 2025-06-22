package projeto.model;

public class EntityRoupa {
    // Variáveis:
    private int id;
    private EntityTipos tipo = new EntityTipos();
    private String marca;
    private String tamanho;
    private String genero;
    private int quantidade;
    // Getters e Setters:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public EntityTipos getTipo() {
        return tipo;
    }
    public void setTipo(EntityTipos tipo) {
        this.tipo = tipo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
