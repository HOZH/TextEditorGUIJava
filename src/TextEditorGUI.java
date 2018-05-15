import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hz
 */
public class TextEditorGUI {

    @FXML
    MenuBar menuBar;

    @FXML
    Menu fileMenu, viewMenu, editMenu;

    @FXML
    MenuItem newButton, openButton, closeButton, saveButton, exitButton;

    @FXML
    MenuItem wordCountButton, sentenceCountButton, fleschScoreButton;

    @FXML
    MenuItem copyButton, cutButton, deleteButton, pasteButton, markovButton, spellCheckButton;

    @FXML
    TextArea textArea;

    @FXML
    TextField statusBar;

    private File currentFile;
    private File tempFile;
    private FileChooser fileChooser = new FileChooser();
    private Scanner scanner;
    private StringBuilder inputTxt;
    public static String currentTxt = "";
    private PrintWriter printWriter;
//    private String=


    /*
    vars for view menu using mainly
     */
    private int wordCount;
    private int sentenceCount;
    private int syllableCount;
    private double fleschScore;
    private String statusMsg;
    private String record;

    private String clipBoard;


    /*
    for markov using
     */
    private int inputLength;
    private String inputString;
    private HashSet uniqueWords = new HashSet();
    private LinkedList masterList = new LinkedList();

    private Iterator<String> iterator;
    private ListIterator iter1 = new ListIterator(masterList);
    ;
    private ListIterator iter2 = new ListIterator(masterList);
    private Random random = new Random();

    /*
    for spell check using
     */

