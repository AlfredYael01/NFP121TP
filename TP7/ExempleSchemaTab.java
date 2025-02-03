import afficheur.Ecran;

public class ExempleSchemaGroupe {
	public static void main(String[] args) {
		// Créer les points et segments
		Point p1 = new PointNomme(3, 2, "A");
		Point p2 = new PointNomme(6, 9, "S");
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new PointNomme(sx / 3, sy / 3, "C");

		// Définir des groupes
		Groupe groupeSegments = new Groupe();
		groupeSegments.ajouter(s12);
		groupeSegments.ajouter(s23);
		groupeSegments.ajouter(s31);

		Groupe groupePrincipal = new Groupe();
		groupePrincipal.ajouter(groupeSegments);
		groupePrincipal.ajouter(barycentre);

		// Afficher le schéma
		System.out.println("Affichage du groupe principal :");
		groupePrincipal.afficher();

		// Créer l’écran et dessiner le schéma
		Ecran ecran = new Ecran("ExempleSchemaGroupe", 600, 400, 20);
		ecran.dessinerAxes();
		groupePrincipal.dessiner(ecran);

		// Translater le groupe principal
		System.out.println("Translater le groupe principal de (4, -3) :");
		groupePrincipal.translater(4, -3);

		// Afficher le schéma après translation
		System.out.println("Affichage après translation :");
		groupePrincipal.afficher();
		groupePrincipal.dessiner(ecran);
		ecran.rafraichir();
	}
}
