package application;

import metier.Catalogue;
import metier.Produit;
import programme.I_Produit;

public class ControllerAchatVente {
	public static Catalogue catalogue = ControllerProduit.catalogue;
	
	public ControllerAchatVente() {
		
	}
	
	public boolean vente(String nom, int qte) {
		I_Produit test = catalogue.getProduit(nom);
		if(test.getQuantite() < qte) {
			System.out.println("Il n'y a pas assez de quantité");
			return false;
		}
		return catalogue.vendreStock(nom, qte);
	}
	
	public boolean achat(String nom, int qte) {
		return catalogue.acheterStock(nom, qte);
	}
}
