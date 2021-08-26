package com.github.dmiller2117.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsWithFiles {
    public static void main(String[] args) throws IOException {
        String path = "src/com/github/dmiller2117/streams/names";
        Stream<String> namesStream = Files.lines(Paths.get(path));
        List<String> stringList = namesStream.filter(x -> x.startsWith("D")).collect(Collectors.toList());
        stringList.forEach(System.out::println);

        // or as a single line
        Files.lines(Paths.get(path)).filter(x -> x.startsWith("M")).forEach(System.out::println);
    }
}