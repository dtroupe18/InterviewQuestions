/**
 * Created by Dave on 6/17/17.
 */
public class SomeClass {
    public static void main(String[] args) {
        System.out.println(Cat.numberOfLegs);

        Cat kitten = new Cat();

        if (kitten instanceof Cat) {

        }
    }
}


class Cat {
    public static int numberOfLegs = 4;
}