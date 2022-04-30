package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProduitDAOFactory {
	public ProduitDAO createProduitDAO() throws ClassNotFoundException, SQLException {
		return new AdaptateurProduitDAOXML();	
	}
}
