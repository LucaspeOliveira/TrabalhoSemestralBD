package projeto.controller;

import projeto.model.EntityEstoque;
import projeto.model.EntityFilial;
import projeto.model.EntityFuncionario;
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

    public void confirma(){
        System.out.println("\nCRIADO COM SUXESSO");
    }
}
