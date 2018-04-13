import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class performanceTestForProject {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/inputData/warAndPeace.txt");
        Scanner scanner;
        String poem = "";
        String[] poemArr;
        LinkedList masterList = new LinkedList();
        HashSet<String> keyword = new HashSet<>();
        Iterator<String> iterator;
        ListIterator iter1;
        ListIterator iter2 = new ListIterator(masterList);
        String inputWord;
        int inputLength;
        boolean isQualified = true;
        String result = "";
        int textLength;

        Random random = new Random();


        StringBuilder stringBuilder = new StringBuilder();

        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
//            poem = poem + scanner.next() + " ";

        }
        poem = stringBuilder.toString();


        long startTime = System.currentTimeMillis();

        poemArr = poem.split(" ");
        System.out.println(poemArr.length);

        System.out.println(System.currentTimeMillis() - startTime);


        startTime = System.currentTimeMillis();

        Arrays.stream(poemArr).forEach(x -> keyword.add(x));

        System.out.println(System.currentTimeMillis() - startTime);


        iterator = keyword.iterator();

        startTime = System.currentTimeMillis();


        while (iterator.hasNext()) {
            masterList.insetLast(new Link(iterator.next()));

        }

        System.out.println(System.currentTimeMillis() - startTime);


        iter1 = new ListIterator(masterList);


        startTime = System.currentTimeMillis();
//        while (!iter1.isNull()) {
//
//            Link temp = iter1.next();
//            String key = (String) temp.getdata();
//            for (int i = 0; i < poemArr.length - 1; i++) {
//                if (poemArr[i].equals(key) && poemArr[i] != null) {
//                    temp.getBabies().insetLast(new Link(poemArr[i + 1]));
//                }
//            }
//        }

        Link temp;
        String key;
        int poemArrLengthminusone = poemArr.length - 1;

        while (!iter1.isNull()) {

            temp = iter1.next();
            key = (String) temp.getdata();
            for (int i = 0; i < poemArrLengthminusone; i++) {
                if (poemArr[i] != null && poemArr[i].equals(key)) {
                    temp.getBabies().insetLast(new Link(poemArr[i + 1]));
                }
            }
        }


        System.out.println(System.currentTimeMillis() - startTime);
        iter1.reset();


    }
}
