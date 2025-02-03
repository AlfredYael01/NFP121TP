/**
 * Interface définissant un ensemble ordonné générique.
 */
public interface EnsembleOrdonne<T extends Comparable<T>> extends Ensemble<T> {
    /** Retourne le plus petit élément de l'ensemble.
     * @return l'élément minimal, ou une exception si l'ensemble est vide.
     */
    T min();
}
