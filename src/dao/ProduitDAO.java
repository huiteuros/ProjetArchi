package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import programme.I_Produit;

public interface ProduitDAO {
	public void ajouterProduit(I_Produit p) throws SQLException;
	public void supprimerProduit(String p) throws SQLException;
	public ArrayList<I_Produit> tousLesProduits() throws Exception;

}
