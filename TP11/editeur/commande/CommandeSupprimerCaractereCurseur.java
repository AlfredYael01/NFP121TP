package editeur.commande;
import editeur.Ligne;
public class CommandeSupprimerCaractereCurseur extends CommandeLigne{

    public CommandeSupprimerCaractereCurseur(Ligne l){
        super(l);
    }

    public void executer(){
        ligne.supprimer();
    }

    public boolean estExecutable() {
        return ligne.getCurseur() < ligne.getLongueur();
    }
}
