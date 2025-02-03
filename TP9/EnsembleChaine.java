/**
 * Implémentation d'un ensemble d'entiers avec une liste chaînée.
 */
public class EnsembleChaine implements Ensemble {
    private Cellule tete;
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
    public boolean contient(int x) {
        Cellule courant = tete;
        while (courant != null) {
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
            tete = new Cellule(x, tete);
            taille++;
        }
    }

    @Override
    public void supprimer(int x) {
        Cellule courant = tete, precedent = null;

        while (courant != null && courant.valeur != x) {
            precedent = courant;
            courant = courant.suivant;
        }

        if (courant != null) {
            if (precedent == null) {
                tete = tete.suivant;
            } else {
                precedent.suivant = courant.suivant;
            }
            taille--;
        }
    }
}
