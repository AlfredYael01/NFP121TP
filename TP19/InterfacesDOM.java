/**
 * InterfacesDOM : Analyser un fichier de description des interfaces réseau
 */

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.util.ArrayList;

public class InterfacesDOM {

	/**
	 * Le texte contenu dans le premier fils. du premier élément accessible
	 * depuis l'élément racine qui a le tag voulu.
	 *
	 * Justification : Cette méthode est utile pour récupérer l'information
	 * (PCDATA) d'un sous-élément si on sait que ce sous élément à un tag unique
	 * dans l'élément racine.
	 *
	 * @param racine l'élément dont part la recherche
	 * @param tag le tag de l'élément dont on veut obtenir le PCDATA contenu
	 */
	//@ requires racine.getElementsByTagName(tag).getLength() > 0;
	public static String getStringByTagName(Element racine, String tag) {
		Node element = racine.getElementsByTagName(tag).item(0);
		String result = null;
		Node fils = element.getFirstChild();
		if (fils != null) { // Cas d'un élément non vide !
			result = fils.getNodeValue();
			// La documentation indique que le PCDATA peut être collecté comme
			// un seul morceau ou comme plusieurs morceaux (tous de type TEXT).
			// Aussi, on concatène également les valeurs des frères !
			while (fils.getNextSibling() != null) {
				fils = fils.getNextSibling();
				result = result + fils.getNodeValue();
			}
		}
		return result;
	}

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(Document doc) {
		// Principe : récupérer les << name >>, puis les compter
		System.out.println("Nb interfaces automatiques = "
				+ doc.getElementsByTagName("name").getLength());
	}

	/** Afficher le nombre d'interfaces spécifiées. */
	public static void afficherNombreInterfacesSpecifiees(Document doc) {
		System.out.println("Nb interfaces spécifiées = "
				+ doc.getElementsByTagName("iface").getLength());
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(Document doc) {
		System.out.println("Noms des interfaces automatiques = ");
		NodeList l = doc.getElementsByTagName("name");
		for (int i = 0; i < l.getLength(); i++) {
			Element elt = (Element) l.item(i);
			System.out.println("   " + elt.getAttribute("value"));
		}
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(Document doc) {
		System.out.println("Noms des interfaces qui utilisent"
				+ " la passerelle 147.127.18.200 = ");
		NodeList l = doc.getElementsByTagName("iface");
		for (int i = 0; i < l.getLength(); i++) {
			Element elt = (Element) l.item(i);
			NodeList l2 = elt.getElementsByTagName("gateway");
			for (int j = 0; j < l2.getLength(); j++) {
				Element theGateway = (Element) l2.item(j);
				String ip = theGateway.getTextContent();
				if (ip.equals("147.127.18.200")) {
					System.out.println("   " + elt.getAttribute("name"));
				}
			}
		}
	}

	/** Afficher les noms des interfaces qui sont définies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(Document doc) {
		ArrayList<String> decl = new ArrayList<String>();
		System.out.println("Noms des interfaces"
				+ " qui sont définies mais non automatiques  = ");
		NodeList ld = doc.getElementsByTagName("name");
		for (int i = 0; i < ld.getLength(); i++) {
			Element elt = (Element) ld.item(i);
			decl.add(elt.getAttribute("value"));
		}

		NodeList ls = doc.getElementsByTagName("iface");
		for (int i = 0; i < ls.getLength(); i++) {
			Element elt = (Element) ls.item(i);
			String nom = elt.getAttribute("name");
			if (!decl.contains(nom)) {
				System.out.println("   " + nom);
			}
		}
	}

	/**
	 * Méthode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
		// Instancier la << factory >>
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			boolean erreur = args.length < 2;
			if (!erreur) {
				String fichier = args[0];

				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(fichier);

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
		} catch (SAXParseException e) {
			System.out.println("Erreur au niveau SAX (DOM utilise SAX) : " + e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}