package projeto.persistence;

import java.sql.SQLException;
import java.util.List;

import projeto.model.EntityRoupa;
import projeto.model.EntityEstoque;

public interface EstoqueDAO {
    public List<EntityRoupa> listarRoupas() throws SQLException, ClassNotFoundException;
    public EntityRoupa procurarRoupa(int idRoupa) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
    public void excluirRoupa(int idRoupa) throws SQLException, ClassNotFoundException;
    public void adicionarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
    public void preencherEstoque() throws SQLException, ClassNotFoundException;
    public void preencherFilial() throws SQLException, ClassNotFoundException;
    public void preencherDistribuidora() throws SQLException, ClassNotFoundException;
    public void preencherFuncionario() throws SQLException, ClassNotFoundException;
}
