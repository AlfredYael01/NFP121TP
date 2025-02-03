/**
 * Classe de test pour EnsembleChaine.
 */
public class EnsembleChaineTest extends EnsembleTestAbstrait<Integer> {
	@Override
	protected Ensemble<Integer> nouvelEnsemble(int capacite) {
		return new EnsembleChaine<>();
	}
}

