import java.util.*;

public class Annuaire{
    private Map<String, String> personnelsVersBureau;
    private Map<String, Set<String>> bureauVersPersonnels;

    public Annuaire(){
        this.personnelsVersBureau = new HashMap<String, String>();
        this.bureauVersPersonnels = new HashMap<String, Set<String>>();
    }
    //1 Enregistrer un nouveau personnel en indiquant son bureau

    public void EnregistrerNouveauPersonnel(String personnel, String bureau){
        if (personnelsVersBureau.containsKey(personnel)){
            throw new IllegalArgumentException("Personnel déjà enregistré");
        }
        personnelsVersBureau.put(personnel, bureau);
        bureauVersPersonnels.add(personnel);
    }
    //2 Modifier le bureau d’un personnel

    public void ModifierBureauPersonnel(String personnel, String nouveauBureau){
        string AncienBureau = personnelsVersBureau.get(personnel);
        //supprime son bureau
        bureauVersPersonnels.get(AncienBureau).remove(personnel);
        if (bureauVersPersonnels.get(AncienBureau).isEmpty()){
            bureauVersPersonnels.remove(AncienBureau);
        }
        personnelsVersBureau.put(personnel, nouveauBureau);
        bureauVersPersonnels.add(personnel);
    }

    //3 Obtenir le bureau occupé par le personnel

    public void BureauOccupéParPersonnel(String personnel){
        return personnelsVersBureau.get(personnel);
    }

    //4 Enregistrer le départ d’un personnel

    public void enregistrerDepartPersonnel(string personnel){
        string bureau = personnelsVersBureau.get(personnel);
        personnelsVersBureau.remove(personnel);
        bureauVersPersonnels.get(bureau).remove(personnel);
        if (bureauVersPersonnels.get(bureau).isEmpty()){
            bureauVersPersonnels.remove(bureau);
        }
    }

    //5 Obtenir tous les personnels de l’organisation (qui occupent un bureau)

    public void ObtenirPersonnelsAvecBureau(String personnel){
        return personnelsVersBureau.keySet();
    }

    //6 Obtenir tous les bureaux de l’organisation (qui sont occupés par au moins un personnel)

    public void ObtenirBureauAvecPersonnel(String personnel){
        return bureauVersPersonnels.keySet();
    }

    //7 Obtenir tous les personnels occupant un bureau donné (pas d’affichage)

    public void ObtenirPersonnelsOccupantBureau(String bureau){
        return bureauVersPersonnels.get(bureau);
    }

    //8 Produire le listing qui affiche dans le terminal l’affectation des personnels. Sur un ligne, on voit le nom du personnel suivi de son bureau.

    public void ListingAffectationPersonnel(){
        for (String personnel : personnelsVersBureau.keySet()){
            System.out.println(personnel + " : " + personnelsVersBureau.get(personnel));
        }
    }

    //9 Obtenir l’occupation des bureaux (pas d’affichage)

    public void ObtenirOccupationBureaux(){
        return bureauVersPersonnels;
    }
    //  Produire le listing d’occupation des bureaux qui affiche dans le terminal le bureau suivi des personnels qui l’occupe

    public void ListingOccupationBureaux(){
        for (String bureau : bureauVersPersonnels.keySet()){
            System.out.println(bureau + " : \n" + bureauVersPersonnels.get(bureau));
        }
    }
}