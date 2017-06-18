/**
 * Created by Dave on 6/17/17.
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class IterateOverArrayList {
    public static void main(String[] args) {

        List<String> myArrayList = new ArrayList<>();
        myArrayList.add("first");
        myArrayList.add("second");
        myArrayList.add("third");
        myArrayList.add("fourth");
        myArrayList.add("fifth");

        // iterate with for loop
        System.out.println("For Loop");
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }

        // iterate with for-each loop
        System.out.println("\nFor-Each Loop");
        for (String s: myArrayList) {
            System.out.println(s);
        }

        // iterate with iterator
        System.out.println("\nIterator");
        Iterator<String> iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // iterate with stream new in java 8
        System.out.println("\nStream");
        myArrayList.forEach((temp) -> {
            System.out.println(temp);
        });
    }
}
