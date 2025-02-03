import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test JUnit pour les ensembles génériques.
 *  @author Xavier Crégut
 */
abstract public class EnsembleTestAbstrait<T extends Comparable<T>> {

	protected Ensemble<T> e1;

	private T[] tab0; // Pour faire les tests élémentaires

	@Before
	public void setUp() throws Exception {
		tab0 = (T[]) new Comparable[]{(T) Integer.valueOf(10), (T) Integer.valueOf(15), (T) Integer.valueOf(-5)};
		this.e1 = nouvelEnsemble(10);
	}

	abstract protected Ensemble<T> nouvelEnsemble(int capacite);

	static <T> void ajouterTous(Ensemble<T> ens, T... elements) {
		for (T n : elements) {
			ens.ajouter(n);
		}
	}

	@Test
	public void testInitVide() {
		assertNotNull(e1);
		assertTrue(e1.estVide());
		assertEquals(0, e1.cardinal());
	}

	@Test
	public void testAjouterPremier() {
		assertTrue(e1.estVide());
		e1.ajouter((T) Integer.valueOf(10));
		assertFalse(e1.estVide());
		assertEquals(1, e1.cardinal());
		assertTrue(e1.contient((T) Integer.valueOf(10)));
	}

	@Test
	public void testAjouterPlusieursFois() {
		assertTrue(e1.estVide());
		e1.ajouter((T) Integer.valueOf(10));
		e1.ajouter((T) Integer.valueOf(10));
		e1.ajouter((T) Integer.valueOf(10));
		assertFalse(e1.estVide());
		assertEquals(1, e1.cardinal());
		assertTrue(e1.contient((T) Integer.valueOf(10)));
	}

	@Test
	public void testAjouterTroisElements() {
		e1.ajouter((T) Integer.valueOf(10));
		e1.ajouter((T) Integer.valueOf(15));
		e1.ajouter((T) Integer.valueOf(-5));
		assertEquals(3, e1.cardinal());
		assertTrue(e1.contient((T) Integer.valueOf(10)));
		assertTrue(e1.contient((T) Integer.valueOf(-5)));
		assertTrue(e1.contient((T) Integer.valueOf(15)));
	}

	@Test
	public void testContient() {
		e1.ajouter((T) Integer.valueOf(10));
		e1.ajouter((T) Integer.valueOf(15));
		e1.ajouter((T) Integer.valueOf(-5));
		assertEquals(3, e1.cardinal());
		assertTrue(e1.contient((T) Integer.valueOf(10)));
		assertTrue(e1.contient((T) Integer.valueOf(-5)));
		assertTrue(e1.contient((T) Integer.valueOf(15)));
		assertFalse(e1.contient((T) Integer.valueOf(1)));
	}

	@Test
	public void testSupprimerAbsent() {
		ajouterTous(e1, tab0);
		assertFalse(e1.contient((T) Integer.valueOf(1)));
		assertEquals(3, e1.cardinal());
		e1.supprimer((T) Integer.valueOf(1));
		assertEquals(3, e1.cardinal());
	}

	@Test
	public void testSupprimerPresent() {
		ajouterTous(e1, tab0);
		assertEquals(3, e1.cardinal());
		assertTrue(e1.contient((T) Integer.valueOf(10)));
		e1.supprimer((T) Integer.valueOf(10));
		assertFalse(e1.contient((T) Integer.valueOf(10)));
		assertEquals(2, e1.cardinal());
	}

	@Test
	public void testSupprimerDifferentsCas() {
		ajouterTous(e1, tab0);
		e1.supprimer((T) Integer.valueOf(10));
		assertFalse(e1.contient((T) Integer.valueOf(10)));
		e1.supprimer((T) Integer.valueOf(-5));
		assertFalse(e1.contient((T) Integer.valueOf(-5)));
		assertTrue(e1.contient((T) Integer.valueOf(15)));
		e1.supprimer((T) Integer.valueOf(15));
		assertEquals(0, e1.cardinal());
		assertTrue(e1.estVide());
	}
}
