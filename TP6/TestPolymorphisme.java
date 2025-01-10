/** Tester le polymorphisme (principe de substitution) et la liaison
 * dynamique.
 */
public class TestPolymorphisme {

	/** Méthode principale */
	public static void main(String[] args) {
		// Créer et afficher un point p1
		Point p1 = new Point(3, 4);  // Autorisé : on crée un objet Point
		p1.translater(10, 10);       // Méthode translater de Point est appelée
		System.out.print("p1 = ");
		p1.afficher();
		System.out.println();        // Affiche : (13.0, 14.0)

		// Créer et afficher un point nommé pn1
		PointNomme pn1 = new PointNomme(30, 40, "PN1");  // Autorisé : on crée un objet PointNomme
		pn1.translater(10, 10);       // Méthode translater de Point est appelée via héritage
		System.out.print("pn1 = ");
		pn1.afficher();
		System.out.println();        // Affiche : PN1:(40.0, 50.0)

		// Définir une poignée sur un point
		Point q;

		// Attacher un point à q et l'afficher
		q = p1;                      // Autorisé : p1 est un Point
		System.out.println("> q = p1;");
		System.out.print("q = ");
		q.afficher();
		System.out.println();        // Affiche : (13.0, 14.0)

		// Attacher un point nommé à q et l'afficher
		q = pn1;                     // Autorisé : pn1 est un PointNomme, une sous-classe de Point
		System.out.println("> q = pn1;");
		System.out.print("q = ");
		q.afficher();
		System.out.println();        // Affiche : PN1:(40.0, 50.0)

		// Définir une poignée sur un point nommé
		PointNomme qn;

		// Attacher un point à q et l'afficher
		//qn = p1;          // Est-ce autorisé ? Pourquoi ? pas autorisé car p1 n'est pas un point nommé
		qn = (PointNomme) p1;  // Non autorisé à l'exécution : p1 n'est pas un PointNomme
		System.out.println("> qn = p1;");
		System.out.print("qn = "); qn.afficher(); System.out.println();

		// Attacher un point nommé à qn et l'afficher
		qn = pn1;          // Est-ce autorisé ? Pourquoi ? Oui car pn1 est un point nommé
		System.out.println("> qn = pn1;");
		System.out.print("qn = "); qn.afficher(); System.out.println();

		double d1 = p1.distance(pn1);  // Autorisé : distance(Point autre) accepte un PointNomme
		System.out.println("distance = " + d1);

		double d2 = pn1.distance(p1);  // Autorisé : distance(Point autre) accepte un Point
		System.out.println("distance = " + d2);

		double d3 = pn1.distance(pn1); // Autorisé : les deux sont PointNomme
		System.out.println("distance = " + d3);

		System.out.println("> qn = q;");
		// qn = q;              // Est-ce autorisé ? Pourquoi ? Non car q est un point et qn un point nommé
		qn = (PointNomme) q;      // autorisé car q est un point nommé + cast de q en point nommé
		System.out.print("qn = "); qn.afficher(); System.out.println();

		System.out.println("> qn = (PointNomme) q;");
		qn = (PointNomme) q;      // Est-ce autorisé ? Pourquoi ? Oui car q est un point nommé
		System.out.print("qn = "); qn.afficher(); System.out.println();

		System.out.println("> qn = (PointNomme) p1;");
		qn = (PointNomme) p1;      // Est-ce autorisé ? Pourquoi ? Oui car p1 est un point nommé
		System.out.print("qn = "); qn.afficher(); System.out.println();
	}
}