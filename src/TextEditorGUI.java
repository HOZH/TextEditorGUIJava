import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author hz
 */
public class TextEditorGUI {

    public static String currentTxt = "";
    @FXML
    MenuBar menuBar;
    @FXML
    Menu fileMenu, viewMenu, editMenu;
    @FXML
    MenuItem newButton, openButton, closeButton, saveButton, exitButton;
    @FXML
    MenuItem wordCountButton, sentenceCountButton, fleschScoreButton, performanceButton;
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
    private PrintWriter printWriter;
//    private String=


    /*
    vars for view menu using mainly
     */
    private String splitKey = ",\\s*|\\s+|\\.\\s*|!\\s*|\\?\\s*|\"\\s*|”\\s*|“\\s*|’\\s*";
    private int wordCount;
    private int sentenceCount;
    private int syllableCount;
    private double fleschScore;
    private String statusMsg;
    private String record;

    private String clipBoard;
    private HashSet filterKey = new HashSet();

    /*
    for markov using
     */
    private int inputLength;
    private String inputString;
    private HashSet uniqueWords = new HashSet();
    private LinkedList masterList = new LinkedList();

    private Iterator<String> iterator;
    private ListIterator iter1 = new ListIterator(masterList);
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
        var spellCheckStage = new Stage();
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


    }

    /**
     * set words count
     */
    private void wordC() {
        var words = currentTxt;
        var strs = words.trim().split(splitKey);
        strs = getWords(strs);

        wordCount = (int) Arrays.stream(strs).count();

    }

    /**
     * set sentences count
     */
    private void sentenceC() {
        var words = currentTxt;
        sentenceCount = words.split("!|\\.|\\?").length;

    }

    /**
     * set syllables count
     */
    private void syllableC() {

        var words = currentTxt;
        var syllables = "[aeiouyAEIOUY]+";


        var pattern = Pattern.compile(syllables);

        var matcher = pattern.matcher(words);
        var count = 0;

        while (matcher.find()) {
            count++;
        }


        var strs = words.split(splitKey);
        strs = getWords(strs);


        var silent = Arrays.stream(strs).map(x -> {
            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {

                return null;

            }
            return x;
        }).filter(Objects::isNull).count();

        var syllablesNum = count - (int) silent;
        syllableCount = syllablesNum;
    }

    /**
     * @return time used in millisecond for text analyzing with 3 loops
     * @see TextEditorGUI#wordC();
     * @see TextEditorGUI#sentenceC()
     * @see TextEditorGUI#syllableC()
     */
    private long analyzeWith3Loops() {
        var timeConsumed = System.currentTimeMillis();
        wordC();
        sentenceC();
        syllableC();
        fleschScore = 206.835 - 1.015 * ((double) wordCount / (double) sentenceCount) - 84.6 * ((double) syllableCount / (double) wordCount);
        fleschScore = Math.round(fleschScore * 1000.0) / 1000.0;

        timeConsumed = System.currentTimeMillis() - timeConsumed;
        return timeConsumed;


    }

    /**
     * @return time used in millisecond for text analyzing with single loop
     */
    public long analyze() {

        var timeConsumed = System.currentTimeMillis();
        var words = currentTxt;
        var syllables = "[aeiouyAEIOUY]+";

        var pattern = Pattern.compile(syllables);


        var counter = new int[1];

        var strs = words.trim().split(splitKey);
        strs = getWords(strs);
        var silent = Arrays.stream(strs).map(x -> {

            var count = 0;

            var matcher = pattern.matcher(x);


            while (matcher.find()) {
                count++;
            }
            counter[0] = counter[0] + count;


            if (x.length() >= 3
                    &&
                    x.substring(0, x.length() - 2).matches("[AEIOUaeiouyY]+")
                    && ((x.endsWith("e") && x.charAt(x.length() - 2) != 'l') || x.endsWith("es") || x.endsWith("ed"))) {

                return null;

            }
            return x;
        }).filter(Objects::isNull).count();

        var syllablesNum = counter[0] - (int) silent;

        syllableCount = syllablesNum;
        wordCount = (int) Arrays.stream(strs).count();
        sentenceCount = words.split("!|\\.|\\?").length;
        fleschScore = 206.835 - 1.015 * ((double) wordCount / (double) sentenceCount) - 84.6 * ((double) syllableCount / (double) wordCount);
        fleschScore = Math.round(fleschScore * 1000.0) / 1000.0;
        timeConsumed = System.currentTimeMillis() - timeConsumed;
        return timeConsumed;

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
        var extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
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

    /**
     * close current editing， wipe the textArea and set it non-editable
     */
    public void closeFile() {
        textArea.setText("");
        currentFile = null;
        statusBar.setText("");
        textArea.setEditable(false);


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
        textArea.setText("");
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
        final var dialog = new Stage();

        var dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Random String Generator"));


        var add = new Button("submit");
        var generate = new Button("generate");
        var textField = new TextField();


        textField.setPromptText("input the key word here (String)");
        var textField1 = new TextField();
        textField1.setPromptText("input the length here (positive integer)");
        generate.setOnAction(event -> {


            var startTime = System.currentTimeMillis();

            generateMarkov();
            System.out.println(System.currentTimeMillis() - startTime + " millisecond used");

        });
        add.setOnAction(event1 -> {
            try {
                inputString = textField.getText();
                inputLength = Integer.valueOf(textField1.getText());

                formMarkov();
            } catch (NumberFormatException ex) {
                textField1.setText("please input a positive integer here");
            }


        });
        dialogVbox.getChildren().addAll(generate, textField, textField1, add);


        var dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /**
     * mapping the words into markov
     */

    private void generateMarkov() {


        uniqueWords = new HashSet();
        var masterList = new LinkedList();
        iter1 = new ListIterator(masterList);
        iter2 = new ListIterator(masterList);


        var words = currentTxt;
        System.out.println(currentTxt);
        var strs = words.split(splitKey);
        strs = getWords(strs);

        Arrays.stream(strs).forEach(x -> uniqueWords.add(x));

        iterator = uniqueWords.iterator();
        while (iterator.hasNext()) {
            masterList.insetLast(new Link(iterator.next()));

        }
        iter1 = new ListIterator(masterList);

        while (!iter1.isNull()) {

            var temp = iter1.next();
            var key = (String) temp.getdata();
            for (var i = 0; i < strs.length - 1; i++) {
                if (strs[i].equals(key) && strs[i] != null) {
                    temp.getBabies().insetLast(new Link(strs[i + 1]));
                }
            }
        }
        iter1.reset();


    }


    /**
     * set the textArea text to the random string that form by markov function
     */
    private void formMarkov() {

        iter1.reset();
        iter2.reset();
        System.out.println(inputLength);
        System.out.println(inputString);
        var result = inputString + " ";
        var textLength = 1;
        String answer = null;
        var isQualified = true;

        while (!iter1.isNull() && isQualified) {
            if (inputString.equals((String) iter1.next().getdata())) {

                answer = randomWord(iter2.next());

                if (answer == null) {
                    break;
                }

                result = result + answer + " ";
                inputString = answer;
                iter1.reset();
                iter2.reset();
                textLength++;
                System.out.println(textLength);
                System.out.println(answer);
                if (textLength >= inputLength) {
                    System.out.println(textLength);
                    isQualified = false;
                    textLength = 1;
                }


            } else {

                iter2.next();


            }

        }
        System.out.println(textLength);
        System.out.println(answer);
        textArea.setText(result);
    }

    /**
     * @param key: root link
     * @return word in the root link and followed by its adjacent word
     */

    private String randomWord(Link key) {
        if (key.getBabies().getFirst() == null) {
            return null;
        }
        var lalala = (String) key.getBabies().getFirst().getdata();
        var iter = new ListIterator(key.getBabies());
        var num = random.nextInt(100);
        for (var i = 0; i < num; i++) {
            if (iter.atEnd()) {
                iter.reset();
            } else {
                iter.next();
            }
        }

        var returnValue = (String) iter.next().getdata();
        return returnValue;


    }


    /**
     * listener for spell checker, sending back the modified data from spell check to the text editor
     */
    private void afterExitSpellCheckStage() {
        demo.ifModified.addListener(e -> {
            textArea.setText(SpellCheckGUI.spellCheckTxt);
            demo.ifModified.setValue(false);


        });
    }


    /**
     * compare the performances differences in calculating the numbers of sentences, words,
     * and syllables in one loop versus in three separate loops of 4 different lengths of words, then record them
     * into a .txt file in the outputData subfolder in the project folder.
     * considering it only deal with single case, it's partially hard coded
     *
     * @throws FileNotFoundException
     * @see TextEditorGUI#analyze()
     * @see TextEditorGUI#analyzeWith3Loops()
     * @see TextEditorGUI#svtHelper(int)
     * @see TextEditorGUI#getLineChart(int[][], int[][])
     */
    public void singleLoopVSThreeLoops() throws FileNotFoundException {
        var singleLoopTime = 0;
        var threeLoopsTime = 0;

        var singleLoopData = new int[4][2];
        var threeLoopsData = new int[4][2];


        currentTxt = svtHelper(100);
        var strBuilder = new StringBuilder();
        singleLoopTime = (int) analyze();
        threeLoopsTime = (int) analyzeWith3Loops();
        analyzeWith3Loops();
        var timeUsed = "single loop cost " + singleLoopTime + " millisecond on " + 100 + " words and three loops cost " + threeLoopsTime + " millisecond.";
        strBuilder.append(timeUsed);
        strBuilder.append("\n");

        singleLoopData[0][0] = 100;
        threeLoopsData[0][0] = 100;
        singleLoopData[0][1] = singleLoopTime;
        threeLoopsData[0][1] = threeLoopsTime;


        currentTxt = svtHelper(1000);
        singleLoopTime = (int) analyze();
        threeLoopsTime = (int) analyzeWith3Loops();

        timeUsed = "single loop cost " + singleLoopTime + " millisecond on " + 1000 + " words and three loops cost " + threeLoopsTime + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");

        singleLoopData[1][0] = 1000;
        threeLoopsData[1][0] = 1000;
        singleLoopData[1][1] = singleLoopTime;
        threeLoopsData[1][1] = threeLoopsTime;

        currentTxt = svtHelper(10000);
        singleLoopTime = (int) analyze();
        threeLoopsTime = (int) analyzeWith3Loops();

        timeUsed = "single loop cost " + singleLoopTime + " millisecond on " + 10000 + " words and three loops cost " + threeLoopsTime + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");

        singleLoopData[2][0] = 10000;
        threeLoopsData[2][0] = 10000;
        singleLoopData[2][1] = singleLoopTime;
        threeLoopsData[2][1] = threeLoopsTime;

        currentTxt = svtHelper(100000);
        singleLoopTime = (int) analyze();
        threeLoopsTime = (int) analyzeWith3Loops();
        timeUsed = "single loop cost " + singleLoopTime + " millisecond on " + 100000 + " words and three loops cost " + threeLoopsTime + " millisecond.";

        strBuilder.append(timeUsed);
        strBuilder.append("\n");

        singleLoopData[3][0] = 100000;
        threeLoopsData[3][0] = 100000;
        singleLoopData[3][1] = singleLoopTime;
        threeLoopsData[3][1] = threeLoopsTime;

        var thePrintWriter = new PrintWriter("src/outputData/3LoopsVS1Loop.txt");
        thePrintWriter.write(strBuilder.toString());
        thePrintWriter.flush();

        getLineChart(singleLoopData, threeLoopsData);


    }

    /**
     * get a line chart that shows performance differences between single loop and three loops
     *
     * @param series1 int[][] for data of single loop performance
     * @param series2 int[][] for data of three loop performance
     */
    private void getLineChart(int[][] series1, int[][] series2) {

        var xAxis = new NumberAxis();
        var yAxis = new NumberAxis();
        xAxis.setLabel("amount of words");
        yAxis.setLabel("time used in milliseconds");

        XYChart xyChart = new LineChart(xAxis, yAxis);

        var singleLoop = new XYChart.Series();
        var threeLoops = new XYChart.Series();

        singleLoop.setName("singleLoop");

        for (var i = 0; i < series1.length; i++) {
            singleLoop.getData().add(new XYChart.Data<>(series1[i][0], series1[i][1]));
            threeLoops.getData().add(new XYChart.Data<>(series2[i][0], series2[i][1]));

        }

        threeLoops.setName("threeLoops");


        var vBox = new VBox();
        vBox.getChildren().add(xyChart);

        var chartScene = new Scene(vBox);

        var chartStage = new Stage();

        chartStage.setScene(chartScene);
        chartStage.show();


        xyChart.getData().addAll(singleLoop, threeLoops);

    }

    /**
     * helper method
     *
     * @param length limited length of string
     * @return pre-determined length string from warAndPeace
     * @see TextEditorGUI#singleLoopVSThreeLoops()
     */
    private String svtHelper(int length) throws FileNotFoundException {
        var limitingAgent = length;
        var txtUsing = "";
        var theFile = new File("src/inputData/warAndPeace.txt");
        var theScanner = new Scanner(theFile);
        var theInputTxt = new StringBuilder();


        while (theScanner.hasNext()) {

            theInputTxt.append(theScanner.next());
            theInputTxt.append("\n");


        }

        var possibleChars = SpellCheckGUI.getChars();


        var ints = new ArrayList();


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

        var endingIndex = (int) ints.get(length * 2 - 1);

        txtUsing = theInputTxt.toString().substring(0, endingIndex);

        return txtUsing;


    }

    /**
     * @param beforeFilter the words collection before filter
     * @return the words collection after filter
     */
    public String[] getWords(String[] beforeFilter) {
        var filterKetString = "!?,.:;'’’'”“\"@#$%^&*()_-+=";

        filterKey.addAll(Arrays.stream(filterKetString.split("")).collect(Collectors.toList()));

        Predicate<String> someCondition1 = x -> !filterKey.contains(x);

        var afterFilter = Arrays.stream(beforeFilter).filter(someCondition1).map(Object::toString).toArray(String[]::new);

        return afterFilter;


    }


}
