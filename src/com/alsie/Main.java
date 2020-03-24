package com.alsie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String wordsFile = args.length > 0 ? args[0] : "words.txt";
        Map<String, Long> wordCount = Arrays.asList( new String(
                Files.readAllBytes(Paths.get(wordsFile))).replaceAll("[-+.^:,]]", "")
                .split(" ")).stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println(wordCount);
    }

}
