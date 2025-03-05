import java.util.List;

/**
 * Quelques outils (méthodes) sur les listes.
 */
public class Outils {

    /**
     * Retourne vrai ssi tous les éléments de la collection respectent le critère.
     */
    public static <E> boolean tous(
            List<E> elements,
            Critere<E> critere) {
        for (E element : elements) {
            if (!critere.satisfaitSur(element)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * Ajouter dans resultat tous les éléments de la source
     * qui satisfont le critère aGarder.
     */
    public static <E> void filtrer(
            List<E> source,
            Critere<E> aGarder,
            List<E> resultat) {
        // TODO : à corriger
    }

}
