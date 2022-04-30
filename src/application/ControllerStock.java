package application;

import metier.Catalogue;
import metier.Produit;
import programme.I_Produit;

public class ControllerStock {
	public static Catalogue catalogue = ControllerProduit.catalogue;
	
	public ControllerStock() {
		
	}
	
	public String getInfo() {
		return ""+catalogue;
	}
	
}
