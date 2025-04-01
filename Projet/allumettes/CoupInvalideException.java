package allumettes;

/** Exception qui indique qu'un coup invalide est jouÃ©.
 * @author	Xavier CrÃ©gut
 * @version	$Revision: 1.3 $
 */
public class CoupInvalideException extends Exception {

	/** Le coup jouÃ©. */
	private int coup;

	/** ProblÃ¨me identifiÃ©. */
	private String probleme;

	/** Initialiser CoupInvalideException Ã  partir du coup jouÃ©
	 * et du problÃ¨me identifiÃ©.  Par exemple, on peut avoir coup
	 * qui vaut 0 et le problÃ¨me "< 1".
	 * @param coup le coup jouÃ©
	 * @param probleme le problÃ¨me identifiÃ©
	 */
	public CoupInvalideException(int coup, String probleme) {
		super("Coup invalide car " + probleme + " : " + coup);
		this.coup = coup;
		this.probleme = probleme;
	}

	/** Retourner le coup.
	  * @return le coup */
	public int getCoup() {
		return this.coup;
	}

	/** Indiquer le problÃ¨me.
	  * @return le problÃ¨me */
	public String getProbleme() {
		return this.probleme;
	}

}
