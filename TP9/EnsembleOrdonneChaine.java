/**
 * Implémentation d'un ensemble ordonné générique sous forme de liste chaînée triée.
 */
public class EnsembleOrdonneChaine<T extends Comparable<T>> implements EnsembleOrdonne<T> {
    private Cellule<T> tete;
    private int taille;

    public EnsembleOrdonneChaine() {
        this.tete = null;
        this.taille = 0;
    }

    @Override
    public int cardinal() {
        return taille;
    }

    @Override
    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public boolean contient(T x) {
        Cellule<T> courant = tete;
        while (courant != null && courant.valeur.compareTo(x) <= 0) {
            if (courant.valeur.equals(x)) {
                return true;
            }
            courant = courant.suivant;
        }
        return false;
    }

    @Override
    public void ajouter(T x) {
        if (!contient(x)) {
            Cellule<T> nouvelle = new Cellule<>(x, null);
            if (tete == null || tete.valeur.compareTo(x) > 0) {
                nouvelle.suivant = tete;
                tete = nouvelle;
            } else {
                Cellule<T> courant = tete;
                while (courant.suivant != null && courant.suivant.valeur.compareTo(x) < 0) {
                    courant = courant.suivant;
                }
                nouvelle.suivant = courant.suivant;
                courant.suivant = nouvelle;
            }
            taille++;
        }
    }

    @Override
    public void supprimer(T x) {
        if (tete == null) return;

        if (tete.valeur.equals(x)) {
            tete = tete.suivant;
            taille--;
            return;
        }

        Cellule<T> courant = tete;
        while (courant.suivant != null && !courant.suivant.valeur.equals(x)) {
            courant = courant.suivant;
        }

        if (courant.suivant != null) {
            courant.suivant = courant.suivant.suivant;
            taille--;
        }
    }

    @Override
    public T min() {
        if (estVide()) {
            throw new IllegalStateException("L'ensemble est vide.");
        }
        return tete.valeur;
    }
}
