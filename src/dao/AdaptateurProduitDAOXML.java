package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.Produit;
import programme.I_Produit;

public class AdaptateurProduitDAOXML implements ProduitDAO {
	private ProduitDAO_XML produitDAO;
	
	public AdaptateurProduitDAOXML() {
		this.produitDAO = new ProduitDAO_XML();
	}

	@Override
	public void ajouterProduit(I_Produit p) throws SQLException {
		this.produitDAO.creer(p);
	}

	@Override
	public void supprimerProduit(String p) throws SQLException {
		this.produitDAO.supprimer(new Produit(p, 12, 12.));

	}

	@Override
	public ArrayList<I_Produit> tousLesProduits() throws Exception {
		return (ArrayList<I_Produit>) this.produitDAO.lireTous();
	}

}
