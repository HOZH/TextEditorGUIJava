import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author hz
 */
public class SpellCheckGUI {


    public static String spellCheckTxt = "";
    @FXML
    public TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    Button nextButton, previousButton, replaceButton, exitButton, loadButton;
    ArrayList<Integer> errorLocations = new ArrayList<Integer>();
    private Object[] errorsLocationsArr;
    private HashMap<Integer, int[]> wordLocation;
    private int[] answer;
    private int currentLocationOnErrorArr;
    private int currentLocation;

    /**
     * @return a hashMap that contains all the letters and ,
     */
    public static HashMap getChars() {

        var map2 = new HashMap();

        for (var i = 'A'; i <= 'Z'; i++) {
            map2.put(i, " ");


        }

        for (var i = 'a'; i <= 'z'; i++) {
            map2.put(i, " ");


        }
        var dot = '\'';
        map2.put(dot, " ");


        return map2;
    }

    /**
     * get to the next incorrect spelling word
     */
    public void getNext() {
        if (currentLocationOnErrorArr < errorsLocationsArr.length - 1) {
            currentLocation = (int) errorsLocationsArr[++currentLocationOnErrorArr];
            answer = wordLocation.get(currentLocation);
            textArea.selectRange(answer[0], answer[1] + 1);

        }
    }

    /**
     * get to the previous miss spelling word
     */
    public void getPrevious() {

        if (currentLocationOnErrorArr > 0) {
            currentLocation = (int) errorsLocationsArr[--currentLocationOnErrorArr];
            answer = wordLocation.get(currentLocation);
            textArea.selectRange(answer[0], answer[1] + 1);
        }

    }

    /**
     * replace selected word with string from textField
     */
    public void replace() {

        spellCheckTxt.substring(answer[0], answer[1]);
        spellCheckTxt = spellCheckTxt.substring(0, answer[0]) + textField.getText() + spellCheckTxt.substring(answer[1] + 1);


        demo.ifModified.setValue(true);
        loadTxt();


    }

    /**
     * exit the spell checker
     */
    public void exit() {
        var stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * main function of spell checker
     *
     * @see SpellCheckGUI#loadSpellChecker()
     */
    public void loadTxt() {
        currentLocationOnErrorArr = -1;
        loadSpellChecker();


    }

    /**
     * fire a spell checker
     *
     * @see SpellCheckGUI#getChars()
     */
    public void loadSpellChecker() {

        spellCheckTxt = TextEditorGUI.currentTxt;
        textArea.setText(spellCheckTxt);


        var words = spellCheckTxt.trim().split(",\\s*|\\s+|\\.\\s*");


        errorLocations = new ArrayList<Integer>();
        for (var i = 0; i < words.length; i++) {
            if (demo.dictionary.get(words[i]) == null) {

                errorLocations.add(i);
                System.out.println(i + "index");


            }

        }
        errorsLocationsArr = errorLocations.toArray();

        var possibleChars = getChars();


        var range = new int[2];
        var ints = new ArrayList();
        wordLocation = new HashMap();

        for (var i = 0; i < spellCheckTxt.length(); i++) {
            if (possibleChars.get(spellCheckTxt.charAt(i)) != null) {


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


        var iter = ints.iterator();
        var count = 0;
        for (int i = 0; i < ints.size() / 2; i++) {

            range[0] = (int) iter.next();
            range[1] = (int) iter.next();
            wordLocation.put(count++, range);
            range = new int[2];

        }

        answer = wordLocation.get(0);


    }


}
