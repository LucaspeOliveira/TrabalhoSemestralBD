package projeto.view;

import java.sql.Connection;
import java.sql.SQLException;
//import java.util.List;

import projeto.persistence.GenericDAO;

public class Main {
    public static void main(String[] args) {
        
        GenericDAO genericDAO = new GenericDAO();
		//EstoqueDAO esDAO = new EstoqueDAO(genericDAO);

        try {
			Connection connection = genericDAO.getConnection();
			String dbProdName = connection.getMetaData().getDatabaseProductName();
			String dbProdVersion = connection.getMetaData().getDatabaseProductVersion();
			System.out.println(dbProdName + " - " + dbProdVersion);
			/* 
			List<Produto> produtos = pdDAO.getProdutos();
			produtos.forEach(prod -> System.out.println(prod.toString()));
            */
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        

    }
}
