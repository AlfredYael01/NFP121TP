import org.jdom2.*;
import org.jdom2.output.*;

/** Construire l'exemple de fichier /etc/network/interfaces donné dans le sujet.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public class ConstruireInterfacesSujet {

	public static void main(String[] args) throws java.io.IOException {
		// Construire le document
		Element racine = new Element("interfaces");

		// auto lo
		// ... TODO ...

		Document document = new Document(racine, new DocType("interfaces",
					"interfaces.dtd"));

		// Afficher le document
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, System.out);
	}

}
