/**
 * Implémentation d'un ensemble d'entiers ordonné sous forme de liste chaînée triée.
 */
public class EnsembleOrdonneChaine implements EnsembleOrdonne {
    private Cellule tete;
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
    public boolean contient(int x) {
        Cellule courant = tete;
        while (courant != null && courant.valeur <= x) {
            if (courant.valeur == x) {
                return true;
            }
            courant = courant.suivant;
        }
        return false;
    }

    @Override
    public void ajouter(int x) {
        if (!contient(x)) {
            Cellule nouvelle = new Cellule(x, null);
            if (tete == null || tete.valeur > x) { // Insertion en tête
                nouvelle.suivant = tete;
                tete = nouvelle;
            } else { // Insertion à la bonne position
                Cellule courant = tete;
                while (courant.suivant != null && courant.suivant.valeur < x) {
                    courant = courant.suivant;
                }
                nouvelle.suivant = courant.suivant;
                courant.suivant = nouvelle;
            }
            taille++;
        }
    }

    @Override
    public void supprimer(int x) {
        if (tete == null) return;

        if (tete.valeur == x) { // Suppression de la tête
            tete = tete.suivant;
            taille--;
            return;
        }

        Cellule courant = tete;
        while (courant.suivant != null && courant.suivant.valeur != x) {
            courant = courant.suivant;
        }

        if (courant.suivant != null) { // Suppression d'un élément dans la liste
            courant.suivant = courant.suivant.suivant;
            taille--;
        }
    }

    @Override
    public int min() {
        if (estVide()) {
            throw new IllegalStateException("L'ensemble est vide.");
        }
        return tete.valeur;
    }
}
