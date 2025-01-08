import afficheur.Ecran;
import afficheur.AfficheurTexte;
import afficheur.AfficheurSVG;
import java.awt.Color;
/** Construire le schéma proposé dans le sujet de TP avec des points,
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchema1 {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args) {
		//Creer un ecran de 600x400
		//Ecran ecran = new Ecran("ExempleSchema1", 600, 400, 20);
		// Créer les trois segments
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new Point(sx / 3, sy / 3);

		// Afficher le schéma
		/*System.out.println("Le schéma est composé de : ");
		s12.afficher();
		System.out.println();
		s23.afficher();
		System.out.println();
		s31.afficher();
		System.out.println();
		p1.dessiner(ecran);
		p2.dessiner(ecran);
		p3.dessiner(ecran);
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);


		// Translater les segments et le barrycentre
		s12.translater(4, -3);
		s23.translater(4, -3);
		s31.translater(4, -3);
		barycentre.translater(4, -3);

		//afficher  le schema
		//la translation permet de deplacer les points et les segments pour  les afficher dans une autre position
		//ainsi on peut voir les points et les segments dans une autre position
		System.out.println("Le schéma après translation est composé de : ");
		s12.afficher();
		System.out.println();
		s23.afficher();
		System.out.println();
		s31.afficher();
		System.out.println();
		p1.dessiner(ecran);
		p2.dessiner(ecran);
		p3.dessiner(ecran);
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
*/
		//affichage en svg
		AfficheurSVG svg = new AfficheurSVG("SchemaSVG","Ceci est un schema en svg", 600, 400);

		svg.dessinerPoint(p1.getX(), p1.getY(), Color.BLACK);
		svg.dessinerPoint(p2.getX(), p2.getY(), Color.BLACK);
		svg.dessinerPoint(p3.getX(), p3.getY(), Color.BLACK);
		svg.dessinerLigne(p1.getX(), p1.getY(), p2.getX(), p2.getY(), Color.BLACK);
		svg.dessinerLigne(p2.getX(), p2.getY(), p3.getX(), p3.getY(), Color.BLACK);
		svg.dessinerLigne(p3.getX(), p3.getY(), p1.getX(), p1.getY(), Color.BLACK);
		svg.dessinerTexte(barycentre.getX(), barycentre.getY(), "Barycentre", Color.BLACK);
		svg.ecrire("schema.svg");

		//afficher les textes des points, des segments et du cercle
		AfficheurTexte afficheurTexte = new AfficheurTexte();
		System.out.println(afficheurTexte.afficherTextePoint(p1.getX(), p1.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTextePoint(p2.getX(), p2.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTextePoint(p3.getX(), p3.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTexteSegment(p1.getX(), p1.getY(), p2.getX(), p2.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTexteSegment(p2.getX(), p2.getY(), p3.getX(), p3.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTexteSegment(p3.getX(), p3.getY(), p1.getX(), p1.getY(), Color.BLACK));
		System.out.println(afficheurTexte.afficherTexteCercle(barycentre.getX(), barycentre.getY(), 3, Color.BLACK));
	}

}
