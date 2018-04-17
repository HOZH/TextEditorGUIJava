import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

public class SpellCheckGUI {


    @FXML
    public TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button nextButton,previousButton,replaceButton,exitButton,loadButton;

    HashSet spellErrors;
    String spellCheckTxt="";
    Integer[] errorsLocationsArr;
    HashMap<Integer, int[]> wordLocation;
    int[] answer;


    public void getNext(){
        textArea.selectRange(answer[0],answer[1]+1);
    }

    public void getPrevious(){}

    public void replace(){}

    public void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loadTxt(){
        loadSpellChecker();

    }



    public void loadSpellChecker() {

//        spellCheckTxt =TextEditorGUI.currentTxt;
//        textArea.setText(spellCheckTxt);
//
//        spellErrors = new HashSet<>();
//        String[] words = spellCheckTxt.split(",\\s*|\\s|\\.\\s*");
//
////        ArrayList<Integer> errorLocations = new ArrayList<>();
////        for (int i = 0; i < words.length; i++) {
////            if (demo.dictionary.get(words[i]) == null) {
////                //   System.out.println(words[i]);
////                errorLocations.add(i);
////                spellErrors.add(words[i]);
////            }
////
////        }
////        errorsLocationsArr =  errorLocations.toArray();
//        var errorLocations = new ArrayList<Integer>();
//        for (int i = 0; i < words.length; i++) {
//            if (demo.dictionary.get(words[i]) == null) {
//                //   System.out.println(words[i]);
//                errorLocations.add(i);
//                spellErrors.add(words[i]);
//            }
//
//        }
//        var errorsLocationsArr =errorLocations.toArray();
//
//
//        int currentLocationOnErrorArr=0;
//        int currentLocation=(int)errorsLocationsArr[currentLocationOnErrorArr];
//
//
//        HashMap possibleChars = getChars();
//        int[] range = new int[2];
//        LinkedHashSet<Integer> ints = new LinkedHashSet();
//        wordLocation = new HashMap();
//
//        for (var i = 0; i < spellCheckTxt.length(); i++) {
//            if (possibleChars.get(spellCheckTxt.charAt(i)) != null) {
//
//
////head index of the word
//                if (i > 0) {
//                    if (possibleChars.get(spellCheckTxt.charAt(i - 1)) == null) {
//
//                        ints.add(i);
//                    }
//                } else {
//
//
//                }
//
//                if (i < spellCheckTxt.length() - 1) {
//                    if (possibleChars.get(spellCheckTxt.charAt(i + 1)) == null) {
//
//                        ints.add(i);
//                    }
//
//                } else {
//
//                    ints.add(i);
//                }
//
//            }
//
//
//        }
//
//
//        Iterator iter = ints.iterator();
//        int count = 0;
//        for (int i = 0; i < ints.size() / 2; i++) {
//
//            range[0] = (int) iter.next();
//            range[1] = (int) iter.next();
//            wordLocation.put(count++, range);
//            range = new int[2];
//
//        }
//
//
//        answer = wordLocation.get(currentLocation);
//
//




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
