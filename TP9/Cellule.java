/**
 * Représente une cellule d'une liste chaînée contenant un entier.
 */
public class Cellule<T> {
    T valeur;
    Cellule<T> suivant;

    public Cellule(T valeur, Cellule<T> suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }
}
