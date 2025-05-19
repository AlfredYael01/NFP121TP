import javax.xml.parsers.*; // SAXParserFactory, SAXParser
import org.xml.sax.*; // XMLReader
import org.xml.sax.helpers.*; // XMLReader
import java.util.*;

/**
 * Calculer le nombre d'interfaces automatiques.
 *
 * Dans cette version, on calcule le nombre d'interfaces dans un attribut de la
 * classe qui sera ensuite être exploité par le reste de l'application.
 */
class NombreInterfacesAutomatiques extends DefaultHandler {

	private int nombreInterfaces;

	public int getNombreInterfaces() {
		return nombreInterfaces;
	}

	public void startDocument() {
		nombreInterfaces = 0;
	}

	public void startElement(String uriEspaceNom, String nom,
							 String nomQualifié, Attributes attributs) throws SAXException {
		if (nomQualifié.equals("name")) {
			nombreInterfaces++;
		}
	}
}

// //////////////////////////////////////////////////////////////////////////////

/** Calculer le nombre d'interfaces spécifiées. */
class NombreInterfacesSpecifiees extends DefaultHandler {

	private int nombreInterfaces;

	public int getNombreInterfaces() {
		return nombreInterfaces;
	}

	public void startDocument() {
		nombreInterfaces = 0;
	}

	public void startElement(String uriEspaceNom, String nom,
							 String nomQualifié, Attributes attributs) throws SAXException {
		if (nomQualifié.equals("iface")) {
			nombreInterfaces++;
		}
	}
}

// //////////////////////////////////////////////////////////////////////////////

/** Constuit la liste des noms des interfaces automatiques. */
class NomsInterfacesAutomatiques extends DefaultHandler {

	private List<String> listeInterfaces;

	public List<String> getInterfaces() {
		return listeInterfaces;
	}

	public void startDocument() {
		listeInterfaces = new ArrayList<String>();
	}

	public void startElement(String uriEspaceNom, String nom,
							 String nomQualifié, Attributes attributs) throws SAXException {
		if (nomQualifié.equals("name")) {
			listeInterfaces.add(attributs.getValue("value"));
		}
	}
}

// //////////////////////////////////////////////////////////////////////////////

/**
 * Constuit la liste des noms des interfaces qui utilisent la passerelle
 * 147.127.18.200
 *
 * Obtenu à partir du "diagramme états - transitions"
 */
class NomsInterfacesPasserelle extends DefaultHandler {
	enum Etat {INIT, IFACE, GATEWAY};

	private Etat etat;		// état de l'analyseur SaX
	private String nomCourant;
	private List<String> interfaces;

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void startDocument() {
		etat = Etat.INIT;
		nomCourant = "";
		interfaces = new ArrayList<String>();
	}

	public void startElement(String uriEspaceNom, String nom,
							 String nomQualifié, Attributes attributs) throws SAXException {
		switch (etat) {
			case INIT:
				if (nomQualifié.equals("iface")) {
					nomCourant = attributs.getValue("name"); // On capture le nom
					etat = Etat.IFACE; // On change d'état
				}
				break;
			case IFACE:
				if (nomQualifié.equals("gateway")) {
					etat = Etat.GATEWAY; // On change d'état
				}
				break;
		}
	}

	public void endElement(String uriEspaceNom, String nom, String nomQualifié)
			throws SAXException {
		if (etat == Etat.IFACE && nomQualifié.equals("iface")) {
			etat = Etat.INIT;
		}
	}

	public void characters(char[] ch, int start, int length) {
		if (etat == Etat.GATEWAY) {
			String ip = new String(ch, start, length);
			if (ip.equals("147.127.18.200")) {
				// nomCourant à la passerelle cherchée
				interfaces.add(nomCourant);
			}
		}
	}

}

// //////////////////////////////////////////////////////////////////////////////

/**
 * Constuit la liste des noms des interfaces qui sont définies mais non
 * automatiques
 */
class InterfacesDefiniesNonAutomatiques extends DefaultHandler {

	private List<String> interfacesD;
	private List<String> interfacesS;
	private List<String> interfaces;

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void startDocument() {
		interfacesD = new ArrayList<String>();
		interfacesS = new ArrayList<String>();
		interfaces = new ArrayList<String>();
	}

	public void startElement(String uriEspaceNom, String nom,
							 String nomQualifié, Attributes attributs) throws SAXException {
		if (nomQualifié.equals("name"))
			interfacesD.add(attributs.getValue("value"));
		if (nomQualifié.equals("iface"))
			interfacesS.add(attributs.getValue("name"));
	}

