/**
 * InterfacesJDOM : Analyser un fichier de description des interfaces réseau
 */

import org.jdom2.*;
import org.jdom2.filter.*;
import org.jdom2.input.*;
import java.io.File;
import java.util.*;

public class InterfacesJDOM {

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(Document doc) {
		// Principe : récupérer les << name >> et les compter
		int nb = 0;
		for (Element e : doc.getDescendants(new ElementFilter("name"))) {
			nb++;
		}
		System.out.println("Nb interfaces automatiques = " + nb);
	}

	/** Afficher le nombre d'interfaces spécifiées. */
	public static void afficherNombreInterfacesSpecifiees(Document doc) {
		System.out.println("Nb interfaces spécifiées = "
				+ doc.getRootElement().getChildren("iface").size());
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(Document doc) {
		// Dommage qu'il n'y ait pas une version de JDom intégrant la généricité !
		System.out.println("Noms des interfaces automatiques = ");
		for (Element autoElt : doc.getRootElement().getChildren("auto")) {
			for (Element nameElt : autoElt.getChildren("name")) {
				System.out.println("   " + nameElt.getAttributeValue("value"));
			}
		}
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(Document doc) {
		String adrIP = "147.127.18.200";
		System.out.println("Noms des interfaces qui utilisent"
				+ " la passerelle " + adrIP + " = ");
		for (Element gatewayElt : doc.getDescendants(new ElementFilter("gateway"))) {
			String ip = gatewayElt.getText();
			if (ip.equals(adrIP)) {
				// C'est la bonne gateway !
				Element ifaceElt = (Element)
						gatewayElt.getParent().getParent().getParent();
				System.out.println("   " + ifaceElt.getAttributeValue("name"));
			}
		}
	}

	/** Afficher les noms des interfaces qui sont définies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(Document doc) {
		// Déterminer les interfaces automatiques
		Set<String> automatiques = new TreeSet<>();
		for (Element nameElt : doc.getDescendants(new ElementFilter("name"))) {
			automatiques.add(nameElt.getAttributeValue("value"));
		}

		// Déterminer les interfaces définies
		Set<String> definies = new TreeSet<>();
		for (Element ifaceElt : doc.getDescendants(new ElementFilter("iface"))) {
			definies.add(ifaceElt.getAttributeValue("name"));
		}

		// Déterminer les interfaces définies non automatiques
		Set<String> resultat = new TreeSet<>(definies);
		resultat.removeAll(automatiques);

		// Afficher le résultat
		System.out.println("Noms des interfaces"
				+ " qui sont définies mais non automatiques  = ");
		for (String nom : resultat) {
			System.out.println("   " + nom);
		}
	}

	/**
	 * Méthode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			boolean erreur = args.length < 2;
			if (!erreur) {
				String fichier = args[0];

				Document document = sxb.build(new File(fichier));

				for (int indice = 1; indice < args.length; indice++) {
					String option = args[indice];
					if (option.equals("nbInterfacesAutomatiques")) {
						afficherNombreInterfacesAutomatiques(document);
					} else if (option.equals("nbInterfacesSpécifiées")) {
						afficherNombreInterfacesSpecifiees(document);
					} else if (option.equals("nomInterfacesAutomatiques")) {
						afficherNomsInterfacesAutomatiques(document);
					} else if (option.equals("nomInterfacesPasserelle")) {
						afficherNomsInterfacesPasserelle(document);
					} else if (option.equals("interfacesDefiniesNonAutomatiques")) {
						afficherInterfacesDefiniesNonAutomatiques(document);
					} else if (option.equals("tout")) {
						afficherNombreInterfacesAutomatiques(document);
						afficherNombreInterfacesSpecifiees(document);
						afficherNomsInterfacesAutomatiques(document);
						afficherNomsInterfacesPasserelle(document);
						afficherInterfacesDefiniesNonAutomatiques(document);
					} else {
						System.out.println("Information inconnue : " + option);
						erreur = true;
					}

				}
			}

			if (erreur) {
				System.out.println();
				System.out.println("Usage : java " + "InterfacesDOM"
						+ " fichier.xml information...");
				System.out.println();
				System.out.println("Information : ");
				System.out.println("   nbInterfacesAutomatiques : "
						+ "nombre d'interfaces automatiques");
				System.out.println("   nbInterfacesSpécifiées : "
						+ "nombre d'interfaces spécifiées");
				System.out.println("   nomInterfacesAutomatiques : "
						+ "noms des interfaces automatiques");
				System.out.println("   nomInterfacesPasserelle : "
						+ "noms des interfaces qui utilisent la passerelle "
						+ "147.127.18.200");
				System.out.println("   interfacesDefiniesNonAutomatiques : "
						+ "noms des interfaces définies mais non automatiques");
			}
		} catch (JDOMException e) {
			System.out.println("Erreur JDOM : " + e);
			e.printStackTrace();
		} catch (java.io.IOException e) {
			System.out.println("Erreur d'entrée/sortie : " + e);
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

}