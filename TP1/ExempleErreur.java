/** Une erreur à la compilation !
  * Pourquoi ?
  * @author	Xavier Crégut
  * @version	1.3
  */
public class ExempleErreur {

	/** Méthode principale */
	public static void main(String[] args) {
		// ajout des arguments necessaires
		Point p1 = new Point(1,2);
		//suppression des arguments
		//p1.setX(1);
		//p1.setY(2);
		p1.afficher();
		System.out.println();
	}

}
