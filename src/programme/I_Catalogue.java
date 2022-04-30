package programme;
import java.util.List; 

public interface I_Catalogue {

	public abstract boolean addProduit(I_Produit produit);
	public abstract boolean addProduit(String nom, double prix, int qte);
	public abstract int addProduits(List<I_Produit> l);
	public abstract boolean removeProduit(String nom);
	public abstract boolean acheterStock(String nomProduit, int qteAchetee);
	public abstract boolean vendreStock(String nomProduit, int qteVendue);
	public abstract String[] getNomProduits();
	public abstract double getMontantTotalTTC();
	public abstract String toString();

	public abstract void clear();

}