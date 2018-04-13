package tests;

public class doubleInt {
    public static void main(String[] args) {
        int i = 3;
        int j = 2;

        double k = (double) i / (double) j;
        System.out.println(k);


        double l = 3.141592657;
        l = Math.round(l * 1000.0) / 1000.0;
        System.out.println(l);

//        double roundOff = Math.round( l* 100.0) / 100.0;
//        System.out.println(roundOff);


    }
}
