package projeto.persistence;

import java.sql.SQLException;
import java.util.List;

import projeto.model.EntityRoupa;
import projeto.model.EntityDistribuidora;
import projeto.model.EntityEstoque;
import projeto.model.EntityFilial;
import projeto.model.EntityFuncionario;

public interface EstoqueDAO {
    public List<EntityRoupa> listarRoupas() throws SQLException, ClassNotFoundException;
    public EntityRoupa procurarRoupa(int idRoupa) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
    public void excluirRoupa(int idRoupa) throws SQLException, ClassNotFoundException;
    public void adicionarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
    public EntityEstoque preencherEstoque() throws SQLException, ClassNotFoundException;
    public EntityFilial preencherFilial() throws SQLException, ClassNotFoundException;
    public EntityDistribuidora preencherDistribuidora() throws SQLException, ClassNotFoundException;
    public EntityFuncionario preencherFuncionario() throws SQLException, ClassNotFoundException;
}
