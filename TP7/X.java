import java.awt.Color;
import afficheur.Afficheur;

public abstract class FormeGeometrique {
    protected Color couleur;

    public FormeGeometrique() {
        this.couleur = Color.green;
    }

    public abstract void afficher();
    public abstract void translater(double dx, double dy);
    public abstract void dessiner(Afficheur afficheur);

    public Color getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }
}