    /**
     * Pop up a new stage for spell checker
     *
     * @throws IOException
     */
    private void popUpSpellCheckWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SpellCheckGUI.fxml"));
        Stage spellCheckStage = new Stage();
        spellCheckStage.setTitle("Spell Checker");
        spellCheckStage.setScene(new Scene(root));
        spellCheckStage.show();
    }

    /**
     * main function for spell checker
     *
     * @throws IOException
     */
    public void spellCheck() throws IOException {
        popUpSpellCheckWindow();
        System.out.println("the size of dictionary is" + demo.dictionary.size());


    }

    /**
     * load .txt file into the editor
     *
     * @throws FileNotFoundException
     */

    private void load() throws FileNotFoundException {
        scanner = new Scanner(currentFile);
        inputTxt = new StringBuilder();

        while (scanner.hasNextLine()) {
            inputTxt.append(scanner.nextLine());
            inputTxt.append("\n");
        }
        textArea.setWrapText(true);
        textArea.setText(inputTxt.toString());
        currentTxt = inputTxt.toString();

//        textArea.appendText(inputTxt.toString());

    }

    /**
     * set words count
     */
    private void wordC() {
        String words = currentTxt;
        String[] strs = words.trim().split(",\\s*|\\s+|\\.\\s*");
        wordCount = (int) Arrays.stream(strs).count();

    }

    /**
     * set sentences count
     */
    private void sentenceC() {
        String words = currentTxt;
        sentenceCount = words.split("!|\\.|\\?").length;

    }

    /**
     * set syllables count
     */
    private void syllableC() {

        String words = currentTxt;
        String syllables = "[aeiouyAEIOUY]+";


        Pattern pattern = Pattern.compile(syllables);

        Matcher matcher = pattern.matcher(words);
        int count = 0;

        while (matcher.find()) {
            count++;
        }


        String[] strs = words.split(",\\s*|\\s|\\.\\s*");


        long silent = Arrays.stream(strs).map(x -> {
            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {
                //   System.out.println(x);
                return null;

            }
            return x;
        }).filter(Objects::isNull).count();

        int syllablesNum = count - (int) silent;
        syllableCount = syllablesNum;
    }

    /**
     * @return time used in millisecond for text analyzing with 3 loops
     * @see TextEditorGUI#wordC();
     * @see TextEditorGUI#sentenceC()
     * @see TextEditorGUI#syllableC()
     */
    public long analyzeWith3Loops() {
        var timeConSumed = System.currentTimeMillis();
        wordC();
        sentenceC();
        syllableC();
        fleschScore = 206.835 - 1.015 * ((double) wordCount / (double) sentenceCount) - 84.6 * ((double) syllableCount / (double) wordCount);
        fleschScore = Math.round(fleschScore * 1000.0) / 1000.0;

        timeConSumed = System.currentTimeMillis() - timeConSumed;
        return timeConSumed;


    }

    /**
     * @return time used in millisecond for text analyzing with single loop
     */
    public long analyze() {

        var timeConSumed = System.currentTimeMillis();
        String words = currentTxt;
        String syllables = "[aeiouyAEIOUY]+";

        Pattern pattern = Pattern.compile(syllables);

        Matcher matcher = pattern.matcher(words);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        String[] strs = words.trim().split(",\\s*|\\s+|\\.\\s*");
        long silent = Arrays.stream(strs).map(x -> {
            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {
                //   System.out.println(x);
                return null;

            }
            return x;
        }).filter(Objects::isNull).count();

        int syllablesNum = count - (int) silent;

        syllableCount = syllablesNum;
        wordCount = (int) Arrays.stream(strs).count();
        sentenceCount = words.split("!|\\.|\\?").length;
        fleschScore = 206.835 - 1.015 * ((double) wordCount / (double) sentenceCount) - 84.6 * ((double) syllableCount / (double) wordCount);
        fleschScore = Math.round(fleschScore * 1000.0) / 1000.0;
        timeConSumed = System.currentTimeMillis() - timeConSumed;
        return timeConSumed;

    }

    /**
     * fire a on-fly inspector text status
     */
    private void onFlyInspector() {
        // textArea.focusedProperty()
        textArea.textProperty().addListener((obs, oldValue, newValue) -> {

            if (oldValue != null) {

                System.out.println(textArea.getText());
                currentTxt = textArea.getText();
                analyze();
                statusMsg = "Found " + sentenceCount + " sentences and " + wordCount + " words and the flesch score is " + fleschScore;
                statusBar.setText(statusMsg);
                System.out.println(statusMsg);
            }

        });
    }

    /**
     * create a temp file that can be save later and set the main textArea to editable
     *
     * @throws IOException
     */

    public void newFile() throws IOException {
        textArea.setEditable(true);

        tempFile = File.createTempFile("tempfile", "txt");
        currentFile = tempFile;
        onFlyInspector();
        afterExitSpellCheckStage();
    }

    /**
     * main function for the fileChooser
     */
    private void fileChooserWindow() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        currentFile = fileChooser.showOpenDialog(new Stage());
    }


    /**
     * open an existing .txt file and load it to the editor
     *
     * @throws FileNotFoundException
     */
    public void openFile() throws FileNotFoundException {
        textArea.setEditable(true);


        fileChooserWindow();
        System.out.println("once upon the time");
        load();
        analyze();
        onFlyInspector();
        afterExitSpellCheckStage();


    }
    //fixme still need to implement the top edit menu

    /**
     * close current editing， wipe the textArea and set it non-editable
     */
    public void closeFile() throws FileNotFoundException {
        textArea.setText("");
        currentFile = null;
        statusBar.setText("");
        textArea.setEditable(false);
//        singleLoopVSThreeLoops();

    }

    /**
     * for temp file: open the file chooser and let user choose the dir to save
     * for existing file: save the modified text back to the original file
     *
     * @throws FileNotFoundException
     */
    public void saveFile() throws FileNotFoundException {
        if (currentFile == tempFile) {
            fileChooserWindow();
        }

        currentFile.getPath();

        printWriter = new PrintWriter(currentFile);
        printWriter.write(currentTxt);
        System.out.println(currentTxt);
        printWriter.flush();
        var singleLoop = analyze();
        var threeLoops = analyzeWith3Loops();

        var stringBuilder = new StringBuilder();
        stringBuilder.append(currentFile.getName());
        var length = stringBuilder.length();
        var record = "record";
        var position = (int) length - 4;
        System.out.println(length - 4);
        stringBuilder.insert(position, record);

        var rawPath = new StringBuilder();

        rawPath.append("src/outputData/");

        rawPath.append(stringBuilder);

        var output = new File(rawPath.toString());


        printWriter = new PrintWriter(output);

        record = "single loop cost " + singleLoop + " millisecond on " + wordCount + " words and three loops cost " + threeLoops + " millisecond.";
        printWriter.write(record);
        printWriter.flush();


    }

    /**
     * close the program
     */
    public void exitFile() {

        System.exit(0);
    }

    /**
     * counter fire by button in the view menu
     */
    public void wordCounter() {
        statusBar.setText("Found " + wordCount + " words in the txt");
    }

    /**
     * counter fire by button in the view menu
     */
    public void sentenceCounter() {
        statusBar.setText("Found " + sentenceCount + " sentences in the txt");
    }

    /**
     * counter fire by button in the view menu
     */
    public void fleschScoreGetter() {
        statusBar.setText("The flesch score of this txt is " + fleschScore);
    }

    /**
     * fire by button in the edit menu, copy the whole text in the textArea
     */
    public void copy() {
        clipBoard = textArea.getText();
        statusBar.setText("copied");
    }

    /**
     * fire by button in the edit menu, cut the whole text into a temporary clipboard
     */

    public void cut() {
        clipBoard = textArea.getText();
        textArea.setText("");
        statusBar.setText("cut");
    }

    /**
     * fire by button in the edit menu, delete text in the textArea
     */
    public void delete() {
        statusBar.setText("deleted");
    }

    /**
     * fire by button in the edit menu, wipe the current context and paste the text that store in the clipboard into the textarea
     */
    public void paste() {
        textArea.setText(clipBoard);
        statusBar.setText("pasted");

    }

    /**
     * main function for markov
     */

    public void markov() {
        popUp();


    }

    /**
     * pop up a single stage for markov
     */

    private void popUp() {
        final Stage dialog = new Stage();
        // dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Random String Generator"));


        Button add = new Button("submit");
        Button generate = new Button("generate");
        TextField textField = new TextField();


        textField.setPromptText("input the key word here (String)");
        TextField textField1 = new TextField();
        textField1.setPromptText("input the length here (positive integer)");
        generate.setOnAction(event -> {
//            try{

            var startTime = System.currentTimeMillis();
            formMarkov();
            System.out.println(System.currentTimeMillis() - startTime + " millisecond used");
//            }
//            catch(NumberFormatException ex)
//            {
//                textField1.setText("please input a positive integer here");
//            }
        });
        add.setOnAction(event1 -> {
            try {
                inputString = textField.getText();
                inputLength = Integer.valueOf(textField1.getText());
//                formMarkov();
                generateMarkov();
            } catch (NumberFormatException ex) {
                textField1.setText("please input a positive integer here");
            }


        });
        dialogVbox.getChildren().addAll(generate, textField, textField1, add);


        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /**
     * set the textArea text to the random string that form by markov function
     */

    private void formMarkov() {

        String words = currentTxt;
        System.out.println(currentTxt);
        String[] strs = words.split(",\\s*|\\s|\\.\\s*");

        Arrays.stream(strs).forEach(x -> uniqueWords.add(x));

        iterator = uniqueWords.iterator();
        while (iterator.hasNext()) {
            masterList.insetLast(new Link(iterator.next()));

        }
        iter1 = new ListIterator(masterList);

        while (!iter1.isNull()) {

            Link temp = iter1.next();
            String key = (String) temp.getdata();
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].equals(key) && strs[i] != null) {
                    temp.getBabies().insetLast(new Link(strs[i + 1]));
                }
            }
        }
        iter1.reset();


    }

    /**
     * mapping the words into markov
     */

    public void generateMarkov() {

        iter1.reset();
        iter2.reset();
        System.out.println(inputLength);
        System.out.println(inputString);
        String result = inputString + " ";
        int textLength = 1;
        String answer;
        var isQualified = true;

        while (!iter1.isNull() && isQualified) {
            if (inputString.equals((String) iter1.next().getdata())) {

                answer = randomWord(iter2.next());
                result = result + answer + " ";
                inputString = answer;
                iter1.reset();
                iter2.reset();
                textLength++;
                if (textLength >= inputLength) {
                    isQualified = false;
                    textLength = 1;
                }


            } else {

                iter2.next();


            }

        }
        textArea.setText(result);
    }

    /**
     * @param key: root link
     * @return word in the root link and followed by its adjacent word
     */

    public String randomWord(Link key) {

        String lalala = (String) key.getBabies().getFirst().getdata();
        ListIterator iter = new ListIterator(key.getBabies());
        int num = random.nextInt(100);
        for (int i = 0; i < num; i++) {
            if (iter.atEnd()) {
                iter.reset();
            } else {
                iter.next();
            }
        }

        String returnValue = (String) iter.next().getdata();
        return returnValue;


    }

