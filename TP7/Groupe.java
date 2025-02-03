import java.util.ArrayList;
import java.util.List;
import afficheur.Afficheur;

public class Groupe extends FormeGeometrique {
    private List<FormeGeometrique> elements;

    public Groupe() {
        this.elements = new ArrayList<>();
    }

    public void ajouter(FormeGeometrique element) {
        elements.add(element);
    }

    @Override
    public void afficher() {
        System.out.println("Groupe contenant " + elements.size() + " éléments :");
        for (FormeGeometrique element : elements) {
            element.afficher();
            System.out.println();
        }
    }

    @Override
    public void translater(double dx, double dy) {
        for (FormeGeometrique element : elements) {
            element.translater(dx, dy);
        }
    }

    @Override
    public void dessiner(Afficheur afficheur) {
        for (FormeGeometrique element : elements) {
            element.dessiner(afficheur);
        }
    }
}
