package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class spellCheckGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        var map = new HashMap<>();

        var file = new File("src/dictionary.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            map.put(scanner.next(), "");
        }









        String txt = "once upods the time there is a mavn whose nawame is mike he is a male lalala";
        HashSet spellErrors = new HashSet<>();
        String[] words = txt.split(",\\s*|\\s|\\.\\s*");

        var errorLocations = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            if (map.get(words[i]) == null) {
                //   System.out.println(words[i]);
                errorLocations.add(i);
                spellErrors.add(words[i]);
            }

        }
        var errorsLocationsArr =errorLocations.toArray();
        int location= (int)errorsLocationsArr[1];










        HashMap map1 = getChars();
        int[] range=new int[2];
        ArrayList ints = new ArrayList();
        HashMap<Integer,int[]> wordLocation= new HashMap();

        for (var i = 0; i < txt.length(); i++) {
            if (map1.get(txt.charAt(i)) != null) {



//head index of the word
                if (i > 0) {
                    if (map1.get(txt.charAt(i - 1)) == null) {
//                        range[0] = i;
//                        System.out.println(i);
//                        System.out.println(txt.charAt(i));
                        ints.add(i);
                    }
                } else {

//                    range[0] = i;
//                    System.out.println(i);
//                    System.out.println(txt.charAt(i));
                    ints.add(i);

                }


                //tail index of the word
                if (i < txt.length() - 1) {
                    if (map1.get(txt.charAt(i + 1)) == null) {
//                        range[1] = i;
//                        System.out.println(i);
//                        System.out.println(txt.charAt(i));
                        ints.add(i);
                    }

                } else {
//                    range[1] = i;
//                    System.out.println(i);
//                    System.out.println(txt.charAt(i));
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

//        arrayList.txteam().forEach(x-> System.out.println(x[0]+" "+x[1]));

        int [] answer = wordLocation.get(location);
        Arrays.stream(answer).forEach(System.out::println);




        var vBox = new VBox();

        var ta = new TextArea();
        ta.setText(txt);

        ta.selectRange(answer[0],answer[1]);


        vBox.getChildren().add(ta);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();





    }



    private static HashMap getChars() {

        HashMap map2 = new HashMap();

        for (char i = 'A'; i <= 'Z'; i++) {
            map2.put(i, " ");


        }

        for (char i = 'a'; i <= 'z'; i++) {
            map2.put(i, " ");


        }
        char dot = '\'';
        map2.put(dot, " ");
        System.out.println(map2.size());
        return map2;
    }
}
