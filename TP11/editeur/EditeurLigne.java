package editeur;

import editeur.commande.*;
import menu.Menu;
import menu.SousMenu;
/** Un éditeur pour une ligne de texte.  Les commandes de
 * l'éditeur sont accessibles par un menu.
 *
 * @author	Xavier Crégut
 * @version	1.6
 */
public class EditeurLigne {

	/** La ligne de notre éditeur */
	private Ligne ligne;

	/** Le menu principal de l'éditeur */
	private Menu menuPrincipal;
	// Remarque : Tous les éditeurs ont le même menu mais on
	// ne peut pas en faire un attribut de classe car chaque
	// commande doit manipuler la ligne propre à un éditeur !

	/** Initialiser l'éditeur à partir de la lign à éditer. */
	public EditeurLigne(Ligne l) {
		ligne = l;

		// Créer le menu principal
		menuPrincipal = new Menu("Menu principal");
		// Creer le sous menu
		SousMenu sousMenuCurseur = new SousMenu("Opérations du curseur", false, ligne);
		sousMenuCurseur.ajouter("Avancer le curseur d'un caractère", new CommandeCurseurAvancer(ligne));
		sousMenuCurseur.ajouter("Reculer le curseur d'un caractère", new CommandeCurseurReculer(ligne));
		sousMenuCurseur.ajouter("Placer le curseur en début de ligne", new CommandeDebutLigne(ligne));

		menuPrincipal.ajouter("Opérations du curseur", sousMenuCurseur);
		menuPrincipal.ajouter("Ajouter un texte en fin de ligne", new CommandeAjouterFin(ligne));
		menuPrincipal.ajouter("Supprimer le caractère sélectionné", new CommandeSupprimerCaractereCurseur(ligne));
	}
	public void editer() {
		do {
			// Afficher la ligne
			System.out.println();
			ligne.afficher();
			System.out.println();

			// Afficher le menu
			menuPrincipal.afficher();

			// Sélectionner une entrée dans le menu
			menuPrincipal.selectionner();

			// Valider l'entrée sélectionnée
			menuPrincipal.valider();

		} while (! menuPrincipal.estQuitte());
	}

}
