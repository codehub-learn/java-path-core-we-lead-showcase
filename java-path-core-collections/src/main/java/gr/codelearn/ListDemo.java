package gr.codelearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
//        createLists();
        createLinkedLists();
    }

    public static void createLists() {
        List<String> list = Arrays.asList("Lars", "Simon");
        list.set(0, "Johny");
        System.out.println("1st list: " + list);

//        list.add("Maria");

        List<String> anotherList = new ArrayList<>();
        anotherList.add("Lars");
        anotherList.add("Simon");
        System.out.println("2nd list: " + anotherList);
        anotherList.add("Maria");
        anotherList.add("Maria");

        anotherList.set(0, "Johny");
        System.out.println(anotherList);

        anotherList.remove("Maria");
        System.out.println(anotherList);

        List<String> listCopy = List.copyOf(anotherList);
//        listCopy.add("George");
//        listCopy.set(0, " George");
        List<String> listCopy2 = List.of("Maria", "Simon");

//        for (String name : anotherList) {
//            System.out.println(name);
//        }

        for (int i = 0; i < anotherList.size(); i++) {
            System.out.println(anotherList.get(i));
        }

//        anotherList.clear();
        System.out.println(anotherList);

        System.out.println(list);
        System.out.println("After add all:");
        anotherList.addAll(1, list);
        System.out.println(anotherList);
    }

    public static List<String> createList() {
        return new ArrayList<>(Arrays.asList("Windows", "Linux", "iOS", "Android", "Mac OS X"));
    }

    private static void createLinkedLists() {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("A");
        linkedList.add("B");
        System.out.println(linkedList);

        linkedList.addLast("D");
        linkedList.addFirst("E");
        System.out.println(linkedList);

        linkedList.add(1, "C");
        linkedList.set(4, "Z");
        System.out.println(linkedList);

        System.out.println("\nRemoving");
        linkedList.removeLast();
        linkedList.removeFirst();
        System.out.println(linkedList);

        System.out.println("\nQueue methods");
        System.out.println(linkedList.peek());
        System.out.println(linkedList);

        System.out.println(linkedList.poll());
        System.out.println("After poll:");
        System.out.println(linkedList);
    }
}
