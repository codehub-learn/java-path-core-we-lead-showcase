import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamDemo {
    private static final String LINE_DELIMITER = "---------------------------------------";
    private static final Logger logger = LoggerFactory.getLogger(StreamDemo.class);
    private static final Lorem generator = LoremIpsum.getInstance();

    public static void main(String[] args) {
//        streamCreateActions();
//        streamBasicActions();
//        streamIntermediateActions();
        streamFinalActions();
    }

    private static void streamCreateActions() {
        logger.info("");
        logger.info("STREAM CREATE ACTIONS");
        logger.info(LINE_DELIMITER);

        Stream<String> emptyStream = Stream.empty();

        Stream<String> sampleStringStream1 = createSampleStringStream();

        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
//        streamOfString.forEach(logger::info);

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        String[] arr = {"a", "b", "c", "d", "e"};
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
//        streamGenerated.forEach(logger::info);

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
        streamIterated.forEach(number -> logger.info(number.toString()));

        IntStream intStream = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);
    }

    private static void streamBasicActions() {
        logger.info("");
        logger.info("STREAM BASIC ACTIONS");
        logger.info(LINE_DELIMITER);

        Stream<String> sampleStringStream = createSampleStringStream();

        sampleStringStream.forEach(logger::info);

        logger.info("");
        logger.info("Print in sorted order");
        logger.info(LINE_DELIMITER);
        Stream<String> sampleStringStream2 = createSampleStringStream();
        sampleStringStream2.sorted().forEach(logger::info);

        logger.info("");
        logger.info("Concatenate streams");
        logger.info(LINE_DELIMITER);
        Stream<String> example1 = createSampleStringStream();
        Stream<String> example2 = createSampleStringStream();

        Stream.concat(example1, example2).sorted().forEach(logger::info);
    }

    private static void streamIntermediateActions() {
        logger.info("");
        logger.info("STREAM INTERMEDIATE ACTIONS");
        logger.info(LINE_DELIMITER);

        Stream<String> filterStream = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("M"));
        logger.info("Filter example");
        logger.info(LINE_DELIMITER);
        filterStream.forEach(logger::info);

        List<String> generatedNames = createSampleNameList(10);
        logger.info("Source list is: ");
        logger.info(LINE_DELIMITER);
        generatedNames.forEach(logger::info);

        logger.info("");
        Stream<String> limitStream = generatedNames.stream();
        logger.info("Limit example");
        logger.info(LINE_DELIMITER);
        limitStream.limit(5).forEach(logger::info);

        logger.info("");
        logger.info("Skip example");
        logger.info(LINE_DELIMITER);
        Stream<String> skipStream = generatedNames.stream();
        skipStream.skip(2).forEach(logger::info);

        logger.info("");
        logger.info("Map example");
        logger.info(LINE_DELIMITER);
        Stream<String> mapStream = generatedNames.stream();
        mapStream.map(String::toLowerCase).forEach(logger::info);

        logger.info("");
        logger.info("Flatting a list ");
        logger.info(LINE_DELIMITER);
        List<String> list1 = Arrays.asList("One", "Two", "Three");
        List<String> list2 = Arrays.asList("Four", "Five", "Six");
        List<String> list3 = Arrays.asList("Seven", "Eight", "Nine");

        List<List<String>> listOfLists = Arrays.asList(list1, list2, list3);

        listOfLists.stream()
                .flatMap(Collection::stream)
                .forEach(logger::info);

        logger.info("Map example on 2d stream ");
        logger.info(LINE_DELIMITER);
        listOfLists.stream()
                .map(Collection::stream)
                .forEach(innerStream -> innerStream.forEach(logger::info));
    }

    private static void streamFinalActions() {
        logger.info("");
        logger.info("STREAM FINAL ACTIONS");
        logger.info(LINE_DELIMITER);

        String[] names = {"John", "Costas", "Costas", "Costas", "Nick", "John", "Costas"};

        Stream<String> forEachOrderedStream = Stream.of(names);
        logger.info("ForeachOrdered example");
        logger.info(LINE_DELIMITER);
        forEachOrderedStream.distinct().forEachOrdered(logger::info);

        Stream<String> collectSetStream = Stream.of(names);
        logger.info("Collect to Set example");
        logger.info(LINE_DELIMITER);
        collectSetStream.collect(Collectors.toSet()).forEach(logger::info);

        Stream<String> collectListStream = Stream.of(names);
        logger.info("Collect to List example");
        logger.info(LINE_DELIMITER);
        List<String> example = collectListStream.collect(Collectors.toList());
        example.add("sth");
        example.forEach(logger::info);

        Stream<String> collectMapStream = Stream.of(names);
        logger.info("Collect to Map example");
        logger.info(LINE_DELIMITER);
        collectMapStream.distinct()
                .collect(Collectors.toMap(Function.identity(), String::length))
                .forEach((k, v) -> logger.info("{}:{}", k, v));

        logger.info("Calculations");
        logger.info(LINE_DELIMITER);
        OptionalDouble optionalDouble = IntStream.of(1, 2).average();
        logger.info("Average is {}", optionalDouble.orElseThrow());

        long max = LongStream.of(12344, 12112, 121212, 12121111).max().orElse(0);
        logger.info("Maximum value is {}.", max);

        // AnyMatch
        logger.info("AnyMatch example");
        logger.info(LINE_DELIMITER);
        Stream<String> anyMatchStream = Stream.of(names);
        boolean anyMatchFound = anyMatchStream.anyMatch(s -> s.startsWith("C"));
        logger.info("{} with character C.",
                anyMatchFound ? "Found at least one token starting" : "No token found " + "starting");
        logger.info("");

        // AllMatch
        logger.info("AllMatch example");
        logger.info(LINE_DELIMITER);
        Stream<String> allMatchStream = Stream.of(names);
        boolean allMatchFound = allMatchStream.allMatch(s -> s.startsWith("C"));
        logger.info("{} with character C.", allMatchFound ? "All tokens starting" : "Not all tokens starting");
        logger.info("");

        // FindFirst
        logger.info("FindFirst example");
        logger.info(LINE_DELIMITER);
        Stream<String> findFirstStream = Stream.of(names);
        // When stream is parallel, result may vary
        Optional<String> findFirst = findFirstStream.filter(s -> s.length() > 5).sorted().findFirst();
        logger.info("Found name with more than 5 characters : {}.", findFirst.orElseGet(() -> "None"));
        logger.info("");
    }


    private static Stream<String> createSampleStringStream() {
        String generatedWords = generator.getWords(30);
        return Arrays.stream(generatedWords.split(" "));
    }

    private static List<String> createSampleNameList(int howMany) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            names.add(generator.getFirstName());
        }
        return names;
    }
}
