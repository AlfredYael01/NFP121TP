package afficheur;

import java.awt.Color;

public class AfficheurTexte {

    public AfficheurTexte()  {
    }

    public String afficherTextePoint(double vx, double vy, Color vcouleur) {
        return "Point{ \n x = " + vx + "\n vy = " + vy + "\n couleur = " + vcouleur + "\n }";
    }

    public String afficherTexteSegment(double vx1, double vy1, double vx2, double vy2, Color vcouleur) {
        return "Segment{ \n x1 = " + vx1 + "\n y1 = " + vy1 + "\n x2 = " + vx2 + "\n y2 = " + vy2 + "\n couleur = " + vcouleur + "\n }";
    }

    public String afficherTexteCercle(double vcentre_x, double vcentre_y, double vrayon, Color vcouleur){
        return "Cercle{ \n centre_x = " + vcentre_x + "\n centre_y = " + vcentre_y + "\n rayon = " + vrayon + "\n couleur = " + vcouleur + "\n }";
    }
}
