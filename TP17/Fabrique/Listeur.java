import java.util.*;
import java.lang.reflect.*;

interface Listeur {
	java.util.List<Method> getMethodes(String nomClasse) throws Exception;
}
