package gr.codelearn;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
//        createMap();
        otherMapActions();
    }

    private static void createMap() {
        Map<String, String> map = Map.of();

        Map<String, String> map2 = Map.of("key1", "value1", "key2", "value2");
        System.out.println(map2);

        Map<String, String> map3 = Map.ofEntries(Map.entry("key1", "value1"), Map.entry("key2", "value2"));
        System.out.println(map3);

        Map<String, String> map4 = new HashMap<>();
        map4.put("Android", "Mobile");
        map4.put("Eclipse IDE", "Java");
        map4.put("Intellij IDEA", "Java");
        map4.put("Git", "Version control system");

        map4.put("Git", "A new value");

        System.out.println(map4);
    }

    private static void otherMapActions() {
        Map<String, String> map = new HashMap<>();
        map.put("Android", "Mobile");
        map.put("Eclipse IDE", "Java");
        map.put("Intellij IDEA", "Java");
        map.put("Git", "Version control system");

//        System.out.println(map.get("Eclipse IDE"));

        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
            System.out.println(mapEntry);
        }

        System.out.println("\nPrinting the keys:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        System.out.println("\nPrinting the values:");
        for (String value : map.values()) {
            System.out.println(value);
        }

        System.out.println("\nDoes the map contain a value for Android:");
        System.out.println(map.containsKey("Android"));
        System.out.println(map.containsValue("Mobile"));

        System.out.println(map.getOrDefault("Sth", "Not found"));

        map.clear();

        System.out.println(map);
    }
}
