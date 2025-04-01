package allumettes;

/** Exception qui indique que la configuration d'une partie est incorrecte.
 * @author	Xavier CrÃ©gut
 * @version	1.4
 */
public class ConfigurationException extends RuntimeException {

	/** Initaliser une ConfigurationException avec le message prÃ©cisÃ©.
	  * @param message le message explicatif
	  */
	public ConfigurationException(String message) {
		super(message);
	}

}