//    public void afterExitSpellCheckerStage(){
//
////        textArea.setText(SpellCheckGUI.spellCheckTxt);
//        demo.ifModified.addListener();
//        if(demo.ifModified)
//        {
//            textArea.setText(SpellCheckGUI.spellCheckTxt);
//            demo.ifModified=false;
//        }
//
//    }


    /**
     * listener for spell checker, sending back the modified data from spell check to the text editor
     */
    public void afterExitSpellCheckStage() {
        demo.ifModified.addListener(e -> {
            textArea.setText(SpellCheckGUI.spellCheckTxt);
            demo.ifModified.setValue(false);
//            textArea.setText(demo.ifModified.getValue().toString());

        });
    }

//    private void onFlyInspector() {
//        // textArea.focusedProperty()
//        textArea.textProperty().addListener((obs, oldValue, newValue) -> {
//
//            if (oldValue != null) {
//
//                System.out.println(textArea.getText());
//                currentTxt = textArea.getText();
//                analyze();
//                statusMsg = "Found " + sentenceCount + " sentences and " + wordCount + " words and the flesch score is " + fleschScore;
//                statusBar.setText(statusMsg);
//                System.out.println(statusMsg);
//            }
//
//        });
//    }


    /**
     * compare the performances differences in calculating the numbers of sentences, words,
     * and syllables in one loop versus in three separate loops of 4 different lengths of words, then record them
     * into a .txt file in the outputData subfolder in the project folder.
     *
     * @throws FileNotFoundException
     * @see TextEditorGUI#analyze()
     * @see TextEditorGUI#analyzeWith3Loops()
     * @see TextEditorGUI#svtHelper(int)
     */
    public void singleLoopVSThreeLoops() throws FileNotFoundException {
        currentTxt = svtHelper(100);
        var strBuilder = new StringBuilder();
        var timeUsed = "single loop cost " + analyze() + " millisecond on " + 100 + " words and three loops cost " + analyzeWith3Loops() + " millisecond.";
        strBuilder.append(timeUsed);
        strBuilder.append("\n");


        currentTxt = svtHelper(1000);
        timeUsed = "single loop cost " + analyze() + " millisecond on " + 1000 + " words and three loops cost " + analyzeWith3Loops() + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");
        currentTxt = svtHelper(10000);
        timeUsed = "single loop cost " + analyze() + " millisecond on " + 10000 + " words and three loops cost " + analyzeWith3Loops() + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");
        currentTxt = svtHelper(100000);
        timeUsed = "single loop cost " + analyze() + " millisecond on " + 100000 + " words and three loops cost " + analyzeWith3Loops() + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");

        var thePrintWriter = new PrintWriter("src/outputData/3LoopsVS1Loop.txt");
        thePrintWriter.write(strBuilder.toString());
        thePrintWriter.flush();


    }

    /**
     * helper method
     *
     * @param length limited length of string
     * @return pre-determined length string from warAndPeace
     * @see TextEditorGUI#singleLoopVSThreeLoops()
     */
    public String svtHelper(int length) throws FileNotFoundException {
        var limitingAgent = length;
        var txtUsing = "";
        var theFile = new File("src/inputData/warAndPeace.txt");
        var theScanner = new Scanner(theFile);
        var theInputTxt = new StringBuilder();


        while (theScanner.hasNext()) {

            theInputTxt.append(theScanner.next());
            theInputTxt.append("\n");


        }

        HashMap possibleChars = SpellCheckGUI.getChars();



        ArrayList ints = new ArrayList();


        for (var i = 0; i < theInputTxt.length(); i++) {
            if (possibleChars.get(theInputTxt.charAt(i)) != null) {


                if (i > 0) {
                    if (possibleChars.get(theInputTxt.charAt(i - 1)) == null) {

                        ints.add(i);
                    }
                } else {
                    ints.add(i);


                }

                if (i < theInputTxt.length() - 1) {
                    if (possibleChars.get(theInputTxt.charAt(i + 1)) == null) {

                        ints.add(i);
                    }

                } else {

                    ints.add(i);
                }

            }


        }

      int endingIndex=(int)ints.get(length*2-1);

       txtUsing= theInputTxt.toString().substring(0,endingIndex);

       return txtUsing;



    }


}
