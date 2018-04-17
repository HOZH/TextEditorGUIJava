package tests;

import java.util.*;

public class wordIndex2 {
    public static void main(String[] args) {
        var str = "o'nce upon the time, there";
        HashMap map = getChars();
        int[] range=new int[2];
        LinkedHashSet<Integer>ints = new LinkedHashSet();
        HashMap<Integer,int[]> wordLocation= new HashMap();

        for (var i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) != null) {



//head index of the word
                if (i > 0) {
                    if (map.get(str.charAt(i - 1)) == null) {
//                        range[0] = i;
//                        System.out.println(i);
//                        System.out.println(str.charAt(i));
                        ints.add(i);
                    }
                } else {

//                    range[0] = i;
//                    System.out.println(i);
//                    System.out.println(str.charAt(i));
                    ints.add(i);

                }


                //tail index of the word
                if (i < str.length() - 1) {
                    if (map.get(str.charAt(i + 1)) == null) {
//                        range[1] = i;
//                        System.out.println(i);
//                        System.out.println(str.charAt(i));
                        ints.add(i);
                    }

                } else {
//                    range[1] = i;
//                    System.out.println(i);
//                    System.out.println(str.charAt(i));
                    ints.add(i);
                }

            }



        }
        System.out.println(ints.size());

        Iterator iter = ints.iterator();
        int count=0;
        for(int i = 0; i<ints.size()/2;i++){

            range[0]=(int)iter.next();
            range[1]=(int)iter.next();
            System.out.println(range[0]+ " "+ range[1]);
            wordLocation.put(count++,range);
            range=new int[2];
//            System.out.println((int)iter.next());
//            System.out.println((int)iter.next());
        }

//        arrayList.stream().forEach(x-> System.out.println(x[0]+" "+x[1]));

        int [] answer = wordLocation.get(1);
        Arrays.stream(answer).forEach(System.out::println);










    }


    private static HashMap getChars() {

        HashMap map = new HashMap();

        for (char i = 'A'; i <= 'Z'; i++) {
            map.put(i, " ");


        }

        for (char i = 'a'; i <= 'z'; i++) {
            map.put(i, " ");


        }
        char dot = '\'';
        map.put(dot, " ");
        System.out.println(map.size());
        return map;
    }
}
