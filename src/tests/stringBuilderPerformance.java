package tests;

public class stringBuilderPerformance {
    public static void main(String[] args) {

        var stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        System.out.println(stringBuilder);
        stringBuilder.insert(0, "2");
        System.out.println(stringBuilder);
        String charr = "34";
        var chars = charr.toCharArray();
//                charr.split(",");
        System.out.println(chars.getClass().getName());
        stringBuilder.insert(1, chars, 0, 1);
        System.out.println(stringBuilder);
        String str = stringBuilder.toString();
        System.out.println(str);

    }
}
