package tests;

public class wordIndex {
    public static void main(String[] args) {
        var str = "once upon the time";

        var strs = str.split(",\\s*|\\s|\\.\\s*");

        var firstNonSpace = 0;
        System.out.println(getFirstNon0(str));


//        Arrays.stream(strs).forEach(System.out::println);

    }

    /*
    first non blank Index
     */
    public static int getFirstNon0(String str) {
        int firstV = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                firstV++;
            else break;


        }

        return firstV;
    }
}
