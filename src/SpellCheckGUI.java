import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
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
    String spellCheckTxt = "";
    Object[] errorsLocationsArr;
    HashMap<Integer, int[]> wordLocation;
    int[] answer;
    int currentLocationOnErrorArr;
    int currentLocation;
    ArrayList<Integer> errorLocations = new ArrayList<Integer>();




    public void getNext() {
//todo 写个到底的限定条件 下面几行可以单独写  晚点reformat 一下
        currentLocation = (int) errorsLocationsArr[currentLocationOnErrorArr++];
        answer = wordLocation.get(currentLocation);
        textArea.selectRange(answer[0], answer[1] + 1);


        //todo 写个可以回来走的
    }

    public void getPrevious() {

    }

    public void replace() {
        // todo textProperty String replace
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loadTxt() {
        currentLocationOnErrorArr = 0;
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

        System.out.println(errorsLocationsArr[0]);
        System.out.println(errorsLocationsArr[1]);


       // currentLocationOnErrorArr = 0;
        currentLocation = (int) errorsLocationsArr[currentLocationOnErrorArr];
        System.out.println(currentLocation);


        HashMap possibleChars = getChars();
        possibleChars.keySet().stream().forEach(System.out::print);


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


        answer = wordLocation.get(currentLocation);
        System.out.println(answer[0] + "aa" + answer[1]);

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
        System.out.println(map2.size());
        return map2;
    }


}
