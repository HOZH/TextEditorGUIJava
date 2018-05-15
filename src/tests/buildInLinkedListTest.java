package tests;

import java.util.LinkedList;

public class buildInLinkedListTest {
    public static void main(String[] args) {


        LinkedList linkedList = new LinkedList();

        for (int i = 0; i < 100; i++) {
            linkedList.addLast(i);

        }

        linkedList.stream().forEach(System.out::println);
    }
}
