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
		List<Element> autos = doc.getRootElement().getChildren("auto");
		int total = 0;
		for (Element auto : autos) {
			total += auto.getChildren("name").size();
		}
		System.out.println("Nombre d'interfaces automatiques : " + total);
	}


	/** Afficher le nombre d'interfaces spécifiées. */
	public static void afficherNombreInterfacesSpecifiees(Document doc) {
		List<Element> ifaces = doc.getRootElement().getChildren("iface");
		System.out.println("Nombre d'interfaces spécifiées : " + ifaces.size());
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(Document doc) {
		List<Element> autos = doc.getRootElement().getChildren("auto");
		System.out.print("Interfaces automatiques : ");
		for (Element auto : autos) {
			for (Element name : auto.getChildren("name")) {
				System.out.print(name.getAttributeValue("value") + " ");
			}
		}
		System.out.println();
	}


	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(Document doc) {
		List<Element> ifaces = doc.getRootElement().getChildren("iface");
		System.out.print("Interfaces utilisant la passerelle 147.127.18.200 : ");
		for (Element iface : ifaces) {
			Element inet = iface.getChild("inet");
			if (inet != null) {
				Element stat = inet.getChild("static");
				if (stat != null) {
					Element gw = stat.getChild("gateway");
					if (gw != null && "147.127.18.200".equals(gw.getTextTrim())) {
						System.out.print(iface.getAttributeValue("name") + " ");
					}
				}
			}
		}
		System.out.println();
	}


	/** Afficher les noms des interfaces qui sont définies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(Document doc) {
		Set<String> interfacesAuto = new HashSet<>();
		for (Element auto : doc.getRootElement().getChildren("auto")) {
			for (Element name : auto.getChildren("name")) {
				interfacesAuto.add(name.getAttributeValue("value"));
			}
		}

		List<Element> ifaces = doc.getRootElement().getChildren("iface");
		System.out.print("Interfaces définies mais non automatiques : ");
		for (Element iface : ifaces) {
			String name = iface.getAttributeValue("name");
			if (!interfacesAuto.contains(name)) {
				System.out.print(name + " ");
			}
		}
		System.out.println();
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
