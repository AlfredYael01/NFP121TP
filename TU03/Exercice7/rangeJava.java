//transformer range de python en java exemple : for n in range(2, 11, 3): print(n)
import java.util.ArrayList;

public class rangeJava {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 2; i < 11; i += 3) {
            list.add(i);
        }
        System.out.println(list);
    }
}

