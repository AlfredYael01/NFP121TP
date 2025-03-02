package TPMorpion.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/** Programmation d'un jeu de Morpion avec une interface graphique Swing.
 *
 * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué !
 * On a un modèle (?), une vue et un contrôleur qui sont fortement liés.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.4 $
 */

public class MorpionSwing {

	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images = new HashMap<>();
	static {
		images.put(ModeleMorpion.Etat.VIDE, new ImageIcon("blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND, new ImageIcon("rond.jpg"));
	}

	private ModeleMorpionSimple modele;
	private JFrame fenetre;
	private JLabel[][] cases = new JLabel[3][3];
	private JLabel joueur;

	// Le constructeur
	// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this.modele = new ModeleMorpionSimple();
		initUI();
	}

	/** Initialiser l'interface utilisateur */
	private void initUI() {
		fenetre = new JFrame("Morpion");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(new BorderLayout());

		// Création de la grille du jeu
		JPanel grille = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cases[i][j] = new JLabel(images.get(ModeleMorpion.Etat.VIDE));
				cases[i][j].setPreferredSize(new Dimension(100, 100));
				int x = i, y = j;
				cases[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						jouer(x, y);
					}
				});
				grille.add(cases[i][j]);
			}
		}

		// Zone affichant le joueur actuel et bouton de redémarrage
		joueur = new JLabel(images.get(modele.getJoueur()));
		JButton restart = new JButton("Nouvelle Partie");
		restart.addActionListener(e -> recommencer());

		JPanel panel = new JPanel();
		panel.add(joueur);
		panel.add(restart);

		fenetre.add(grille, BorderLayout.CENTER);
		fenetre.add(panel, BorderLayout.SOUTH);

		fenetre.pack();
		fenetre.setVisible(true);
	}

	// Quelques réactions aux interactions de l'utilisateur
	// ----------------------------------------------------

	/** Jouer un coup en (x, y) */
	private void jouer(int x, int y) {
		try {
			modele.cocher(x, y);
			cases[x][y].setIcon(images.get(modele.getValeur(x, y)));
			if (modele.estGagnee()) {
				JOptionPane.showMessageDialog(fenetre, "Le joueur " + modele.getJoueur() + " a gagné !");
				recommencer();
			} else if (modele.estTerminee()) {
				JOptionPane.showMessageDialog(fenetre, "Match nul !");
				recommencer();
			} else {
				joueur.setIcon(images.get(modele.getJoueur()));
			}
		} catch (CaseOccupeeException e) {
			JOptionPane.showMessageDialog(fenetre, "Case déjà occupée !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Recommencer une nouvelle partie. */
	private void recommencer() {
		modele.recommencer();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cases[i][j].setIcon(images.get(ModeleMorpion.Etat.VIDE));
			}
		}
		joueur.setIcon(images.get(modele.getJoueur()));
	}

	// La méthode principale
	// ---------------------

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MorpionSwing::new);
	}
}