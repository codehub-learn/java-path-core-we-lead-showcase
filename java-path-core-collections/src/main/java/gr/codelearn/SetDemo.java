package gr.codelearn;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
//        createSets();
        createLinkedHashSet();
    }

    private static void createSets() {
        Set<String> sampleSet = new HashSet<>();
        sampleSet.add("one");
        sampleSet.add("two");
        sampleSet.add("three");
        sampleSet.add("four");
        sampleSet.add("fifth");

        System.out.println(sampleSet);

        sampleSet.remove("one");

        for (String item : sampleSet) {
            System.out.println(item);
        }

        System.out.println(sampleSet.contains("three") ? "Exists" : "Does not exist");
        System.out.println(sampleSet.contains("four") ? "Exists" : "Does not exist");

        Set<String> emptyReadOnlySet = Set.of();
        Set<String> readOnlySet = Set.of("Athens", "Thessaloniki", "Patra", "Herakleion", "Ioannina", "Larisa",
                "Volos", "Rodos");
    }

    private static void createLinkedHashSet() {
        LinkedHashSet<String> sampleLinkedHashSet = new LinkedHashSet<>(Set.of("Athens", "Thessaloniki", "Patra",
                "Herakleion", "Ioannina", "Larisa", "Volos", "Rodos"));

//        System.out.println(sampleLinkedHashSet);


        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();

        // Adding element to LinkedHashSet
        linkedSet.add("A");
        linkedSet.add("B");
        linkedSet.add("C");
        linkedSet.add("D");

        System.out.println(linkedSet);

        // This will not add new element as A already exists
        linkedSet.add("A");
        linkedSet.add("E");

        System.out.println(linkedSet);
    }
}
