package application;

import java.util.Arrays;

import metier.Catalogue;
import metier.Produit;
import programme.I_Produit;

public class ControllerProduit {
	
	public static Catalogue catalogue = new Catalogue();
	
	public ControllerProduit() {
		
	}
	
	public boolean creerProduit(String nom, int qte, double prix) {
		
		if(Arrays.asList(catalogue.getNomProduits()).contains(nom)) {
			System.out.println("Le produit existe déja");
			return false;
		}
	
		if(prix < 0) {
			System.out.println("Le prix ne peut pas être négatif");
			return false;
		}
		
		return catalogue.addProduit(new Produit(nom, qte, prix));
	}
	
	public boolean supprimerProduit(String p) {
		return catalogue.removeProduit(p);
	}
}
