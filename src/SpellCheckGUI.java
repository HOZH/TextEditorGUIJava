import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class SpellCheckGUI {


    @FXML
    public TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button nextButton, previousButton, replaceButton, exitButton, loadButton;

    //  HashSet spellErrors;
    static String spellCheckTxt = "";
    Object[] errorsLocationsArr;
    HashMap<Integer, int[]> wordLocation;
    int[] answer;
    int currentLocationOnErrorArr;
    int currentLocation;
    ArrayList<Integer> errorLocations = new ArrayList<Integer>();




    public void getNext() {
if(currentLocationOnErrorArr<errorsLocationsArr.length-1){
    //todo 写个到底的限定条件 下面几行可以单独写  晚点reformat 一下
//        if(currentLocationOnErrorArr==0)
//        {
//            currentLocation = (int) errorsLocationsArr[currentLocationOnErrorArr];
//            currentLocationOnErrorArr=currentLocationOnErrorArr+1;
//
//        }else{
    currentLocation = (int) errorsLocationsArr[++currentLocationOnErrorArr];
//    }
    System.out.println("current location is at index"+currentLocation);
    answer = wordLocation.get(currentLocation);
    textArea.selectRange(answer[0], answer[1] + 1);


    //todo 写个可以回来走的
}
    }

    public void getPrevious() {

      if(currentLocationOnErrorArr>0){
          currentLocation = (int) errorsLocationsArr[--currentLocationOnErrorArr];

          System.out.println("current location is at index"+currentLocation);


          answer = wordLocation.get(currentLocation);
          textArea.selectRange(answer[0], answer[1] + 1);
      }

    }

    public void replace() {
        // todo textProperty String replace
        spellCheckTxt.substring(answer[0],answer[1]);
        spellCheckTxt=spellCheckTxt.substring(0,answer[0])+textField.getText()+spellCheckTxt.substring(answer[1]+1);
        System.out.println(demo.ifModified.getValue());
        System.out.println(spellCheckTxt+"123");

        //TextEditorGUI.textArea.setText(spellCheckTxt);
        demo.ifModified.setValue(true);
        System.out.println(demo.ifModified.getValue());
        loadTxt();


    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loadTxt() {
        currentLocationOnErrorArr = -1;
        loadSpellChecker();


    }


    public void loadSpellChecker() {

        spellCheckTxt = TextEditorGUI.currentTxt;
        textArea.setText(spellCheckTxt);


        String[] words = spellCheckTxt.split(",\\s*|\\s|\\.\\s*");


        errorLocations = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            if (demo.dictionary.get(words[i]) == null) {
                //   System.out.println(words[i]);
                errorLocations.add(i);

                System.out.println(i + "index");


            }

        }
        errorsLocationsArr = errorLocations.toArray();

//        System.out.println("that"+errorsLocationsArr[0]);
//        System.out.println("this"+errorsLocationsArr[1]);


       // currentLocationOnErrorArr = 0;
        //fixme no sure if need this line under
//        currentLocation = (int) errorsLocationsArr[currentLocationOnErrorArr];
        System.out.println(currentLocation);


        HashMap possibleChars = getChars();
//        possibleChars.keySet().stream().forEach(System.out::print);


        int[] range = new int[2];
        ArrayList ints = new ArrayList();
        wordLocation = new HashMap();

        for (var i = 0; i < spellCheckTxt.length(); i++) {
            if (possibleChars.get(spellCheckTxt.charAt(i)) != null) {


//head index of the word
                if (i > 0) {
                    if (possibleChars.get(spellCheckTxt.charAt(i - 1)) == null) {

                        ints.add(i);
                    }
                } else {
                    ints.add(i);


                }

                if (i < spellCheckTxt.length() - 1) {
                    if (possibleChars.get(spellCheckTxt.charAt(i + 1)) == null) {

                        ints.add(i);
                    }

                } else {

                    ints.add(i);
                }

            }


        }

        ints.stream().forEach(System.out::println);
        System.out.println(ints.stream().count());


        Iterator iter = ints.iterator();
        int count = 0;
        for (int i = 0; i < ints.size() / 2; i++) {

            range[0] = (int) iter.next();
            range[1] = (int) iter.next();
            wordLocation.put(count++, range);
            range = new int[2];

        }
        System.out.println(wordLocation.size());

//        System.out.println("-----------------------------------aa");
//
//        wordLocation.keySet().stream().forEach(System.out::println);
//
//        System.out.println("-----------------------------------aa");
        //
        Iterator iter4399=wordLocation.entrySet().iterator();
        System.out.println(iter4399.next().getClass().getName());
        System.out.println(Arrays.toString(wordLocation.get(0)));
        System.out.println(Arrays.toString(wordLocation.get(1)));
        System.out.println(Arrays.toString(wordLocation.get(2)));
        System.out.println(Arrays.toString(wordLocation.get(3)));
        //
        System.out.println("----------------------------------bbb");


        answer = wordLocation.get(0);

        System.out.println("000000");
        for(int i=0;i<wordLocation.size();i++)
        {
            System.out.println(Arrays.toString(wordLocation.get(i)));
        }

        System.out.println("000000");
//        System.out.println(answer[0] + "aa" + answer[1]);

        //fixme this part is bug free


    }

    private HashMap getChars() {

        HashMap map2 = new HashMap();

        for (char i = 'A'; i <= 'Z'; i++) {
            map2.put(i, " ");


        }

        for (char i = 'a'; i <= 'z'; i++) {
            map2.put(i, " ");


        }
        char dot = '\'';
        map2.put(dot, " ");
//        System.out.println(map2.size());
//        System.out.println("-------------------------------------");
//        map2.keySet().stream().forEach(System.out::println);

//        System.out.println("-------------------------------------");

        return map2;
    }


}
