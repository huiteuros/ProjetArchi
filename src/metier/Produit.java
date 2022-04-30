package metier;

import java.util.Locale;

import programme.I_Produit;

public class Produit implements I_Produit{

	private String nom;
	private int quantiteStock;
	private double prixUnitaireHT;
	static double tauxTVA = 0.2;
	
	public Produit(String nom, int quantiteStock, double prixUnitaireHT) {
		this.nom = nom;
		this.quantiteStock = quantiteStock;
		this.prixUnitaireHT = prixUnitaireHT;
	}
	
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
		this.nom = nom;
		this.quantiteStock = quantiteStock;
		this.prixUnitaireHT = prixUnitaireHT;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		this.quantiteStock += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		this.quantiteStock -= qteVendue;
		return true;
	}

	@Override
	public String getNom() {
		return this.nom.trim().replace('\t', ' ');
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT + this.prixUnitaireHT * this.tauxTVA;
	}

	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock * this.getPrixUnitaireTTC();
	}

	@Override
	public String toString() {
		return this.getNom() + " - " + "prix HT : " + String.format(Locale.FRANCE, "%,.2f", prixUnitaireHT) + " € - prix TTC : " + String.format(Locale.FRANCE, "%,.2f", getPrixUnitaireTTC()) + " € - quantité en stock : " + quantiteStock;
	}
	
	

}
