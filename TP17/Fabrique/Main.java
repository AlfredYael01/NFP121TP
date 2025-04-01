import java.util.*;
import java.lang.reflect.*;

class Main {
	public static String toString(List<?> liste) {
		String resultat = "";
		for (Object o : liste) {
			resultat += "\n - " + o;
		}
		return resultat;
	}

	public static void main(String[] args) throws Exception {
		Listeur listeur = new ListeurFabriquesStatiques();
		String nom = args.length > 0 ?  args[0] : "C";
		System.out.println("Fabriques statiques de " + nom + " : "
				+ toString(listeur.getMethodes(nom)));
	}
}
