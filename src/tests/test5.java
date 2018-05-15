package tests;

public class test5 {
    public static void main(String[] args) {

        System.out.println(a(2));

    }

    public static int a(int x) {
        if (x > 20)
            return -3;
        else
            return a(x * 3) + x / 3;
    }
}
