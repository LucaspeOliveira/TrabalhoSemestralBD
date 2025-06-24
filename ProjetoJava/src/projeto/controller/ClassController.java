package projeto.controller;

import projeto.model.EntityEstoque;
import projeto.model.EntityFilial;
import projeto.model.EntityFuncionario;
import projeto.model.EntityRoupa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityDistribuidora;
import projeto.persistence.EstoqueDAO;
import projeto.persistence.EstoqueDAOImplementation;
import projeto.persistence.GenericDAO;

public class ClassController {
    EntityEstoque est = new EntityEstoque();
    EntityDistribuidora dist = new EntityDistribuidora();
    EntityFilial fili = new EntityFilial();
    EntityFuncionario func = new EntityFuncionario();
    GenericDAO gDao = new GenericDAO();
    EstoqueDAO estDao = new EstoqueDAOImplementation(gDao);

    public List<EntityRoupa> listar(){
        List<EntityRoupa> lista = new ArrayList<>();
        try { 
            lista = estDao.listarRoupas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public EntityRoupa pesquisar(String termoPesquisa){
        EntityRoupa roupa = new EntityRoupa();
        int id = 0;
        if(termoPesquisa == "")
            return roupa;
        try {
            id = Integer.parseInt(termoPesquisa);
            roupa = estDao.procurarRoupa(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roupa;
    }

    public void addRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException{
        try {
            estDao.adicionarRoupa(roupa);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void excluiRoupa(int id) {
        try {
            estDao.excluirRoupa(id);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void alterar (EntityRoupa ropa) throws ClassNotFoundException, SQLException{
        estDao.atualizarRoupa(ropa);
    }

}
