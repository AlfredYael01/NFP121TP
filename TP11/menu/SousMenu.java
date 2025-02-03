package menu;

import editeur.Ligne;

public class SousMenu implements Commande {
    private Menu menu;
    private boolean singleOperation;
    private Ligne ligne;

    public SousMenu(String titre, boolean singleOperation, Ligne ligne) {
        this.menu = new Menu(titre);
        this.singleOperation = singleOperation;
        this.ligne = ligne;
    }

    public void ajouter(String texte, Commande cmd) {
        menu.ajouter(texte, cmd);
    }

    @Override
    public void executer() {
        do {
            System.out.println();
            ligne.afficher();
            System.out.println();
            menu.afficher();
            menu.selectionner();
            menu.valider();
        } while (!singleOperation && !menu.estQuitte());
    }

    @Override
    public boolean estExecutable() {
        return true;
    }
}