package editeur.commande;
import editeur.Ligne;
import util.Console;
public class DebutLigne extends CommandeLigne
{
    public DebutLigne(Ligne l)
    {
        super(l);
    }

    public void executer(){
        ligne.raz();
    }

    public boolean estExecutable() {
        return ligne.getCurseur() < ligne.getLongueur();
    }

}
