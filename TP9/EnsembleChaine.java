/**
 * Implémentation d'un ensemble générique sous forme de liste chaînée.
 */
public class EnsembleChaine<T> implements Ensemble<T> {
    private Cellule<T> tete;
    private int taille;

    public EnsembleChaine() {
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
        while (courant != null) {
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
            tete = new Cellule<>(x, tete);
            taille++;
        }
    }

    @Override
    public void supprimer(T x) {
        Cellule<T> courant = tete, precedent = null;

        while (courant != null && !courant.valeur.equals(x)) {
            precedent = courant;
            courant = courant.suivant;
        }

        if (courant != null) { // Élement trouvé
            if (precedent == null) { // Suppression de la tête
                tete = tete.suivant;
            } else {
                precedent.suivant = courant.suivant;
            }
            taille--;
        }
    }
}
