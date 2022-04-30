package dao;

import java.sql.*;
import java.util.ArrayList;

import metier.Produit;
import programme.I_Produit;

public class ProduitDAORelationnel implements ProduitDAO {
	Connection cn;

	public ProduitDAORelationnel() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
		String login = "AMPHOUXG";
		String mdp = "1109012811H";

		this.cn = DriverManager.getConnection(url, login, mdp);
	}

	public void ajouterProduit(I_Produit p) throws SQLException {
		String nom = p.getNom();
		int quantite = p.getQuantite();
		double prix = p.getPrixUnitaireHT();

		CallableStatement cst = cn.prepareCall("{call createProduit(?,?,?)}");

		cst.setString(1, nom);
		cst.setInt(2, quantite);
		cst.setDouble(3, prix);
		cst.execute();
	}

	public void supprimerProduit(String p) throws SQLException {

		CallableStatement cst = cn.prepareCall("{call removeProduit(?)}");

		cst.setString(1, p);
		cst.execute();
	}

	public ArrayList<I_Produit> tousLesProduits() throws Exception {
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT nom, quantitestock, prixunitaireht FROM Produit");
		ArrayList<I_Produit> listeProduit = new ArrayList<I_Produit>();
		while (rs.next()) {
			Produit p = new Produit(rs.getString(1), rs.getInt(2), rs.getLong(3));
			listeProduit.add(p);
		}
		return listeProduit;
	}

}
