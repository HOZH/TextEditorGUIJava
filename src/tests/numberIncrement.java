package tests;

public class numberIncrement {
    public static void main(String[] args) {
        var i = 5;

        for (int j = 0; j < 5; j++) {
            System.out.println(++i);
            System.out.println(i);
            System.out.println("--");
        }
        System.out.println(i);

        for (int j = 0; j < 5; j++) {
            System.out.println(--i);
            System.out.println(i);
            System.out.println("--");
        }


    }
}
