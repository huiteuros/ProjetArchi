package dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import metier.Produit;
import programme.I_Produit;


public class ProduitDAO_XML {
	private String uri = "C:/Users/perso/Desktop/E05/BDDXML/Produits.xml";
	private Document doc;

	public ProduitDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	public boolean creer(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("produit");
			prod.setAttribute("nom", p.getNom());
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	public boolean maj(I_Produit p) {
		try {
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	public boolean supprimer(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}

	public I_Produit lire(String nom) {
		Element e = chercheProduit(nom);
		if (e != null)
			return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")));
		else
			return null;
	}

	public List<I_Produit> lireTous() {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("produit");

			for (Element prod : lProd) {
				String nomP = prod.getAttributeValue("nom");
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return l;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private Element chercheProduit(String nom) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("produit");
		int i = 0;
		while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lProd.size())
			return lProd.get(i);
		else
			return null;
	}
}

