/**
 * Représente une cellule d'une liste chaînée contenant un entier.
 */
public class Cellule {
    int valeur;
    Cellule suivant;

    public Cellule(int valeur, Cellule suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }
}