	public void endDocument() {
		for (int i = 0; i < interfacesS.size(); i++) {
			if (!interfacesD.contains(interfacesS.get(i)))
				interfaces.add(interfacesS.get(i));
		}
	}
}

// //////////////////////////////////////////////////////////////////////////////

/**
 * InterfacesSAX :  Analyser un fichier de description des interfaces réseau
 */
public class InterfacesSAX {

	public static XMLReader creerXMLReader() throws SAXException,
			ParserConfigurationException, java.io.IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		XMLReader xmlReader = spf.newSAXParser().getXMLReader();
		xmlReader.setFeature("http://xml.org/sax/features/validation", true);
		return xmlReader;
	}

	public static void analyserXML(String fichier, DefaultHandler handler)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		XMLReader xmlReader = creerXMLReader();
		xmlReader.setContentHandler(handler);
		xmlReader.parse(fichier);
	}

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(String fichier)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		NombreInterfacesAutomatiques handler = new NombreInterfacesAutomatiques();
		analyserXML(fichier, handler);
		System.out.println("Nb interfaces automatiques = "
				+ handler.getNombreInterfaces());
	}

	/** Afficher le nombre d'interfaces spécifiées. */
	public static void afficherNombreInterfacesSpecifiees(String fichier)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		NombreInterfacesSpecifiees handler = new NombreInterfacesSpecifiees();
		analyserXML(fichier, handler);
		System.out.println("Nb interfaces spécifiées = "
				+ handler.getNombreInterfaces());
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(String fichier)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		NomsInterfacesAutomatiques handler = new NomsInterfacesAutomatiques();
		analyserXML(fichier, handler);
		List<String> v = handler.getInterfaces();
		System.out.println("Noms des interfaces automatiques = ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("   " + v.get(i));
		}
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(String fichier)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		NomsInterfacesPasserelle handler = new NomsInterfacesPasserelle();
		analyserXML(fichier, handler);
		List<String> v = handler.getInterfaces();
		System.out
				.println("Noms des interfaces qui utilisent la passerelle 147.127.18.200 =  ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("   " + v.get(i));
		}
	}

	/** Afficher les noms des interfaces qui sont définies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(String fichier)
			throws SAXException, ParserConfigurationException,
			java.io.IOException {
		InterfacesDefiniesNonAutomatiques handler = new InterfacesDefiniesNonAutomatiques();
		analyserXML(fichier, handler);
		List<String> v = handler.getInterfaces();
		System.out
				.println("Noms des interfaces  qui sont définies mais non automatiques  = ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("   " + v.get(i));
		}
	}

	/*
	 * Méthode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
		try {
			boolean erreur = args.length < 2;
			if (!erreur) {
				String fichier = args[0];

				for (int indice = 1; indice < args.length; indice++) {
					String option = args[indice];
					if (option.equals("nbInterfacesAutomatiques")) {
						afficherNombreInterfacesAutomatiques(fichier);
					} else if (option.equals("nbInterfacesSpécifiées")) {
						afficherNombreInterfacesSpecifiees(fichier);
					} else if (option.equals("nomInterfacesAutomatiques")) {
						afficherNomsInterfacesAutomatiques(fichier);
					} else if (option.equals("nomInterfacesPasserelle")) {
						afficherNomsInterfacesPasserelle(fichier);
					} else if (option.equals("interfacesDefiniesNonAutomatiques")) {
						afficherInterfacesDefiniesNonAutomatiques(fichier);
					} else if (option.equals("tout")) {
						afficherNombreInterfacesAutomatiques(fichier);
						afficherNombreInterfacesSpecifiees(fichier);
						afficherNomsInterfacesAutomatiques(fichier);
						afficherNomsInterfacesPasserelle(fichier);
						afficherInterfacesDefiniesNonAutomatiques(fichier);
					} else {
						System.out.println("Information inconnue : " + option);
						erreur = true;
					}

				}
			}

			if (erreur) {
				System.out.println();
				System.out.println("Usage : java " + "InterfacesSAX"
						+ " fichier.xml information...");
				System.out.println();
				System.out.println("Information : ");
				System.out
						.println("   nbInterfacesAutomatiques : nombre d'interfaces automatiques");
				System.out
						.println("   nbInterfacesSpécifiées : nombre d'interfaces spécifiées");
				System.out
						.println("   nomInterfacesAutomatiques : noms des interfaces automatiques");
				System.out
						.println("   nomInterfacesPasserelle : noms des interfaces qui utilisent la passerelle 147.127.18.200");
				System.out.println("   interfacesDefiniesNonAutomatiques : "
						+ "noms des interfaces définies mais non automatiques");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}