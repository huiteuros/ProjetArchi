package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import dao.ProduitDAO;
import dao.ProduitDAOFactory;
import dao.ProduitDAORelationnel;
import programme.I_Catalogue;
import programme.I_Produit;

public class Catalogue implements I_Catalogue{
	private ArrayList<I_Produit> listeProduits;
	private ProduitDAO produitDAO;
	
	public Catalogue() {
		try {
			ProduitDAOFactory produitDAOFact = new ProduitDAOFactory();
			this.produitDAO = produitDAOFact.createProduitDAO();
			this.listeProduits = produitDAO.tousLesProduits();
		}
		catch(Exception e) {
			
		}
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit == null) {
			return false;
		}
		if(Arrays.asList(this.getNomProduits()).contains(produit.getNom())) {
			System.out.println("Le produit existe déja");
			return false;
		}
		if (produit.getPrixUnitaireHT() <= 0 || produit.getQuantite() < 0) {
			return false;
		}
		
		try {
			this.produitDAO.ajouterProduit(produit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.listeProduits.add(produit);
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		Produit p = new Produit(nom, qte, prix);
		return this.addProduit(p);
		
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int cpt = 0;
		if(l == null) {
			return 0;
		}
		for(I_Produit p: l) {
			if (this.addProduit(p)) {			
				cpt++;
			}
		}
		return cpt;
	}

	@Override
	public boolean removeProduit(String nom) {
		if(this.getProduit(nom) == null) {
			return false;
		}
		for(I_Produit elem :this.listeProduits) {
			if(nom.equals(elem.getNom())) {
				try {
					ProduitDAORelationnel produitDAO = new ProduitDAORelationnel();
					produitDAO.supprimerProduit(nom);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return this.listeProduits.remove(elem);
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean isAdd = false;
		if(qteAchetee <= 0) {
			return false;
		}
		for(I_Produit elem :this.listeProduits) {
			if(nomProduit.equals(elem.getNom())) {
				elem.ajouter(qteAchetee);
				isAdd = true;
			}
		}
		return isAdd;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean isSell = false;
		if(qteVendue <= 0) {
			return false;
		}
		if(!Arrays.asList(this.getNomProduits()).contains(nomProduit.trim())) {
			return false;
		}
		I_Produit p = this.getProduit(nomProduit);
		if(p.getQuantite() < qteVendue) {
			System.out.println("Il n'y a pas assez de quantité");
			return false;
		}
		return p.enlever(qteVendue);

	}
	
	public I_Produit getProduit(String nom) {
		for(I_Produit elem :this.listeProduits) {
			if(elem.getNom().equals(nom)) {
				return elem;
			}
		}
		return null;
	}

	@Override
	public String[] getNomProduits() {
		String nomProduits[] = new String[this.listeProduits.size()];
		int cpt = 0;
		for(I_Produit elem :this.listeProduits) {
			nomProduits[cpt] = elem.getNom().trim();
			cpt++;
		}
		Arrays.sort(nomProduits);
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double total = 0;
		for(I_Produit elem :this.listeProduits) {
			total += elem.getPrixStockTTC();
		}
		total = Math.round(total*100.0)/100.0;
		return total;
	}

	@Override
	public void clear() {
		this.listeProduits = new ArrayList<I_Produit>();
		
	}

	@Override
	public String toString() {
		String tout = "";
		for(I_Produit p: this.listeProduits) {
			tout += p+"\n";
		}
		tout+= "\nMontant total TTC du stock : " + String.format(Locale.FRANCE, "%,.2f", getMontantTotalTTC()) + " €";
		return tout;
	}
	
	

}
