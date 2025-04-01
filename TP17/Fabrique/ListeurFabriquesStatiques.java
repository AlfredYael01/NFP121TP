import java.util.*;
import java.lang.reflect.*;

class ListeurFabriquesStatiques implements Listeur {
    @Override
    public List<Method> getMethodes(String nomClass) throws Exception {
        List<Method> methodsFabricates = new ArrayList<>();
        Class<?> classe = Class.forName(nomClass);
        for (Method method : classe.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) &&
                    method.getReturnType().equals(classe) &&
                    //Arrays.stream(method.getParameterTypes()).noneMatch(param -> param.equals(classe))
                    !Arrays.asList(method.getParameterTypes()).contains(classe)
            )
            {
                methodsFabricates.add(method);
            }
        }

        return methodsFabricates;
    }
}